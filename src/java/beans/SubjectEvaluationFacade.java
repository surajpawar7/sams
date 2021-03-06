/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Subject;
import entities.SubjectEvaluation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Enterprise JavaBean for subjectevaluation entity
 * @author piit
 */
@Stateless
public class SubjectEvaluationFacade extends AbstractFacade<SubjectEvaluation> {
    @PersistenceContext(unitName = "SamJPAPU")
    private EntityManager em;

    /**
     * gets entity manager for subjectevaluation EJB
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * creates subjectevaluation EJB
     */
    public SubjectEvaluationFacade() {
        super(SubjectEvaluation.class);
    }

    /**
     * gets the evaluation scheme for the specified subject
     * @param sub
     * @return
     */
    public List<SubjectEvaluation> getByIdSubject(Subject sub) {
        Query q = em.createNamedQuery("SubjectEvaluation.findByIdSubject");
        q.setParameter("idSubject", sub);
        return q.getResultList();
    }
    
}
