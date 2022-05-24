package com.Manager;

import com.dao.ProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/delete"})
public class DeleteProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String id = request.getParameter("pid");
        ProductDao productDao = new ProductDao();
        productDao.deleteProduct(Integer.parseInt(id));

        response.sendRedirect("listProduct");
    }

    protected void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}

