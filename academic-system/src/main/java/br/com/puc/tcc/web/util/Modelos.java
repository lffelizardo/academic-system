package br.com.puc.tcc.web.util;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.log4j.Logger;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
@JsonAutoDetect(fieldVisibility=Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public abstract class Modelos implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -9193814354153444181L;

	protected static final Logger logger = Logger.getLogger(Modelos.class);

	@CreatedDate
	@JsonIgnore
	private Date dataCriacao = new Date();

	@LastModifiedDate
	@JsonIgnore
	private Date dataAtualizacao = new Date();

	public abstract Long getId();

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		if (obj instanceof Modelos) {
			final Modelos other = (Modelos) obj;
			return new EqualsBuilder().append(getId(), other.getId()).isEquals();
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return this.getClass().getName()+"[id=" + getId() + "]";
	}

	public boolean isSalvo() {
		return getId() != null;
	}

	public boolean isNaoSalvo() {
		return !isSalvo();
	}

        public Date getDataCriacao() {
                return dataCriacao;
        }

        public Date getDataAtualizacao() {
                return dataAtualizacao;
        }

}
