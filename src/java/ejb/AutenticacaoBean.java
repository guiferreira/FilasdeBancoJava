/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import DAO.Funcionario;

@Stateless
public class AutenticacaoBean implements AutenticacaoLocal {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("mnunesorPU");
    private EntityManager em = emf.createEntityManager();
    
    @Override
    public boolean Autenticar(String user, String pass) {
        try{
            Funcionario funcionario = (Funcionario) em.createQuery("SELECT f "
                    + " from Funcionario f WHERE f.login =:user "
                    + " AND f.senha = :pass")
                    .setParameter("user", user)
                    .setParameter("pass", pass)
                    .getSingleResult();
            
            return funcionario!=null;
        }catch(NoResultException e){
            return false;
        }
    }
    
}
