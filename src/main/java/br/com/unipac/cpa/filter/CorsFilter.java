package br.com.unipac.cpa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorsFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		log.info("init filter - Cors");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse responseResult = (HttpServletResponse) response;
		responseResult.setHeader("Access-Control-Allow-Origin", "*");
		responseResult.setHeader("Access-Control-Allow-Credentials", "true");
		responseResult.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, HEAD");
		responseResult.setHeader("Access-Control-Max-Age", "3600");
		responseResult.setHeader("Access-Control-Allow-Headers",
				"Origin, Accept, x-auth-token, X-Requested-With, Content-Type, Referer, User-Agent, Authorization, Access-Control-Allow-Origin, Access-Control-Allow-Credentials, Access-Control-Request-Method, Access-Control-Request-Headers");
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		log.info("destroy filter - Cors");
	}

}