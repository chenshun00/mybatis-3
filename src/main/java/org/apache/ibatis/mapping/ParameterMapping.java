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
package org.apache.ibatis.mapping;

import java.sql.ResultSet;

import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

/**
 * SQL语句 = SELECT * FROM TABLE WHERE A = #{A} AND B = #{B}
 * 这个时候 A 是一个ParameterMapping，B也是一个
 * 每一个这样的参数都对应了这样一个mapping
 *
 * @author Clinton Begin
 */
public class ParameterMapping {

    private Configuration configuration;

    private String property;//A or B , and other value
    private ParameterMode mode;//模式
    private Class<?> javaType = Object.class;//默认的java类型，反正可以向下转型
    private JdbcType jdbcType;//jdbc 类型
    private Integer numericScale;//未知
    private TypeHandler<?> typeHandler;//类型处理器
    private String resultMapId;//
    private String jdbcTypeName;
    private String expression;

    private ParameterMapping() {
    }

    public static class Builder {
        private ParameterMapping parameterMapping = new ParameterMapping();

        public Builder(Configuration configuration, String property, TypeHandler<?> typeHandler) {
            parameterMapping.configuration = configuration;
            parameterMapping.property = property;
            parameterMapping.typeHandler = typeHandler;
            parameterMapping.mode = ParameterMode.IN;
        }

        public Builder(Configuration configuration, String property, Class<?> javaType) {
            parameterMapping.configuration = configuration;
            parameterMapping.property = property;
            parameterMapping.javaType = javaType;
            parameterMapping.mode = ParameterMode.IN;
        }

        public Builder mode(ParameterMode mode) {
            parameterMapping.mode = mode;
            return this;
        }

        public Builder javaType(Class<?> javaType) {
            parameterMapping.javaType = javaType;
            return this;
        }

        public Builder jdbcType(JdbcType jdbcType) {
            parameterMapping.jdbcType = jdbcType;
            return this;
        }

        public Builder numericScale(Integer numericScale) {
            parameterMapping.numericScale = numericScale;
            return this;
        }

        public Builder resultMapId(String resultMapId) {
            parameterMapping.resultMapId = resultMapId;
            return this;
        }

        public Builder typeHandler(TypeHandler<?> typeHandler) {
            parameterMapping.typeHandler = typeHandler;
            return this;
        }

        public Builder jdbcTypeName(String jdbcTypeName) {
            parameterMapping.jdbcTypeName = jdbcTypeName;
            return this;
        }

        public Builder expression(String expression) {
            parameterMapping.expression = expression;
            return this;
        }

        public ParameterMapping build() {
            resolveTypeHandler();
            return parameterMapping;
        }

        private void resolveTypeHandler() {
            if (parameterMapping.typeHandler == null && parameterMapping.javaType != null) {
                Configuration configuration = parameterMapping.configuration;
                TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
                parameterMapping.typeHandler = typeHandlerRegistry.getTypeHandler(parameterMapping.javaType, parameterMapping.jdbcType);
            }
        }

    }

    public String getProperty() {
        return property;
    }

    public ParameterMode getMode() {
        return mode;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public JdbcType getJdbcType() {
        return jdbcType;
    }

    public Integer getNumericScale() {
        return numericScale;
    }

    public TypeHandler<?> getTypeHandler() {
        return typeHandler;
    }

    public String getResultMapId() {
        return resultMapId;
    }

    public String getJdbcTypeName() {
        return jdbcTypeName;
    }
}
