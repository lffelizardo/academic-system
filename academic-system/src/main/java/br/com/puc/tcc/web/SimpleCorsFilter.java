package br.com.puc.tcc.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SimpleCorsFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletResponse response = (HttpServletResponse)res;
		HttpServletRequest request = (HttpServletRequest)req;
		response.setHeader("Access-Controle-Allow-Origin", "*");
		response.setHeader("Access-Controle-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		response.setHeader("Access-Controle-Max-Age", "3600");
		response.setHeader("Access-Controle-Allow-Headers", "x-requested-witch, authorization");

		if("OPTIONS".equalsIgnoreCase(request.getMethod())){
			response.setStatus(HttpServletResponse.SC_OK);
		}else{
			chain.doFilter(req, res);
		}

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
