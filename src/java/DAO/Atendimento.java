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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "atendimento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atendimento.findAll", query = "SELECT a FROM Atendimento a"),
    @NamedQuery(name = "Atendimento.findByIdatendimento", query = "SELECT a FROM Atendimento a WHERE a.idatendimento = :idatendimento"),
    @NamedQuery(name = "Atendimento.findByDthrInicio", query = "SELECT a FROM Atendimento a WHERE a.dthrInicio = :dthrInicio"),
    @NamedQuery(name = "Atendimento.findByDthrFim", query = "SELECT a FROM Atendimento a WHERE a.dthrFim = :dthrFim")})
public class Atendimento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idatendimento")
    private Integer idatendimento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dthr_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthrInicio;
    @Column(name = "dthr_fim")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthrFim;
    @JoinColumn(name = "idsenha", referencedColumnName = "idsenha")
    @ManyToOne(optional = false)
    private Senha idsenha;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "idfuncionario")
    @ManyToOne(optional = false)
    private Funcionario idfuncionario;

    public Atendimento() {
    }

    public Atendimento(Integer idatendimento) {
        this.idatendimento = idatendimento;
    }

    public Atendimento(Integer idatendimento, Date dthrInicio) {
        this.idatendimento = idatendimento;
        this.dthrInicio = dthrInicio;
    }

    public Integer getIdatendimento() {
        return idatendimento;
    }

    public void setIdatendimento(Integer idatendimento) {
        this.idatendimento = idatendimento;
    }

    public Date getDthrInicio() {
        return dthrInicio;
    }

    public void setDthrInicio(Date dthrInicio) {
        this.dthrInicio = dthrInicio;
    }

    public Date getDthrFim() {
        return dthrFim;
    }

    public void setDthrFim(Date dthrFim) {
        this.dthrFim = dthrFim;
    }

    public Senha getIdsenha() {
        return idsenha;
    }

    public void setIdsenha(Senha idsenha) {
        this.idsenha = idsenha;
    }

    public Funcionario getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(Funcionario idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idatendimento != null ? idatendimento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atendimento)) {
            return false;
        }
        Atendimento other = (Atendimento) object;
        if ((this.idatendimento == null && other.idatendimento != null) || (this.idatendimento != null && !this.idatendimento.equals(other.idatendimento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Atendimento[ idatendimento=" + idatendimento + " ]";
    }
    
}
