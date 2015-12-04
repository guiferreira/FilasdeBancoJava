/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import ejb.AutenticacaoLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Maike Nunes
 */
@ManagedBean
@RequestScoped
@SessionScoped
public class LoginBean {

    private String usuario;
    private String senha;

    @EJB
    private AutenticacaoLocal ejb;

    public LoginBean() {
    }

    public String autenticar() {
        if (ejb.Autenticar(usuario, senha)) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("username", usuario);
            return "/home";
        } else {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
            FacesMessage fm = new FacesMessage("usuario/senha inválidos!");
            FacesContext.getCurrentInstance().addMessage("msg", fm);
            return "/index";
        }
    }

    public String sair() {
        // Invalidate session of a sessionscoped managed bean
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            // Redirect to page you want after logout
            FacesContext.getCurrentInstance().getExternalContext().redirect("/TrabalhoOO3");
        } catch (IOException ex) {
        }
        return "/TrabalhoOO3";
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
