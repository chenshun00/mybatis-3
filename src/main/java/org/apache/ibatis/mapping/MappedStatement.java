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
package org.apache.ibatis.mapping;

import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 映射状态，包含了一些基本的数据把
 * 1、这个跟我们平时的有什么本质的区别吗？其实没有，我们平时是在业务，而我们业务的bean是对业务(所完成功能的一种抽象)，而mybatis的这个对象
 * MappedStatement其实本质上是对sql语句的一个抽象，剥离了sql执行的种种逻辑，从而形成了这个对象，其实内在是抽象了我们的执行过程，而这个执行过程
 * 又是在mybatis制定的规则下，比如resultMap，再比如select，update，delete，insert，cache，include，都是一样的，其实抽象的对象不一样而已
 * </p>
 * <p>
 * 一个sql语句，一个MappedStatement示例，且每一个Configuration虽然他们都示例对象(hashcode)是一致的，但是他们内部的key其实已经变换掉了
 *
 * @author Clinton Begin
 */
public final class MappedStatement {

    //resource说明的是什么,xml配置文件或者mapper接口
    private String resource;
    //全局配置文件
    private Configuration configuration;
    //定义id，<select id = "xx" ></select> 调用方式 = namespace + id
    private String id;
    //不知道 一次搞多少的意思
    private Integer fetchSize;
    //sql 超时时间
    private Integer timeout;
    //三种类型，RoutingStatementHandler判断使用
    private StatementType statementType;//执行类型 STATEMENT(直接类型x), PREPARED(预编译 √), CALLABLE(存储过程 位置)
    //这里需要去了解
    //todo
    private ResultSetType resultSetType;//(结果集设置位置)
    //todo 我还没有了解的地方
    private SqlSource sqlSource;//sql语句from xml or annotation，there is static sql ， dy sql
    //<cache/>打开的缓存
    private Cache cache;//cache

    private ParameterMap parameterMap;//参数映射
    private List<ResultMap> resultMaps;//返回的结果映射
    //设置的
    private boolean flushCacheRequired;//是否刷新缓存
    //使用缓存这样就和2级缓存干上了
    private boolean useCache;
    //
    private boolean resultOrdered;
    //命令类型，
    private SqlCommandType sqlCommandType;
    private KeyGenerator keyGenerator;//转
    private String[] keyProperties;//resultMap里边的属性
    private String[] keyColumns;//resultMap里边的column
    private boolean hasNestedResultMaps;//嵌套map
    private String databaseId;//数据库
    private LanguageDriver lang;//
    private String[] resultSets;

    MappedStatement() {
        // constructor disabled
    }

    public static class Builder {
        private MappedStatement mappedStatement = new MappedStatement();

        public Builder(Configuration configuration, String id, SqlSource sqlSource, SqlCommandType sqlCommandType) {
            mappedStatement.configuration = configuration;
            mappedStatement.id = id;
            mappedStatement.sqlSource = sqlSource;
            mappedStatement.statementType = StatementType.PREPARED;
            //todo 了解一下啊
            mappedStatement.parameterMap = new ParameterMap.Builder("defaultParameterMap", null, new ArrayList<>()).build();
            mappedStatement.resultMaps = new ArrayList<>();
            mappedStatement.sqlCommandType = sqlCommandType;
            // NoKeyGenerator.INSTANCE;
            mappedStatement.keyGenerator = configuration.isUseGeneratedKeys() && SqlCommandType.INSERT.equals(sqlCommandType) ? Jdbc3KeyGenerator.INSTANCE : NoKeyGenerator.INSTANCE;
            mappedStatement.lang = configuration.getDefaultScriptingLanguageInstance();
        }

        public Builder resource(String resource) {
            mappedStatement.resource = resource;
            return this;
        }

        public String id() {
            return mappedStatement.id;
        }

        public Builder parameterMap(ParameterMap parameterMap) {
            mappedStatement.parameterMap = parameterMap;
            return this;
        }

        public Builder resultMaps(List<ResultMap> resultMaps) {
            mappedStatement.resultMaps = resultMaps;
            for (ResultMap resultMap : resultMaps) {
                mappedStatement.hasNestedResultMaps = mappedStatement.hasNestedResultMaps || resultMap.hasNestedResultMaps();
            }
            return this;
        }

        public Builder fetchSize(Integer fetchSize) {
            mappedStatement.fetchSize = fetchSize;
            return this;
        }

        public Builder timeout(Integer timeout) {
            mappedStatement.timeout = timeout;
            return this;
        }

        public Builder statementType(StatementType statementType) {
            mappedStatement.statementType = statementType;
            return this;
        }

        public Builder resultSetType(ResultSetType resultSetType) {
            mappedStatement.resultSetType = resultSetType;
            return this;
        }

        public Builder cache(Cache cache) {
            mappedStatement.cache = cache;
            return this;
        }

        public Builder flushCacheRequired(boolean flushCacheRequired) {
            mappedStatement.flushCacheRequired = flushCacheRequired;
            return this;
        }

        public Builder useCache(boolean useCache) {
            mappedStatement.useCache = useCache;
            return this;
        }

        public Builder resultOrdered(boolean resultOrdered) {
            mappedStatement.resultOrdered = resultOrdered;
            return this;
        }

        public Builder keyGenerator(KeyGenerator keyGenerator) {
            mappedStatement.keyGenerator = keyGenerator;
            return this;
        }

        public Builder keyProperty(String keyProperty) {
            mappedStatement.keyProperties = delimitedStringToArray(keyProperty);
            return this;
        }

        public Builder keyColumn(String keyColumn) {
            mappedStatement.keyColumns = delimitedStringToArray(keyColumn);
            return this;
        }

        public Builder databaseId(String databaseId) {
            mappedStatement.databaseId = databaseId;
            return this;
        }

        public Builder lang(LanguageDriver driver) {
            mappedStatement.lang = driver;
            return this;
        }

        public Builder resultSets(String resultSet) {
            mappedStatement.resultSets = delimitedStringToArray(resultSet);
            return this;
        }

        /**
         * @deprecated Use {@link #resultSets}
         */
        @Deprecated
        public Builder resulSets(String resultSet) {
            mappedStatement.resultSets = delimitedStringToArray(resultSet);
            return this;
        }

        public MappedStatement build() {
            assert mappedStatement.configuration != null;
            assert mappedStatement.id != null;
            assert mappedStatement.sqlSource != null;
            assert mappedStatement.lang != null;
            mappedStatement.resultMaps = Collections.unmodifiableList(mappedStatement.resultMaps);
            return mappedStatement;
        }
    }

    public KeyGenerator getKeyGenerator() {
        return keyGenerator;
    }

    public SqlCommandType getSqlCommandType() {
        return sqlCommandType;
    }

    public String getResource() {
        return resource;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getId() {
        return id;
    }

    public boolean hasNestedResultMaps() {
        return hasNestedResultMaps;
    }

    public Integer getFetchSize() {
        return fetchSize;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public StatementType getStatementType() {
        return statementType;
    }

    public ResultSetType getResultSetType() {
        return resultSetType;
    }

    public SqlSource getSqlSource() {
        return sqlSource;
    }

    public ParameterMap getParameterMap() {
        return parameterMap;
    }

    public List<ResultMap> getResultMaps() {
        return resultMaps;
    }

    public Cache getCache() {
        return cache;
    }

    public boolean isFlushCacheRequired() {
        return flushCacheRequired;
    }

    public boolean isUseCache() {
        return useCache;
    }

    public boolean isResultOrdered() {
        return resultOrdered;
    }

    public String getDatabaseId() {
        return databaseId;
    }

    public String[] getKeyProperties() {
        return keyProperties;
    }

    public String[] getKeyColumns() {
        return keyColumns;
    }

    public LanguageDriver getLang() {
        return lang;
    }

    public String[] getResultSets() {
        return resultSets;
    }

    /**
     * @deprecated Use {@link #getResultSets()}
     */
    @Deprecated
    public String[] getResulSets() {
        return resultSets;
    }

    public BoundSql getBoundSql(Object parameterObject) {
        //每一个sql语句对应了一个mappedstatement对象
        //sqlSource 是什么，其实就是这个sql语句，分为静态sql，动态sql
        BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
        //参数映射
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        //
        if (parameterMappings == null || parameterMappings.isEmpty()) {
            boundSql = new BoundSql(configuration, boundSql.getSql(), parameterMap.getParameterMappings(), parameterObject);
        }

        // check for nested result maps in parameter mappings (issue #30)
        for (ParameterMapping pm : boundSql.getParameterMappings()) {
            String rmId = pm.getResultMapId();
            if (rmId != null) {
                ResultMap rm = configuration.getResultMap(rmId);
                if (rm != null) {
                    hasNestedResultMaps |= rm.hasNestedResultMaps();
                }
            }
        }

        return boundSql;
    }

    private static String[] delimitedStringToArray(String in) {
        if (in == null || in.trim().length() == 0) {
            return null;
        } else {
            return in.split(",");
        }
    }

}
