/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.Playlist;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author stefanini
 */
@Stateless
public class PlaylistFacade extends AbstractFacade<Playlist> {

    @PersistenceContext(unitName = "HackathonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PlaylistFacade() {
        super(Playlist.class);
    }
    
}