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
package top.huzhurong;

import org.apache.ibatis.io.Resources;
import top.huzhurong.cmdb.bean.PerformData;
import top.huzhurong.cmdb.dao.PerformDataDao;
import top.huzhurong.cmdb.dao.impl.PerformDataDaoImpl;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * -javaagent:/Users/chenshun/.m2/repository/top/huzhurong/agent/o-mysql/1.0-SNAPSHOT/o-mysql-1.0-SNAPSHOT.jar
 * -XX:-UseSplitVerifier
 * -noverify
 * <p>
 * 如何测试 mybatis 缓存
 * 1、session 级别的缓存
 * why:sqlSession每一次执行都需要去新获取(new defaultSqlSession() --> 内置缓存)，所以说是sqlSession级别的缓存
 * 2、statement级别的缓存
 * why:在执行的最后根据statement的情况去清空缓存
 * <p>
 * #---- 1级缓存测试
 * 1、一级缓存测试，默认设置，即 session级别的缓存操作
 * 2、同sqlSession 2次查询                        √ 缓存命中
 * 3、不通sqlSession 2次查询                      √  缓存未命中
 * 4、同sqlSession 一次查询一次更新一次查询1          √ 更新之后缓存失效
 * 5、不同sqlSession，一次查询一次更新一次查询
 * 6、脏数据演示
 * <p>
 * #---- 2级缓存测试
 * 1、明确二级缓存是什么  是cache，这个东西，
 * 2、如何打开，如何关闭 使用 <cache/> 标签打开，或者@CacheNamespace 注解打开，并且要求settings开启 enableCache(默认开启)
 * 同时使用才能生效
 * 3、使用二级缓存各种测试
 * 4、脏数据测试
 * <p>
 * 由于mybatis是本地缓存，但是实际的线上部署都是用过多点tomcat去放置单点问题，所以有可能因为mybatis的缓存1，2级缓存造成脏数据
 * 如何说明脏数据
 *
 * @author luobo.cs@raycloud.com
 * @since 2018/8/5
 */
public class App {
    public static void main(String[] args) throws Exception {
        String resource = "config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        PerformDataDao performDataDao = new PerformDataDaoImpl(inputStream);
        performDataDao.deletePerformDataByKey(1);
//        List<PerformData> performData = performDataDao.selectPerformDataByKey(1234571443);
//        PerformData data = performData.get(0);
//        data.setId(1L);
//        data.setAddTime(new Date());

//        performDataDao.addPerformData(data);


//        Connection connection = DriverManager.getConnection("xx", null);
//        PreparedStatement preparedStatement = connection.prepareStatement("select * from table_name_x where id = ?");
//        preparedStatement.setInt(1,1);
//        preparedStatement.execute();
//        ResultSet resultSet = preparedStatement.getResultSet();



//
    }
}
