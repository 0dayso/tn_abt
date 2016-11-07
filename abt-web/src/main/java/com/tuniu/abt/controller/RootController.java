package com.tuniu.abt.controller;

import com.tuniu.operation.platform.tsg.base.core.annotation.Json;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试
 *
 * @author chengyao
 */
@Controller
public class RootController {

    @RequestMapping(value = "/")
    public ModelAndView root(@Json Integer id) {
        return new ModelAndView("redirect:/statics/html/index.html");
    }

}