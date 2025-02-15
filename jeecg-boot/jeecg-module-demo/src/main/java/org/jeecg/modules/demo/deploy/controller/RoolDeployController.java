package org.jeecg.modules.demo.deploy.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.demo.deploy.entity.RoolDeploy;
import org.jeecg.modules.demo.deploy.service.IRoolDeployService;

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
 * @Description: rool_deploy
 * @Author: jeecg-boot
 * @Date:   2024-07-15
 * @Version: V1.0
 */
@Api(tags="rool_deploy")
@RestController
@RequestMapping("/deploy/roolDeploy")
@Slf4j
public class RoolDeployController extends JeecgController<RoolDeploy, IRoolDeployService> {
	@Autowired
	private IRoolDeployService roolDeployService;
	
	/**
	 * 分页列表查询
	 *
	 * @param roolDeploy
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "rool_deploy-分页列表查询")
	@ApiOperation(value="rool_deploy-分页列表查询", notes="rool_deploy-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<RoolDeploy>> queryPageList(RoolDeploy roolDeploy,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<RoolDeploy> queryWrapper = QueryGenerator.initQueryWrapper(roolDeploy, req.getParameterMap());
		Page<RoolDeploy> page = new Page<RoolDeploy>(pageNo, pageSize);
		IPage<RoolDeploy> pageList = roolDeployService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param roolDeploy
	 * @return
	 */
	@AutoLog(value = "rool_deploy-添加")
	@ApiOperation(value="rool_deploy-添加", notes="rool_deploy-添加")
	@RequiresPermissions("deploy:rool_deploy:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody RoolDeploy roolDeploy) {
		roolDeployService.save(roolDeploy);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param roolDeploy
	 * @return
	 */
	@AutoLog(value = "rool_deploy-编辑")
	@ApiOperation(value="rool_deploy-编辑", notes="rool_deploy-编辑")
	@RequiresPermissions("deploy:rool_deploy:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody RoolDeploy roolDeploy) {
		roolDeployService.updateById(roolDeploy);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "rool_deploy-通过id删除")
	@ApiOperation(value="rool_deploy-通过id删除", notes="rool_deploy-通过id删除")
	@RequiresPermissions("deploy:rool_deploy:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		roolDeployService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "rool_deploy-批量删除")
	@ApiOperation(value="rool_deploy-批量删除", notes="rool_deploy-批量删除")
	@RequiresPermissions("deploy:rool_deploy:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.roolDeployService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "rool_deploy-通过id查询")
	@ApiOperation(value="rool_deploy-通过id查询", notes="rool_deploy-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<RoolDeploy> queryById(@RequestParam(name="id",required=true) String id) {
		RoolDeploy roolDeploy = roolDeployService.getById(id);
		if(roolDeploy==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(roolDeploy);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param roolDeploy
    */
    @RequiresPermissions("deploy:rool_deploy:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, RoolDeploy roolDeploy) {
        return super.exportXls(request, roolDeploy, RoolDeploy.class, "rool_deploy");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequiresPermissions("deploy:rool_deploy:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, RoolDeploy.class);
    }

}
