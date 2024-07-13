package org.jeecg.modules.demo.datadrlobject.vo;

import java.util.List;
import org.jeecg.modules.demo.datadrlobject.entity.RoolDrlObject;
import org.jeecg.modules.demo.datadrlobject.entity.RoolDataDrlCode;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelCollection;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Description: 规则文件DRL
 * @Author: jeecg-boot
 * @Date:   2024-07-13
 * @Version: V1.0
 */
@Data
@ApiModel(value="rool_project_objectPage对象", description="规则文件DRL")
public class RoolDrlObjectPage {

	/**ID*/
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
	/**对象名称*/
	@Excel(name = "对象名称", width = 15)
	@ApiModelProperty(value = "对象名称")
    private java.lang.String objectName;
	/**对象类型*/
	@Excel(name = "对象类型", width = 15, dicCode = "object_data_type")
    @Dict(dicCode = "object_data_type")
	@ApiModelProperty(value = "对象类型")
    private java.lang.String objectType;
	/**软件包*/
	@Excel(name = "软件包", width = 15)
	@ApiModelProperty(value = "软件包")
    private java.lang.String pkg;
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

	@ExcelCollection(name="规则文件DRL")
	@ApiModelProperty(value = "规则文件DRL")
	private List<RoolDataDrlCode> roolDataDrlCodeList;

}
