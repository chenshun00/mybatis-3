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

import java.sql.Connection;

/**
 * 从外部的connection或者DataSource获取一个SqlSession实例
 */
public interface SqlSessionFactory {

    /**
     * 获取一个SqlSession，使用默认的执行类型，数据库的默认隔离级别，非自动提交
     */
    SqlSession openSession();

    /**
     * 获取一个SqlSession，使用默认的执行类型，数据库的默认隔离级别
     * @param autoCommit 设置是否自动提交
     */
    SqlSession openSession(boolean autoCommit);

    /**
     * 从connection获取一个SqlSession，使用默认的执行类型，数据库的默认隔离级别
     */
    SqlSession openSession(Connection connection);

    /**
     * 设置隔离界别
     */
    SqlSession openSession(TransactionIsolationLevel level);

    /**
     * 设置执行类型
     */
    SqlSession openSession(ExecutorType execType);

    /**
     *
     * @param execType 执行类型
     * @param autoCommit 是否自动提交
     */
    SqlSession openSession(ExecutorType execType, boolean autoCommit);

    /**
     *
     * @param execType 执行类型
     * @param level 隔离级别
     */
    SqlSession openSession(ExecutorType execType, TransactionIsolationLevel level);

    SqlSession openSession(ExecutorType execType, Connection connection);

    /**
     * 全局配置
     */
    Configuration getConfiguration();

}
