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
@Table(name = "setor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Setor.findAll", query = "SELECT s FROM Setor s"),
    @NamedQuery(name = "Setor.findByIdsetor", query = "SELECT s FROM Setor s WHERE s.idsetor = :idsetor"),
    @NamedQuery(name = "Setor.findByDescricao", query = "SELECT s FROM Setor s WHERE s.descricao = :descricao"),
    @NamedQuery(name = "Setor.findByAtivo", query = "SELECT s FROM Setor s WHERE s.ativo = :ativo")})
public class Setor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsetor")
    private Integer idsetor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "ativo")
    private String ativo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "setor")
    private Collection<FuncionarioSetor> funcionarioSetorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsetor")
    private Collection<Senha> senhaCollection;

    public Setor() {
    }

    public Setor(Integer idsetor) {
        this.idsetor = idsetor;
    }

    public Setor(Integer idsetor, String descricao, String ativo) {
        this.idsetor = idsetor;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public Integer getIdsetor() {
        return idsetor;
    }

    public void setIdsetor(Integer idsetor) {
        this.idsetor = idsetor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    @XmlTransient
    public Collection<FuncionarioSetor> getFuncionarioSetorCollection() {
        return funcionarioSetorCollection;
    }

    public void setFuncionarioSetorCollection(Collection<FuncionarioSetor> funcionarioSetorCollection) {
        this.funcionarioSetorCollection = funcionarioSetorCollection;
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
        hash += (idsetor != null ? idsetor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Setor)) {
            return false;
        }
        Setor other = (Setor) object;
        if ((this.idsetor == null && other.idsetor != null) || (this.idsetor != null && !this.idsetor.equals(other.idsetor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.getDescricao();
    }
    
}
