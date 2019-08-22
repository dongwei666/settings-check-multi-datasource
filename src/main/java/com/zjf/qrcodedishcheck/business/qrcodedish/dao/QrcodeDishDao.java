package com.zjf.qrcodedishcheck.business.qrcodedish.dao;

import com.zjf.qrcodedishcheck.business.qrcodedish.entity.AccountStoreConf;

public interface QrcodeDishDao {

    AccountStoreConf getDemoConf(String userId);
}
