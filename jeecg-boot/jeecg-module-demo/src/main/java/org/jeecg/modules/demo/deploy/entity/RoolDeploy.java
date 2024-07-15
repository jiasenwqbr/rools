package org.jeecg.modules.demo.deploy.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: rool_deploy
 * @Author: jeecg-boot
 * @Date:   2024-07-15
 * @Version: V1.0
 */
@Data
@TableName("rool_deploy")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="rool_deploy对象", description="rool_deploy")
public class RoolDeploy implements Serializable {
    private static final long serialVersionUID = 1L;

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
    private java.lang.String id;
	/**包名*/
	@Excel(name = "包名", width = 15, dictTable = "rool_build_jar", dicText = "pkg_name", dicCode = "id")
	@Dict(dictTable = "rool_build_jar", dicText = "pkg_name", dicCode = "id")
    @ApiModelProperty(value = "包名")
    private java.lang.String packageId;
	/**服务器*/
	@Excel(name = "服务器", width = 15, dictTable = "rool_server_template", dicText = "templeate_name", dicCode = "id")
	@Dict(dictTable = "rool_server_template", dicText = "templeate_name", dicCode = "id")
    @ApiModelProperty(value = "服务器")
    private java.lang.String serverId;
	/**容器*/
	@Excel(name = "容器", width = 15, dictTable = "rool_container", dicText = "container_id", dicCode = "id")
	@Dict(dictTable = "rool_container", dicText = "container_name", dicCode = "id")
    @ApiModelProperty(value = "容器")
    private java.lang.String containerId;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private java.lang.String description;
	/**部署人*/
    @ApiModelProperty(value = "部署人")
    private java.lang.String createBy;
	/**部署时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "部署时间")
    private java.util.Date createTime;
	/**修改人*/
    @ApiModelProperty(value = "修改人")
    private java.lang.String updateBy;
	/**修改时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private java.util.Date updateTime;
}
