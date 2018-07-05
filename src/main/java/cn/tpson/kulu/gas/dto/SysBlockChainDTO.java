package cn.tpson.kulu.gas.dto;

import cn.tpson.kulu.gas.json.InstantSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.Instant;

public class SysBlockChainDTO extends BaseDTO {
    /**
     * 数据来源；1：用户信息；2：检测信息；3：设备信息
     */
    public static final short SOURCE_USER = 1;
    public static final short SOURCE_DETECT = 2;
    public static final short SOURCE_EQP = 3;

    private Integer level;

    private String blockHash;

    private String lastBlockHash;

    private String contentHash;

    private String contentCreator;

    @JsonSerialize(using = InstantSerializer.class)
    private Instant gmtContentModified;

    private Integer contentId;

    private Short source;

    private Integer sid;

    public SysBlockChainDTO() {}

    public SysBlockChainDTO(Integer id, Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        super(id, deleted, gmtCreate, gmtModified);
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public String getContentHash() {
        return contentHash;
    }

    public void setContentHash(String contentHash) {
        this.contentHash = contentHash;
    }

    public String getContentCreator() {
        return contentCreator;
    }

    public void setContentCreator(String contentCreator) {
        this.contentCreator = contentCreator;
    }

    public Instant getGmtContentModified() {
        return gmtContentModified;
    }

    public void setGmtContentModified(Instant gmtContentModified) {
        this.gmtContentModified = gmtContentModified;
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public Short getSource() {
        return source;
    }

    public void setSource(Short source) {
        this.source = source;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getLastBlockHash() {
        return lastBlockHash;
    }

    public void setLastBlockHash(String lastBlockHash) {
        this.lastBlockHash = lastBlockHash;
    }
}