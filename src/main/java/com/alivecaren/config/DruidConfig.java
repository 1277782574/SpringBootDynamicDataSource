package com.alivecaren.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alivecaren.enums.DBTypeEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource.cloud")
    @Bean("cloud")
    public DataSource getCloudDateSource(){
        return new DruidDataSource();
    }

    @ConfigurationProperties(prefix = "spring.datasource.test")
    @Bean("test")
    public DataSource getTestDateSource(){
        return new DruidDataSource();
    }

    @Bean
    @Primary
    public DataSource multipartDataSource(@Qualifier("cloud") DataSource cloudDataSource,@Qualifier("test") DataSource testDataSource){
        DynamicDataSource dynamicDataSource=new DynamicDataSource();
        Map<Object,Object> targetDataSource=new HashMap<>();
        targetDataSource.put(DBTypeEnum.CLOUD_DB.getValue(),cloudDataSource);
        targetDataSource.put(DBTypeEnum.TEST_DB.getValue(),testDataSource);
        // AbstractRoutingDataSource 类中 TargetDataSources（Map） 存放 key-datasource
        dynamicDataSource.setTargetDataSources(targetDataSource);
        return dynamicDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(multipartDataSource(getCloudDateSource(),getTestDateSource()));

//        PathMatchingResourcePatternResolver patternResolver = new  PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setMapperLocations(patternResolver.getResources("classpath:com/alivecaren/mapper/*Mapper.xml"));

        return sqlSessionFactoryBean.getObject();
    }

}
