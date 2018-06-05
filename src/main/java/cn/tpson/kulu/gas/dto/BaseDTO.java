package cn.tpson.kulu.gas.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * Created by Zhangka in 2018/06/05
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseDTO implements Serializable {
    @JsonIgnore
    private Integer offset;
    @JsonIgnore
    private Integer limit;

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
}
