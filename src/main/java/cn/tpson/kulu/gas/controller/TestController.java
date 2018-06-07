package cn.tpson.kulu.gas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Zhangka in 2018/06/06
 */
@Controller
@RequestMapping("/gas/front")
public class TestController {
    @RequestMapping("/add")
    public String add() {
        return "/gas/front/add";
    }
}
