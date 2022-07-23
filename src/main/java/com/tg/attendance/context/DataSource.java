package com.tg.attendance.context;

import com.tg.attendance.util.PassEncTech1;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.impl.DataSourceConnectionProvider;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    static {
        try {
            PassEncTech1 td = new PassEncTech1();
            Properties prop = readPropertiesFile("application.properties");
            config.setJdbcUrl(prop.getProperty("dbUrl"));
            config.setUsername(prop.getProperty("dbUser"));
            config.setPassword(td.decrypt(prop.getProperty("dbPassword")));
            config.addDataSourceProperty("cachePrepStmts", "true");
            config.addDataSourceProperty("prepStmtCacheSize", "250");
            config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            ds = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DataSource() {}

    public static HikariDataSource getDataSource() throws SQLException {
        return ds;
    }

    private static Properties readPropertiesFile(String fileName) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try(InputStream resourceStream = loader.getResourceAsStream(fileName)) {
            props.load(resourceStream);
        }
        return props;
    }
}