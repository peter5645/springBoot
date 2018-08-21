package com.test.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration // 給予@ 讓他知道自己是 Configuration
@MapperScan("com.test.dao") // MapperScan自動掃描有哪些 
@PropertySource(value="classpath:mybatis.properties")
public class MybatisConfig {

//    @Bean // DataSource 基本設定
//    @ConfigurationProperties(prefix = "datasource")
//    protected DataSource oracleDataSource() throws Exception {
//    	return DataSourceBuilder.create().build();
//    }
//
//    @Bean // SqlSessionFactory設定
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        System.out.println(oracleDataSource());
//        factoryBean.setDataSource(oracleDataSource());
//        return factoryBean.getObject();
//    }
//
//    @Bean // 交易設定
//    public DataSourceTransactionManager trx(DataSource oracleDataSource) throws Exception {
//        DataSourceTransactionManager trxManager = new DataSourceTransactionManager();
//        trxManager.setDataSource(oracleDataSource);
//        return trxManager;
//    }
}
