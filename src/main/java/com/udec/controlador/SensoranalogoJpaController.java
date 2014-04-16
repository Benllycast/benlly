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
import com.udec.benlly.Sensoranalogo;
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
public class SensoranalogoJpaController implements Serializable {

    public SensoranalogoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sensoranalogo sensoranalogo) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Sensor sensorOrphanCheck = sensoranalogo.getSensor();
        if (sensorOrphanCheck != null) {
            Sensoranalogo oldSensoranalogoOfSensor = sensorOrphanCheck.getSensoranalogo();
            if (oldSensoranalogoOfSensor != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Sensor " + sensorOrphanCheck + " already has an item of type Sensoranalogo whose sensor column cannot be null. Please make another selection for the sensor field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sensor sensor = sensoranalogo.getSensor();
            if (sensor != null) {
                sensor = em.getReference(sensor.getClass(), sensor.getIdsensor());
                sensoranalogo.setSensor(sensor);
            }
            em.persist(sensoranalogo);
            if (sensor != null) {
                sensor.setSensoranalogo(sensoranalogo);
                sensor = em.merge(sensor);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSensoranalogo(sensoranalogo.getSensoridsensor()) != null) {
                throw new PreexistingEntityException("Sensoranalogo " + sensoranalogo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sensoranalogo sensoranalogo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sensoranalogo persistentSensoranalogo = em.find(Sensoranalogo.class, sensoranalogo.getSensoridsensor());
            Sensor sensorOld = persistentSensoranalogo.getSensor();
            Sensor sensorNew = sensoranalogo.getSensor();
            List<String> illegalOrphanMessages = null;
            if (sensorNew != null && !sensorNew.equals(sensorOld)) {
                Sensoranalogo oldSensoranalogoOfSensor = sensorNew.getSensoranalogo();
                if (oldSensoranalogoOfSensor != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Sensor " + sensorNew + " already has an item of type Sensoranalogo whose sensor column cannot be null. Please make another selection for the sensor field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (sensorNew != null) {
                sensorNew = em.getReference(sensorNew.getClass(), sensorNew.getIdsensor());
                sensoranalogo.setSensor(sensorNew);
            }
            sensoranalogo = em.merge(sensoranalogo);
            if (sensorOld != null && !sensorOld.equals(sensorNew)) {
                sensorOld.setSensoranalogo(null);
                sensorOld = em.merge(sensorOld);
            }
            if (sensorNew != null && !sensorNew.equals(sensorOld)) {
                sensorNew.setSensoranalogo(sensoranalogo);
                sensorNew = em.merge(sensorNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sensoranalogo.getSensoridsensor();
                if (findSensoranalogo(id) == null) {
                    throw new NonexistentEntityException("The sensoranalogo with id " + id + " no longer exists.");
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
            Sensoranalogo sensoranalogo;
            try {
                sensoranalogo = em.getReference(Sensoranalogo.class, id);
                sensoranalogo.getSensoridsensor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sensoranalogo with id " + id + " no longer exists.", enfe);
            }
            Sensor sensor = sensoranalogo.getSensor();
            if (sensor != null) {
                sensor.setSensoranalogo(null);
                sensor = em.merge(sensor);
            }
            em.remove(sensoranalogo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sensoranalogo> findSensoranalogoEntities() {
        return findSensoranalogoEntities(true, -1, -1);
    }

    public List<Sensoranalogo> findSensoranalogoEntities(int maxResults, int firstResult) {
        return findSensoranalogoEntities(false, maxResults, firstResult);
    }

    private List<Sensoranalogo> findSensoranalogoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sensoranalogo.class));
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

    public Sensoranalogo findSensoranalogo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sensoranalogo.class, id);
        } finally {
            em.close();
        }
    }

    public int getSensoranalogoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sensoranalogo> rt = cq.from(Sensoranalogo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
