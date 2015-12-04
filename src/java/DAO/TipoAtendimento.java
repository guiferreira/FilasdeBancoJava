/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maike Nunes
 */
@Entity
@Table(name = "tipo_atendimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoAtendimento.findAll", query = "SELECT t FROM TipoAtendimento t"),
    @NamedQuery(name = "TipoAtendimento.findByIdtipoAtendimento", query = "SELECT t FROM TipoAtendimento t WHERE t.idtipoAtendimento = :idtipoAtendimento"),
    @NamedQuery(name = "TipoAtendimento.findByDescricao", query = "SELECT t FROM TipoAtendimento t WHERE t.descricao = :descricao"),
    @NamedQuery(name = "TipoAtendimento.findByPreferencia", query = "SELECT t FROM TipoAtendimento t WHERE t.preferencia = :preferencia"),
    @NamedQuery(name = "TipoAtendimento.findByAtivo", query = "SELECT t FROM TipoAtendimento t WHERE t.ativo = :ativo")})
public class TipoAtendimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipo_atendimento")
    private Integer idtipoAtendimento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "preferencia")
    private int preferencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "ativo")
    private String ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idtipoAtendimento")
    private Collection<Senha> senhaCollection;

    public TipoAtendimento() {
    }

    public TipoAtendimento(Integer idtipoAtendimento) {
        this.idtipoAtendimento = idtipoAtendimento;
    }

    public TipoAtendimento(Integer idtipoAtendimento, String descricao, int preferencia, String ativo) {
        this.idtipoAtendimento = idtipoAtendimento;
        this.descricao = descricao;
        this.preferencia = preferencia;
        this.ativo = ativo;
    }

    public Integer getIdtipoAtendimento() {
        return idtipoAtendimento;
    }

    public void setIdtipoAtendimento(Integer idtipoAtendimento) {
        this.idtipoAtendimento = idtipoAtendimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPreferencia() {
        return preferencia;
    }

    public void setPreferencia(int preferencia) {
        this.preferencia = preferencia;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public Collection<Senha> getSenhaCollection() {
        return senhaCollection;
    }

    public void setSenhaCollection(Collection<Senha> senhaCollection) {
        this.senhaCollection = senhaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoAtendimento != null ? idtipoAtendimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoAtendimento)) {
            return false;
        }
        TipoAtendimento other = (TipoAtendimento) object;
        if ((this.idtipoAtendimento == null && other.idtipoAtendimento != null) || (this.idtipoAtendimento != null && !this.idtipoAtendimento.equals(other.idtipoAtendimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
    
}
