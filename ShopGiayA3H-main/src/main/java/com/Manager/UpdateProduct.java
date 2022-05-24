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

@WebServlet(urlPatterns = {"/update"})
public class UpdateProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        ProductDao dao = new ProductDao();
        System.out.println(id);
        ProductEntity product = dao.getProductByIdHieu(id);
        request.setAttribute("product", product);
        request.getRequestDispatcher("updateProduct.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        CategoryEntity category = (new CategoryDao()).getCategoryAdmin(Integer.parseInt(request.getParameter("cid")));

        ProductEntity product = new ProductEntity();
        product.setId(Integer.parseInt(request.getParameter("id")));
        product.setName(request.getParameter("name"));
        product.setDescription(request.getParameter("description"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setImage(request.getParameter("image"));
        product.setTitle(request.getParameter("title"));
        product.setCateId(category);
        product.setSaleId(1);

        ProductDao pro = new ProductDao();
        pro.updateProduct(product);
        response.sendRedirect("listProduct");
    }
}
