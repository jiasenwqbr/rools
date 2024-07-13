package org.jeecg.modules.demo.datadrlobject.controller;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.vo.LoginUser;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.datadrlobject.entity.RoolDataDrlCode;
import org.jeecg.modules.demo.datadrlobject.entity.RoolDrlObject;
import org.jeecg.modules.demo.datadrlobject.vo.RoolDrlObjectPage;
import org.jeecg.modules.demo.datadrlobject.service.IRoolDrlObjectService;
import org.jeecg.modules.demo.datadrlobject.service.IRoolDataDrlCodeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;


 /**
 * @Description: 规则文件DRL
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
@Api(tags="规则文件DRL")
@RestController
@RequestMapping("/datadrlobject/roolDrlObject")
@Slf4j
public class RoolDrlObjectController {
	@Autowired
	private IRoolDrlObjectService roolDrlObjectService;
	@Autowired
	private IRoolDataDrlCodeService roolDataDrlCodeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param roolDrlObject
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "规则文件DRL-分页列表查询")
	@ApiOperation(value="规则文件DRL-分页列表查询", notes="规则文件DRL-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RoolDrlObject>> queryPageList(RoolDrlObject roolDrlObject,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		roolDrlObject.setObjectType("001");
		QueryWrapper<RoolDrlObject> queryWrapper = QueryGenerator.initQueryWrapper(roolDrlObject, req.getParameterMap());
		Page<RoolDrlObject> page = new Page<RoolDrlObject>(pageNo, pageSize);
		IPage<RoolDrlObject> pageList = roolDrlObjectService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param roolDrlObjectPage
	 * @return
	 */
	@AutoLog(value = "规则文件DRL-添加")
	@ApiOperation(value="规则文件DRL-添加", notes="规则文件DRL-添加")
    @RequiresPermissions("datadrlobject:rool_project_object:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody RoolDrlObjectPage roolDrlObjectPage) {
		RoolDrlObject roolDrlObject = new RoolDrlObject();
		BeanUtils.copyProperties(roolDrlObjectPage, roolDrlObject);
		roolDrlObjectService.saveMain(roolDrlObject, roolDrlObjectPage.getRoolDataDrlCodeList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param roolDrlObjectPage
	 * @return
	 */
	@AutoLog(value = "规则文件DRL-编辑")
	@ApiOperation(value="规则文件DRL-编辑", notes="规则文件DRL-编辑")
    @RequiresPermissions("datadrlobject:rool_project_object:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody RoolDrlObjectPage roolDrlObjectPage) {
		RoolDrlObject roolDrlObject = new RoolDrlObject();
		BeanUtils.copyProperties(roolDrlObjectPage, roolDrlObject);
		RoolDrlObject roolDrlObjectEntity = roolDrlObjectService.getById(roolDrlObject.getId());
		if(roolDrlObjectEntity==null) {
			return Result.error("未找到对应数据");
		}
		roolDrlObjectService.updateMain(roolDrlObject, roolDrlObjectPage.getRoolDataDrlCodeList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "规则文件DRL-通过id删除")
	@ApiOperation(value="规则文件DRL-通过id删除", notes="规则文件DRL-通过id删除")
    @RequiresPermissions("datadrlobject:rool_project_object:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		roolDrlObjectService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "规则文件DRL-批量删除")
	@ApiOperation(value="规则文件DRL-批量删除", notes="规则文件DRL-批量删除")
    @RequiresPermissions("datadrlobject:rool_project_object:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.roolDrlObjectService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "规则文件DRL-通过id查询")
	@ApiOperation(value="规则文件DRL-通过id查询", notes="规则文件DRL-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<RoolDrlObject> queryById(@RequestParam(name="id",required=true) String id) {
		RoolDrlObject roolDrlObject = roolDrlObjectService.getById(id);
		if(roolDrlObject==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(roolDrlObject);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "规则文件DRL通过主表ID查询")
	@ApiOperation(value="规则文件DRL主表ID查询", notes="规则文件DRL-通主表ID查询")
	@GetMapping(value = "/queryRoolDataDrlCodeByMainId")
	public Result<List<RoolDataDrlCode>> queryRoolDataDrlCodeListByMainId(@RequestParam(name="id",required=true) String id) {
		List<RoolDataDrlCode> roolDataDrlCodeList = roolDataDrlCodeService.selectByMainId(id);
		return Result.OK(roolDataDrlCodeList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param roolDrlObject
    */
    @RequiresPermissions("datadrlobject:rool_project_object:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RoolDrlObject roolDrlObject) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<RoolDrlObject> queryWrapper = QueryGenerator.initQueryWrapper(roolDrlObject, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //配置选中数据查询条件
      String selections = request.getParameter("selections");
      if(oConvertUtils.isNotEmpty(selections)) {
         List<String> selectionList = Arrays.asList(selections.split(","));
         queryWrapper.in("id",selectionList);
      }
      //Step.2 获取导出数据
      List<RoolDrlObject> roolDrlObjectList = roolDrlObjectService.list(queryWrapper);

      // Step.3 组装pageList
      List<RoolDrlObjectPage> pageList = new ArrayList<RoolDrlObjectPage>();
      for (RoolDrlObject main : roolDrlObjectList) {
          RoolDrlObjectPage vo = new RoolDrlObjectPage();
          BeanUtils.copyProperties(main, vo);
          List<RoolDataDrlCode> roolDataDrlCodeList = roolDataDrlCodeService.selectByMainId(main.getId());
          vo.setRoolDataDrlCodeList(roolDataDrlCodeList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "规则文件DRL列表");
      mv.addObject(NormalExcelConstants.CLASS, RoolDrlObjectPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("规则文件DRL数据", "导出人:"+sysUser.getRealname(), "规则文件DRL"));
      mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
      return mv;
    }

    /**
    * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("datadrlobject:rool_project_object:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
      MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
      Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
      for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
          // 获取上传文件对象
          MultipartFile file = entity.getValue();
          ImportParams params = new ImportParams();
          params.setTitleRows(2);
          params.setHeadRows(1);
          params.setNeedSave(true);
          try {
              List<RoolDrlObjectPage> list = ExcelImportUtil.importExcel(file.getInputStream(), RoolDrlObjectPage.class, params);
              for (RoolDrlObjectPage page : list) {
                  RoolDrlObject po = new RoolDrlObject();
                  BeanUtils.copyProperties(page, po);
                  roolDrlObjectService.saveMain(po, page.getRoolDataDrlCodeList());
              }
              return Result.OK("文件导入成功！数据行数:" + list.size());
          } catch (Exception e) {
              log.error(e.getMessage(),e);
              return Result.error("文件导入失败:"+e.getMessage());
          } finally {
              try {
                  file.getInputStream().close();
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }
      }
      return Result.OK("文件导入失败！");
    }

}
