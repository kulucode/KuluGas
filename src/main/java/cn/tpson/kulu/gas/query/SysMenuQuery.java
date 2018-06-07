package cn.tpson.kulu.gas.query;

import java.util.List;

public class SysMenuQuery extends BaseQuery {
    private Long pid;

    private String text;

    private String aliasText;

    private String uri;

    private Short type;

    private Short order;

    private String icon;

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAliasText() {
        return aliasText;
    }

    public void setAliasText(String aliasText) {
        this.aliasText = aliasText;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getOrder() {
        return order;
    }

    public void setOrder(Short order) {
        this.order = order;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}