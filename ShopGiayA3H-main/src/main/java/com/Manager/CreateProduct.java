package com.Manager;

import com.dao.CategoryDao;
import com.dao.ProductDao;
import com.entities.CategoryEntity;
import com.entities.ProductEntity;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/create"})
public class CreateProduct extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        System.out.println(action);
        if(action == null) {}
        else if (action.equals("Update"))
        {
            updatePro(request, response);
        }
        else if(action.equals("Create")) {
            insertPro(request, response);
        }
        response.sendRedirect("listProduct");
    }

    protected void updatePro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        CategoryEntity category = (new CategoryDao())
                .getCategoryAdmin(
                Integer.parseInt(request.getParameter("cid")));

        ProductEntity product = new ProductEntity();
        product.setId(Integer.parseInt(request.getParameter("pid")));
        product.setName(request.getParameter("name"));
        product.setDescription(request.getParameter("description"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setImage(request.getParameter("images"));
        product.setTitle(request.getParameter("title"));
        product.setCateId(category);
        product.setSaleId(Integer.parseInt(request.getParameter("saleID")));

        ProductDao pro = new ProductDao();
        pro.updateProduct(product);
    }

    protected void insertPro(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        CategoryEntity category = (new CategoryDao()).getCategoryAdmin(
                Integer.parseInt(request.getParameter("cid")));
        ProductEntity product = new ProductEntity();
        product.setName(request.getParameter("name"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setImage(request.getParameter("images"));
        product.setTitle(request.getParameter("title"));
        product.setDescription(request.getParameter("description"));
        product.setCateId(category);
        if(request.getParameter("saleID") != null || !request.getParameter("saleID").equals("")) {
            product.setSaleId(Integer.parseInt(request.getParameter("saleID")));
        }

        ProductDao dao = new ProductDao();
        dao.insertProduct(product);
    }
}
