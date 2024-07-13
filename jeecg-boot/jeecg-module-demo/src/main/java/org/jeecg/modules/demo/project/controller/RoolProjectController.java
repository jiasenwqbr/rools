package org.jeecg.modules.demo.project.controller;

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
import org.jeecg.modules.demo.project.entity.RoolProject;
import org.jeecg.modules.demo.project.service.IRoolProjectService;

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
 * @Description: 项目
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
@Api(tags="项目")
@RestController
@RequestMapping("/project/roolProject")
@Slf4j
public class RoolProjectController extends JeecgController<RoolProject, IRoolProjectService> {
	@Autowired
	private IRoolProjectService roolProjectService;
	
	/**
	 * 分页列表查询
	 *
	 * @param roolProject
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "项目-分页列表查询")
	@ApiOperation(value="项目-分页列表查询", notes="项目-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RoolProject>> queryPageList(RoolProject roolProject,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RoolProject> queryWrapper = QueryGenerator.initQueryWrapper(roolProject, req.getParameterMap());
		Page<RoolProject> page = new Page<RoolProject>(pageNo, pageSize);
		IPage<RoolProject> pageList = roolProjectService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param roolProject
	 * @return
	 */
	@AutoLog(value = "项目-添加")
	@ApiOperation(value="项目-添加", notes="项目-添加")
	@RequiresPermissions("project:rool_project:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody RoolProject roolProject) {
		roolProjectService.save(roolProject);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param roolProject
	 * @return
	 */
	@AutoLog(value = "项目-编辑")
	@ApiOperation(value="项目-编辑", notes="项目-编辑")
	@RequiresPermissions("project:rool_project:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody RoolProject roolProject) {
		roolProjectService.updateById(roolProject);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "项目-通过id删除")
	@ApiOperation(value="项目-通过id删除", notes="项目-通过id删除")
	@RequiresPermissions("project:rool_project:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		roolProjectService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "项目-批量删除")
	@ApiOperation(value="项目-批量删除", notes="项目-批量删除")
	@RequiresPermissions("project:rool_project:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.roolProjectService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "项目-通过id查询")
	@ApiOperation(value="项目-通过id查询", notes="项目-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<RoolProject> queryById(@RequestParam(name="id",required=true) String id) {
		RoolProject roolProject = roolProjectService.getById(id);
		if(roolProject==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(roolProject);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param roolProject
    */
    @RequiresPermissions("project:rool_project:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RoolProject roolProject) {
        return super.exportXls(request, roolProject, RoolProject.class, "项目");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("project:rool_project:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, RoolProject.class);
    }

}
