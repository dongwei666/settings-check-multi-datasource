package com.zjf.qrcodedishcheck.business.boh.dao;

import com.zjf.qrcodedishcheck.business.boh.entity.BohSysConfig;

import java.util.List;

public interface BohDao {

     List<BohSysConfig> queryDemoConfig(String userId);
}
