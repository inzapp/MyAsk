package com.myask;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@MapperScan("com.myask.mapper")
@SpringBootApplication
public class MyAskApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyAskApplication.class, args);
	}
	
	// set sqlSessionFactory
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		
		// import core.io
		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*Mapper.xml");
		sessionFactory.setMapperLocations(res);
		
		return sessionFactory.getObject();
	}

	// set hiddenHttpMethodFilter : import web.filter
	@Bean
	public HiddenHttpMethodFilter hiddenHttpMethodFilter(){
	    HiddenHttpMethodFilter filter = new HiddenHttpMethodFilter();
	    return filter;
	}
}
