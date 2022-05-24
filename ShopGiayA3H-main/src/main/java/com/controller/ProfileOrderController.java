package com.controller;

import com.dao.DetailorderDao;
import com.dao.OrderDao;
import com.dao.ProductDao;
import com.entities.DetailorderEntity;
import com.entities.OrderEntity;
import com.entities.ProductEntity;
import com.mvc.utility.MD5Utils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProfileOrder", value = "/profile")
public class ProfileOrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url;
        url = "/profileOrder.jsp";
        Integer oid = Integer.parseInt(request.getParameter("oid"));
        String email = request.getParameter("email");
        OrderDao orderDao = new OrderDao();
        OrderEntity order = orderDao.getOrderById(String.valueOf(oid));
        String hashemail = MD5Utils.hashcode(order.getEmail());
        if (email.equals(hashemail))
        {
            DetailorderDao detailorderDao = new DetailorderDao();
            List<DetailorderEntity> detailorderEntities = detailorderDao.getDetailOrderByOid(String.valueOf(oid));
            List<CartItemBean> listItem = new ArrayList<>();
            ProductDao productDao = new ProductDao();
            for (DetailorderEntity detail : detailorderEntities ) {
                CartItemBean cartItemBean = new CartItemBean();
                cartItemBean.setProductEntity(productDao.getProductById(String.valueOf(detail.getProduct().getId())));
                cartItemBean.setQuantity(detail.getQuantity());
                listItem.add(cartItemBean);
            }
            request.setAttribute("order", order);
            request.setAttribute("listItem", listItem);
        }
        else
        {
            url = "/test.jsp";
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
