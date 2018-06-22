package cn.tpson.kulu.gas.dto;

import cn.tpson.kulu.gas.json.InstantSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.time.Instant;

/**
 * Created by Zhangka in 2018/06/05
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO implements Serializable {
    @JsonIgnore
    private Integer offset;
    @JsonIgnore
    private Integer limit;

    private Integer id;
    private Boolean deleted;
    @JsonSerialize(using = InstantSerializer.class)
    private Instant gmtCreate;
    @JsonSerialize(using = InstantSerializer.class)
    private Instant gmtModified;

    public BaseDTO() {}

    public BaseDTO(Integer id, Boolean deleted, Instant gmtCreate, Instant gmtModified) {
        this.id = id;
        this.deleted = deleted;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

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
