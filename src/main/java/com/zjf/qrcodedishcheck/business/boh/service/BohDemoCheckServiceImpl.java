package com.zjf.qrcodedishcheck.business.boh.service;

import com.zjf.qrcodedishcheck.business.boh.dao.BohDao;
import com.zjf.qrcodedishcheck.business.boh.entity.BohSysConfig;
import com.zjf.qrcodedishcheck.business.common.constant.CheckResEnum;
import com.zjf.qrcodedishcheck.business.common.service.AbstractCheckService;
import com.zjf.qrcodedishcheck.business.settingcheck.vo.SettingCheckResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("bohCheckService")
@Slf4j
public class BohDemoCheckServiceImpl extends AbstractCheckService {

    @Autowired
    private BohDao bohDao;

    @Override
    public SettingCheckResultVo check(String userId, String tenantId) {
        List<BohSysConfig> bohSysConfigs = bohDao.queryDemoConfig(userId);
        log.info("bohSysConfigs={}", bohSysConfigs);

        if (bohSysConfigs.size() > 0) {
            return this.retSuccess(CheckResEnum.BOH_DEMO);
        } else {
            return this.retError(CheckResEnum.BOH_DEMO);
        }

    }
}
