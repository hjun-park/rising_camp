package com.example.demo.config.auth;

import com.example.demo.src.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//	@Override
//	public void configure(HttpSecurity httpSecurity) throws Exception {
//		// 인증
//		httpSecurity
//			.antMatcher("/**").authorizeRequests()    // 모든 요청에 대해 인증 필요
//			.antMatchers("/").permitAll() // 누구에게나 허용
//			.anyRequest().authenticated() // 그 외에는 모든 인증 필요
//			.and()
//			.oauth2Login();
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.headers().frameOptions().disable()
			.and() // 아래는 각 페이지 별 접근권한 설정
				.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/test").hasRole(Status.Used.name())
				.anyRequest().authenticated()
			.and()
				.logout()
					.logoutSuccessUrl("/")	// 로그아웃 성공 시 리다이렉트
			.and()
				.oauth2Login() // oauth2 로그인 설정 진입점
					.userInfoEndpoint()
						.userService();

	}

}
