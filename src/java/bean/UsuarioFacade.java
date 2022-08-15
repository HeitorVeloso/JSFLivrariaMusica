/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;


import dao.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author stefanini
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    
    
    @PersistenceContext(unitName = "HackathonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public Usuario getUsuarioSenha(String login,String senha){         
        System.out.println("senha getSenha: " + senha);
        Query query = em.createNamedQuery("Usuario.findLoginSenha");        
        query.setParameter("login", login);
        query.setParameter("senha", senha);
        try{
            Usuario usuario;
            usuario = (Usuario) query.getSingleResult();
            //rollBack();
            return usuario;
        }catch(Exception ex){
            //rollBack();
            return null; 
        }        
    }

    public void rollBack(){
        if (this.em == null) {
            return;
        }
        if (this.em.getTransaction().isActive()) {
            this.em.getTransaction().rollback();
        }
    }
    
    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    
    
}
