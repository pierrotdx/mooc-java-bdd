package com.mycompany.tennis.core;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;

public class DataSourceProvider {
    private static BasicDataSource singleDataSource;

    public static DataSource getSingleDataSourceInstance() {
        if (singleDataSource == null) {
            singleDataSource = new BasicDataSource();
            int nbPools = 5;
            singleDataSource.setInitialSize(nbPools);
            singleDataSource.setUrl("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris&allowPublicKeyRetrieval=true");
            singleDataSource.setUsername("COURSDB");
            singleDataSource.setPassword("coursdb");
        }
        return singleDataSource;
    }
}
