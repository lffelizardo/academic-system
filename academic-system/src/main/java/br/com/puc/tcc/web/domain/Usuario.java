package br.com.puc.tcc.web.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedSubgraph;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.puc.tcc.web.config.Constants;
import br.com.puc.tcc.web.util.Modelos;

@Entity
@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
@EntityListeners(AuditingEntityListener.class)
@NamedEntityGraph(name = "perfil.permissoes", attributeNodes = @NamedAttributeNode(value = "perfil", subgraph = "permissoes"), subgraphs = @NamedSubgraph(name = "permissoes", attributeNodes = @NamedAttributeNode("permissoes")))
public class Usuario extends Modelos {


	/**
	 *
	 */
	private static final long serialVersionUID = -4246092316564580533L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Size(max = 50)
    @Column(name = "nome", length = 50)
	private String nome;

	@Pattern(regexp = Constants.LOGIN_REGEX)
	@Size(min = 1, max = 100)
	@Column(name = "login", length = 100, unique = true, nullable = false)
	private String login;

	@NotEmpty
	@Size(min = 1)
	private String senha;

    @Size(max = 50)
    @Column(name = "sobrenome", length = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    @Column(length = 100, unique = true)
    private String email;

    @NotNull
    @Column(name="ativado", nullable = false)
    private boolean activated = false;

	@CreatedBy
	@Column(name = "created_by", nullable = false, length = 50, updatable = false)
	@JsonIgnore
	private String createdBy;

	@LastModifiedBy
	@Column(name = "last_modified_by", length = 50)
	@JsonIgnore
	private String lastModifiedBy;

	@ManyToOne(optional = false)
	private Perfil perfil;

	public Usuario() {
	}

	public Usuario(Long id) {
		this.id = id;
	}

	public Usuario(String login) {
		this.login = login;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
