package org.jeecg.modules.demo.projectobject.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.projectobject.entity.RoolProjectObject;
import org.jeecg.modules.demo.projectobject.service.IRoolProjectObjectService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
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
@RequestMapping("/projectobject/roolProjectObject")
@Slf4j
public class RoolProjectObjectController extends JeecgController<RoolProjectObject, IRoolProjectObjectService> {
	@Autowired
	private IRoolProjectObjectService roolProjectObjectService;
	
	/**
	 * 分页列表查询
	 *
	 * @param roolProjectObject
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "项目对象-分页列表查询")
	@ApiOperation(value="项目对象-分页列表查询", notes="项目对象-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RoolProjectObject>> queryPageList(RoolProjectObject roolProjectObject,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RoolProjectObject> queryWrapper = QueryGenerator.initQueryWrapper(roolProjectObject, req.getParameterMap());
		Page<RoolProjectObject> page = new Page<RoolProjectObject>(pageNo, pageSize);
		IPage<RoolProjectObject> pageList = roolProjectObjectService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param roolProjectObject
	 * @return
	 */
	@AutoLog(value = "项目对象-添加")
	@ApiOperation(value="项目对象-添加", notes="项目对象-添加")
	@RequiresPermissions("projectobject:rool_project_object:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody RoolProjectObject roolProjectObject) {
		roolProjectObjectService.save(roolProjectObject);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param roolProjectObject
	 * @return
	 */
	@AutoLog(value = "项目对象-编辑")
	@ApiOperation(value="项目对象-编辑", notes="项目对象-编辑")
	@RequiresPermissions("projectobject:rool_project_object:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody RoolProjectObject roolProjectObject) {
		roolProjectObjectService.updateById(roolProjectObject);
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
	@RequiresPermissions("projectobject:rool_project_object:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		roolProjectObjectService.removeById(id);
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
	@RequiresPermissions("projectobject:rool_project_object:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.roolProjectObjectService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
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
	public Result<RoolProjectObject> queryById(@RequestParam(name="id",required=true) String id) {
		RoolProjectObject roolProjectObject = roolProjectObjectService.getById(id);
		if(roolProjectObject==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(roolProjectObject);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param roolProjectObject
    */
    @RequiresPermissions("projectobject:rool_project_object:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RoolProjectObject roolProjectObject) {
        return super.exportXls(request, roolProjectObject, RoolProjectObject.class, "项目对象");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("projectobject:rool_project_object:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, RoolProjectObject.class);
    }

}
