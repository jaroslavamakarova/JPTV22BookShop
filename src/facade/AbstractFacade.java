/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

/**
 *
 * @author pupil
 */
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

/**
 *
 * @author pupil
 */
public abstract class AbstractFacade<T> {
    
    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    protected abstract EntityManager getEntityManager();
    
    public void create(T entity){
        getEntityManager().getTransaction().begin();
            getEntityManager().persist(entity);
        getEntityManager().getTransaction().commit();
    }
    public void edit(T entity){
        getEntityManager().getTransaction().begin();
            getEntityManager().merge(entity);
        getEntityManager().getTransaction().commit();
    }
    public T find(Long id){
        return getEntityManager().find(entityClass, id);
    }
    public List<T> findAll(){
        //return getEntityManager().createQuery("SELECT entity FROM T entity").getResultList();
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder(); 
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass); 
        Root<T> root = criteriaQuery.from(entityClass); 
        criteriaQuery.select(root); 
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }
}