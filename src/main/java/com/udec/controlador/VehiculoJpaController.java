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
import java.util.ArrayList;
import java.util.List;
import com.udec.benlly.Recorrido;
import com.udec.benlly.Vehiculo;
import com.udec.controlador.exceptions.IllegalOrphanException;
import com.udec.controlador.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Oscar
 */
public class VehiculoJpaController implements Serializable {

    public VehiculoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vehiculo vehiculo) {
        if (vehiculo.getSensorList() == null) {
            vehiculo.setSensorList(new ArrayList<Sensor>());
        }
        if (vehiculo.getRecorridoList() == null) {
            vehiculo.setRecorridoList(new ArrayList<Recorrido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Sensor> attachedSensorList = new ArrayList<Sensor>();
            for (Sensor sensorListSensorToAttach : vehiculo.getSensorList()) {
                sensorListSensorToAttach = em.getReference(sensorListSensorToAttach.getClass(), sensorListSensorToAttach.getIdsensor());
                attachedSensorList.add(sensorListSensorToAttach);
            }
            vehiculo.setSensorList(attachedSensorList);
            List<Recorrido> attachedRecorridoList = new ArrayList<Recorrido>();
            for (Recorrido recorridoListRecorridoToAttach : vehiculo.getRecorridoList()) {
                recorridoListRecorridoToAttach = em.getReference(recorridoListRecorridoToAttach.getClass(), recorridoListRecorridoToAttach.getIdRecorrido());
                attachedRecorridoList.add(recorridoListRecorridoToAttach);
            }
            vehiculo.setRecorridoList(attachedRecorridoList);
            em.persist(vehiculo);
            for (Sensor sensorListSensor : vehiculo.getSensorList()) {
                Vehiculo oldVehiculoidVehiculoOfSensorListSensor = sensorListSensor.getVehiculoidVehiculo();
                sensorListSensor.setVehiculoidVehiculo(vehiculo);
                sensorListSensor = em.merge(sensorListSensor);
                if (oldVehiculoidVehiculoOfSensorListSensor != null) {
                    oldVehiculoidVehiculoOfSensorListSensor.getSensorList().remove(sensorListSensor);
                    oldVehiculoidVehiculoOfSensorListSensor = em.merge(oldVehiculoidVehiculoOfSensorListSensor);
                }
            }
            for (Recorrido recorridoListRecorrido : vehiculo.getRecorridoList()) {
                Vehiculo oldVehiculoidVehiculoOfRecorridoListRecorrido = recorridoListRecorrido.getVehiculoidVehiculo();
                recorridoListRecorrido.setVehiculoidVehiculo(vehiculo);
                recorridoListRecorrido = em.merge(recorridoListRecorrido);
                if (oldVehiculoidVehiculoOfRecorridoListRecorrido != null) {
                    oldVehiculoidVehiculoOfRecorridoListRecorrido.getRecorridoList().remove(recorridoListRecorrido);
                    oldVehiculoidVehiculoOfRecorridoListRecorrido = em.merge(oldVehiculoidVehiculoOfRecorridoListRecorrido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vehiculo vehiculo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehiculo persistentVehiculo = em.find(Vehiculo.class, vehiculo.getIdVehiculo());
            List<Sensor> sensorListOld = persistentVehiculo.getSensorList();
            List<Sensor> sensorListNew = vehiculo.getSensorList();
            List<Recorrido> recorridoListOld = persistentVehiculo.getRecorridoList();
            List<Recorrido> recorridoListNew = vehiculo.getRecorridoList();
            List<String> illegalOrphanMessages = null;
            for (Sensor sensorListOldSensor : sensorListOld) {
                if (!sensorListNew.contains(sensorListOldSensor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Sensor " + sensorListOldSensor + " since its vehiculoidVehiculo field is not nullable.");
                }
            }
            for (Recorrido recorridoListOldRecorrido : recorridoListOld) {
                if (!recorridoListNew.contains(recorridoListOldRecorrido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Recorrido " + recorridoListOldRecorrido + " since its vehiculoidVehiculo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Sensor> attachedSensorListNew = new ArrayList<Sensor>();
            for (Sensor sensorListNewSensorToAttach : sensorListNew) {
                sensorListNewSensorToAttach = em.getReference(sensorListNewSensorToAttach.getClass(), sensorListNewSensorToAttach.getIdsensor());
                attachedSensorListNew.add(sensorListNewSensorToAttach);
            }
            sensorListNew = attachedSensorListNew;
            vehiculo.setSensorList(sensorListNew);
            List<Recorrido> attachedRecorridoListNew = new ArrayList<Recorrido>();
            for (Recorrido recorridoListNewRecorridoToAttach : recorridoListNew) {
                recorridoListNewRecorridoToAttach = em.getReference(recorridoListNewRecorridoToAttach.getClass(), recorridoListNewRecorridoToAttach.getIdRecorrido());
                attachedRecorridoListNew.add(recorridoListNewRecorridoToAttach);
            }
            recorridoListNew = attachedRecorridoListNew;
            vehiculo.setRecorridoList(recorridoListNew);
            vehiculo = em.merge(vehiculo);
            for (Sensor sensorListNewSensor : sensorListNew) {
                if (!sensorListOld.contains(sensorListNewSensor)) {
                    Vehiculo oldVehiculoidVehiculoOfSensorListNewSensor = sensorListNewSensor.getVehiculoidVehiculo();
                    sensorListNewSensor.setVehiculoidVehiculo(vehiculo);
                    sensorListNewSensor = em.merge(sensorListNewSensor);
                    if (oldVehiculoidVehiculoOfSensorListNewSensor != null && !oldVehiculoidVehiculoOfSensorListNewSensor.equals(vehiculo)) {
                        oldVehiculoidVehiculoOfSensorListNewSensor.getSensorList().remove(sensorListNewSensor);
                        oldVehiculoidVehiculoOfSensorListNewSensor = em.merge(oldVehiculoidVehiculoOfSensorListNewSensor);
                    }
                }
            }
            for (Recorrido recorridoListNewRecorrido : recorridoListNew) {
                if (!recorridoListOld.contains(recorridoListNewRecorrido)) {
                    Vehiculo oldVehiculoidVehiculoOfRecorridoListNewRecorrido = recorridoListNewRecorrido.getVehiculoidVehiculo();
                    recorridoListNewRecorrido.setVehiculoidVehiculo(vehiculo);
                    recorridoListNewRecorrido = em.merge(recorridoListNewRecorrido);
                    if (oldVehiculoidVehiculoOfRecorridoListNewRecorrido != null && !oldVehiculoidVehiculoOfRecorridoListNewRecorrido.equals(vehiculo)) {
                        oldVehiculoidVehiculoOfRecorridoListNewRecorrido.getRecorridoList().remove(recorridoListNewRecorrido);
                        oldVehiculoidVehiculoOfRecorridoListNewRecorrido = em.merge(oldVehiculoidVehiculoOfRecorridoListNewRecorrido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vehiculo.getIdVehiculo();
                if (findVehiculo(id) == null) {
                    throw new NonexistentEntityException("The vehiculo with id " + id + " no longer exists.");
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
            Vehiculo vehiculo;
            try {
                vehiculo = em.getReference(Vehiculo.class, id);
                vehiculo.getIdVehiculo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vehiculo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Sensor> sensorListOrphanCheck = vehiculo.getSensorList();
            for (Sensor sensorListOrphanCheckSensor : sensorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vehiculo (" + vehiculo + ") cannot be destroyed since the Sensor " + sensorListOrphanCheckSensor + " in its sensorList field has a non-nullable vehiculoidVehiculo field.");
            }
            List<Recorrido> recorridoListOrphanCheck = vehiculo.getRecorridoList();
            for (Recorrido recorridoListOrphanCheckRecorrido : recorridoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vehiculo (" + vehiculo + ") cannot be destroyed since the Recorrido " + recorridoListOrphanCheckRecorrido + " in its recorridoList field has a non-nullable vehiculoidVehiculo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(vehiculo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vehiculo> findVehiculoEntities() {
        return findVehiculoEntities(true, -1, -1);
    }

    public List<Vehiculo> findVehiculoEntities(int maxResults, int firstResult) {
        return findVehiculoEntities(false, maxResults, firstResult);
    }

    private List<Vehiculo> findVehiculoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vehiculo.class));
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

    public Vehiculo findVehiculo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vehiculo.class, id);
        } finally {
            em.close();
        }
    }

    public int getVehiculoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vehiculo> rt = cq.from(Vehiculo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
