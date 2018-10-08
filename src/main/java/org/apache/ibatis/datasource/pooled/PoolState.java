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
package org.apache.ibatis.datasource.pooled;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库链接吃的状态，最大连接数，可用连接数，休眠的连接数等等信息，池子的状态如何都是很明确的
 * ，这里可以学习一波
 */
public class PoolState {

    //数据源
    protected PooledDataSource dataSource;

    //空闲的链接
    protected final List<PooledConnection> idleConnections = new ArrayList<>();
    //工作的链接
    protected final List<PooledConnection> activeConnections = new ArrayList<>();
    //请求次数
    protected long requestCount = 0;
    //请求时间，累计rt
    protected long accumulatedRequestTime = 0;
    //检查时间
    protected long accumulatedCheckoutTime = 0;
    //
    protected long claimedOverdueConnectionCount = 0;
    protected long accumulatedCheckoutTimeOfOverdueConnections = 0;
    protected long accumulatedWaitTime = 0;
    protected long hadToWaitCount = 0;
    protected long badConnectionCount = 0;

    public PoolState(PooledDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public synchronized long getRequestCount() {
        return requestCount;
    }

    //平均请求时间
    public synchronized long getAverageRequestTime() {
        return requestCount == 0 ? 0 : accumulatedRequestTime / requestCount;
    }

    //平均等待时间
    public synchronized long getAverageWaitTime() {
        return hadToWaitCount == 0 ? 0 : accumulatedWaitTime / hadToWaitCount;

    }

    public synchronized long getHadToWaitCount() {
        return hadToWaitCount;
    }

    public synchronized long getBadConnectionCount() {
        return badConnectionCount;
    }

    public synchronized long getClaimedOverdueConnectionCount() {
        return claimedOverdueConnectionCount;
    }

    public synchronized long getAverageOverdueCheckoutTime() {
        return claimedOverdueConnectionCount == 0 ? 0 : accumulatedCheckoutTimeOfOverdueConnections / claimedOverdueConnectionCount;
    }

    public synchronized long getAverageCheckoutTime() {
        return requestCount == 0 ? 0 : accumulatedCheckoutTime / requestCount;
    }


    public synchronized int getIdleConnectionCount() {
        return idleConnections.size();
    }

    public synchronized int getActiveConnectionCount() {
        return activeConnections.size();
    }

    @Override
    public synchronized String toString() {
        String builder = "\n===CONFINGURATION==============================================" +
                "\n jdbcDriver                     " + dataSource.getDriver() +
                "\n jdbcUrl                        " + dataSource.getUrl() +
                "\n jdbcUsername                   " + dataSource.getUsername() +
                "\n jdbcPassword                   " + (dataSource.getPassword() == null ? "NULL" : "************") +
                "\n poolMaxActiveConnections       " + dataSource.poolMaximumActiveConnections +
                "\n poolMaxIdleConnections         " + dataSource.poolMaximumIdleConnections +
                "\n poolMaxCheckoutTime            " + dataSource.poolMaximumCheckoutTime +
                "\n poolTimeToWait                 " + dataSource.poolTimeToWait +
                "\n poolPingEnabled                " + dataSource.poolPingEnabled +
                "\n poolPingQuery                  " + dataSource.poolPingQuery +
                "\n poolPingConnectionsNotUsedFor  " + dataSource.poolPingConnectionsNotUsedFor +
                "\n ---STATUS-----------------------------------------------------" +
                "\n activeConnections              " + getActiveConnectionCount() +
                "\n idleConnections                " + getIdleConnectionCount() +
                "\n requestCount                   " + getRequestCount() +
                "\n averageRequestTime             " + getAverageRequestTime() +
                "\n averageCheckoutTime            " + getAverageCheckoutTime() +
                "\n claimedOverdue                 " + getClaimedOverdueConnectionCount() +
                "\n averageOverdueCheckoutTime     " + getAverageOverdueCheckoutTime() +
                "\n hadToWait                      " + getHadToWaitCount() +
                "\n averageWaitTime                " + getAverageWaitTime() +
                "\n badConnectionCount             " + getBadConnectionCount() +
                "\n===============================================================";
        return builder;
    }

}
