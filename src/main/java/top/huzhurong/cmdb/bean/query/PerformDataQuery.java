/**
 *    Copyright 2009-2018 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package top.huzhurong.cmdb.bean.query;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
*
*@author chenshun
*@since 2018-08-06 09:53:58
*/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class PerformDataQuery extends BaseQuery {
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

    private List<OrderFileds> orderFileds = new ArrayList<>();

    @AllArgsConstructor
    private class OrderFileds{
        @Getter
        @Setter
        private String orderBy;
        @Getter
        @Setter
        private Boolean desc;
    }

    public PerformDataQuery orderById(boolean desc){
        orderFileds.add(new OrderFileds("id",desc));
        return this;
    }
    public PerformDataQuery orderByContext(boolean desc){
        orderFileds.add(new OrderFileds("context",desc));
        return this;
    }
    public PerformDataQuery orderByType(boolean desc){
        orderFileds.add(new OrderFileds("type",desc));
        return this;
    }
    public PerformDataQuery orderByMetaId(boolean desc){
        orderFileds.add(new OrderFileds("meta_id",desc));
        return this;
    }
    public PerformDataQuery orderByAddTime(boolean desc){
        orderFileds.add(new OrderFileds("add_time",desc));
        return this;
    }
}
