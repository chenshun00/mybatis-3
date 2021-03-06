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
package top.huzhurong.cmdb.dao;

import top.huzhurong.cmdb.bean.PerformData;
import top.huzhurong.cmdb.bean.query.PerformDataQuery;

import java.util.List;
import java.util.Map;

/**
*
* @author chenshun
* @since 2018-08-06 09:56:59
*/
public interface PerformDataDao{

    List<PerformData> selectPerformDataByKey(Integer key);

    Integer addPerformData(PerformData performData);

    Integer updatePerformDataByKey(PerformData performData);

    Integer deletePerformDataByKey(Integer key);

    Map<String,Object> getMap();
}