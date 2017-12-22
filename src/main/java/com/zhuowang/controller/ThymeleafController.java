package com.zhuowang.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// @formatter:off
 @Controller
// @formatter:on

@RequestMapping("/thymeleaf")
public class ThymeleafController {

	private static final Logger logger = LoggerFactory.getLogger(ThymeleafController.class);

    /**
     * 测试thymeleaf
     * @return
     */
    @RequestMapping(value = "/hello")
    public String hello(Model model) {
    	logger.info("--------------------------------------");
        model.addAttribute("name", "Dear fdewdedewoooooooooooooooooooooo");
        return "hello";
    }
}
