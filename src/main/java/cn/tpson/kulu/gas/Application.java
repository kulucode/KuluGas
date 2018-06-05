package cn.tpson.kulu.gas;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Zhangka in 2018/06/04
 */
@SpringBootApplication
@MapperScan("cn.tpson.kulu.gas.repository")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
