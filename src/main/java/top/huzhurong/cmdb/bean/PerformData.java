package top.huzhurong.cmdb.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 *
 *@author chenshun
 *@since 2018-08-06 09:53:58
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerformData {
    /**
     * id
     */
    private Long id;
    /**
     * 内容
     */
    private String context;
    /**
     * ecs/rds
     */
    private String type;
    /**
     * 实例
     */
    private String metaId;
    /**
     * 时间
     */
    private Date addTime;
}