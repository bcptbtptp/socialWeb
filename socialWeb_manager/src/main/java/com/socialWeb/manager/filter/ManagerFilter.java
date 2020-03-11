package com.socialWeb.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

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
		return null;
	}
}
