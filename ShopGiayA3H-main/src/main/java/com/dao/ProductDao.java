package com.dao;

import com.entities.CategoryEntity;
import com.entities.KhachEntity;
import com.entities.ProductEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.*;
import java.util.List;

public class ProductDao {
    private final static SessionFactory factory = HibernateUtility.getSessionFactory();

    public List<ProductEntity> getProduct() {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);
            Root<ProductEntity> root = query.from(ProductEntity.class);
            query.select(root);

            return  session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public List<ProductEntity> getListProduct()
    {
        Transaction transaction = null;
        List<ProductEntity> products = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<ProductEntity> query = session.createQuery("FROM ProductEntity ");
            products = query.list();
            return products;
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
        return products;
    }

    public List<ProductEntity> findProductById(int id)
    {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);
            Root<ProductEntity> root = query.from(ProductEntity.class);
            query.select(root);


            query.where(builder.equal(root.get("cateId").get("cid"), id));

            List<ProductEntity> products = session.createQuery(query).getResultList();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public List<ProductEntity> findProductId(int id)
    {
        Transaction transaction = null;
        List<ProductEntity> products = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<ProductEntity> query = session.createQuery("Select u From ProductEntity u Where u.id=:id");
            query.setParameter("id", id);
            products = query.list();
            return products;
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
        return products;
    }
    public ProductEntity getProductById(String id) {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);
            Root<ProductEntity> root = query.from(ProductEntity.class);
            query.select(root);


            query.where(builder.equal(root.get("id").as(Integer.class), id));

            ProductEntity products = session.createQuery(query).getSingleResult();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public void updateProduct(ProductEntity product) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaUpdate<ProductEntity> update = builder.createCriteriaUpdate(ProductEntity.class);
            Root<ProductEntity> root = update.from(ProductEntity.class);
            update.set(root.get("name"), product.getName());
            update.set(root.get("image"), product.getImage());
            update.set(root.get("price"), product.getPrice());
            update.set(root.get("title"), product.getTitle());
            update.set(root.get("description"), product.getDescription());
            update.set(root.get("cateId"), product.getCateId());
            update.where(builder.equal(root.get("id"), product.getId()));

            session.createQuery(update).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

    }

    public void deleteProduct(Integer id) {
        Session session = factory.openSession();
        try {
            session.getTransaction().begin();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaDelete<ProductEntity> delete = builder.createCriteriaDelete(ProductEntity.class);
            Root<ProductEntity> root = delete.from(ProductEntity.class);
            delete.where(builder.equal(root.get("id"), id));

            session.createQuery(delete).executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public void insertProduct(ProductEntity product) {
        Session session = factory.openSession();
        try{
            session.getTransaction().begin();
            session.save(product);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    public ProductEntity getProductByIdHieu(String id) {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);
            Root<ProductEntity> root = query.from(ProductEntity.class);
            query.select(root);


            query.where(builder.equal(root.get("id").as(Integer.class), id));

            ProductEntity products = session.createQuery(query).getSingleResult();
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public List<ProductEntity> searchName(String name)
    {
        Transaction transaction = null;
        List<ProductEntity> products = null;
        Session session = HibernateUtility.getSessionFactory().openSession();
        try
        {
            transaction = session.beginTransaction();
            Query<ProductEntity> query = session.createQuery("Select u From ProductEntity u Where u.name LIKE '%"+name+"%' ");
            products = query.list();
            return products;
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
        return products;
    }
}
