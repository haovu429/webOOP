package com.Manager;

import com.dao.ReportDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/dashboard"})
public class Report extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url;
        url = "/dashboardAdmin.jsp";
        ReportDao dao = new ReportDao();
        List<Double> revenue = dao.revenueMonthly();
        List<Long> orders = dao.orderMonthly();
        List<Object[]> repL = dao.getStatsLine();

        List<Object[]> repD = dao.getStatsDoughnut();


        request.setAttribute("reportLine", repL);
        request.setAttribute("reportDoughnut", repD);
        request.setAttribute("revenueM", revenue);
        request.setAttribute("orderM", orders);

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
