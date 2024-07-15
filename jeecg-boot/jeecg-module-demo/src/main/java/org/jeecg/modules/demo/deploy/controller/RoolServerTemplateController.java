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
import org.jeecg.modules.demo.deploy.entity.RoolServerTemplate;
import org.jeecg.modules.demo.deploy.service.IRoolServerTemplateService;

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
 * @Description: 服务器模版
 * @Author: jeecg-boot
 * @Date:   2024-07-15
 * @Version: V1.0
 */
@Api(tags="服务器模版")
@RestController
@RequestMapping("/deploy/roolServerTemplate")
@Slf4j
public class RoolServerTemplateController extends JeecgController<RoolServerTemplate, IRoolServerTemplateService> {
	@Autowired
	private IRoolServerTemplateService roolServerTemplateService;
	
	/**
	 * 分页列表查询
	 *
	 * @param roolServerTemplate
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "服务器模版-分页列表查询")
	@ApiOperation(value="服务器模版-分页列表查询", notes="服务器模版-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RoolServerTemplate>> queryPageList(RoolServerTemplate roolServerTemplate,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RoolServerTemplate> queryWrapper = QueryGenerator.initQueryWrapper(roolServerTemplate, req.getParameterMap());
		Page<RoolServerTemplate> page = new Page<RoolServerTemplate>(pageNo, pageSize);
		IPage<RoolServerTemplate> pageList = roolServerTemplateService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param roolServerTemplate
	 * @return
	 */
	@AutoLog(value = "服务器模版-添加")
	@ApiOperation(value="服务器模版-添加", notes="服务器模版-添加")
	@RequiresPermissions("deploy:rool_server_template:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody RoolServerTemplate roolServerTemplate) {
		roolServerTemplateService.save(roolServerTemplate);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param roolServerTemplate
	 * @return
	 */
	@AutoLog(value = "服务器模版-编辑")
	@ApiOperation(value="服务器模版-编辑", notes="服务器模版-编辑")
	@RequiresPermissions("deploy:rool_server_template:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody RoolServerTemplate roolServerTemplate) {
		roolServerTemplateService.updateById(roolServerTemplate);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "服务器模版-通过id删除")
	@ApiOperation(value="服务器模版-通过id删除", notes="服务器模版-通过id删除")
	@RequiresPermissions("deploy:rool_server_template:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		roolServerTemplateService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "服务器模版-批量删除")
	@ApiOperation(value="服务器模版-批量删除", notes="服务器模版-批量删除")
	@RequiresPermissions("deploy:rool_server_template:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.roolServerTemplateService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "服务器模版-通过id查询")
	@ApiOperation(value="服务器模版-通过id查询", notes="服务器模版-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<RoolServerTemplate> queryById(@RequestParam(name="id",required=true) String id) {
		RoolServerTemplate roolServerTemplate = roolServerTemplateService.getById(id);
		if(roolServerTemplate==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(roolServerTemplate);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param roolServerTemplate
    */
    @RequiresPermissions("deploy:rool_server_template:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RoolServerTemplate roolServerTemplate) {
        return super.exportXls(request, roolServerTemplate, RoolServerTemplate.class, "服务器模版");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("deploy:rool_server_template:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, RoolServerTemplate.class);
    }

}
