package com.tg.attendance.context;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.tg.attendance.util.PassEncTech1;
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
                PassEncTech1 td = new PassEncTech1();
                DefaultConfiguration jooqConfiguration = new DefaultConfiguration();
                MysqlDataSource datasource = new MysqlDataSource();
                Properties prop = readPropertiesFile("application.properties");
                datasource.setDatabaseName(prop.getProperty("dbName"));
                datasource.setUser(prop.getProperty("dbUser"));
                datasource.setPassword(td.decrypt(prop.getProperty("dbPassword")));
                datasource.setUrl(prop.getProperty("dbUrl"));
                jooqConfiguration.set(new DataSourceConnectionProvider(datasource));
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

    private Properties readPropertiesFile(String fileName) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(fileName)) {
            props.load(resourceStream);
        }
// use props
        return props;
    }
}
