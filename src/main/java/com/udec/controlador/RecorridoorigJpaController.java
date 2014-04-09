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
import com.udec.benlly.Recorridoorig;
import com.udec.controlador.exceptions.IllegalOrphanException;
import com.udec.controlador.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ususario
 */
public class RecorridoorigJpaController implements Serializable {

    public RecorridoorigJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Recorridoorig recorridoorig) {
        if (recorridoorig.getLogList() == null) {
            recorridoorig.setLogList(new ArrayList<Log>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conductor conductoridConductor = recorridoorig.getConductoridConductor();
            if (conductoridConductor != null) {
                conductoridConductor = em.getReference(conductoridConductor.getClass(), conductoridConductor.getIdConductor());
                recorridoorig.setConductoridConductor(conductoridConductor);
            }
            Vehiculo vehiculoIdvehiculo = recorridoorig.getVehiculoIdvehiculo();
            if (vehiculoIdvehiculo != null) {
                vehiculoIdvehiculo = em.getReference(vehiculoIdvehiculo.getClass(), vehiculoIdvehiculo.getIdvehiculo());
                recorridoorig.setVehiculoIdvehiculo(vehiculoIdvehiculo);
            }
            List<Log> attachedLogList = new ArrayList<Log>();
            for (Log logListLogToAttach : recorridoorig.getLogList()) {
                logListLogToAttach = em.getReference(logListLogToAttach.getClass(), logListLogToAttach.getIdlog());
                attachedLogList.add(logListLogToAttach);
            }
            recorridoorig.setLogList(attachedLogList);
            em.persist(recorridoorig);
            if (conductoridConductor != null) {
                conductoridConductor.getRecorridoorigList().add(recorridoorig);
                conductoridConductor = em.merge(conductoridConductor);
            }
            if (vehiculoIdvehiculo != null) {
                vehiculoIdvehiculo.getRecorridoorigList().add(recorridoorig);
                vehiculoIdvehiculo = em.merge(vehiculoIdvehiculo);
            }
            for (Log logListLog : recorridoorig.getLogList()) {
                Recorridoorig oldRecorridoorigIdrecorridoorigOfLogListLog = logListLog.getRecorridoorigIdrecorridoorig();
                logListLog.setRecorridoorigIdrecorridoorig(recorridoorig);
                logListLog = em.merge(logListLog);
                if (oldRecorridoorigIdrecorridoorigOfLogListLog != null) {
                    oldRecorridoorigIdrecorridoorigOfLogListLog.getLogList().remove(logListLog);
                    oldRecorridoorigIdrecorridoorigOfLogListLog = em.merge(oldRecorridoorigIdrecorridoorigOfLogListLog);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Recorridoorig recorridoorig) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Recorridoorig persistentRecorridoorig = em.find(Recorridoorig.class, recorridoorig.getIdrecorridoorig());
            Conductor conductoridConductorOld = persistentRecorridoorig.getConductoridConductor();
            Conductor conductoridConductorNew = recorridoorig.getConductoridConductor();
            Vehiculo vehiculoIdvehiculoOld = persistentRecorridoorig.getVehiculoIdvehiculo();
            Vehiculo vehiculoIdvehiculoNew = recorridoorig.getVehiculoIdvehiculo();
            List<Log> logListOld = persistentRecorridoorig.getLogList();
            List<Log> logListNew = recorridoorig.getLogList();
            List<String> illegalOrphanMessages = null;
            for (Log logListOldLog : logListOld) {
                if (!logListNew.contains(logListOldLog)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Log " + logListOldLog + " since its recorridoorigIdrecorridoorig field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (conductoridConductorNew != null) {
                conductoridConductorNew = em.getReference(conductoridConductorNew.getClass(), conductoridConductorNew.getIdConductor());
                recorridoorig.setConductoridConductor(conductoridConductorNew);
            }
            if (vehiculoIdvehiculoNew != null) {
                vehiculoIdvehiculoNew = em.getReference(vehiculoIdvehiculoNew.getClass(), vehiculoIdvehiculoNew.getIdvehiculo());
                recorridoorig.setVehiculoIdvehiculo(vehiculoIdvehiculoNew);
            }
            List<Log> attachedLogListNew = new ArrayList<Log>();
            for (Log logListNewLogToAttach : logListNew) {
                logListNewLogToAttach = em.getReference(logListNewLogToAttach.getClass(), logListNewLogToAttach.getIdlog());
                attachedLogListNew.add(logListNewLogToAttach);
            }
            logListNew = attachedLogListNew;
            recorridoorig.setLogList(logListNew);
            recorridoorig = em.merge(recorridoorig);
            if (conductoridConductorOld != null && !conductoridConductorOld.equals(conductoridConductorNew)) {
                conductoridConductorOld.getRecorridoorigList().remove(recorridoorig);
                conductoridConductorOld = em.merge(conductoridConductorOld);
            }
            if (conductoridConductorNew != null && !conductoridConductorNew.equals(conductoridConductorOld)) {
                conductoridConductorNew.getRecorridoorigList().add(recorridoorig);
                conductoridConductorNew = em.merge(conductoridConductorNew);
            }
            if (vehiculoIdvehiculoOld != null && !vehiculoIdvehiculoOld.equals(vehiculoIdvehiculoNew)) {
                vehiculoIdvehiculoOld.getRecorridoorigList().remove(recorridoorig);
                vehiculoIdvehiculoOld = em.merge(vehiculoIdvehiculoOld);
            }
            if (vehiculoIdvehiculoNew != null && !vehiculoIdvehiculoNew.equals(vehiculoIdvehiculoOld)) {
                vehiculoIdvehiculoNew.getRecorridoorigList().add(recorridoorig);
                vehiculoIdvehiculoNew = em.merge(vehiculoIdvehiculoNew);
            }
            for (Log logListNewLog : logListNew) {
                if (!logListOld.contains(logListNewLog)) {
                    Recorridoorig oldRecorridoorigIdrecorridoorigOfLogListNewLog = logListNewLog.getRecorridoorigIdrecorridoorig();
                    logListNewLog.setRecorridoorigIdrecorridoorig(recorridoorig);
                    logListNewLog = em.merge(logListNewLog);
                    if (oldRecorridoorigIdrecorridoorigOfLogListNewLog != null && !oldRecorridoorigIdrecorridoorigOfLogListNewLog.equals(recorridoorig)) {
                        oldRecorridoorigIdrecorridoorigOfLogListNewLog.getLogList().remove(logListNewLog);
                        oldRecorridoorigIdrecorridoorigOfLogListNewLog = em.merge(oldRecorridoorigIdrecorridoorigOfLogListNewLog);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = recorridoorig.getIdrecorridoorig();
                if (findRecorridoorig(id) == null) {
                    throw new NonexistentEntityException("The recorridoorig with id " + id + " no longer exists.");
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
            Recorridoorig recorridoorig;
            try {
                recorridoorig = em.getReference(Recorridoorig.class, id);
                recorridoorig.getIdrecorridoorig();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The recorridoorig with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Log> logListOrphanCheck = recorridoorig.getLogList();
            for (Log logListOrphanCheckLog : logListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Recorridoorig (" + recorridoorig + ") cannot be destroyed since the Log " + logListOrphanCheckLog + " in its logList field has a non-nullable recorridoorigIdrecorridoorig field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Conductor conductoridConductor = recorridoorig.getConductoridConductor();
            if (conductoridConductor != null) {
                conductoridConductor.getRecorridoorigList().remove(recorridoorig);
                conductoridConductor = em.merge(conductoridConductor);
            }
            Vehiculo vehiculoIdvehiculo = recorridoorig.getVehiculoIdvehiculo();
            if (vehiculoIdvehiculo != null) {
                vehiculoIdvehiculo.getRecorridoorigList().remove(recorridoorig);
                vehiculoIdvehiculo = em.merge(vehiculoIdvehiculo);
            }
            em.remove(recorridoorig);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Recorridoorig> findRecorridoorigEntities() {
        return findRecorridoorigEntities(true, -1, -1);
    }

    public List<Recorridoorig> findRecorridoorigEntities(int maxResults, int firstResult) {
        return findRecorridoorigEntities(false, maxResults, firstResult);
    }

    private List<Recorridoorig> findRecorridoorigEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Recorridoorig.class));
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

    public Recorridoorig findRecorridoorig(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Recorridoorig.class, id);
        } finally {
            em.close();
        }
    }

    public int getRecorridoorigCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Recorridoorig> rt = cq.from(Recorridoorig.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
