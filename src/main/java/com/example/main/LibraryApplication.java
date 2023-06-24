package com.example.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.db.CustomUserDetailsService;
import com.example.db.UserRepository;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;

@SpringBootApplication
@Configuration
@ComponentScan(basePackages = "com.example.*")
@EntityScan("com.example.model")
@EnableJpaRepositories(basePackages = "com.example.db")
@EnableWebSecurity
public class LibraryApplication {
    @Autowired  
    private UserRepository userRepository; 

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().expressionHandler(webExpressionHandler()).antMatchers("/register/**").permitAll()
						.antMatchers("/books").permitAll()
						.antMatchers("/books/search").permitAll()
						.antMatchers("/books/search/genre").permitAll()
						.antMatchers("/books/rate").hasRole("USER")
						.antMatchers("/books/add").hasRole("USER")
						.antMatchers("/books/edit/*").hasRole("ADMIN")
						.antMatchers("/books/delete/*").hasRole("ADMIN")
						.and()
				.formLogin(form -> form.loginPage("/login")
						.loginProcessingUrl("/login")
						.defaultSuccessUrl("/books")
						.permitAll())
				.logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll());
		return http.build();
	}

	@Bean  
	public SecurityExpressionHandler<FilterInvocation> webExpressionHandler() {  
	    DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();  
	    expressionHandler.setRoleHierarchy(roleHierarchy());  
	    return expressionHandler;  
	}  
	
    @Bean  
    public RoleHierarchy roleHierarchy() {  
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();  
        roleHierarchy.setHierarchy(  
                "ROLE_ADMIN > ROLE_USER"  
        );  
        return roleHierarchy;  
    }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new CustomUserDetailsService(userRepository)).passwordEncoder(passwordEncoder());
	}
}
