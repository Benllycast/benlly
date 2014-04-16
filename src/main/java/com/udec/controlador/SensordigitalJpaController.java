/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.controlador;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udec.benlly.Sensor;
import com.udec.benlly.Sensordigital;
import com.udec.controlador.exceptions.IllegalOrphanException;
import com.udec.controlador.exceptions.NonexistentEntityException;
import com.udec.controlador.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Oscar
 */
public class SensordigitalJpaController implements Serializable {

    public SensordigitalJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sensordigital sensordigital) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Sensor sensorOrphanCheck = sensordigital.getSensor();
        if (sensorOrphanCheck != null) {
            Sensordigital oldSensordigitalOfSensor = sensorOrphanCheck.getSensordigital();
            if (oldSensordigitalOfSensor != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Sensor " + sensorOrphanCheck + " already has an item of type Sensordigital whose sensor column cannot be null. Please make another selection for the sensor field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sensor sensor = sensordigital.getSensor();
            if (sensor != null) {
                sensor = em.getReference(sensor.getClass(), sensor.getIdsensor());
                sensordigital.setSensor(sensor);
            }
            em.persist(sensordigital);
            if (sensor != null) {
                sensor.setSensordigital(sensordigital);
                sensor = em.merge(sensor);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSensordigital(sensordigital.getSensoridsensor()) != null) {
                throw new PreexistingEntityException("Sensordigital " + sensordigital + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sensordigital sensordigital) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sensordigital persistentSensordigital = em.find(Sensordigital.class, sensordigital.getSensoridsensor());
            Sensor sensorOld = persistentSensordigital.getSensor();
            Sensor sensorNew = sensordigital.getSensor();
            List<String> illegalOrphanMessages = null;
            if (sensorNew != null && !sensorNew.equals(sensorOld)) {
                Sensordigital oldSensordigitalOfSensor = sensorNew.getSensordigital();
                if (oldSensordigitalOfSensor != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Sensor " + sensorNew + " already has an item of type Sensordigital whose sensor column cannot be null. Please make another selection for the sensor field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (sensorNew != null) {
                sensorNew = em.getReference(sensorNew.getClass(), sensorNew.getIdsensor());
                sensordigital.setSensor(sensorNew);
            }
            sensordigital = em.merge(sensordigital);
            if (sensorOld != null && !sensorOld.equals(sensorNew)) {
                sensorOld.setSensordigital(null);
                sensorOld = em.merge(sensorOld);
            }
            if (sensorNew != null && !sensorNew.equals(sensorOld)) {
                sensorNew.setSensordigital(sensordigital);
                sensorNew = em.merge(sensorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sensordigital.getSensoridsensor();
                if (findSensordigital(id) == null) {
                    throw new NonexistentEntityException("The sensordigital with id " + id + " no longer exists.");
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
            Sensordigital sensordigital;
            try {
                sensordigital = em.getReference(Sensordigital.class, id);
                sensordigital.getSensoridsensor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sensordigital with id " + id + " no longer exists.", enfe);
            }
            Sensor sensor = sensordigital.getSensor();
            if (sensor != null) {
                sensor.setSensordigital(null);
                sensor = em.merge(sensor);
            }
            em.remove(sensordigital);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sensordigital> findSensordigitalEntities() {
        return findSensordigitalEntities(true, -1, -1);
    }

    public List<Sensordigital> findSensordigitalEntities(int maxResults, int firstResult) {
        return findSensordigitalEntities(false, maxResults, firstResult);
    }

    private List<Sensordigital> findSensordigitalEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sensordigital.class));
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

    public Sensordigital findSensordigital(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sensordigital.class, id);
        } finally {
            em.close();
        }
    }

    public int getSensordigitalCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sensordigital> rt = cq.from(Sensordigital.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
