/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.Image;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author stefanini
 */
@Stateless
public class ImageFacade extends AbstractFacade<Image> {

    @PersistenceContext(unitName = "HackathonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImageFacade() {
        super(Image.class);
    }
    
    @Override
    public List<Image> findAll(){
        
        return null;
    }
    
}
