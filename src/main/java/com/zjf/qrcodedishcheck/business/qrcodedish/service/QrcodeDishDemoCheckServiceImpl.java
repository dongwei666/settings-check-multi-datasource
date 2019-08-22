package com.zjf.qrcodedishcheck.business.qrcodedish.service;

import com.zjf.qrcodedishcheck.business.common.constant.CheckResEnum;
import com.zjf.qrcodedishcheck.business.qrcodedish.dao.QrcodeDishDao;
import com.zjf.qrcodedishcheck.business.qrcodedish.entity.AccountStoreConf;
import com.zjf.qrcodedishcheck.business.common.service.AbstractCheckService;
import com.zjf.qrcodedishcheck.business.settingcheck.vo.SettingCheckResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("qrcodeDishCheckService")
@Slf4j
public class QrcodeDishDemoCheckServiceImpl extends AbstractCheckService {

    @Autowired
    private QrcodeDishDao qrcodeDishDao;

    @Override
    public SettingCheckResultVo check(String userId, String tenantId) {
        AccountStoreConf demoConf = qrcodeDishDao.getDemoConf(userId);
        log.info("demoConf={}",demoConf);
        if (demoConf != null) {
            return this.retSuccess(CheckResEnum.QRCODE_DISH_DEMO);
        } else {
            return this.retError(CheckResEnum.QRCODE_DISH_DEMO);
        }

    }
}
