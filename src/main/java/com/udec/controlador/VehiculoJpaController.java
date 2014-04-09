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
import com.udec.benlly.Recorridoorig;
import com.udec.benlly.Vehiculo;
import com.udec.controlador.exceptions.IllegalOrphanException;
import com.udec.controlador.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ususario
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
        if (vehiculo.getRecorridoorigList() == null) {
            vehiculo.setRecorridoorigList(new ArrayList<Recorridoorig>());
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
            List<Recorridoorig> attachedRecorridoorigList = new ArrayList<Recorridoorig>();
            for (Recorridoorig recorridoorigListRecorridoorigToAttach : vehiculo.getRecorridoorigList()) {
                recorridoorigListRecorridoorigToAttach = em.getReference(recorridoorigListRecorridoorigToAttach.getClass(), recorridoorigListRecorridoorigToAttach.getIdrecorridoorig());
                attachedRecorridoorigList.add(recorridoorigListRecorridoorigToAttach);
            }
            vehiculo.setRecorridoorigList(attachedRecorridoorigList);
            em.persist(vehiculo);
            for (Sensor sensorListSensor : vehiculo.getSensorList()) {
                Vehiculo oldVehiculoIdvehiculoOfSensorListSensor = sensorListSensor.getVehiculoIdvehiculo();
                sensorListSensor.setVehiculoIdvehiculo(vehiculo);
                sensorListSensor = em.merge(sensorListSensor);
                if (oldVehiculoIdvehiculoOfSensorListSensor != null) {
                    oldVehiculoIdvehiculoOfSensorListSensor.getSensorList().remove(sensorListSensor);
                    oldVehiculoIdvehiculoOfSensorListSensor = em.merge(oldVehiculoIdvehiculoOfSensorListSensor);
                }
            }
            for (Recorridoorig recorridoorigListRecorridoorig : vehiculo.getRecorridoorigList()) {
                Vehiculo oldVehiculoIdvehiculoOfRecorridoorigListRecorridoorig = recorridoorigListRecorridoorig.getVehiculoIdvehiculo();
                recorridoorigListRecorridoorig.setVehiculoIdvehiculo(vehiculo);
                recorridoorigListRecorridoorig = em.merge(recorridoorigListRecorridoorig);
                if (oldVehiculoIdvehiculoOfRecorridoorigListRecorridoorig != null) {
                    oldVehiculoIdvehiculoOfRecorridoorigListRecorridoorig.getRecorridoorigList().remove(recorridoorigListRecorridoorig);
                    oldVehiculoIdvehiculoOfRecorridoorigListRecorridoorig = em.merge(oldVehiculoIdvehiculoOfRecorridoorigListRecorridoorig);
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
            Vehiculo persistentVehiculo = em.find(Vehiculo.class, vehiculo.getIdvehiculo());
            List<Sensor> sensorListOld = persistentVehiculo.getSensorList();
            List<Sensor> sensorListNew = vehiculo.getSensorList();
            List<Recorridoorig> recorridoorigListOld = persistentVehiculo.getRecorridoorigList();
            List<Recorridoorig> recorridoorigListNew = vehiculo.getRecorridoorigList();
            List<String> illegalOrphanMessages = null;
            for (Sensor sensorListOldSensor : sensorListOld) {
                if (!sensorListNew.contains(sensorListOldSensor)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Sensor " + sensorListOldSensor + " since its vehiculoIdvehiculo field is not nullable.");
                }
            }
            for (Recorridoorig recorridoorigListOldRecorridoorig : recorridoorigListOld) {
                if (!recorridoorigListNew.contains(recorridoorigListOldRecorridoorig)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Recorridoorig " + recorridoorigListOldRecorridoorig + " since its vehiculoIdvehiculo field is not nullable.");
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
            List<Recorridoorig> attachedRecorridoorigListNew = new ArrayList<Recorridoorig>();
            for (Recorridoorig recorridoorigListNewRecorridoorigToAttach : recorridoorigListNew) {
                recorridoorigListNewRecorridoorigToAttach = em.getReference(recorridoorigListNewRecorridoorigToAttach.getClass(), recorridoorigListNewRecorridoorigToAttach.getIdrecorridoorig());
                attachedRecorridoorigListNew.add(recorridoorigListNewRecorridoorigToAttach);
            }
            recorridoorigListNew = attachedRecorridoorigListNew;
            vehiculo.setRecorridoorigList(recorridoorigListNew);
            vehiculo = em.merge(vehiculo);
            for (Sensor sensorListNewSensor : sensorListNew) {
                if (!sensorListOld.contains(sensorListNewSensor)) {
                    Vehiculo oldVehiculoIdvehiculoOfSensorListNewSensor = sensorListNewSensor.getVehiculoIdvehiculo();
                    sensorListNewSensor.setVehiculoIdvehiculo(vehiculo);
                    sensorListNewSensor = em.merge(sensorListNewSensor);
                    if (oldVehiculoIdvehiculoOfSensorListNewSensor != null && !oldVehiculoIdvehiculoOfSensorListNewSensor.equals(vehiculo)) {
                        oldVehiculoIdvehiculoOfSensorListNewSensor.getSensorList().remove(sensorListNewSensor);
                        oldVehiculoIdvehiculoOfSensorListNewSensor = em.merge(oldVehiculoIdvehiculoOfSensorListNewSensor);
                    }
                }
            }
            for (Recorridoorig recorridoorigListNewRecorridoorig : recorridoorigListNew) {
                if (!recorridoorigListOld.contains(recorridoorigListNewRecorridoorig)) {
                    Vehiculo oldVehiculoIdvehiculoOfRecorridoorigListNewRecorridoorig = recorridoorigListNewRecorridoorig.getVehiculoIdvehiculo();
                    recorridoorigListNewRecorridoorig.setVehiculoIdvehiculo(vehiculo);
                    recorridoorigListNewRecorridoorig = em.merge(recorridoorigListNewRecorridoorig);
                    if (oldVehiculoIdvehiculoOfRecorridoorigListNewRecorridoorig != null && !oldVehiculoIdvehiculoOfRecorridoorigListNewRecorridoorig.equals(vehiculo)) {
                        oldVehiculoIdvehiculoOfRecorridoorigListNewRecorridoorig.getRecorridoorigList().remove(recorridoorigListNewRecorridoorig);
                        oldVehiculoIdvehiculoOfRecorridoorigListNewRecorridoorig = em.merge(oldVehiculoIdvehiculoOfRecorridoorigListNewRecorridoorig);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vehiculo.getIdvehiculo();
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
                vehiculo.getIdvehiculo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vehiculo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Sensor> sensorListOrphanCheck = vehiculo.getSensorList();
            for (Sensor sensorListOrphanCheckSensor : sensorListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vehiculo (" + vehiculo + ") cannot be destroyed since the Sensor " + sensorListOrphanCheckSensor + " in its sensorList field has a non-nullable vehiculoIdvehiculo field.");
            }
            List<Recorridoorig> recorridoorigListOrphanCheck = vehiculo.getRecorridoorigList();
            for (Recorridoorig recorridoorigListOrphanCheckRecorridoorig : recorridoorigListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vehiculo (" + vehiculo + ") cannot be destroyed since the Recorridoorig " + recorridoorigListOrphanCheckRecorridoorig + " in its recorridoorigList field has a non-nullable vehiculoIdvehiculo field.");
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
