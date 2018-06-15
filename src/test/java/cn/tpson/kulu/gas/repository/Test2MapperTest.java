package cn.tpson.kulu.gas.repository;

import cn.tpson.kulu.gas.domain.SysMenuDO;
import cn.tpson.kulu.gas.dto.Page;
import cn.tpson.kulu.gas.dto.SysMenuDTO;
import cn.tpson.kulu.gas.service.SysMenuService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by Zhangka in 2018/06/04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test2MapperTest {
    /*@Autowired
    private SysMenuDOMapper sysMenuDOMapper;*/
    @Autowired
    private SysMenuService sysMenuService;

    @Test
    public void testSelectAll() {
        /*SysMenuDTO sysMenuDTO = new SysMenuDTO();
        sysMenuDTO.setName("后台管理");
        sysMenuDTO.setAliasName("backend");
        sysMenuDTO.setPid(null);
        sysMenuDTO.setUri("/gas/backend");
        sysMenuDTO.setGmtCreate(new Date());
        long id = sysMenuService.insert(sysMenuDTO);
        System.out.println(id);
        System.out.println(JSON.toJSONString(sysMenuDTO));*/

        /*SysMenuDTO dto = new SysMenuDTO();
        dto.setLimit(20);
        dto.setOffset(0);

        Page<SysMenuDTO> page = new Page<>();
        page.setPageNo(1);
        page.setPageSize(20);
        page.setCondition(dto);
        System.out.println(JSON.toJSONString(sysMenuService.pageByExample(page)));
        System.out.println(JSON.toJSONString(sysMenuService.listByExample(dto)));*/
        System.out.println(JSON.toJSONString(sysMenuService.getById(1)));
    }
}
