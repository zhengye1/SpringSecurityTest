package com.vincent.configuration;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.vincent.configuration" })
@MapperScan(basePackages="com.vincent.mapper")
@PropertySource(value = { "classpath:application.properties" })
public class DatabaseConfig implements EnvironmentAware{
	@Autowired
	private Environment environment;

    protected final Log logger = LogFactory.getLog(this.getClass());
	
    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    
    @Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		return dataSource;
	}

	@Bean
	public DataSourceTransactionManager transactionManager(){
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setTypeAliasesPackage("com.vincent.model");
		return (SqlSessionFactory)factoryBean.getObject();
	}

	@Bean
	public MapperScannerConfigurer mapperScan(){
		MapperScannerConfigurer sc = new MapperScannerConfigurer();
		sc.setSqlSessionFactoryBeanName("sqlSessionFactory");
		sc.setBasePackage("com.vincent.mapper");
		return sc;
	}
	@Bean
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}
