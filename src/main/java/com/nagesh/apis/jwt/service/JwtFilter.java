package com.nagesh.apis.jwt.service;

import java.io.IOException;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.nagesh.apis.config.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtService jwtService;
	

	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("JWT Filter triggered: " + request.getRequestURI());

		String authHeader = request.getHeader("Authorization");
		String token = null;
		String userName=null;
		
		if(authHeader !=null && authHeader.startsWith("Bearer "))
		{
			token=authHeader.substring(7);
			userName=jwtService.extractUserName(token);
			
		}
		if(userName!=null && SecurityContextHolder.getContext().getAuthentication()==null)
		{
			UserDetails userDetails=applicationContext.getBean(CustomUserDetailsService.class).loadUserByUsername(userName);
			
			if(jwtService.validateToken(token,userDetails))
			{
				UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		filterChain.doFilter(request, response);
		
	}

}
