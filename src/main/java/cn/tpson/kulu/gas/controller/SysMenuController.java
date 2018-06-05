package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.service.SysMenuService;
import cn.tpson.kulu.gas.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Zhangka in 2018/06/05
 */
@RequestMapping("/gas/sysmenu")
@RestController
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @RequestMapping("/getById")
    public ResultVO getById(Long id) {
        return ResultVO.successResult(sysMenuService.getById(id));
    }
}
