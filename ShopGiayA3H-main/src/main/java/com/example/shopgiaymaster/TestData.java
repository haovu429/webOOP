package com.example.shopgiaymaster;

import com.dao.DetailorderDao;
import com.dao.KhachDao;
import com.entities.DetailorderEntity;
import com.entities.KhachEntity;
import com.entities.OrderEntity;
import com.mvc.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TestData {
    public static void main(String[] args){
        try {
            Session session = HibernateUtility.getSessionFactory().openSession();

//            Query<OrderEntity> query = session.createQuery("FROM OrderEntity ");
//            List<OrderEntity> cats = query.list();
//            cats.forEach(c -> System.out.println(c.getOid()));
//            OrderDao orderDao = new OrderDao();
//            List<OrderDao> cats = orderDao.getListOrder();
//            cats.forEach(c -> System.out.println(c.));

//            Query<DetailorderEntity> query = session.createQuery("FROM DetailorderEntity ");
//            List<DetailorderEntity> cats = query.list();
//            cats.forEach(c -> System.out.println(c.getProduct().getId()));

            KhachDao khachDao = new KhachDao();
            List<KhachEntity> detailorderEntities = khachDao.getKhach();
            detailorderEntities.forEach(c -> System.out.println(c.getUsername()));

//            ReportDao dao = new ReportDao();
//            List<Object[]> ord = dao.getStatsPie();
//            ord.forEach(c -> {
//
//                System.out.printf("Xuat ngay: %s Tổng tiền: %s    %s\n", c[0], c[1]);
//            });
        } catch (Exception e){

        }
    }
}
