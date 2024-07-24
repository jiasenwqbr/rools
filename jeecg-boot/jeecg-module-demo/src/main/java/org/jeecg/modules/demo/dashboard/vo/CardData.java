package org.jeecg.modules.demo.dashboard.vo;

import lombok.Data;

/**
 * @Description:
 * @author: 贾森
 * @date: 2024年07月24日 16:01
 */
@Data
public class CardData {
    private String title;
    private String icon;
    private long value;
    private long total;
    private String color;
    private String action;

}
