/**
 * Copyright 2009-2018 the original author or authors.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package top.huzhurong.cmdb.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * @author chenshun
 * @since 2018-08-06 09:53:58
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerformData implements Cloneable {
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

    @Override
    public PerformData clone() throws CloneNotSupportedException {
        return (PerformData) super.clone();
    }
}