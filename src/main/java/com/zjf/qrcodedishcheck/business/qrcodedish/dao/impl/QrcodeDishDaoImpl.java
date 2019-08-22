package com.zjf.qrcodedishcheck.business.qrcodedish.dao.impl;

import com.zjf.qrcodedishcheck.business.qrcodedish.dao.QrcodeDishDao;
import com.zjf.qrcodedishcheck.business.qrcodedish.entity.AccountStoreConf;
import com.zjf.qrcodedishcheck.common.datasource.DataSourceManager;
import com.zjf.qrcodedishcheck.common.datasource.constant.BusiDBTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QrcodeDishDaoImpl implements QrcodeDishDao {

    /**
     * 每个dao查询那个业务线的数据源是确定的
     */
    private final BusiDBTypeEnum dbType = BusiDBTypeEnum.QRCODE_DISH;

    /**
     * 数据源
     */
    @Autowired
    private DataSourceManager dsManager;

    public AccountStoreConf getDemoConf(String userId) {
        return dsManager.getSqlSessionTemplate(userId, dbType)
                .selectOne("com.choice.qrcodedishcheck.business.settingcheck.dao.QrcodeDishDao.getPrePayment");
    }
}
