package br.com.puc.tcc.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.puc.tcc.web.controller.util.ExemploOAuthController;
import br.com.puc.tcc.web.domain.SegurancaAPI;
import br.com.puc.tcc.web.domain.enums.RoleEnum;
import br.com.puc.tcc.web.security.annotation.Privado;
import br.com.puc.tcc.web.security.annotation.Publico;
import br.com.puc.tcc.web.service.SegurancaService;

@Controller
@CrossOrigin
@RequestMapping(value = "/seguranca")
public class SegurancaController extends ExemploOAuthController {

	@Autowired
	private SegurancaService segurancaServico;

	@Publico
	@ResponseBody
	@RequestMapping(value = "/logar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String logar(@Context HttpServletRequest request, Map<String, String> form) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", (String) request.getParameter("username"));
		params.put("password", (String) request.getParameter("password"));
		params.put("grant_type","authorization_code");
        params.put("credentials","true");
        params.put("scope","write");
        params.put("code","0");
        params.put("client_id","exemploaplicativocliente");
        params.put("client_secret","client_secret");
        params.put("redirect_uri","http://localhost:8080");


		OAuthResponse response = segurancaServico.logarOAuth(request, params);
		// response.setBody(segurancaServico.getUsuarioLogado());
		return response.getBody();
	}

	@Privado(role = RoleEnum.ROLE_GERAL)
	@ResponseBody
	@RequestMapping(value = "/usuario/logado", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public SegurancaAPI retornarUsuarioLogado(HttpServletRequest request) {
		return segurancaServico.getUsuarioLogado();
	}
}
