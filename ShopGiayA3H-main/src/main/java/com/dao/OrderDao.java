package com.dao;

import com.controller.CartItemBean;
import com.entities.DetailorderEntity;
import com.entities.OrderEntity;
import com.entities.ProductEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private final static SessionFactory factory = HibernateUtility.getSessionFactory();

    public boolean insertOrder(OrderEntity item) {
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

    public boolean insertOrderAndDetail(OrderEntity item, ArrayList<CartItemBean> lineItems){
        try{
            insertOrder(item);
            //Lay Id cua Order moi vua them vao(truoc ham nay phai dung ham luu Order)
            OrderDao orderDao = new OrderDao();
            OrderEntity lastOrder = orderDao.getLastOrder();
            //tao mot detailorder thay doi thuoc tinh theo tung lineitem va luu
            DetailorderEntity detailorderEntity = new DetailorderEntity();

            detailorderEntity.setOrderEntity(lastOrder);

            DetailorderDao detailorderDao = new DetailorderDao();

            for (CartItemBean lineItem : lineItems){
                detailorderEntity.setProduct(lineItem.getProductEntity());
                detailorderEntity.setQuantity(lineItem.getQuantity());
                detailorderDao.insertDetailOrder(detailorderEntity);
            }


        }catch(Exception e){
            return false;
        }
        return true;
    }

    public boolean updateOrder(OrderEntity item) {
        Transaction transaction = null;
        Session session = HibernateUtility.getSessionFactory().openSession();

        try {
            transaction = session.beginTransaction();
            session.update(item);
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
    public boolean updateOrderAfterAddDetail(int orderId){
        String strId = Integer.toString(orderId);
        OrderEntity order = new OrderEntity();

        order = getOrderById(strId);
        return updateOrder(order);
    }

    public OrderEntity getOrderById(String id) {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<OrderEntity> query = builder.createQuery(OrderEntity.class);
            Root<OrderEntity> root = query.from(OrderEntity.class);
            query.select(root);


            query.where(builder.equal(root.get("oid").as(Integer.class), id));

            OrderEntity OrderEntity = session.createQuery(query).getSingleResult();
            return OrderEntity;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public OrderEntity getLastOrder() {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<OrderEntity> query = builder.createQuery(OrderEntity.class);
            Root<OrderEntity> root = query.from(OrderEntity.class);
            query.select(root);

            int maxId = getLastOrderId();

            query.where(builder.equal(root.get("oid").as(Integer.class), maxId));

            OrderEntity OrderEntity = session.createQuery(query).getSingleResult();
            return OrderEntity;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public Integer getLastOrderId() {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Integer> query = builder.createQuery(Integer.class);
            Root<OrderEntity> root = query.from(OrderEntity.class);

            //query.select(root);

            //Lay last id
            query.select(builder.max(root.get("oid")));


            int maxId = session.createQuery(query).getSingleResult();
            System.out.println("oid = "+ maxId);

            return maxId;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

}