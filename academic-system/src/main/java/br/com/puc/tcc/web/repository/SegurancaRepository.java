package br.com.puc.tcc.web.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.puc.tcc.web.domain.SegurancaAPI;
import br.com.puc.tcc.web.domain.Usuario;

public interface SegurancaRepository extends CrudRepository<SegurancaAPI, Long> {

        @Query("SELECT s FROM SegurancaAPI s WHERE s.token = :token")
        SegurancaAPI findByToken(@Param("token") String token);

        SegurancaAPI findByUsuario(Usuario usuario);

}
