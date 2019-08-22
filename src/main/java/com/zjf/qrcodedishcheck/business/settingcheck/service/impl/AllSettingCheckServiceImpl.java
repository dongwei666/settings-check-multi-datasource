package com.zjf.qrcodedishcheck.business.settingcheck.service.impl;

import com.zjf.qrcodedishcheck.business.common.service.AbstractCheckService;
import com.zjf.qrcodedishcheck.business.common.service.CheckListManager;
import com.zjf.qrcodedishcheck.business.settingcheck.dto.DoCheckDTO;
import com.zjf.qrcodedishcheck.business.settingcheck.service.AllSettingCheckService;
import com.zjf.qrcodedishcheck.business.settingcheck.vo.SettingCheckResultVo;
import com.zjf.qrcodedishcheck.common.datasource.DataSourceManager;
import com.zjf.qrcodedishcheck.common.datasource.entity.DataSourceConfig;
import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Consumer;

@Service
public class AllSettingCheckServiceImpl implements AllSettingCheckService {

    @Autowired
    private CheckListManager checkManager;

    @Autowired
    private DataSourceManager dataSourceManager;

    /**
     * 执行校验
     * 
     * @param userId 用户id，约定使用tenantId + sessionId做用户id，用于区分各个操作用户
     * @param doCheckDTO
     * @param sendMsg 输出结果回调函数
     */
    @Override
    public void checkAllSettings(String userId, DoCheckDTO doCheckDTO, Consumer<SettingCheckResultVo> sendMsg) {

        // 1、首先创建校验需要的数据源
        List<DataSourceConfig> dsList = Lists.newArrayList();
        doCheckDTO.getDsList().forEach(bean -> {
            DataSourceConfig dataSourceConfig = new DataSourceConfig();
            BeanUtils.copyProperties(bean, dataSourceConfig);
            dataSourceConfig.setDataSourceInfoByType();
            dsList.add(dataSourceConfig);
        });

        dataSourceManager.createDataSource(userId, dsList);

        // 2、获取校验列表，进行校验
        List<AbstractCheckService> checkList = checkManager.getCheckList(doCheckDTO.getTenantType());

        for (int i = 0; i < checkList.size(); i++) {
            SettingCheckResultVo resultVo = checkList.get(i).check(userId, doCheckDTO.getTenantId());
            resultVo.setProgress(calPercent((i + 1), checkList.size()));
            // 输出校验结果
            sendMsg.accept(resultVo);
        }

        // 3、清理用户使用资源
        dataSourceManager.cleanUserResource(userId);

    }

    /**
     * 计算进度
     */
    private BigDecimal calPercent(int i, int total) {
        return new BigDecimal(i).divide(new BigDecimal(total), 2, RoundingMode.DOWN).multiply(new BigDecimal(100));
    }
}
