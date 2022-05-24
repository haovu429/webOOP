package com.Manager;

import com.controller.CartItemBean;
import com.dao.CategoryDao;
import com.dao.ProductDao;
import com.dao.StorageDAO;
import com.entities.CategoryEntity;
import com.entities.ProductEntity;
import com.entities.StorageEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/listProduct"})
public class ReadProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url;
        url = "/productAdmin.jsp";
        ProductDao productDao = new ProductDao();

        //List<CartItemBean> cartItemBeans = new ArrayList<>();
        List<ProductEntity> products = productDao.getListProduct();

        System.out.println("So luong: "+ products.size());

//        CartItemBean cartItemBean = new CartItemBean();
//
//        StorageDAO storageDAO = new StorageDAO();
//        StorageEntity storageEntity;
//        for(ProductEntity product : products){
//             cartItemBean.setProductEntity(product);
//             int current_quantity = 0;
//            storageEntity = storageDAO.getStorageByProductId(product.getId().toString());
//            if (storageEntity != null){
//                current_quantity = storageEntity.getQuantity();
//            }
//             cartItemBean.setQuantity(current_quantity);
//            cartItemBeans.add(cartItemBean);
//        }
        request.setAttribute("product", products);

        CategoryDao categoryDao = new CategoryDao();
        List<CategoryEntity> categories = categoryDao.getCategory();
        request.setAttribute("categories", categories);


        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
