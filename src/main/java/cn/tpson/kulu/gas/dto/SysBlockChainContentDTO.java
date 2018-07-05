package cn.tpson.kulu.gas.dto;

import java.time.Instant;

public class SysBlockChainContentDTO extends BaseDTO {
    private String content;

    public SysBlockChainContentDTO() {}

    public SysBlockChainContentDTO(Integer id, Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        super(id, deleted, gmtCreate, gmtModified);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}