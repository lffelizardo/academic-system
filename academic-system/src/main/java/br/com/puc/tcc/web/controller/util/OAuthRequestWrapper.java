package br.com.puc.tcc.web.controller.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class OAuthRequestWrapper extends HttpServletRequestWrapper {

	private Map<String, String> form;

	public OAuthRequestWrapper(HttpServletRequest request, Map<String, String> form) {
		super(request);
		this.form = form;
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(name);
		if (value == null) {
			value = (String)form.get(name);
		}
		return value;
	}

}
