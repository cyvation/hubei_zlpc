package com.start.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 邮件数据源配置
 *
 * @caomin
 * @create 2017-12-09 19:01
 **/
@Configuration
@MapperScan(basePackages = EmailDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "emailSqlSessionFactory")
public class EmailDataSourceConfig {

    // 精确到 cluster 目录，以便跟其他数据源隔离

    static final String PACKAGE = "com.start.boot.dao.email";

    static final String MAPPER_LOCATION = "classpath:mapper/email/*.xml";

    @Value("${email.dataSource.url}")
    private String dbUrl;

    @Value("${email.dataSource.userName}")
    private String username;

    @Value("${email.dataSource.passWord}")
    private String password;


    @Bean(name = "emailDataSource")
    public DataSource clusterDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }


    @Bean(name = "emailTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager() {

        return new DataSourceTransactionManager(clusterDataSource());

    }

    @Bean(name = "emailSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("emailDataSource") DataSource clusterDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("autoRuntimeDialect", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);
        //添加插件
        sessionFactory.setPlugins(new Interceptor[]{pageHelper});

        sessionFactory.setDataSource(clusterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(EmailDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();

    }

}
