package com.dao;

import com.entities.CategoryEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class CategoryDao {
    private final static SessionFactory factory = HibernateUtility.getSessionFactory();
    public List<CategoryEntity> getListCategory()
    {
        Transaction transaction = null;
        List<CategoryEntity> categorys = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<CategoryEntity> query = session.createQuery("FROM CategoryEntity ");
            categorys = query.list();
            return categorys;
        }
        catch (Exception e)
        {
            if (transaction != null)
            {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally
        {
            session.close();
        }
        return categorys;
    }
    public List<CategoryEntity> getCategory() {
        try (Session session = factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CategoryEntity> query = builder.createQuery(CategoryEntity.class);
            Root<CategoryEntity> root = query.from(CategoryEntity.class);
            query.select(root);
            return  session.createQuery(query).getResultList();
        }
    }

    public CategoryEntity getCategory(Integer id) {
        try (Session session = factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CategoryEntity> query = builder.createQuery(CategoryEntity.class);
            Root<CategoryEntity> root = query.from(CategoryEntity.class);
            query.select(root);


            query.where(builder.equal(root.get("id").as(Integer.class), id));

            CategoryEntity category = session.createQuery(query).getSingleResult();
            return category;
        }
    }
    public CategoryEntity getCategoryAdmin(Integer id) {
        try (Session session = factory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<CategoryEntity> query = builder.createQuery(CategoryEntity.class);
            Root<CategoryEntity> root = query.from(CategoryEntity.class);
            query.select(root);

            query.where(builder.equal(root.get("cid").as(Integer.class), id));

            CategoryEntity category = session.createQuery(query).getSingleResult();
            return category;
        }
    }
    public void insertCategory(CategoryEntity category) {
        try (Session session = factory.openSession()) {
            try {
                session.getTransaction().begin();
                session.save(category);
                session.getTransaction().commit();

            } catch (Exception e) {
                e.printStackTrace();
                session.getTransaction().rollback();
            }
        }
    }

    public void deleteCategory(Integer id) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<CategoryEntity> criteriaDelete = builder.createCriteriaDelete(CategoryEntity.class);
            Root<CategoryEntity> root = criteriaDelete.from(CategoryEntity.class);
            criteriaDelete.where(builder.equal(root.get("cid"), id));

            session.createQuery(criteriaDelete).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void updateCategory(CategoryEntity category) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<CategoryEntity> update = builder.createCriteriaUpdate(CategoryEntity.class);
            Root<CategoryEntity> root = update.from(CategoryEntity.class);
            update.set(root.get("cname"), category.getCname());
            update.where(builder.equal(root.get("cid"), category.getCid()));

            session.createQuery(update).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }
}
