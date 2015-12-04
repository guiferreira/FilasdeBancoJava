/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Maike Nunes
 */
@Embeddable
public class FuncionarioSetorPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idfuncionario")
    private int idfuncionario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsetor")
    private int idsetor;

    public FuncionarioSetorPK() {
    }

    public FuncionarioSetorPK(int idfuncionario, int idsetor) {
        this.idfuncionario = idfuncionario;
        this.idsetor = idsetor;
    }

    public int getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(int idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public int getIdsetor() {
        return idsetor;
    }

    public void setIdsetor(int idsetor) {
        this.idsetor = idsetor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idfuncionario;
        hash += (int) idsetor;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FuncionarioSetorPK)) {
            return false;
        }
        FuncionarioSetorPK other = (FuncionarioSetorPK) object;
        if (this.idfuncionario != other.idfuncionario) {
            return false;
        }
        if (this.idsetor != other.idsetor) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.FuncionarioSetorPK[ idfuncionario=" + idfuncionario + ", idsetor=" + idsetor + " ]";
    }
    
}
