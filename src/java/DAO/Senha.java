/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Maike Nunes
 */
@Entity
@Table(name = "senha")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Senha.findAll", query = "SELECT s FROM Senha s"),
    @NamedQuery(name = "Senha.findByIdsenha", query = "SELECT s FROM Senha s WHERE s.idsenha = :idsenha"),
    @NamedQuery(name = "Senha.findByChave", query = "SELECT s FROM Senha s WHERE s.chave = :chave"),
    @NamedQuery(name = "Senha.findByDthrRetirada", query = "SELECT s FROM Senha s WHERE s.dthrRetirada = :dthrRetirada"),
    @NamedQuery(name = "Senha.findByDthrChamada", query = "SELECT s FROM Senha s WHERE s.dthrChamada = :dthrChamada"),
    @NamedQuery(name = "Senha.findByDthrCancelada", query = "SELECT s FROM Senha s WHERE s.dthrCancelada = :dthrCancelada")})
public class Senha implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsenha")
    private Integer idsenha;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "chave")
    private String chave;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dthr_retirada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthrRetirada;
    @Column(name = "dthr_chamada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthrChamada;
    @Column(name = "dthr_cancelada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthrCancelada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsenha")
    private Collection<Atendimento> atendimentoCollection;
    @JoinColumn(name = "idtipo_atendimento", referencedColumnName = "idtipo_atendimento")
    @ManyToOne(optional = false)
    private TipoAtendimento idtipoAtendimento;
    @JoinColumn(name = "idsetor", referencedColumnName = "idsetor")
    @ManyToOne(optional = false)
    private Setor idsetor;
    @JoinColumn(name = "idfuncionario", referencedColumnName = "idfuncionario")
    @ManyToOne
    private Funcionario idfuncionario;

    public Senha() {
    }

    public Senha(Integer idsenha) {
        this.idsenha = idsenha;
    }

    public Senha(Integer idsenha, String chave, Date dthrRetirada) {
        this.idsenha = idsenha;
        this.chave = chave;
        this.dthrRetirada = dthrRetirada;
    }

    public Integer getIdsenha() {
        return idsenha;
    }

    public void setIdsenha(Integer idsenha) {
        this.idsenha = idsenha;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public Date getDthrRetirada() {
        return dthrRetirada;
    }

    public void setDthrRetirada(Date dthrRetirada) {
        this.dthrRetirada = dthrRetirada;
    }

    public Date getDthrChamada() {
        return dthrChamada;
    }

    public void setDthrChamada(Date dthrChamada) {
        this.dthrChamada = dthrChamada;
    }

    public Date getDthrCancelada() {
        return dthrCancelada;
    }

    public void setDthrCancelada(Date dthrCancelada) {
        this.dthrCancelada = dthrCancelada;
    }

    @XmlTransient
    public Collection<Atendimento> getAtendimentoCollection() {
        return atendimentoCollection;
    }

    public void setAtendimentoCollection(Collection<Atendimento> atendimentoCollection) {
        this.atendimentoCollection = atendimentoCollection;
    }

    public TipoAtendimento getIdtipoAtendimento() {
        return idtipoAtendimento;
    }

    public void setIdtipoAtendimento(TipoAtendimento idtipoAtendimento) {
        this.idtipoAtendimento = idtipoAtendimento;
    }

    public Setor getIdsetor() {
        return idsetor;
    }

    public void setIdsetor(Setor idsetor) {
        this.idsetor = idsetor;
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
        hash += (idsenha != null ? idsenha.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Senha)) {
            return false;
        }
        Senha other = (Senha) object;
        if ((this.idsenha == null && other.idsenha != null) || (this.idsenha != null && !this.idsenha.equals(other.idsenha))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "["+idsenha+"] "+chave;
    }
    
}
