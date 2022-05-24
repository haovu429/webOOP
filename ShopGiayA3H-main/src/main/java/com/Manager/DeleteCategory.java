package com.Manager;

import com.dao.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteCategory")
public class DeleteCategory extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
//        System.out.println(id);
        CategoryDao categoryDao = new CategoryDao();
        categoryDao.deleteCategory(id);

        response.sendRedirect("listCategory");
    }
}
