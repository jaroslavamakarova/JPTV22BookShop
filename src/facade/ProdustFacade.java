/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entity.Product;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author pupil
 */
public class ProdustFacade extends AbstractFacade<Product>{
    EntityManager em;

    public ProdustFacade() {
        super(Product.class);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPTV22LibraryPU");
        this.em= emf.createEntityManager();
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
}
