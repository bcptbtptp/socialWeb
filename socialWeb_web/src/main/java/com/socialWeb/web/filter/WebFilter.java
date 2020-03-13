package com.socialWeb.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName WebFilter
 * @Description TODO
 * @Author 42
 * @Date 2020/3/12 上午 9:16
 * @Version 1.0
 */
public class WebFilter extends ZuulFilter
{

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		//得到request上下文，解决头信息转发丢失问题
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();
		String header = request.getHeader("Authorization");
		if (header != null && !"".equals(header)){
			currentContext.addZuulRequestHeader("Authorization", header);
		}
		return null;
	}
}
