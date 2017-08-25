package br.com.puc.tcc.web.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.puc.tcc.web.domain.Usuario;

/**
 * Spring Data JPA repository for the Usuario entity.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	@EntityGraph(value = "perfil.permissoes", type = EntityGraph.EntityGraphType.FETCH)
    Usuario findByLogin(String login);

    @EntityGraph(value = "perfil.permissoes", type = EntityGraph.EntityGraphType.FETCH)
    Usuario findByLoginAndSenha(String login, String senha);
}
