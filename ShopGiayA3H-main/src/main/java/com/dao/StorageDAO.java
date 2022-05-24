package com.dao;

import com.entities.ProductEntity;
import com.entities.StorageEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.*;
import java.util.List;

public class StorageDAO {
    private final static SessionFactory factory = HibernateUtility.getSessionFactory();

    public StorageEntity getStorageByProductId(String productId) {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<StorageEntity> query = builder.createQuery(StorageEntity.class);
            Root<StorageEntity> root = query.from(StorageEntity.class);
            query.select(root);

            query.where(builder.equal(root.get("idProduct").as(Integer.class), productId));

            StorageEntity storages = session.createQuery(query).getSingleResult();
            return storages;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public void updateStorage(StorageEntity storages) {
        System.out.println("Cập nhật số lượng sau mua hàng");
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<StorageEntity> update = builder.createCriteriaUpdate(StorageEntity.class);
            Root<StorageEntity> root = update.from(StorageEntity.class);
            update.set(root.get("idProduct"), storages.getIdProduct());
            update.set(root.get("quantity"), storages.getQuantity());
            update.where(builder.equal(root.get("id"), storages.getId()));

            session.createQuery(update).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

    }

    public void deleteStorage(Integer idProduct) {
        System.out.println("Hết hàng xoá luôn");
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<StorageEntity> delete = builder.createCriteriaDelete(StorageEntity.class);
            Root<StorageEntity> root = delete.from(StorageEntity.class);
            delete.where(builder.equal(root.get("idProduct"), idProduct));

            session.createQuery(delete).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void insertStorages(StorageEntity storages) {
        Session session = factory.openSession();
        try{
            session.getTransaction().begin();
            session.save(storages);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
