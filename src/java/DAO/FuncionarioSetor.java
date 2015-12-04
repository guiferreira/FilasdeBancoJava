/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Maike Nunes
 */
@Entity
@Table(name = "funcionario_setor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FuncionarioSetor.findAll", query = "SELECT f FROM FuncionarioSetor f"),
    @NamedQuery(name = "FuncionarioSetor.findByIdfuncionario", query = "SELECT f FROM FuncionarioSetor f WHERE f.funcionarioSetorPK.idfuncionario = :idfuncionario"),
    @NamedQuery(name = "FuncionarioSetor.findByIdsetor", query = "SELECT f FROM FuncionarioSetor f WHERE f.funcionarioSetorPK.idsetor = :idsetor"),
    @NamedQuery(name = "FuncionarioSetor.findByDthrAlteracao", query = "SELECT f FROM FuncionarioSetor f WHERE f.dthrAlteracao = :dthrAlteracao")})
public class FuncionarioSetor implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected FuncionarioSetorPK funcionarioSetorPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dthr_alteracao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthrAlteracao;
    @JoinColumn(name = "idsetor", referencedColumnName = "idsetor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Setor setor;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "idfuncionario", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Funcionario funcionario;

    public FuncionarioSetor() {
    }

    public FuncionarioSetor(FuncionarioSetorPK funcionarioSetorPK) {
        this.funcionarioSetorPK = funcionarioSetorPK;
    }

    public FuncionarioSetor(FuncionarioSetorPK funcionarioSetorPK, Date dthrAlteracao) {
        this.funcionarioSetorPK = funcionarioSetorPK;
        this.dthrAlteracao = dthrAlteracao;
    }

    public FuncionarioSetor(int idfuncionario, int idsetor) {
        this.funcionarioSetorPK = new FuncionarioSetorPK(idfuncionario, idsetor);
    }

    public FuncionarioSetorPK getFuncionarioSetorPK() {
        return funcionarioSetorPK;
    }

    public void setFuncionarioSetorPK(FuncionarioSetorPK funcionarioSetorPK) {
        this.funcionarioSetorPK = funcionarioSetorPK;
    }

    public Date getDthrAlteracao() {
        return dthrAlteracao;
    }

    public void setDthrAlteracao(Date dthrAlteracao) {
        this.dthrAlteracao = dthrAlteracao;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (funcionarioSetorPK != null ? funcionarioSetorPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FuncionarioSetor)) {
            return false;
        }
        FuncionarioSetor other = (FuncionarioSetor) object;
        if ((this.funcionarioSetorPK == null && other.funcionarioSetorPK != null) || (this.funcionarioSetorPK != null && !this.funcionarioSetorPK.equals(other.funcionarioSetorPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.FuncionarioSetor[ funcionarioSetorPK=" + funcionarioSetorPK + " ]";
    }
    
}
