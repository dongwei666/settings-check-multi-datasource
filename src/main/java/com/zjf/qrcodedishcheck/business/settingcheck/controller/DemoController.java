package com.zjf.qrcodedishcheck.business.settingcheck.controller;

import com.zjf.qrcodedishcheck.business.settingcheck.dto.SettingCheckDTO;
import com.zjf.qrcodedishcheck.business.settingcheck.service.AllSettingCheckService;
import com.zjf.qrcodedishcheck.business.settingcheck.vo.SettingCheckResultVo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * http方式调用service，主要测试后面的代码是否可以正常运行
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private AllSettingCheckService allSettingCheckService;

    @PostMapping("/docheck")
    public List<SettingCheckResultVo> docheck(@RequestBody SettingCheckDTO settingCheckDTO) {

        List<SettingCheckResultVo> list = Lists.newArrayList();

        allSettingCheckService.checkAllSettings(settingCheckDTO.getDoCheckDTO().getTenantId() + "id",
                settingCheckDTO.getDoCheckDTO(), resultVo -> list.add(resultVo));

        return list;
    }
}
