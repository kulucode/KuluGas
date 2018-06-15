package cn.tpson.kulu.gas.domain;

import java.io.Serializable;
import java.time.Instant;

/**
 * Created by Zhangka in 2018/06/06
 */
public class BaseDO implements Serializable {
    private Integer id;
    private Boolean deleted;
    private Instant gmtCreate;
    private Instant gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Instant getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Instant gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Instant getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Instant gmtModified) {
        this.gmtModified = gmtModified;
    }
}
