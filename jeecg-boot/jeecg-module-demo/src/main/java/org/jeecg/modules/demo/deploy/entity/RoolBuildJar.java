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
 * @Description: 打包发布
 * @Author: jeecg-boot
 * @Date:   2024-07-15
 * @Version: V1.0
 */
@Data
@TableName("rool_build_jar")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="rool_build_jar对象", description="打包发布")
public class RoolBuildJar implements Serializable {
    private static final long serialVersionUID = 1L;

	/**ID*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "ID")
    private java.lang.String id;
	/**空间*/
	@Excel(name = "空间", width = 15, dictTable = "rool_space", dicText = "space_name", dicCode = "id")
	@Dict(dictTable = "rool_space", dicText = "space_name", dicCode = "id")
    @ApiModelProperty(value = "空间")
    private java.lang.String spaceId;
	/**项目*/
	@Excel(name = "项目", width = 15, dictTable = "rool_project", dicText = "project_name", dicCode = "id")
	@Dict(dictTable = "rool_project", dicText = "project_name", dicCode = "id")
    @ApiModelProperty(value = "项目")
    private java.lang.String projectId;
	/**包名程*/
	@Excel(name = "包名程", width = 15)
    @ApiModelProperty(value = "包名程")
    private java.lang.String pkgName;
	/**包扩展名*/
	@Excel(name = "包扩展名", width = 15, dicCode = "extends")
	@Dict(dicCode = "extends")
    @ApiModelProperty(value = "包扩展名")
    private java.lang.String extend;
	/**版本*/
	@Excel(name = "版本", width = 15)
    @ApiModelProperty(value = "版本")
    private java.lang.String version;
	/**描述*/
	@Excel(name = "描述", width = 15)
    @ApiModelProperty(value = "描述")
    private java.lang.String description;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
	/**创建时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
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
