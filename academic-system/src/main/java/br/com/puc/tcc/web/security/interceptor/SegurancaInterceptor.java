package br.com.puc.tcc.web.security.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import br.com.puc.tcc.web.domain.SegurancaAPI;
import br.com.puc.tcc.web.domain.enums.RoleEnum;
import br.com.puc.tcc.web.security.annotation.Privado;
import br.com.puc.tcc.web.security.annotation.Publico;
import br.com.puc.tcc.web.service.SegurancaService;

public class SegurancaInterceptor extends HandlerInterceptorAdapter {

        @Autowired
        private SegurancaService segurancaService;

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                if(handler instanceof HandlerMethod){
                        HandlerMethod met = (HandlerMethod) handler;

                        //acesso publico, libera
                        Publico annotPublico = met.getMethodAnnotation(Publico.class);
                        if(annotPublico!=null){
                                return true;
                        }

                        //acesso privado, verifica as roles do usuario com a role configurada no metodo.
                        Privado annotPrivado = met.getMethodAnnotation(Privado.class);
                        if(annotPrivado!=null){
                                segurancaService.verificaValidadeTokenAdicionandoNoContexto(request);

                                RoleEnum roleConfigurada = annotPrivado.role();
                                SegurancaAPI usuarioLogado = segurancaService.getUsuarioLogado();
                                if( usuarioLogado.getUsuario().getPerfil().contemRoleOuAdmin(roleConfigurada) ){
                                        return true;
                                }
                        }

                        //se nao eh publico nem privado, nega por padrao.
                        return this.negarAcesso(response);
                }else{
                        return super.preHandle(request, response, handler);
                }
        }

        private boolean negarAcesso(HttpServletResponse response) throws IOException{
                response.getWriter().println("Sem acesso ao recurso.");
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return false;
        }

}
