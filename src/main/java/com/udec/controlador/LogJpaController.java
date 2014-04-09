/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.controlador;

import com.udec.benlly.Log;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udec.benlly.Recorridoorig;
import com.udec.controlador.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ususario
 */
public class LogJpaController implements Serializable {

    public LogJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Log log) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recorridoorig recorridoorigIdrecorridoorig = log.getRecorridoorigIdrecorridoorig();
            if (recorridoorigIdrecorridoorig != null) {
                recorridoorigIdrecorridoorig = em.getReference(recorridoorigIdrecorridoorig.getClass(), recorridoorigIdrecorridoorig.getIdrecorridoorig());
                log.setRecorridoorigIdrecorridoorig(recorridoorigIdrecorridoorig);
            }
            em.persist(log);
            if (recorridoorigIdrecorridoorig != null) {
                recorridoorigIdrecorridoorig.getLogList().add(log);
                recorridoorigIdrecorridoorig = em.merge(recorridoorigIdrecorridoorig);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Log log) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Log persistentLog = em.find(Log.class, log.getIdlog());
            Recorridoorig recorridoorigIdrecorridoorigOld = persistentLog.getRecorridoorigIdrecorridoorig();
            Recorridoorig recorridoorigIdrecorridoorigNew = log.getRecorridoorigIdrecorridoorig();
            if (recorridoorigIdrecorridoorigNew != null) {
                recorridoorigIdrecorridoorigNew = em.getReference(recorridoorigIdrecorridoorigNew.getClass(), recorridoorigIdrecorridoorigNew.getIdrecorridoorig());
                log.setRecorridoorigIdrecorridoorig(recorridoorigIdrecorridoorigNew);
            }
            log = em.merge(log);
            if (recorridoorigIdrecorridoorigOld != null && !recorridoorigIdrecorridoorigOld.equals(recorridoorigIdrecorridoorigNew)) {
                recorridoorigIdrecorridoorigOld.getLogList().remove(log);
                recorridoorigIdrecorridoorigOld = em.merge(recorridoorigIdrecorridoorigOld);
            }
            if (recorridoorigIdrecorridoorigNew != null && !recorridoorigIdrecorridoorigNew.equals(recorridoorigIdrecorridoorigOld)) {
                recorridoorigIdrecorridoorigNew.getLogList().add(log);
                recorridoorigIdrecorridoorigNew = em.merge(recorridoorigIdrecorridoorigNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = log.getIdlog();
                if (findLog(id) == null) {
                    throw new NonexistentEntityException("The log with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Log log;
            try {
                log = em.getReference(Log.class, id);
                log.getIdlog();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The log with id " + id + " no longer exists.", enfe);
            }
            Recorridoorig recorridoorigIdrecorridoorig = log.getRecorridoorigIdrecorridoorig();
            if (recorridoorigIdrecorridoorig != null) {
                recorridoorigIdrecorridoorig.getLogList().remove(log);
                recorridoorigIdrecorridoorig = em.merge(recorridoorigIdrecorridoorig);
            }
            em.remove(log);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Log> findLogEntities() {
        return findLogEntities(true, -1, -1);
    }

    public List<Log> findLogEntities(int maxResults, int firstResult) {
        return findLogEntities(false, maxResults, firstResult);
    }

    private List<Log> findLogEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Log.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Log findLog(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Log.class, id);
        } finally {
            em.close();
        }
    }

    public int getLogCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Log> rt = cq.from(Log.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
