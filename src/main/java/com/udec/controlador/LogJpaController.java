/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.controlador;

import com.udec.persistencia.Log;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udec.persistencia.Recorrido;
import com.udec.connection.jpaConnection;
import com.udec.controlador.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Oscar
 */
public class LogJpaController implements Serializable {

    public LogJpaController() {
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return jpaConnection.getEntityManager();
    }

    public void create(Log log) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recorrido recorridoidRecorrido = log.getRecorridoidRecorrido();
            if (recorridoidRecorrido != null) {
                recorridoidRecorrido = em.getReference(recorridoidRecorrido.getClass(), recorridoidRecorrido.getIdRecorrido());
                log.setRecorridoidRecorrido(recorridoidRecorrido);
            }
            em.persist(log);
            if (recorridoidRecorrido != null) {
                recorridoidRecorrido.getLogList().add(log);
                recorridoidRecorrido = em.merge(recorridoidRecorrido);
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
            Log persistentLog = em.find(Log.class, log.getIdLog());
            Recorrido recorridoidRecorridoOld = persistentLog.getRecorridoidRecorrido();
            Recorrido recorridoidRecorridoNew = log.getRecorridoidRecorrido();
            if (recorridoidRecorridoNew != null) {
                recorridoidRecorridoNew = em.getReference(recorridoidRecorridoNew.getClass(), recorridoidRecorridoNew.getIdRecorrido());
                log.setRecorridoidRecorrido(recorridoidRecorridoNew);
            }
            log = em.merge(log);
            if (recorridoidRecorridoOld != null && !recorridoidRecorridoOld.equals(recorridoidRecorridoNew)) {
                recorridoidRecorridoOld.getLogList().remove(log);
                recorridoidRecorridoOld = em.merge(recorridoidRecorridoOld);
            }
            if (recorridoidRecorridoNew != null && !recorridoidRecorridoNew.equals(recorridoidRecorridoOld)) {
                recorridoidRecorridoNew.getLogList().add(log);
                recorridoidRecorridoNew = em.merge(recorridoidRecorridoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = log.getIdLog();
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
                log.getIdLog();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The log with id " + id + " no longer exists.", enfe);
            }
            Recorrido recorridoidRecorrido = log.getRecorridoidRecorrido();
            if (recorridoidRecorrido != null) {
                recorridoidRecorrido.getLogList().remove(log);
                recorridoidRecorrido = em.merge(recorridoidRecorrido);
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
    
    public List<Log> findByRecorridoAndSensorOrderFecha(Object recorrido, Object sensor) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Log.class));
        Query q = getEntityManager().createQuery("SELECT c FROM " + Log.class.getSimpleName() + " c WHERE c.recorridoidRecorrido = :parametro1 and c.canal = :parametro2 ORDER BY c.fecha" , Log.class);
        q.setParameter("parametro1", recorrido);
        q.setParameter("parametro2", sensor);
        return q.getResultList();
    }
}
