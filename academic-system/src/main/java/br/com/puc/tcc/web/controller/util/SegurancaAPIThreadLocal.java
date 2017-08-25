package br.com.puc.tcc.web.controller.util;

import br.com.puc.tcc.web.domain.SegurancaAPI;

public class SegurancaAPIThreadLocal {

	private static final ThreadLocal<SegurancaAPI> threadLocal = new ThreadLocal<>();

	public static void setSegurancaAPI(SegurancaAPI segurancaAPI) {
		threadLocal.remove();
		threadLocal.set(segurancaAPI);
	}

	public static SegurancaAPI getSegurancaAPI() {
		return threadLocal.get();
	}
}
