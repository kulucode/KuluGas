package cn.tpson.kulu.gas.util;

import cn.tpson.kulu.gas.dto.SysMenuDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Zhangka in 2018/06/06
 */
public class MenuTreeUtils {
    private MenuTreeUtils() {
        throw new AssertionError("No cn.tpson.kulu.gas.util.MenuTreeUtils instances for you!");
    }

    /**
     * @param sysMenuDTOList
     * @return
     */
    public static List<SysMenuDTO> createMenuTree(List<SysMenuDTO> sysMenuDTOList) {
        // 根菜单
        List<SysMenuDTO> rootMenus = sysMenuDTOList.stream().filter(m -> Objects.isNull(m.getPid())).collect(Collectors.toList());

        // 初始化子菜单及子功能
        for (SysMenuDTO root : rootMenus) {
            root.setChildren(getChildren(sysMenuDTOList, root.getId()));
        }

        return rootMenus;
    }

    /**
     *
     * @param menus
     * @param pid
     * @return
     */
    private static List<SysMenuDTO> getChildren(final List<SysMenuDTO> menus, final Integer pid) {
        final List<SysMenuDTO> children = new ArrayList<>();
        menus.forEach(m -> {
            if (Objects.nonNull(m.getPid())) {
                if (m.getPid().equals(pid)) {
                    children.add(m);
                    if (m.getType() == 0) {
                        m.setChildren(getChildren(menus, m.getId()));
                    }
                }
            }
        });
        return children.size() > 0 ? children : null;
    }
}
