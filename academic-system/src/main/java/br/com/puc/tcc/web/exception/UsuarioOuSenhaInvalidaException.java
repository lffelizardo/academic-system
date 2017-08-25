package br.com.puc.tcc.web.exception;

public class UsuarioOuSenhaInvalidaException extends ExemploOAuthException {

        public UsuarioOuSenhaInvalidaException(String message) {
                super(message);
        }

        public UsuarioOuSenhaInvalidaException(Throwable cause) {
                super(cause);
        }
        
}
