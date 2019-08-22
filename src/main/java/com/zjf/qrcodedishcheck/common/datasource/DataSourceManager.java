package com.zjf.qrcodedishcheck.common.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.zjf.qrcodedishcheck.common.datasource.constant.BusiDBTypeEnum;
import com.zjf.qrcodedishcheck.common.datasource.entity.DataSourceConfig;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class DataSourceManager {

    /**
     * 数据源Map
     */
    public static Map<String, Map<BusiDBTypeEnum, SqlSessionTemplate>> sqlSessionTemplateMap = Maps.newHashMap();

    /**
     * 通过两个key获取数据源
     * 
     * @param key 区别操作用户，使用tenantId + seseeionId
     * @param busiDBType，业务线数据源
     * @return
     */
    public SqlSessionTemplate getSqlSessionTemplate(String key, BusiDBTypeEnum busiDBType) {
        return sqlSessionTemplateMap.get(key).get(busiDBType);
    }

    /**
     * 清理该用户使用的资源
     *
     * @param key
     */
    public void cleanUserResource(String key) {
        sqlSessionTemplateMap.remove(key);
    }

    /**
     * 创建单个数据源，如果已存在则覆盖
     */
    public void createDataSource(String key, BusiDBTypeEnum busiDBType, DataSourceConfig config) {
        try {
            Map<BusiDBTypeEnum, SqlSessionTemplate> curMap = sqlSessionTemplateMap.get(key);
            if (curMap == null) {
                curMap = Maps.newHashMap();
            }

            curMap.put(busiDBType, createSqlTemplateByConf(config));
            sqlSessionTemplateMap.put(key, curMap);
        } catch (Exception e) {
            log.error("创建数据源失败, errMsg = {}, error stack = {}", e.getMessage(), e);
        }
    }

    /**
     * 批量创建各个业务线数据源
     * 
     * @param key
     * @param dsList
     */
    public void createDataSource(String key, List<DataSourceConfig> dsList) {

        // 1、构造当前用户的数据源Map
        Map<BusiDBTypeEnum, SqlSessionTemplate> curMap = sqlSessionTemplateMap.get(key);
        if (curMap == null) {
            curMap = Maps.newHashMap();
        }

        // 2、构造各个业务线数据源
        for (DataSourceConfig conf : dsList) {
            try {
                curMap.put(BusiDBTypeEnum.getEnumByCode(conf.getBusiDBType()), createSqlTemplateByConf(conf));
            } catch (Exception e) {
                log.error("创建数据源失败, errMsg = {}, error stack = {}", e.getMessage(), e);
            }
        }

        // 3、放回外层Map
        sqlSessionTemplateMap.put(key, curMap);
    }

    /**
     * 根据数据源配置，创建sqltemplate
     */
    private SqlSessionTemplate createSqlTemplateByConf(DataSourceConfig config) throws Exception {

        // 1、首先创建数据源
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(config.getDriverClassName());
        ds.setUrl(config.getUrl());
        ds.setUsername(config.getUsername());
        ds.setPassword(config.getPassword());

        // 2、创建sqlSessionFactory
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(ds);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resolver.getResources(config.getMapperLocations()));

        // 3、创建sqlSessionTemplate
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory.getObject());

        return sqlSessionTemplate;
    }

}
