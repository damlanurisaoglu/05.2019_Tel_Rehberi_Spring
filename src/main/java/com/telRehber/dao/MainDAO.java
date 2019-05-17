package com.telRehber.dao;


import com.telRehber.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Repository
public class MainDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public Object loadObject(Class clazz, Serializable id) {
        return getCurrentSession().get(clazz, id);
    }

    public boolean saveOrUpdateObject(Object object) {
        getCurrentSession().save(object);
        return true;
    }

    public boolean removeObject(Object object) {
        getCurrentSession().remove(object);
        return true;
    }

    public List<tblPersonel> loadPersonel(int loginId) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<tblPersonel> criteriaQuery = criteriaBuilder.createQuery(tblPersonel.class);
        Root<tblPersonel> root = criteriaQuery.from(tblPersonel.class);
        criteriaQuery.select(root);
        Predicate predicate=criteriaBuilder.equal(root.get("kaydedenId"),loginId);
        criteriaQuery.where(predicate);
        Query<tblPersonel> query = getCurrentSession().createQuery(criteriaQuery);
        return query.getResultList();
    }
    public  List<tblPersonel> loadPersonelUnvanOrBirim(String tip,int tipId, int loginId){
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<tblPersonel> criteriaQuery = criteriaBuilder.createQuery(tblPersonel.class);
        Root<tblPersonel> root = criteriaQuery.from(tblPersonel.class);
        Predicate predicate;
        Predicate predicate12;
        predicate=(criteriaBuilder.equal(root.get(tip),tipId));
        predicate12=(criteriaBuilder.equal(root.get("kaydedenId"),loginId));
        criteriaQuery.select(root).where(criteriaBuilder.and(predicate, predicate12));


        Query<tblPersonel> query = getCurrentSession().createQuery(criteriaQuery);
        return query.getResultList();

    }
    public  List<tblPersonel> loadPersonelAdaGore(String ad){
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<tblPersonel> criteriaQuery = criteriaBuilder.createQuery(tblPersonel.class);
        Root<tblPersonel> root = criteriaQuery.from(tblPersonel.class);
        Predicate predicate;
        predicate=(criteriaBuilder.like(root.get("adi"),"%"+ad+"%"));
        criteriaQuery.where(predicate);
        Query<tblPersonel> query = getCurrentSession().createQuery(criteriaQuery);
        return query.getResultList();

    }
    public  List<String> loadTelefonAdaGore(String ad){
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<tblPersonel> root = criteriaQuery.from(tblPersonel.class);
        Predicate predicate;
        criteriaQuery.select(root.get("telefonNo"));
        predicate=(criteriaBuilder.like(root.get("adi"),"%"+ad+"%"));
        criteriaQuery.where(predicate);
        Query<String> query = getCurrentSession().createQuery(criteriaQuery);
        return query.getResultList();

    }
    public  List<String> loadAdTelefonaGore(String telNo){
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
        Root<tblPersonel> root = criteriaQuery.from(tblPersonel.class);
        Predicate predicate;
        criteriaQuery.select(root.get("adi"));
        predicate=(criteriaBuilder.like(root.get("telefonNo"),"%"+telNo+"%"));
        criteriaQuery.where(predicate);
        Query<String> query =getCurrentSession().createQuery(criteriaQuery);
        return query.getResultList();

    }
    public List<tblUnvan> loadUnvan() {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<tblUnvan> criteriaQuery = criteriaBuilder.createQuery(tblUnvan.class);
        Root<tblUnvan> root = criteriaQuery.from(tblUnvan.class);
        criteriaQuery.select(root);
        Query<tblUnvan> query = getCurrentSession().createQuery(criteriaQuery);
        return query.getResultList();
    }

    public List<tblBirim> loadBirim() {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<tblBirim> criteriaQuery = criteriaBuilder.createQuery(tblBirim.class);
        Root<tblBirim> root = criteriaQuery.from(tblBirim.class);
        criteriaQuery.select(root);
        Query<tblBirim> query = getCurrentSession().createQuery(criteriaQuery);
        return query.getResultList();
    }
    public List<tblLogin> loadLogin(String adi, String sifre) {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<tblLogin> criteriaQuery = criteriaBuilder.createQuery(tblLogin.class);
        Root<tblLogin> root = criteriaQuery.from(tblLogin.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("kullaniciAdi"),adi),
                criteriaBuilder.equal(root.get("sifre"),sifre));
        Query<tblLogin> query = getCurrentSession().createQuery(criteriaQuery);
        return query.getResultList();
    }
}