import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.huzhurong.cmdb.bean.PerformData;
import top.huzhurong.cmdb.bean.query.PerformDataQuery;
import top.huzhurong.cmdb.dao.PerformDataDao;
import top.huzhurong.cmdb.dao.impl.PerformDataDaoImpl;
import top.huzhurong.me.IpMark;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author luobo.cs@raycloud.com
 * @since 2018/8/5
 */
public class App {
    public static void main(String[] args) throws IOException {
        String resource = "config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        PerformDataDao performDataDao = new PerformDataDaoImpl(inputStream);
        System.out.println(1);
        System.out.println(JSONObject.toJSONString(performDataDao.selectPerformDataByKey(1154080)));
    }
}
