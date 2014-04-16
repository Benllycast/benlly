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
import com.udec.benlly.Conductor;
import com.udec.benlly.Vehiculo;
import com.udec.benlly.Log;
import com.udec.benlly.Recorrido;
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
public class RecorridoJpaController implements Serializable {

    public RecorridoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Recorrido recorrido) {
        if (recorrido.getLogList() == null) {
            recorrido.setLogList(new ArrayList<Log>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conductor conductoridConductor = recorrido.getConductoridConductor();
            if (conductoridConductor != null) {
                conductoridConductor = em.getReference(conductoridConductor.getClass(), conductoridConductor.getIdConductor());
                recorrido.setConductoridConductor(conductoridConductor);
            }
            Vehiculo vehiculoidVehiculo = recorrido.getVehiculoidVehiculo();
            if (vehiculoidVehiculo != null) {
                vehiculoidVehiculo = em.getReference(vehiculoidVehiculo.getClass(), vehiculoidVehiculo.getIdVehiculo());
                recorrido.setVehiculoidVehiculo(vehiculoidVehiculo);
            }
            List<Log> attachedLogList = new ArrayList<Log>();
            for (Log logListLogToAttach : recorrido.getLogList()) {
                logListLogToAttach = em.getReference(logListLogToAttach.getClass(), logListLogToAttach.getIdLog());
                attachedLogList.add(logListLogToAttach);
            }
            recorrido.setLogList(attachedLogList);
            em.persist(recorrido);
            if (conductoridConductor != null) {
                conductoridConductor.getRecorridoList().add(recorrido);
                conductoridConductor = em.merge(conductoridConductor);
            }
            if (vehiculoidVehiculo != null) {
                vehiculoidVehiculo.getRecorridoList().add(recorrido);
                vehiculoidVehiculo = em.merge(vehiculoidVehiculo);
            }
            for (Log logListLog : recorrido.getLogList()) {
                Recorrido oldRecorridoidRecorridoOfLogListLog = logListLog.getRecorridoidRecorrido();
                logListLog.setRecorridoidRecorrido(recorrido);
                logListLog = em.merge(logListLog);
                if (oldRecorridoidRecorridoOfLogListLog != null) {
                    oldRecorridoidRecorridoOfLogListLog.getLogList().remove(logListLog);
                    oldRecorridoidRecorridoOfLogListLog = em.merge(oldRecorridoidRecorridoOfLogListLog);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Recorrido recorrido) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recorrido persistentRecorrido = em.find(Recorrido.class, recorrido.getIdRecorrido());
            Conductor conductoridConductorOld = persistentRecorrido.getConductoridConductor();
            Conductor conductoridConductorNew = recorrido.getConductoridConductor();
            Vehiculo vehiculoidVehiculoOld = persistentRecorrido.getVehiculoidVehiculo();
            Vehiculo vehiculoidVehiculoNew = recorrido.getVehiculoidVehiculo();
            List<Log> logListOld = persistentRecorrido.getLogList();
            List<Log> logListNew = recorrido.getLogList();
            List<String> illegalOrphanMessages = null;
            for (Log logListOldLog : logListOld) {
                if (!logListNew.contains(logListOldLog)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Log " + logListOldLog + " since its recorridoidRecorrido field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (conductoridConductorNew != null) {
                conductoridConductorNew = em.getReference(conductoridConductorNew.getClass(), conductoridConductorNew.getIdConductor());
                recorrido.setConductoridConductor(conductoridConductorNew);
            }
            if (vehiculoidVehiculoNew != null) {
                vehiculoidVehiculoNew = em.getReference(vehiculoidVehiculoNew.getClass(), vehiculoidVehiculoNew.getIdVehiculo());
                recorrido.setVehiculoidVehiculo(vehiculoidVehiculoNew);
            }
            List<Log> attachedLogListNew = new ArrayList<Log>();
            for (Log logListNewLogToAttach : logListNew) {
                logListNewLogToAttach = em.getReference(logListNewLogToAttach.getClass(), logListNewLogToAttach.getIdLog());
                attachedLogListNew.add(logListNewLogToAttach);
            }
            logListNew = attachedLogListNew;
            recorrido.setLogList(logListNew);
            recorrido = em.merge(recorrido);
            if (conductoridConductorOld != null && !conductoridConductorOld.equals(conductoridConductorNew)) {
                conductoridConductorOld.getRecorridoList().remove(recorrido);
                conductoridConductorOld = em.merge(conductoridConductorOld);
            }
            if (conductoridConductorNew != null && !conductoridConductorNew.equals(conductoridConductorOld)) {
                conductoridConductorNew.getRecorridoList().add(recorrido);
                conductoridConductorNew = em.merge(conductoridConductorNew);
            }
            if (vehiculoidVehiculoOld != null && !vehiculoidVehiculoOld.equals(vehiculoidVehiculoNew)) {
                vehiculoidVehiculoOld.getRecorridoList().remove(recorrido);
                vehiculoidVehiculoOld = em.merge(vehiculoidVehiculoOld);
            }
            if (vehiculoidVehiculoNew != null && !vehiculoidVehiculoNew.equals(vehiculoidVehiculoOld)) {
                vehiculoidVehiculoNew.getRecorridoList().add(recorrido);
                vehiculoidVehiculoNew = em.merge(vehiculoidVehiculoNew);
            }
            for (Log logListNewLog : logListNew) {
                if (!logListOld.contains(logListNewLog)) {
                    Recorrido oldRecorridoidRecorridoOfLogListNewLog = logListNewLog.getRecorridoidRecorrido();
                    logListNewLog.setRecorridoidRecorrido(recorrido);
                    logListNewLog = em.merge(logListNewLog);
                    if (oldRecorridoidRecorridoOfLogListNewLog != null && !oldRecorridoidRecorridoOfLogListNewLog.equals(recorrido)) {
                        oldRecorridoidRecorridoOfLogListNewLog.getLogList().remove(logListNewLog);
                        oldRecorridoidRecorridoOfLogListNewLog = em.merge(oldRecorridoidRecorridoOfLogListNewLog);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = recorrido.getIdRecorrido();
                if (findRecorrido(id) == null) {
                    throw new NonexistentEntityException("The recorrido with id " + id + " no longer exists.");
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
            Recorrido recorrido;
            try {
                recorrido = em.getReference(Recorrido.class, id);
                recorrido.getIdRecorrido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recorrido with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Log> logListOrphanCheck = recorrido.getLogList();
            for (Log logListOrphanCheckLog : logListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Recorrido (" + recorrido + ") cannot be destroyed since the Log " + logListOrphanCheckLog + " in its logList field has a non-nullable recorridoidRecorrido field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Conductor conductoridConductor = recorrido.getConductoridConductor();
            if (conductoridConductor != null) {
                conductoridConductor.getRecorridoList().remove(recorrido);
                conductoridConductor = em.merge(conductoridConductor);
            }
            Vehiculo vehiculoidVehiculo = recorrido.getVehiculoidVehiculo();
            if (vehiculoidVehiculo != null) {
                vehiculoidVehiculo.getRecorridoList().remove(recorrido);
                vehiculoidVehiculo = em.merge(vehiculoidVehiculo);
            }
            em.remove(recorrido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Recorrido> findRecorridoEntities() {
        return findRecorridoEntities(true, -1, -1);
    }

    public List<Recorrido> findRecorridoEntities(int maxResults, int firstResult) {
        return findRecorridoEntities(false, maxResults, firstResult);
    }

    private List<Recorrido> findRecorridoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Recorrido.class));
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

    public Recorrido findRecorrido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Recorrido.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecorridoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Recorrido> rt = cq.from(Recorrido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
