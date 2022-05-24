package com.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.entities.DetailorderEntity;
import com.entities.OrderEntity;
import com.entities.ProductEntity;
import com.mvc.utility.HibernateUtility;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ReportDao {
    private final static SessionFactory factory = HibernateUtility.getSessionFactory();

    public List<Object[]> getStatsColumn() {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
            Root<OrderEntity> root = query.from(OrderEntity.class);
            query.multiselect(root.get("purchaseDate").as(Date.class),
                    builder.sum(root.get("totalQuantity")),
                    builder.sum(root.get("totalMoney")));

            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, -8);
            System.out.println(cal.getTime());

            Predicate d1 = builder.lessThan(root.get("purchaseDate").as(Date.class), new Date());
            Predicate d2 = builder.greaterThanOrEqualTo(root.get("purchaseDate").as(Date.class), cal.getTime());
            Predicate d3 = builder.like(root.get("state").as(String.class), "delivered");
            query.where(builder.and(builder.and(d1, d2), d3));
            query.orderBy(builder.asc(root.get("purchaseDate").as(Date.class)));

            query.groupBy(root.get("purchaseDate").as(Date.class));
//            System.out.println(new Date());

//            Thực hiện chia doanh thu 1.000
            List<Object[]> k = session.createQuery(query).getResultList();
            k.forEach(obj -> {
                obj[2] = Double.parseDouble(obj[2].toString())/1000000;
                System.out.printf("Xuat ngay:%s %s \n",obj[0], obj[2]);
            });


            return k;
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public List<Object[]> getStatsPie() {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
            Root<OrderEntity> root = query.from(OrderEntity.class);
            Root<ProductEntity> proot = query.from(ProductEntity.class);
            Root<DetailorderEntity> droot = query.from(DetailorderEntity.class);
            query.multiselect(proot.get("cateId").get("cname"), builder.sum(droot.get("quantity")));
//            query.multiselect(root.get("totalMoney"),detailOrderEntityRoot.get("orderEntity").as(Integer.class),root.get("purchaseDate").as(Date.class));

            Calendar cal = Calendar.getInstance();

            cal.add(Calendar.DATE, -8);
            System.out.println(cal.getTime());

            long millis=System.currentTimeMillis();
            java.sql.Date date=new java.sql.Date(millis);
//            Predicate d1 = builder.lessThan(root.get("purchaseDate").as(Date.class), date);
//            Predicate d2 = builder.greaterThanOrEqualTo(root.get("purchaseDate").as(Date.class), cal.getTime());
//            Predicate d3 = builder.like(root.get("state").as(String.class), "delivered");
            Predicate d4 = builder.equal(root.get("oid"), droot.get("orderEntity"));
            Predicate d5 = builder.equal(droot.get("product"), proot.get("id"));
            query.where(builder.and(builder.and(builder.and(d4,d5))));
//            query.groupBy(root.get("purchaseDate").as(Date.class));
            query.groupBy(proot.get("cateId"));

            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public List<Object[]> getStatsLine() {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
            Root<OrderEntity> root = query.from(OrderEntity.class);
            query.multiselect(
                    builder.function("MONTH", Integer.class, root.get("purchaseDate")),
                    builder.sum(root.get("totalMoney")));

            Calendar cal1 = Calendar.getInstance();
            cal1.add(Calendar.MONTH, -7);

            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.MONTH, -1);

            SimpleDateFormat formatter = new SimpleDateFormat("MM");
            Integer strDate1 = Integer.parseInt(formatter.format(cal1.getTime()));
            Integer strDate2 = Integer.parseInt(formatter.format(cal2.getTime()));
            System.out.println("Date Format with MM: " + strDate2);

            Predicate d1 = builder.lessThanOrEqualTo(builder.function("MONTH", Integer.class, root.get("purchaseDate")),strDate2);
            Predicate d2 = builder.greaterThanOrEqualTo(builder.function("MONTH", Integer.class, root.get("purchaseDate")),strDate1);
            query.where(builder.and(d1, d2));

            query.orderBy(builder.asc(builder.function("MONTH", Integer.class, root.get("purchaseDate"))));

            query.groupBy(builder.function("MONTH", Integer.class, root.get("purchaseDate")));


            return  session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public List<Object[]> getStatsDoughnut() {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
            Root<DetailorderEntity> root = query.from(DetailorderEntity.class);
            query.multiselect(builder.sum(root.get("quantity")),
                    root.get("product").get("cateId").get("cname"));

//            Calendar cal1 = Calendar.getInstance();
//            cal1.add(Calendar.MONTH, -2);
//
            Calendar cal2 = Calendar.getInstance();
            cal2.add(Calendar.MONTH, -1);

            SimpleDateFormat formatter = new SimpleDateFormat("MM");
//            Integer strDate1 = Integer.parseInt(formatter.format(cal1.getTime()));
            Integer strDate2 = Integer.parseInt(formatter.format(cal2.getTime()));
//
//            Integer date3 = Integer.parseInt(formatter.format(date));
//            System.out.println("Date Format with MM: " + strDate1);

//            Predicate d1 = builder.lessThanOrEqualTo(builder.function("MONTH", Integer.class, root.get("orderEntity").get("purchaseDate")),strDate2);
//            Predicate d2 = builder.greaterThanOrEqualTo(builder.function("MONTH", Integer.class, root.get("orderEntity").get("purchaseDate")),strDate1);
            query.where(builder.equal(builder.function("MONTH", Integer.class, root.get("orderEntity").get("purchaseDate")),strDate2));


            query.groupBy(root.get("product").get("cateId").get("cid"));


            return  session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public Double getStatsRevenue(int month) {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Double> query = builder.createQuery(Double.class);
            Root<OrderEntity> root = query.from(OrderEntity.class);
            query.multiselect(
                    builder.sum(root.get("totalMoney")));
            query.where(builder.equal(builder.function("MONTH", Integer.class, root.get("purchaseDate")),month));

            return  session.createQuery(query).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public List<Double> revenueMonthly() {
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.MONTH, -2);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.MONTH, -1);

        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        Integer strDate1 = Integer.parseInt(formatter.format(cal1.getTime()));
        Integer strDate2 = Integer.parseInt(formatter.format(cal2.getTime()));

        Double objects1 = getStatsRevenue(strDate1);
        Double objects2 = getStatsRevenue(strDate2);

        Double reVe = objects2 - objects1;
        Double divRev = reVe/objects1*100;
        divRev = (double) Math.ceil(divRev * 1) / 1;
        List<Double> strObj = new ArrayList<>();
        strObj.add(objects2);
        strObj.add(divRev);
        return strObj;
    }

    public Long getStatsOrder(int month) {
        Session session = factory.openSession();
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            Root<OrderEntity> root = query.from(OrderEntity.class);
            query.multiselect(
                    builder.count(root.get("oid")));
            query.where(builder.equal(builder.function("MONTH", Integer.class, root.get("purchaseDate")),month));

            return  session.createQuery(query).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return null;
    }

    public List<Long> orderMonthly() {
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.MONTH, -2);

        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.MONTH, -1);

        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        Integer strDate1 = Integer.parseInt(formatter.format(cal1.getTime()));
        Integer strDate2 = Integer.parseInt(formatter.format(cal2.getTime()));

        Long objects1 = getStatsOrder(strDate1);
        Long objects2 = getStatsOrder(strDate2);
//        System.out.printf("%d  %d \n", objects1, objects2);

        Long reVe = objects2 - objects1;
        Long divRev = reVe/objects1*100;
        List<Long> strObj = new ArrayList<>();
        strObj.add(objects2);
        strObj.add(divRev);
//        System.out.printf("%d  %d",strObj.get(0), strObj.get(1));
        return strObj;
    }

}
