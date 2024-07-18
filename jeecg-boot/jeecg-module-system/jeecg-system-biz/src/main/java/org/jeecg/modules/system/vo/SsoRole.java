package org.jeecg.modules.system.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @author: 贾森
 * @date: 2024年07月18日 23:07
 */
@Data
public class SsoRole implements Serializable {
    private Long id;
    private Long created_ts;
    private Long updated_ts;
    private Long deleted_at;
    private String name;
    private boolean visible;
    private boolean editable;
}
