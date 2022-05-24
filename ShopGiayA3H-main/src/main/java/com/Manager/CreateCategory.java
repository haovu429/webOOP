package com.Manager;

import com.dao.CategoryDao;
import com.entities.CategoryEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/createCategory"})
public class CreateCategory extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        System.out.println(action);
        if(action == null) {}
        else if (action.equals("Update"))
        {
            updateCate(request, response);
        }
        else if(action.equals("Create")) {
            insertCate(request, response);
        }
        response.sendRedirect("listCategory");
    }

    protected void updateCate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        CategoryEntity category = new CategoryEntity();

        category.setCid(Integer.parseInt(request.getParameter("cid")));
        category.setCname(request.getParameter("cname"));

        CategoryDao categoryDao = new CategoryDao();
        categoryDao.updateCategory(category);
    }

    protected void insertCate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        System.out.printf("vao insert");
        CategoryEntity category = new CategoryEntity();

        category.setCname(request.getParameter("cname"));

        System.out.println(request.getParameter("cname"));

        CategoryDao categoryDao = new CategoryDao();
        categoryDao.insertCategory(category);
    }
}
