package com.dao;

import com.entities.DetailorderEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DetailorderDao {
    private final static SessionFactory factory = HibernateUtility.getSessionFactory();
    public boolean insertDetailOrder(DetailorderEntity item) {

        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.save(item);

            transaction.commit();
        } catch (Exception var8) {
            if (transaction != null) {
                transaction.rollback();
            }

            var8.printStackTrace();
        } finally {
            session.close();
        }

        return true;
    }
    public boolean insertItemOfOrder(ArrayList<DetailorderEntity> listItem, int oid){
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            for(DetailorderEntity item : listItem){
                //dat so oid cho detail
                OrderDao orderDao = new OrderDao();
                item.setOrderEntity(orderDao.getOrderById(String.valueOf(oid)));
                session.save(item);
            }

            transaction.commit();
        } catch (Exception var8) {
            if (transaction != null) {
                transaction.rollback();
            }

            var8.printStackTrace();
        } finally {
            session.close();
        }

        return true;
    }
    public List<DetailorderEntity> getDetailOrderByOid(String oid) {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<DetailorderEntity> query = builder.createQuery(DetailorderEntity.class);
            Root<DetailorderEntity> root = query.from(DetailorderEntity.class);
            query.select(root);


            query.where(builder.equal(root.get("orderEntity").get("oid"), Integer.parseInt(oid)));

            List<DetailorderEntity> detailorderEntitys = new ArrayList<>();

            detailorderEntitys = session.createQuery(query).getResultList();
            return detailorderEntitys;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }
}
