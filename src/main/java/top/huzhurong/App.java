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
import java.util.List;

/**
 * -javaagent:/Users/chenshun/.m2/repository/top/huzhurong/agent/o-mysql/1.0-SNAPSHOT/o-mysql-1.0-SNAPSHOT.jar
 *
 * @author luobo.cs@raycloud.com
 * @since 2018/8/5
 */
public class App {
    public static void main(String[] args) throws Exception {
        String resource = "config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        PerformDataDao performDataDao = new PerformDataDaoImpl(inputStream);
        List<PerformData> performData = performDataDao.selectPerformDataByKey(1154080);
        performDataDao.selectPerformDataByKey(111111);

    }
}
