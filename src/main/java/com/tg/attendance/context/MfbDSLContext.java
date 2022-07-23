package com.tg.attendance.context;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.tg.attendance.util.PassEncTech1;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.jooq.SQLDialect;
import org.jooq.impl.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class MfbDSLContext implements MfbDSL {
    private DefaultDSLContext dsl;

    public DefaultDSLContext getContext() throws Exception {
        if (dsl == null) {
            try {
                DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
                jooqConfiguration.set(new DataSourceConnectionProvider(DataSource.getDataSource()));
                jooqConfiguration.setSQLDialect(SQLDialect.MYSQL);
                return new DefaultDSLContext(jooqConfiguration);
            } catch (Exception e) {
                log.error("Decryption error", e);
                throw new Exception("Decryption Error");
            }
        } else {
            return dsl;
        }
    }
}
