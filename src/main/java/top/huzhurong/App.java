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
package top.huzhurong;

import org.apache.ibatis.io.Resources;
import top.huzhurong.cmdb.dao.PerformDataDao;
import top.huzhurong.cmdb.dao.impl.PerformDataDaoImpl;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 *
 * @author luobo.cs@raycloud.com
 * @since 2018/8/5
 */
public class App {
    public static void main(String[] args) throws IOException {
        String resource = "config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        PerformDataDao performDataDao = new PerformDataDaoImpl(inputStream);
        performDataDao.selectPerformDataByKey(1154080);
    }
}
