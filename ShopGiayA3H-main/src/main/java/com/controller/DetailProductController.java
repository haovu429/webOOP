package com.controller;

import com.dao.ProductDao;
import com.dao.StorageDAO;
import com.entities.ProductEntity;
import com.entities.StorageEntity;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "Detailproduct", value = "/detailproductbyid")
public class DetailProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url;
        url = "/detailproduct.jsp";
        Integer id = Integer.parseInt(request.getParameter("id"));
        ProductDao productDao = new ProductDao();
        List<ProductEntity> productid = productDao.findProductId(id);
        request.setAttribute("productid", productid);
        int quantity = 0;
        StorageDAO storageDAO = new StorageDAO();
        StorageEntity storageEntity = storageDAO.getStorageByProductId(String.valueOf(id));
        if (storageEntity != null){
            quantity = storageEntity.getQuantity();
        }
        request.setAttribute("quantity", quantity);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
