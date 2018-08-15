package top.huzhurong.cmdb.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.huzhurong.cmdb.bean.PerformData;
import top.huzhurong.cmdb.bean.query.PerformDataQuery;
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

    public PerformData selectPerformDataByKey(Integer key) {
        try (SqlSession sqlSessionTemplate = this.getSqlSessionTemplate()) {
            return sqlSessionTemplate.selectOne("PerformData.selectPerformDataByKey", key);
        }
    }

    public Integer addPerformData(PerformData performData) {
        try (SqlSession sqlSessionTemplate = this.getSqlSessionTemplate()){
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
        return this.getSqlSessionTemplate().selectMap("","");
    }

}
