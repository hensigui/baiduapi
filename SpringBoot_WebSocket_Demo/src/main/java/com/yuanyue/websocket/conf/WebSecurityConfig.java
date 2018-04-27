package com.yuanyue.websocket.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	/**
	 * 再内存中分别配置两个用户
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("wyf").password("wyf").roles("USER")
			.and()
			.withUser("wisely").password("wisely").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
			.antMatchers("/","/login").permitAll()//对/和login路径不拦截
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login")//设置登陆路径为/login
			.defaultSuccessUrl("/chat")//登陆后转向/chat
			.permitAll()
			.and()
			.logout()
			.permitAll();
	}
	/**
	 * 设置静态资源不拦截
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		// TODO Auto-generated method stub
		web.ignoring().antMatchers("/resources/static/**");
	}

	
}
