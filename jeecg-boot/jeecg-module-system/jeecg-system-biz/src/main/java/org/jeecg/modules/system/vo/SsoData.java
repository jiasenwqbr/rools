package org.jeecg.modules.system.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @author: 贾森
 * @date: 2024年07月18日 23:02
 */
@Data
public class SsoData implements Serializable {
    private String access;
    private String refresh;
    private Long id;
    private Long created_ts;
    private Long updated_ts;
    private Long deleted_at;
    private String account;
    private String avatar;
    private Long unlocktime;
    private List<SsoRole> roles;
}
