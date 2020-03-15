package org.kakarrot.loading;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kakarrot.config.RootConfig;
import org.kakarrot.mapper.TimeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Log4j
public class MySQLConnetTests {

    @Test
    public void testConnet() throws Exception {
        Class<?> clz = Class.forName("com.mysql.jdbc.Driver");

        log.info(clz);

        Connection con =
                DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/bitdb?serverTimezone=Asia/Seoul&useSSL=false"
                        ,"bit04"
                        , "bit04"
                );

        log.info(con);

        con.close();
    }

}
