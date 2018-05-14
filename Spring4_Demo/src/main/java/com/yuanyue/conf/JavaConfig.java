package com.yuanyue.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.yuanyue.component")
@EnableAspectJAutoProxy
public class JavaConfig {

	
}
