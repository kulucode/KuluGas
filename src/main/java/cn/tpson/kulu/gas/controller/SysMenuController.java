package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.dto.SysMenuDTO;
import cn.tpson.kulu.gas.service.SysMenuService;
import cn.tpson.kulu.gas.util.MenuTreeUtils;
import cn.tpson.kulu.gas.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping("/add")
    public ResultVO add() {
        SysMenuDTO sysMenuDTO = new SysMenuDTO();
        sysMenuDTO.setText("用户管理");
        sysMenuDTO.setAliasText("user");
        sysMenuDTO.setType((short)0);
        sysMenuDTO.setOrder((short)3);
        sysMenuDTO.setDeleted(Boolean.TRUE);
        return ResultVO.successResult(sysMenuService.insert(sysMenuDTO));
    }

    @RequestMapping("/menus")
    public ResultVO menus() {
        List<SysMenuDTO> list = sysMenuService.list();
        return ResultVO.successResult(MenuTreeUtils.createMenuTree(list));
    }
}
