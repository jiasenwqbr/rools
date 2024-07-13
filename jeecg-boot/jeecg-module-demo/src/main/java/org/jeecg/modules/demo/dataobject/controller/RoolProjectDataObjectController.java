package org.jeecg.modules.demo.dataobject.controller;

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
import org.jeecg.modules.demo.dataobject.entity.RoolDataObject;
import org.jeecg.modules.demo.dataobject.entity.RoolDataObjectCode;
import org.jeecg.modules.demo.dataobject.entity.RoolProjectDataObject;
import org.jeecg.modules.demo.dataobject.vo.RoolProjectDataObjectPage;
import org.jeecg.modules.demo.dataobject.service.IRoolProjectDataObjectService;
import org.jeecg.modules.demo.dataobject.service.IRoolDataObjectService;
import org.jeecg.modules.demo.dataobject.service.IRoolDataObjectCodeService;
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
 * @Description: 项目对象
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
@Api(tags="项目对象")
@RestController
@RequestMapping("/dataobject/roolProjectDataObject")
@Slf4j
public class RoolProjectDataObjectController {
	@Autowired
	private IRoolProjectDataObjectService roolProjectDataObjectService;
	@Autowired
	private IRoolDataObjectService roolDataObjectService;
	@Autowired
	private IRoolDataObjectCodeService roolDataObjectCodeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param roolProjectDataObject
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "项目对象-分页列表查询")
	@ApiOperation(value="项目对象-分页列表查询", notes="项目对象-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RoolProjectDataObject>> queryPageList(RoolProjectDataObject roolProjectDataObject,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		roolProjectDataObject.setObjectType("000");
		QueryWrapper<RoolProjectDataObject> queryWrapper = QueryGenerator.initQueryWrapper(roolProjectDataObject, req.getParameterMap());
		Page<RoolProjectDataObject> page = new Page<RoolProjectDataObject>(pageNo, pageSize);
		IPage<RoolProjectDataObject> pageList = roolProjectDataObjectService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param roolProjectDataObjectPage
	 * @return
	 */
	@AutoLog(value = "项目对象-添加")
	@ApiOperation(value="项目对象-添加", notes="项目对象-添加")
    @RequiresPermissions("dataobject:rool_project_object:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody RoolProjectDataObjectPage roolProjectDataObjectPage) {
		RoolProjectDataObject roolProjectDataObject = new RoolProjectDataObject();
		BeanUtils.copyProperties(roolProjectDataObjectPage, roolProjectDataObject);
		roolProjectDataObjectService.saveMain(roolProjectDataObject, roolProjectDataObjectPage.getRoolDataObjectList(),roolProjectDataObjectPage.getRoolDataObjectCodeList());
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param roolProjectDataObjectPage
	 * @return
	 */
	@AutoLog(value = "项目对象-编辑")
	@ApiOperation(value="项目对象-编辑", notes="项目对象-编辑")
    @RequiresPermissions("dataobject:rool_project_object:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody RoolProjectDataObjectPage roolProjectDataObjectPage) {
		RoolProjectDataObject roolProjectDataObject = new RoolProjectDataObject();
		BeanUtils.copyProperties(roolProjectDataObjectPage, roolProjectDataObject);
		RoolProjectDataObject roolProjectDataObjectEntity = roolProjectDataObjectService.getById(roolProjectDataObject.getId());
		if(roolProjectDataObjectEntity==null) {
			return Result.error("未找到对应数据");
		}
		roolProjectDataObjectService.updateMain(roolProjectDataObject, roolProjectDataObjectPage.getRoolDataObjectList(),roolProjectDataObjectPage.getRoolDataObjectCodeList());
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "项目对象-通过id删除")
	@ApiOperation(value="项目对象-通过id删除", notes="项目对象-通过id删除")
    @RequiresPermissions("dataobject:rool_project_object:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		roolProjectDataObjectService.delMain(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "项目对象-批量删除")
	@ApiOperation(value="项目对象-批量删除", notes="项目对象-批量删除")
    @RequiresPermissions("dataobject:rool_project_object:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.roolProjectDataObjectService.delBatchMain(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "项目对象-通过id查询")
	@ApiOperation(value="项目对象-通过id查询", notes="项目对象-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<RoolProjectDataObject> queryById(@RequestParam(name="id",required=true) String id) {
		RoolProjectDataObject roolProjectDataObject = roolProjectDataObjectService.getById(id);
		if(roolProjectDataObject==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(roolProjectDataObject);

	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "字段通过主表ID查询")
	@ApiOperation(value="字段主表ID查询", notes="字段-通主表ID查询")
	@GetMapping(value = "/queryRoolDataObjectByMainId")
	public Result<List<RoolDataObject>> queryRoolDataObjectListByMainId(@RequestParam(name="id",required=true) String id) {
		List<RoolDataObject> roolDataObjectList = roolDataObjectService.selectByMainId(id);
		return Result.OK(roolDataObjectList);
	}
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "源代码通过主表ID查询")
	@ApiOperation(value="源代码主表ID查询", notes="源代码-通主表ID查询")
	@GetMapping(value = "/queryRoolDataObjectCodeByMainId")
	public Result<List<RoolDataObjectCode>> queryRoolDataObjectCodeListByMainId(@RequestParam(name="id",required=true) String id) {
		List<RoolDataObjectCode> roolDataObjectCodeList = roolDataObjectCodeService.selectByMainId(id);
		return Result.OK(roolDataObjectCodeList);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param roolProjectDataObject
    */
    @RequiresPermissions("dataobject:rool_project_object:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RoolProjectDataObject roolProjectDataObject) {
      // Step.1 组装查询条件查询数据
      QueryWrapper<RoolProjectDataObject> queryWrapper = QueryGenerator.initQueryWrapper(roolProjectDataObject, request.getParameterMap());
      LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();

      //配置选中数据查询条件
      String selections = request.getParameter("selections");
      if(oConvertUtils.isNotEmpty(selections)) {
         List<String> selectionList = Arrays.asList(selections.split(","));
         queryWrapper.in("id",selectionList);
      }
      //Step.2 获取导出数据
      List<RoolProjectDataObject> roolProjectDataObjectList = roolProjectDataObjectService.list(queryWrapper);

      // Step.3 组装pageList
      List<RoolProjectDataObjectPage> pageList = new ArrayList<RoolProjectDataObjectPage>();
      for (RoolProjectDataObject main : roolProjectDataObjectList) {
          RoolProjectDataObjectPage vo = new RoolProjectDataObjectPage();
          BeanUtils.copyProperties(main, vo);
          List<RoolDataObject> roolDataObjectList = roolDataObjectService.selectByMainId(main.getId());
          vo.setRoolDataObjectList(roolDataObjectList);
          List<RoolDataObjectCode> roolDataObjectCodeList = roolDataObjectCodeService.selectByMainId(main.getId());
          vo.setRoolDataObjectCodeList(roolDataObjectCodeList);
          pageList.add(vo);
      }

      // Step.4 AutoPoi 导出Excel
      ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
      mv.addObject(NormalExcelConstants.FILE_NAME, "项目对象列表");
      mv.addObject(NormalExcelConstants.CLASS, RoolProjectDataObjectPage.class);
      mv.addObject(NormalExcelConstants.PARAMS, new ExportParams("项目对象数据", "导出人:"+sysUser.getRealname(), "项目对象"));
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
    @RequiresPermissions("dataobject:rool_project_object:importExcel")
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
              List<RoolProjectDataObjectPage> list = ExcelImportUtil.importExcel(file.getInputStream(), RoolProjectDataObjectPage.class, params);
              for (RoolProjectDataObjectPage page : list) {
                  RoolProjectDataObject po = new RoolProjectDataObject();
                  BeanUtils.copyProperties(page, po);
                  roolProjectDataObjectService.saveMain(po, page.getRoolDataObjectList(),page.getRoolDataObjectCodeList());
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
