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
import com.udec.benlly.Sensoranalogo;
import com.udec.benlly.Sensordigital;
import com.udec.controlador.exceptions.IllegalOrphanException;
import com.udec.controlador.exceptions.NonexistentEntityException;
import java.util.ArrayList;
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
            Sensoranalogo sensoranalogo = sensor.getSensoranalogo();
            if (sensoranalogo != null) {
                sensoranalogo = em.getReference(sensoranalogo.getClass(), sensoranalogo.getSensoridsensor());
                sensor.setSensoranalogo(sensoranalogo);
            }
            Sensordigital sensordigital = sensor.getSensordigital();
            if (sensordigital != null) {
                sensordigital = em.getReference(sensordigital.getClass(), sensordigital.getSensoridsensor());
                sensor.setSensordigital(sensordigital);
            }
            em.persist(sensor);
            if (vehiculoidVehiculo != null) {
                vehiculoidVehiculo.getSensorList().add(sensor);
                vehiculoidVehiculo = em.merge(vehiculoidVehiculo);
            }
            if (sensoranalogo != null) {
                Sensor oldSensorOfSensoranalogo = sensoranalogo.getSensor();
                if (oldSensorOfSensoranalogo != null) {
                    oldSensorOfSensoranalogo.setSensoranalogo(null);
                    oldSensorOfSensoranalogo = em.merge(oldSensorOfSensoranalogo);
                }
                sensoranalogo.setSensor(sensor);
                sensoranalogo = em.merge(sensoranalogo);
            }
            if (sensordigital != null) {
                Sensor oldSensorOfSensordigital = sensordigital.getSensor();
                if (oldSensorOfSensordigital != null) {
                    oldSensorOfSensordigital.setSensordigital(null);
                    oldSensorOfSensordigital = em.merge(oldSensorOfSensordigital);
                }
                sensordigital.setSensor(sensor);
                sensordigital = em.merge(sensordigital);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sensor sensor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sensor persistentSensor = em.find(Sensor.class, sensor.getIdsensor());
            Vehiculo vehiculoidVehiculoOld = persistentSensor.getVehiculoidVehiculo();
            Vehiculo vehiculoidVehiculoNew = sensor.getVehiculoidVehiculo();
            Sensoranalogo sensoranalogoOld = persistentSensor.getSensoranalogo();
            Sensoranalogo sensoranalogoNew = sensor.getSensoranalogo();
            Sensordigital sensordigitalOld = persistentSensor.getSensordigital();
            Sensordigital sensordigitalNew = sensor.getSensordigital();
            List<String> illegalOrphanMessages = null;
            if (sensoranalogoOld != null && !sensoranalogoOld.equals(sensoranalogoNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Sensoranalogo " + sensoranalogoOld + " since its sensor field is not nullable.");
            }
            if (sensordigitalOld != null && !sensordigitalOld.equals(sensordigitalNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Sensordigital " + sensordigitalOld + " since its sensor field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (vehiculoidVehiculoNew != null) {
                vehiculoidVehiculoNew = em.getReference(vehiculoidVehiculoNew.getClass(), vehiculoidVehiculoNew.getIdVehiculo());
                sensor.setVehiculoidVehiculo(vehiculoidVehiculoNew);
            }
            if (sensoranalogoNew != null) {
                sensoranalogoNew = em.getReference(sensoranalogoNew.getClass(), sensoranalogoNew.getSensoridsensor());
                sensor.setSensoranalogo(sensoranalogoNew);
            }
            if (sensordigitalNew != null) {
                sensordigitalNew = em.getReference(sensordigitalNew.getClass(), sensordigitalNew.getSensoridsensor());
                sensor.setSensordigital(sensordigitalNew);
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
            if (sensoranalogoNew != null && !sensoranalogoNew.equals(sensoranalogoOld)) {
                Sensor oldSensorOfSensoranalogo = sensoranalogoNew.getSensor();
                if (oldSensorOfSensoranalogo != null) {
                    oldSensorOfSensoranalogo.setSensoranalogo(null);
                    oldSensorOfSensoranalogo = em.merge(oldSensorOfSensoranalogo);
                }
                sensoranalogoNew.setSensor(sensor);
                sensoranalogoNew = em.merge(sensoranalogoNew);
            }
            if (sensordigitalNew != null && !sensordigitalNew.equals(sensordigitalOld)) {
                Sensor oldSensorOfSensordigital = sensordigitalNew.getSensor();
                if (oldSensorOfSensordigital != null) {
                    oldSensorOfSensordigital.setSensordigital(null);
                    oldSensorOfSensordigital = em.merge(oldSensorOfSensordigital);
                }
                sensordigitalNew.setSensor(sensor);
                sensordigitalNew = em.merge(sensordigitalNew);
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            Sensoranalogo sensoranalogoOrphanCheck = sensor.getSensoranalogo();
            if (sensoranalogoOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sensor (" + sensor + ") cannot be destroyed since the Sensoranalogo " + sensoranalogoOrphanCheck + " in its sensoranalogo field has a non-nullable sensor field.");
            }
            Sensordigital sensordigitalOrphanCheck = sensor.getSensordigital();
            if (sensordigitalOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sensor (" + sensor + ") cannot be destroyed since the Sensordigital " + sensordigitalOrphanCheck + " in its sensordigital field has a non-nullable sensor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
