package com.zjf.qrcodedishcheck.business.boh.dao.impl;

import com.zjf.qrcodedishcheck.business.boh.entity.BohSysConfig;
import com.zjf.qrcodedishcheck.common.datasource.DataSourceManager;
import com.zjf.qrcodedishcheck.common.datasource.constant.BusiDBTypeEnum;
import com.zjf.qrcodedishcheck.business.boh.dao.BohDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BohDaoImpl implements BohDao {

    /**
     * 每个dao查询那个业务线的数据源是确定的
     */
    private final BusiDBTypeEnum dbType = BusiDBTypeEnum.BOH;

    /**
     * 数据源
     */
    @Autowired
    private DataSourceManager dsManager;

    @Override
    public List<BohSysConfig> queryDemoConfig(String userId) {
        return dsManager.getSqlSessionTemplate(userId, dbType)
                .selectList("com.choice.qrcodedishcheck.business.settingcheck.dao.BohDao.queryRedisConfig");
    }
}
