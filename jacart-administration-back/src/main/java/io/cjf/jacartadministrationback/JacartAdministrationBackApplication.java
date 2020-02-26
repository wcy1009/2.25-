package io.cjf.jacartadministrationback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("io.cjf.jacartadministrationback.dao")
public class JacartAdministrationBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(JacartAdministrationBackApplication.class, args);
    }

}
