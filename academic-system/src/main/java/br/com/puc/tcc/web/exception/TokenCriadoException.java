package br.com.puc.tcc.web.exception;

import br.com.puc.tcc.web.domain.SegurancaAPI;


public class TokenCriadoException extends ExemploOAuthException {

        private SegurancaAPI segurancaAPI;

        public TokenCriadoException(SegurancaAPI segurancaAPI) {
                super("Token ja criado para este usuario.");
                this.segurancaAPI = segurancaAPI;
        }

        public SegurancaAPI getSegurancaAPI() {
                return segurancaAPI;
        }
}
