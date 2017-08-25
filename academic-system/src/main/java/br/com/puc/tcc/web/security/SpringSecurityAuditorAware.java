package br.com.puc.tcc.web.security;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import br.com.puc.tcc.web.config.Constants;

/**
 * Implementation of AuditorAware based on Spring Security.
 */
@Component(value="springSecurityAuditorAware")
public class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        String userName = SecurityUtils.getCurrentUserLogin();
        return userName != null ? userName : Constants.SYSTEM_ACCOUNT;
    }
}
