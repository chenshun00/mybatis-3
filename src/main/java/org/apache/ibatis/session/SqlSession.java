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

import java.io.Closeable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.executor.BatchResult;

/**
 * mybatis的核心接口，接口能执行sql，获取mapper，管理事务
 */
public interface SqlSession extends Closeable {

    /**
     * 执行sql
     * @param statement xxx.xxx
     * @param <T> 返回结果，泛型
     */
    <T> T selectOne(String statement);

    /**
     * @param <T> 返回结果类型，泛型
     * @param statement 独一无二的标识符
     * @param parameter 参数
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * @param <E> list类型的数据
     * @param statement 独一无二的标识符
     */
    <E> List<E> selectList(String statement);

    /**
     * @param <E> list类型的数据
     * @param statement 独一无二的标识符
     */
    <E> List<E> selectList(String statement, Object parameter);

    /**
     * @param <E> list类型的数据
     * @param statement 独一无二的标识符
     * @param rowBounds  限制扫描多少行数据，一般不用这个去实现
     */
    <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds);

    /**
     * map查找，我用的比较少
     */
    <K, V> Map<K, V> selectMap(String statement, String mapKey);

    <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey);

    <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds);

    /**
     * 游标查询，mysql好像不支持
     */
    <T> Cursor<T> selectCursor(String statement);


    <T> Cursor<T> selectCursor(String statement, Object parameter);


    <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds);


    void select(String statement, Object parameter, ResultHandler handler);


    void select(String statement, ResultHandler handler);


    void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler);

    int insert(String statement);

    int insert(String statement, Object parameter);

    int update(String statement);

    int update(String statement, Object parameter);

    int delete(String statement);

    int delete(String statement, Object parameter);

    void commit();

    void commit(boolean force);

    void rollback();

    void rollback(boolean force);

    List<BatchResult> flushStatements();

    @Override
    void close();

    void clearCache();

    Configuration getConfiguration();

    <T> T getMapper(Class<T> type);

    Connection getConnection();
}
