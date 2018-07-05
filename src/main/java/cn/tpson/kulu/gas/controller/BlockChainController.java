package cn.tpson.kulu.gas.controller;

import cn.tpson.kulu.gas.constant.ErrorCodeEnum;
import cn.tpson.kulu.gas.dto.SysBlockChainDTO;
import cn.tpson.kulu.gas.query.SysBlockChainQuery;
import cn.tpson.kulu.gas.service.SysBlockChainService;
import cn.tpson.kulu.gas.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Zhangka in 2018/06/26
 */
@RestController
@RequestMapping("/bs")
public class BlockChainController extends BaseController {
    @Autowired
    private SysBlockChainService sysBlockChainService;

    /**
     * 获取区块链信息.
     * @param source
     * @param sid
     * @return
     */
    @GetMapping("/block")
    public ResultVO block(Short source, @RequestParam(value = "sourceId") Integer sid) {
        SysBlockChainQuery query = new SysBlockChainQuery();
        query.setSource(source);
        query.setSid(sid);
        SysBlockChainDTO sysBlockChainDTO = sysBlockChainService.selectOneByExample(query);
        return sysBlockChainDTO == null
                ? ResultVO.failResult(ErrorCodeEnum.BLOCK_NOT_FOUND)
                : ResultVO.successResult(sysBlockChainDTO);
    }
}
