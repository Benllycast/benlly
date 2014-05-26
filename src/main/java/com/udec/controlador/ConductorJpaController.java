/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.controlador;

import com.udec.persistencia.Conductor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udec.persistencia.Recorrido;
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
public class ConductorJpaController implements Serializable {

    public ConductorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Conductor conductor) {
        if (conductor.getRecorridoList() == null) {
            conductor.setRecorridoList(new ArrayList<Recorrido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Recorrido> attachedRecorridoList = new ArrayList<Recorrido>();
            for (Recorrido recorridoListRecorridoToAttach : conductor.getRecorridoList()) {
                recorridoListRecorridoToAttach = em.getReference(recorridoListRecorridoToAttach.getClass(), recorridoListRecorridoToAttach.getIdRecorrido());
                attachedRecorridoList.add(recorridoListRecorridoToAttach);
            }
            conductor.setRecorridoList(attachedRecorridoList);
            em.persist(conductor);
            for (Recorrido recorridoListRecorrido : conductor.getRecorridoList()) {
                Conductor oldConductoridConductorOfRecorridoListRecorrido = recorridoListRecorrido.getConductoridConductor();
                recorridoListRecorrido.setConductoridConductor(conductor);
                recorridoListRecorrido = em.merge(recorridoListRecorrido);
                if (oldConductoridConductorOfRecorridoListRecorrido != null) {
                    oldConductoridConductorOfRecorridoListRecorrido.getRecorridoList().remove(recorridoListRecorrido);
                    oldConductoridConductorOfRecorridoListRecorrido = em.merge(oldConductoridConductorOfRecorridoListRecorrido);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Conductor conductor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Conductor persistentConductor = em.find(Conductor.class, conductor.getIdConductor());
            List<Recorrido> recorridoListOld = persistentConductor.getRecorridoList();
            List<Recorrido> recorridoListNew = conductor.getRecorridoList();
            List<String> illegalOrphanMessages = null;
            for (Recorrido recorridoListOldRecorrido : recorridoListOld) {
                if (!recorridoListNew.contains(recorridoListOldRecorrido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Recorrido " + recorridoListOldRecorrido + " since its conductoridConductor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Recorrido> attachedRecorridoListNew = new ArrayList<Recorrido>();
            for (Recorrido recorridoListNewRecorridoToAttach : recorridoListNew) {
                recorridoListNewRecorridoToAttach = em.getReference(recorridoListNewRecorridoToAttach.getClass(), recorridoListNewRecorridoToAttach.getIdRecorrido());
                attachedRecorridoListNew.add(recorridoListNewRecorridoToAttach);
            }
            recorridoListNew = attachedRecorridoListNew;
            conductor.setRecorridoList(recorridoListNew);
            conductor = em.merge(conductor);
            for (Recorrido recorridoListNewRecorrido : recorridoListNew) {
                if (!recorridoListOld.contains(recorridoListNewRecorrido)) {
                    Conductor oldConductoridConductorOfRecorridoListNewRecorrido = recorridoListNewRecorrido.getConductoridConductor();
                    recorridoListNewRecorrido.setConductoridConductor(conductor);
                    recorridoListNewRecorrido = em.merge(recorridoListNewRecorrido);
                    if (oldConductoridConductorOfRecorridoListNewRecorrido != null && !oldConductoridConductorOfRecorridoListNewRecorrido.equals(conductor)) {
                        oldConductoridConductorOfRecorridoListNewRecorrido.getRecorridoList().remove(recorridoListNewRecorrido);
                        oldConductoridConductorOfRecorridoListNewRecorrido = em.merge(oldConductoridConductorOfRecorridoListNewRecorrido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = conductor.getIdConductor();
                if (findConductor(id) == null) {
                    throw new NonexistentEntityException("The conductor with id " + id + " no longer exists.");
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
            Conductor conductor;
            try {
                conductor = em.getReference(Conductor.class, id);
                conductor.getIdConductor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The conductor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Recorrido> recorridoListOrphanCheck = conductor.getRecorridoList();
            for (Recorrido recorridoListOrphanCheckRecorrido : recorridoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Conductor (" + conductor + ") cannot be destroyed since the Recorrido " + recorridoListOrphanCheckRecorrido + " in its recorridoList field has a non-nullable conductoridConductor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(conductor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Conductor> findConductorEntities() {
        return findConductorEntities(true, -1, -1);
    }

    public List<Conductor> findConductorEntities(int maxResults, int firstResult) {
        return findConductorEntities(false, maxResults, firstResult);
    }

    private List<Conductor> findConductorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Conductor.class));
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

    public Conductor findConductor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Conductor.class, id);
        } finally {
            em.close();
        }
    }

    public int getConductorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Conductor> rt = cq.from(Conductor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
