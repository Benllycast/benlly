/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.udec.controlador;

import com.udec.benlly.Conductor;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.udec.benlly.Recorridoorig;
import com.udec.controlador.exceptions.IllegalOrphanException;
import com.udec.controlador.exceptions.NonexistentEntityException;
import com.udec.controlador.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ususario
 */
public class ConductorJpaController implements Serializable {

    public ConductorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Conductor conductor) throws PreexistingEntityException, Exception {
        if (conductor.getRecorridoorigList() == null) {
            conductor.setRecorridoorigList(new ArrayList<Recorridoorig>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Recorridoorig> attachedRecorridoorigList = new ArrayList<Recorridoorig>();
            for (Recorridoorig recorridoorigListRecorridoorigToAttach : conductor.getRecorridoorigList()) {
                recorridoorigListRecorridoorigToAttach = em.getReference(recorridoorigListRecorridoorigToAttach.getClass(), recorridoorigListRecorridoorigToAttach.getIdrecorridoorig());
                attachedRecorridoorigList.add(recorridoorigListRecorridoorigToAttach);
            }
            conductor.setRecorridoorigList(attachedRecorridoorigList);
            em.persist(conductor);
            for (Recorridoorig recorridoorigListRecorridoorig : conductor.getRecorridoorigList()) {
                Conductor oldConductoridConductorOfRecorridoorigListRecorridoorig = recorridoorigListRecorridoorig.getConductoridConductor();
                recorridoorigListRecorridoorig.setConductoridConductor(conductor);
                recorridoorigListRecorridoorig = em.merge(recorridoorigListRecorridoorig);
                if (oldConductoridConductorOfRecorridoorigListRecorridoorig != null) {
                    oldConductoridConductorOfRecorridoorigListRecorridoorig.getRecorridoorigList().remove(recorridoorigListRecorridoorig);
                    oldConductoridConductorOfRecorridoorigListRecorridoorig = em.merge(oldConductoridConductorOfRecorridoorigListRecorridoorig);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findConductor(conductor.getIdConductor()) != null) {
                throw new PreexistingEntityException("Conductor " + conductor + " already exists.", ex);
            }
            throw ex;
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
            List<Recorridoorig> recorridoorigListOld = persistentConductor.getRecorridoorigList();
            List<Recorridoorig> recorridoorigListNew = conductor.getRecorridoorigList();
            List<String> illegalOrphanMessages = null;
            for (Recorridoorig recorridoorigListOldRecorridoorig : recorridoorigListOld) {
                if (!recorridoorigListNew.contains(recorridoorigListOldRecorridoorig)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Recorridoorig " + recorridoorigListOldRecorridoorig + " since its conductoridConductor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Recorridoorig> attachedRecorridoorigListNew = new ArrayList<Recorridoorig>();
            for (Recorridoorig recorridoorigListNewRecorridoorigToAttach : recorridoorigListNew) {
                recorridoorigListNewRecorridoorigToAttach = em.getReference(recorridoorigListNewRecorridoorigToAttach.getClass(), recorridoorigListNewRecorridoorigToAttach.getIdrecorridoorig());
                attachedRecorridoorigListNew.add(recorridoorigListNewRecorridoorigToAttach);
            }
            recorridoorigListNew = attachedRecorridoorigListNew;
            conductor.setRecorridoorigList(recorridoorigListNew);
            conductor = em.merge(conductor);
            for (Recorridoorig recorridoorigListNewRecorridoorig : recorridoorigListNew) {
                if (!recorridoorigListOld.contains(recorridoorigListNewRecorridoorig)) {
                    Conductor oldConductoridConductorOfRecorridoorigListNewRecorridoorig = recorridoorigListNewRecorridoorig.getConductoridConductor();
                    recorridoorigListNewRecorridoorig.setConductoridConductor(conductor);
                    recorridoorigListNewRecorridoorig = em.merge(recorridoorigListNewRecorridoorig);
                    if (oldConductoridConductorOfRecorridoorigListNewRecorridoorig != null && !oldConductoridConductorOfRecorridoorigListNewRecorridoorig.equals(conductor)) {
                        oldConductoridConductorOfRecorridoorigListNewRecorridoorig.getRecorridoorigList().remove(recorridoorigListNewRecorridoorig);
                        oldConductoridConductorOfRecorridoorigListNewRecorridoorig = em.merge(oldConductoridConductorOfRecorridoorigListNewRecorridoorig);
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
            List<Recorridoorig> recorridoorigListOrphanCheck = conductor.getRecorridoorigList();
            for (Recorridoorig recorridoorigListOrphanCheckRecorridoorig : recorridoorigListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Conductor (" + conductor + ") cannot be destroyed since the Recorridoorig " + recorridoorigListOrphanCheckRecorridoorig + " in its recorridoorigList field has a non-nullable conductoridConductor field.");
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
