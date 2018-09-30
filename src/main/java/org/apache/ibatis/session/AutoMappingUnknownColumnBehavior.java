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
package org.apache.ibatis.session;

import org.apache.ibatis.mapping.MappedStatement;

/**
 * 自动映射目标对象的时候检测到一个未知的列column或者未知的属性类型如何处理
 * @author Kazuki Shimizu
 */
public enum AutoMappingUnknownColumnBehavior {

    /**
     * 啥都不敢
     */
    NONE {
        @Override
        public void doAction(MappedStatement mappedStatement, String columnName, String property, Class<?> propertyType) {
            // do nothing
        }
    },

    /**
     * 警告日志，但是 AutoMappingUnknownColumnBehavior 必须设置到 warn 级别
     */
    WARNING {
        @Override
        public void doAction(MappedStatement mappedStatement, String columnName, String property, Class<?> propertyType) {
        }
    },

    /**
     * 映射失败，抛出SqlSessionException异常
     */
    FAILING {
        @Override
        public void doAction(MappedStatement mappedStatement, String columnName, String property, Class<?> propertyType) {
            throw new SqlSessionException(buildMessage(mappedStatement, columnName, property, propertyType));
        }
    };

    public abstract void doAction(MappedStatement mappedStatement, String columnName, String propertyName, Class<?> propertyType);

    /**
     * build error message.
     */
    private static String buildMessage(MappedStatement mappedStatement, String columnName, String property, Class<?> propertyType) {
        return new StringBuilder("Unknown column is detected on '")
                .append(mappedStatement.getId())
                .append("' auto-mapping. Mapping parameters are ")
                .append("[")
                .append("columnName=").append(columnName)
                .append(",").append("propertyName=").append(property)
                .append(",").append("propertyType=").append(propertyType != null ? propertyType.getName() : null)
                .append("]")
                .toString();
    }

}
