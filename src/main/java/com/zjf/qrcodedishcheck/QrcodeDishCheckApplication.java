package com.zjf.qrcodedishcheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@Controller
public class QrcodeDishCheckApplication {


    /**
     * 工程启动
     */
    public static void main(String[] args) {
        SpringApplication.run(QrcodeDishCheckApplication.class, args);
        System.out.println("---------------启动完成-----------------");
    }

    /**
     * 首页
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index() {
        return "index";
    }

}
