package com.socialWeb.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

/**
 * @ClassName ManagerFilter
 * @Description TODO
 * @Author 42
 * @Date 2020/3/11 下午 5:45
 * @Version 1.0
 */
@Component
public class ManagerFilter extends ZuulFilter
{
	@Autowired
	private JwtUtil jwtUtil;

	//在请求前执行pre，后执行post
	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		//执行顺序，越小越先执行
		return 0;
	}
	//是否启用过滤器
	@Override
	public boolean shouldFilter() {
		return true;
	}
	//过滤器执行的操作，return任何object值都表示继续执行
	//setSendZuulResponse(false)表示不再继续执行
	@Override
	public Object run() throws ZuulException {
		System.out.println("经过后台过滤器");
		RequestContext requestContext = RequestContext.getCurrentContext();
		HttpServletRequest request = requestContext.getRequest();

		if (request.getMethod().equals("OPTIONS")){
			return null;
		}

		if (request.getRequestURL().indexOf("login") > 0){
			return null;
		}

		String header = request.getHeader("Authorization");
		if (header != null && !"".equals(header)){
			if (header.startsWith("Bearer ")){
				String token = header.substring(7);
				try {
					Claims claims = jwtUtil.parseJWT(token);
					String roles = (String) claims.get("roles");
					if ("admin".equals(roles)){
						requestContext.addZuulRequestHeader("Authorization", header);
						return null;
					}
				}catch (Exception e){
					e.printStackTrace();
					requestContext.setSendZuulResponse(false);
				}
			}
		}
		requestContext.setSendZuulResponse(false);
		requestContext.setResponseBody("权限不足");
		requestContext.setResponseStatusCode(403);
		requestContext.getResponse().setContentType("text/html;charset=utf-8");
		return null;
	}
}
