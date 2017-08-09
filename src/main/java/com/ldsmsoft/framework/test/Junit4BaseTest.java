package com.hnepsoft.framework.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;

@RunWith(JUnit4ClassRunner.class)
/*
@ContextConfiguration(locations = { "classpath:/configs/spring-init.xml",
		"classpath:/configs/spring-orm-*.xml", "classpath:/configs/spring-datasource-*.xml"})
*/
@ContextHierarchy({
    @ContextConfiguration(name = "parent",locations = { "classpath:/configs/spring-init.xml",
		"classpath:/configs/spring-orm-*.xml", "classpath:/configs/spring-datasource-*.xml"}),
    @ContextConfiguration(name = "child", locations = "classpath:/configs/spring-mvc.xml")  
    }) 
public class Junit4BaseTest {

}
