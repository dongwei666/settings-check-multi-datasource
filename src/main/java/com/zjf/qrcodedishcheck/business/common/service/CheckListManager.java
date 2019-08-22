package com.zjf.qrcodedishcheck.business.common.service;

import com.zjf.qrcodedishcheck.business.common.constant.TenantTypeEnum;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CheckListManager {

    /**
     * 注入boh校验服务；
     * 实际开发，一个校验项目一个service，便于细粒度计算校验进度，也便于组装成不同的校验组供不同商户使用
     */
    @Autowired
    @Qualifier("bohCheckService")
    private AbstractCheckService bohCheckService;

    /**
     * 注入扫码点餐校验服务
     */
    @Autowired
    @Qualifier("qrcodeDishCheckService")
    private AbstractCheckService qrcodeDishCheckService;

    /**
     * 校验分组：不同的商户校验可能不同
     */
    private static Map<TenantTypeEnum, List<AbstractCheckService>> checkGourps = Maps.newHashMap();

    /**
     * 根据实际需要构造各个商户校验所需service组
     */
    @PostConstruct
    public void init() {

        // 1、标版扫码点餐校验项
        List<AbstractCheckService> common = Lists.newArrayList();
        common.add(bohCheckService);
        common.add(qrcodeDishCheckService);
        common.add(qrcodeDishCheckService);
        checkGourps.put(TenantTypeEnum.COMMON, common);

        // 2、九毛九扫码点餐配置项
        List<AbstractCheckService> jiumaojiu = Lists.newArrayList();
        jiumaojiu.add(bohCheckService);
        jiumaojiu.add(qrcodeDishCheckService);
        checkGourps.put(TenantTypeEnum.JIUMAOJIU, common);
    }

    /**
     * 获取某个校验类型的checkList
     */
    public List<AbstractCheckService> getCheckList(String tenantType) {
        TenantTypeEnum typeEnum = TenantTypeEnum.getEnumByCode(tenantType);
        List<AbstractCheckService> checkServiceList = checkGourps.get(typeEnum);
        return checkServiceList;
    }

}
