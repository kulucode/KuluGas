package cn.tpson.kulu.gas.domain;

import java.time.Instant;

public class SysBlockChainContentDO extends BaseDO {
    public SysBlockChainContentDO() {}
    public SysBlockChainContentDO(Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        super(deleted, gmtCreate, gmtModified);
    }

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}