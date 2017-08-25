package br.com.puc.tcc.web.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.puc.tcc.web.domain.enums.RoleEnum;
import br.com.puc.tcc.web.util.Modelos;

@Entity
public class Perfil extends Modelos implements Serializable {


	/**
	 *
	 */
	private static final long serialVersionUID = 3871568404406872305L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

        @NotEmpty
        private String nome;

        private String descricao;

        @JsonIgnore //nao eh bom retornar as permissoes em tela...
        @ManyToMany
        private List<Permissao> permissoes;

        @JsonIgnore
        @OneToMany(mappedBy = "perfil")
        private List<Usuario> usuarios;

        public boolean contemRoleOuAdmin(RoleEnum roleConfigurada) {
                if(permissoes!=null && !permissoes.isEmpty()){
                        if (permissoes.stream().anyMatch((perm) -> (perm.getPapel().equals(roleConfigurada) || perm.isAdmin()))) {
                                return true;
                        }
                }
                return false;
        }

        @Override
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getNome() {
                return nome;
        }

        public void setNome(String nome) {
                this.nome = nome;
        }

        public String getDescricao() {
                return descricao;
        }

        public void setDescricao(String descricao) {
                this.descricao = descricao;
        }

        public List<Permissao> getPermissoes() {
                return permissoes;
        }

        public void setPermissoes(List<Permissao> permissoes) {
                this.permissoes = permissoes;
        }

        public List<Usuario> getUsuarios() {
                return usuarios;
        }

        public void setUsuarios(List<Usuario> usuarios) {
                this.usuarios = usuarios;
        }

}
