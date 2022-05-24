package com.controller;

import com.dao.OrderDao;
import com.dao.ProductDao;
import com.dao.StorageDAO;
import com.entities.OrderEntity;
import com.entities.ProductEntity;
import com.entities.StorageEntity;
import com.mvc.utility.SendEmail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderControl", urlPatterns = {"/order","/Order"})
public class OrderController extends HttpServlet  {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String mess)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            // Dữ liệu truyền vào trong html mail
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String total = request.getParameter("totalPrice");
            String link = request.getParameter("rootlink");
            //create instance object of the SendEmail Class
            System.out.println("link"+link);
            SendEmail sm = new SendEmail();
            //get the 6-digit code
            String code = sm.getRandom();

            //craete new user using all information
            User user = new User(name,email,code);

            //call the send email method

            //boolean test = true;
            //check if the email send successfully

            //Trường hợp hết hàng
            if (mess != null){
                out.println(mess);
            } else {
                boolean test = sm.sendEmail(user, email, name, phone, address, total, link);
                if(test){
                    HttpSession session  = request.getSession();
                    session.setAttribute("authcode", user);
                    response.sendRedirect("thanks.jsp");
                }else{
                    //Trường hợp thanh toán không thành công (do gửi mail ko đc)
                    out.println("Please Enter Your Full Email Information Or Your Email Does Not Exist.");
                }
            }
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws SecurityException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");

        String iAction = request.getParameter("action");

        if (iAction != null && !iAction.equals("")) {
            if (iAction.equals("SaveOrder")) {
                HttpSession session = request.getSession();
                Object objCartBean = session.getAttribute("cart");

                CartItemBean cartItemBean_OutOfStock = saveOrder(request);
                String mess = null;
                if (cartItemBean_OutOfStock != null){
                    // Thông báo món hàng nào đã hết hàng
                    mess  = cartItemBean_OutOfStock.getProductEntity().getName() + " không đủ hàng trong kho hoặc đã hết hàng";
                } else {
                    objCartBean = null;
                }
                processRequest(request, response, mess);
                session.setAttribute("cart", objCartBean);
            } else if (iAction.equals("Update")) {
                saveOrderPaypal(request);
                payPal(request,response);
            } else if (iAction.equals("Delete")) {

            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws SecurityException, IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
        doPost(request, response);
    }

    protected CartItemBean saveOrder(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        System.out.println("email: "+email);

        CartBean cartBean = null;

        Object objCartBean = session.getAttribute("cart");

        if (objCartBean != null) {
            cartBean = (CartBean) objCartBean;
        } else {
            cartBean = new CartBean();
            session.setAttribute("cart", cartBean);
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setEmail(email);
        orderEntity.setPhone(phone);
        orderEntity.setAddress(address);
        orderEntity.setTotalMoney(cartBean.getTong());
        orderEntity.setTotalQuantity(cartBean.getQuantity());
        orderEntity.setState("Created");

        //lay ngay gio hien tai
        long millis=System.currentTimeMillis();
        java.sql.Date nowDate=new java.sql.Date(millis);

        orderEntity.setPurchaseDate(nowDate);

        OrderDao orderDao = new OrderDao();

        ArrayList<CartItemBean> cartItemBeans = new ArrayList<>();

        //Xét xem món nào hết hàng thì không thể thanh toán
        StorageDAO storageDAO = new StorageDAO();
        StorageEntity storageEntity;
        int currentQuantity = 0;
        CartItemBean cartItemBean;

        List<CartItemBean> update_quantity_after_payment = new ArrayList<>();
        List<Integer> quantity_after_payment = new ArrayList<>();
        for(Object object : cartBean.getList()){
            cartItemBean = (CartItemBean)object;
            storageEntity = storageDAO.getStorageByProductId(cartItemBean.getProductEntity().getId().toString());

            if (storageEntity != null){
                currentQuantity = storageEntity.getQuantity();
            } else {
                currentQuantity = 0;
            }

            if (cartItemBean.getQuantity()> currentQuantity){
                return cartItemBean;
            } else {
                cartItemBeans.add(cartItemBean);
                update_quantity_after_payment.add(cartItemBean);
                quantity_after_payment.add(currentQuantity- cartItemBean.getQuantity());
            }
        }
        System.out.println(orderEntity);
        //Trừ số lượng hàng còn trong kho sau khi mua
        for (int i = 0; i<update_quantity_after_payment.size(); i++){
            storageEntity = storageDAO.getStorageByProductId(update_quantity_after_payment.get(i).getProductEntity().getId().toString());
            int bought_quantity = quantity_after_payment.get(i);
            if (bought_quantity >0 ){
                //Còn hàng thì update số lượng
                storageEntity.setQuantity(bought_quantity);
                storageDAO.updateStorage(storageEntity);
            } else {
                // hết hàng thì xoá luôn dữ liệu số lượng trong kho
                storageDAO.deleteStorage(storageEntity.getIdProduct());
            }
        }
        orderDao.insertOrderAndDetail(orderEntity,cartItemBeans);
        return null;
    }

    protected void payPal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String totalPrice = request.getParameter("totalPrice");

        String url;
        url = "/payment.jsp";
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.setAttribute("totalPrice", totalPrice);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    protected void saveOrderPaypal(HttpServletRequest request) {
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        System.out.println("email: "+email);

        CartBean cartBean = null;

        Object objCartBean = session.getAttribute("cart");

        if (objCartBean != null) {
            cartBean = (CartBean) objCartBean;
        } else {
            cartBean = new CartBean();
            session.setAttribute("cart", cartBean);
        }

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setEmail(email);
        orderEntity.setPhone(phone);
        orderEntity.setAddress(address);
        orderEntity.setTotalMoney(cartBean.getTong());
        orderEntity.setTotalQuantity(cartBean.getQuantity());
        orderEntity.setState("Complete");

        //lay ngay gio hien tai
        long millis=System.currentTimeMillis();
        java.sql.Date nowDate=new java.sql.Date(millis);

        orderEntity.setPurchaseDate(nowDate);

        OrderDao orderDao = new OrderDao();

        ArrayList<CartItemBean> cartItemBeans = new ArrayList<>();
        for(Object object : cartBean.getList()){
            cartItemBeans.add((CartItemBean)object);
        }
        orderDao.insertOrderAndDetail(orderEntity,cartItemBeans);
    }
}
