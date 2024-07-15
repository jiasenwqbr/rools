package org.jeecg.modules.demo.deploy.controller;

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
import org.jeecg.modules.demo.deploy.entity.RoolBuildJar;
import org.jeecg.modules.demo.deploy.service.IRoolBuildJarService;

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
 * @Description: 打包发布
 * @Author: jeecg-boot
 * @Date:   2024-07-15
 * @Version: V1.0
 */
@Api(tags="打包发布")
@RestController
@RequestMapping("/deploy/roolBuildJar")
@Slf4j
public class RoolBuildJarController extends JeecgController<RoolBuildJar, IRoolBuildJarService> {
	@Autowired
	private IRoolBuildJarService roolBuildJarService;
	
	/**
	 * 分页列表查询
	 *
	 * @param roolBuildJar
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "打包发布-分页列表查询")
	@ApiOperation(value="打包发布-分页列表查询", notes="打包发布-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RoolBuildJar>> queryPageList(RoolBuildJar roolBuildJar,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RoolBuildJar> queryWrapper = QueryGenerator.initQueryWrapper(roolBuildJar, req.getParameterMap());
		Page<RoolBuildJar> page = new Page<RoolBuildJar>(pageNo, pageSize);
		IPage<RoolBuildJar> pageList = roolBuildJarService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param roolBuildJar
	 * @return
	 */
	@AutoLog(value = "打包发布-添加")
	@ApiOperation(value="打包发布-添加", notes="打包发布-添加")
	@RequiresPermissions("deploy:rool_build_jar:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody RoolBuildJar roolBuildJar) {
		roolBuildJarService.save(roolBuildJar);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param roolBuildJar
	 * @return
	 */
	@AutoLog(value = "打包发布-编辑")
	@ApiOperation(value="打包发布-编辑", notes="打包发布-编辑")
	@RequiresPermissions("deploy:rool_build_jar:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody RoolBuildJar roolBuildJar) {
		roolBuildJarService.updateById(roolBuildJar);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "打包发布-通过id删除")
	@ApiOperation(value="打包发布-通过id删除", notes="打包发布-通过id删除")
	@RequiresPermissions("deploy:rool_build_jar:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		roolBuildJarService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "打包发布-批量删除")
	@ApiOperation(value="打包发布-批量删除", notes="打包发布-批量删除")
	@RequiresPermissions("deploy:rool_build_jar:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.roolBuildJarService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "打包发布-通过id查询")
	@ApiOperation(value="打包发布-通过id查询", notes="打包发布-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<RoolBuildJar> queryById(@RequestParam(name="id",required=true) String id) {
		RoolBuildJar roolBuildJar = roolBuildJarService.getById(id);
		if(roolBuildJar==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(roolBuildJar);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param roolBuildJar
    */
    @RequiresPermissions("deploy:rool_build_jar:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RoolBuildJar roolBuildJar) {
        return super.exportXls(request, roolBuildJar, RoolBuildJar.class, "打包发布");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("deploy:rool_build_jar:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, RoolBuildJar.class);
    }

}
