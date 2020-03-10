package com.socialWeb.user.interceptor;

import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

/**
 * @ClassName JwtInterceptor
 * @Description TODO
 * @Author 42
 * @Date 2020/3/10 上午 11:00
 * @Version 1.0
 */
@Component
public class JwtInterceptor implements HandlerInterceptor
{
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("经过拦截器");
		String authHeader = request.getHeader("Authorization");
		if(authHeader != null && !"".equals(authHeader)){
			if(authHeader.startsWith("Bearer ")){
				String token=authHeader.substring(7);
				try {
					Claims claims = jwtUtil.parseJWT(token);
					if (claims != null && !"".equals(claims)){
						String roles = (String) claims.get("roles");
						if (roles != null && "admin".equals(roles)){
							request.setAttribute("claims_admin", token);
						}
						if (roles != null && "user".equals(roles)){
							request.setAttribute("claims_user", token);
						}
					}
				}catch (Exception e){
					throw new RuntimeException("权限不足");
				}
			}
		}
		return true;
	}
}
