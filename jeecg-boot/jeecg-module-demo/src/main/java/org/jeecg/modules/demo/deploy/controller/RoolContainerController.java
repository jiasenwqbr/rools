package org.jeecg.modules.demo.deploy.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.deploy.entity.RoolContainer;
import org.jeecg.modules.demo.deploy.service.IRoolContainerService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;

 /**
 * @Description: 容器
 * @Author: jeecg-boot
 * @Date:   2024-07-15
 * @Version: V1.0
 */
@Api(tags="容器")
@RestController
@RequestMapping("/deploy/roolContainer")
@Slf4j
public class RoolContainerController extends JeecgController<RoolContainer, IRoolContainerService> {
	@Autowired
	private IRoolContainerService roolContainerService;
	
	/**
	 * 分页列表查询
	 *
	 * @param roolContainer
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "容器-分页列表查询")
	@ApiOperation(value="容器-分页列表查询", notes="容器-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RoolContainer>> queryPageList(RoolContainer roolContainer,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RoolContainer> queryWrapper = QueryGenerator.initQueryWrapper(roolContainer, req.getParameterMap());
		Page<RoolContainer> page = new Page<RoolContainer>(pageNo, pageSize);
		IPage<RoolContainer> pageList = roolContainerService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param roolContainer
	 * @return
	 */
	@AutoLog(value = "容器-添加")
	@ApiOperation(value="容器-添加", notes="容器-添加")
	@RequiresPermissions("deploy:rool_container:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody RoolContainer roolContainer) {
		roolContainerService.save(roolContainer);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param roolContainer
	 * @return
	 */
	@AutoLog(value = "容器-编辑")
	@ApiOperation(value="容器-编辑", notes="容器-编辑")
	@RequiresPermissions("deploy:rool_container:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody RoolContainer roolContainer) {
		roolContainerService.updateById(roolContainer);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "容器-通过id删除")
	@ApiOperation(value="容器-通过id删除", notes="容器-通过id删除")
	@RequiresPermissions("deploy:rool_container:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		roolContainerService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "容器-批量删除")
	@ApiOperation(value="容器-批量删除", notes="容器-批量删除")
	@RequiresPermissions("deploy:rool_container:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.roolContainerService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "容器-通过id查询")
	@ApiOperation(value="容器-通过id查询", notes="容器-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<RoolContainer> queryById(@RequestParam(name="id",required=true) String id) {
		RoolContainer roolContainer = roolContainerService.getById(id);
		if(roolContainer==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(roolContainer);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param roolContainer
    */
    @RequiresPermissions("deploy:rool_container:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RoolContainer roolContainer) {
        return super.exportXls(request, roolContainer, RoolContainer.class, "容器");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("deploy:rool_container:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, RoolContainer.class);
    }

}
