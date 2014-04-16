/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.controlador;

import com.udec.benlly.Sensor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udec.benlly.Vehiculo;
import com.udec.controlador.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Oscar
 */
public class SensorJpaController implements Serializable {

    public SensorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sensor sensor) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehiculo vehiculoidVehiculo = sensor.getVehiculoidVehiculo();
            if (vehiculoidVehiculo != null) {
                vehiculoidVehiculo = em.getReference(vehiculoidVehiculo.getClass(), vehiculoidVehiculo.getIdVehiculo());
                sensor.setVehiculoidVehiculo(vehiculoidVehiculo);
            }
            em.persist(sensor);
            if (vehiculoidVehiculo != null) {
                vehiculoidVehiculo.getSensorList().add(sensor);
                vehiculoidVehiculo = em.merge(vehiculoidVehiculo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sensor sensor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sensor persistentSensor = em.find(Sensor.class, sensor.getIdsensor());
            Vehiculo vehiculoidVehiculoOld = persistentSensor.getVehiculoidVehiculo();
            Vehiculo vehiculoidVehiculoNew = sensor.getVehiculoidVehiculo();
            if (vehiculoidVehiculoNew != null) {
                vehiculoidVehiculoNew = em.getReference(vehiculoidVehiculoNew.getClass(), vehiculoidVehiculoNew.getIdVehiculo());
                sensor.setVehiculoidVehiculo(vehiculoidVehiculoNew);
            }
            sensor = em.merge(sensor);
            if (vehiculoidVehiculoOld != null && !vehiculoidVehiculoOld.equals(vehiculoidVehiculoNew)) {
                vehiculoidVehiculoOld.getSensorList().remove(sensor);
                vehiculoidVehiculoOld = em.merge(vehiculoidVehiculoOld);
            }
            if (vehiculoidVehiculoNew != null && !vehiculoidVehiculoNew.equals(vehiculoidVehiculoOld)) {
                vehiculoidVehiculoNew.getSensorList().add(sensor);
                vehiculoidVehiculoNew = em.merge(vehiculoidVehiculoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sensor.getIdsensor();
                if (findSensor(id) == null) {
                    throw new NonexistentEntityException("The sensor with id " + id + " no longer exists.");
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
            Sensor sensor;
            try {
                sensor = em.getReference(Sensor.class, id);
                sensor.getIdsensor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sensor with id " + id + " no longer exists.", enfe);
            }
            Vehiculo vehiculoidVehiculo = sensor.getVehiculoidVehiculo();
            if (vehiculoidVehiculo != null) {
                vehiculoidVehiculo.getSensorList().remove(sensor);
                vehiculoidVehiculo = em.merge(vehiculoidVehiculo);
            }
            em.remove(sensor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sensor> findSensorEntities() {
        return findSensorEntities(true, -1, -1);
    }

    public List<Sensor> findSensorEntities(int maxResults, int firstResult) {
        return findSensorEntities(false, maxResults, firstResult);
    }

    private List<Sensor> findSensorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sensor.class));
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

    public Sensor findSensor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sensor.class, id);
        } finally {
            em.close();
        }
    }

    public int getSensorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sensor> rt = cq.from(Sensor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
