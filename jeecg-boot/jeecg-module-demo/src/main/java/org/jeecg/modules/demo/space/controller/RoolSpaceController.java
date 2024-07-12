package org.jeecg.modules.demo.space.controller;

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
import org.jeecg.modules.demo.space.entity.RoolSpace;
import org.jeecg.modules.demo.space.service.IRoolSpaceService;

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
 * @Description: 空间
 * @Author: jeecg-boot
 * @Date:   2024-07-12
 * @Version: V1.0
 */
@Api(tags="空间")
@RestController
@RequestMapping("/space/roolSpace")
@Slf4j
public class RoolSpaceController extends JeecgController<RoolSpace, IRoolSpaceService> {
	@Autowired
	private IRoolSpaceService roolSpaceService;
	
	/**
	 * 分页列表查询
	 *
	 * @param roolSpace
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "空间-分页列表查询")
	@ApiOperation(value="空间-分页列表查询", notes="空间-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RoolSpace>> queryPageList(RoolSpace roolSpace,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RoolSpace> queryWrapper = QueryGenerator.initQueryWrapper(roolSpace, req.getParameterMap());
		Page<RoolSpace> page = new Page<RoolSpace>(pageNo, pageSize);
		IPage<RoolSpace> pageList = roolSpaceService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param roolSpace
	 * @return
	 */
	@AutoLog(value = "空间-添加")
	@ApiOperation(value="空间-添加", notes="空间-添加")
	@RequiresPermissions("space:rool_space:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody RoolSpace roolSpace) {
		roolSpaceService.save(roolSpace);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param roolSpace
	 * @return
	 */
	@AutoLog(value = "空间-编辑")
	@ApiOperation(value="空间-编辑", notes="空间-编辑")
	@RequiresPermissions("space:rool_space:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody RoolSpace roolSpace) {
		roolSpaceService.updateById(roolSpace);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "空间-通过id删除")
	@ApiOperation(value="空间-通过id删除", notes="空间-通过id删除")
	@RequiresPermissions("space:rool_space:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		roolSpaceService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "空间-批量删除")
	@ApiOperation(value="空间-批量删除", notes="空间-批量删除")
	@RequiresPermissions("space:rool_space:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.roolSpaceService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "空间-通过id查询")
	@ApiOperation(value="空间-通过id查询", notes="空间-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<RoolSpace> queryById(@RequestParam(name="id",required=true) String id) {
		RoolSpace roolSpace = roolSpaceService.getById(id);
		if(roolSpace==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(roolSpace);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param roolSpace
    */
    @RequiresPermissions("space:rool_space:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RoolSpace roolSpace) {
        return super.exportXls(request, roolSpace, RoolSpace.class, "空间");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("space:rool_space:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, RoolSpace.class);
    }

}
