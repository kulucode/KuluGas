package cn.tpson.kulu.gas.dto;

import java.io.Serializable;

/**
 * Created by Zhangka in 2018/07/05
 */
public class StatisticsDTO implements Serializable {
    private String date;
    private Integer total;

    public StatisticsDTO() {}
    public StatisticsDTO(String date, Integer total) {
        this.date = date;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
