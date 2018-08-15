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

    PerformData selectPerformDataByKey(Integer key);

    Integer addPerformData(PerformData performData);

    Integer updatePerformDataByKey(PerformData performData);

    Integer deletePerformDataByKey(Integer key);

    Map<String,Object> getMap();
}