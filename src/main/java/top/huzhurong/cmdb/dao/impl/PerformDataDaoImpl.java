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
package top.huzhurong.cmdb.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.huzhurong.cmdb.bean.PerformData;
import top.huzhurong.cmdb.dao.PerformDataDao;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author chenshun
 * @since 2018-08-06 09:56:59
 */
public class PerformDataDaoImpl implements PerformDataDao {

    private InputStream inputStream;

    public PerformDataDaoImpl(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private SqlSessionFactory sqlSessionFactory;

    private SqlSession getSqlSessionTemplate() {
        if (sqlSessionFactory == null) {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(this.inputStream);
        }
        return sqlSessionFactory.openSession(true);
    }

    public List<PerformData> selectPerformDataByKey(Integer key) {
        try (SqlSession sqlSessionTemplate = this.getSqlSessionTemplate()) {
            return sqlSessionTemplate.selectList("PerformData.selectPerformDataByKey", key);
        }
    }

    public Integer addPerformData(PerformData performData) {
        try (SqlSession sqlSessionTemplate = this.getSqlSessionTemplate()) {
            return sqlSessionTemplate.insert("PerformData.insertPerformData", performData);
        }
    }


    public Integer updatePerformDataByKey(PerformData performData) {
        return this.getSqlSessionTemplate().update("PerformData.updatePerformDataByKey", performData);
    }


    public Integer deletePerformDataByKey(Integer key) {
        return this.getSqlSessionTemplate().delete("PerformData.deletePerformDataByKey", key);
    }

    @Override
    public Map<String, Object> getMap() {
        return this.getSqlSessionTemplate().selectMap("", "");
    }

}
