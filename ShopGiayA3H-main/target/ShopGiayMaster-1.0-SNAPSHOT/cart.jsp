<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 10/19/2021
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cart Shop</title>

    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />

    <!-- font awesome cdn link  -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    <!-- custom css file link  -->
    <link rel="stylesheet" href="css/stylehome.css">
    <link rel="stylesheet" href="css/stylecart.css">
</head>
<body>
<header>

    <a href="<c:url value = "/index1"/>" class="logo"><i class="fas fa-utensils"></i>resto.</a>

    <nav class="navbar">
        <a href="<c:url value = "/index1"/>">home</a>
        <a href="<c:url value = "/index1#dishes"/>">best</a>
        <a href="<c:url value = "/about.jsp"/>">about</a>
        <a href="<c:url value = "/product"/>">product</a>
        <a href="#review">review</a>
    </nav>

    <div class="icons">
        <i class="fas fa-bars" id="menu-bars"></i>
        <i class="fas fa-search" id="search-icon"></i>
        <a href="" class="fas fa-heart"></a>
        <a href="<c:url value = "/cart.jsp"/>" class="fas fa-shopping-cart" style="background: limegreen"></a>
        <a href="#" class="fas fa-user-plus"></a>
        <a href="<c:url value = "/login.jsp"/>" class="fas fa-sign-in-alt"></a>

    </div>

</header>

<!-- header section ends-->

<!-- search form  -->

<form action="search" id="search-form">
    <input type="search" placeholder="Search here..." name="search" id="search-box">
    <i class="fas fa-times" id="close"></i>
    <input type="submit" class="btn" value="Search">
</form>
<!-- Cart Items -->
<div class="container-md cart">
    <table>
        <tr>
            <th>Description</th>
            <th>Product</th>
            <th>Quantity</th>
            <th>Subtotal</th>
        </tr>
        <jsp:useBean id="cart" scope="session" class="com.controller.CartBean" />

        <c:if test="${cart.lineItemCount == 0}">
            <tr> <td colspan="4">- Cart is currently empty -</td></tr>
        </c:if>
        <c:forEach var="cartItem" items="${cart.list}" varStatus="counter">
            <form name="item" method="POST" action="CartController">
        <tr>
                <tr>
                    <td style="display: flex; align-items: center">
                        <img src="${cartItem.image}" alt="">
                        <c:out value="${cartItem.description}"/>
                    </td>
                    <td>
                        <input type='hidden' name='stt' value='<c:out value="${counter.count}"/>'>
                        <input type='number' name="quantity" value='<c:out value="${cartItem.quantity}"/>'>
                        <input type="submit" name="action" value="Update">
                        <input type="submit" name="action" value="Delete">
                    </td>
                    <td>$<c:out value="${cartItem.price}"/></td>
                    <td>$<c:out value="${cartItem.totalCost}"/></td>
                </tr>
        </tr>
            </form>
                </c:forEach>
    </table>

    <div class="total-price">
        <table>
            <tr>
                <td>Subtotal</td>
                <td>$<c:out value="${cart.total}"/></td>
            </tr>
            <tr>
                <td>Tax</td>
                <td>$50</td>
            </tr>
            <tr>
                <td>Total</td>
                <td>$<c:out value="${cart.tong}"/></td>
            </tr>
        </table>
        <form method="POST" action="order">
            <input type="text" class="" placeholder="Name" name="name" style="font-size: 20px; border: none ;border-bottom:1px solid #757575; margin-bottom: 10px; text-transform: none;"><br>
            <input type="email" class="" placeholder="Email" name="email" style="font-size: 20px; border: none ;border-bottom:1px solid #757575; margin-bottom: 10px; text-transform: none;"><br>
            <input type="text" class="" placeholder="Phone" name="phone" style="font-size: 20px; border: none ;border-bottom:1px solid #757575; margin-bottom: 10px; text-transform: none;"><br>
            <input type="text" class="" placeholder="Address" name="address" style="font-size: 20px; border: none ;border-bottom:1px solid #757575; margin-bottom: 10px; text-transform: none;">

            <input type="hidden" name="totalPrice" value="<c:out value="${cart.tong}"/>"><br>
            <button type="submit" class="checkout btn" name="action" value="SaveOrder" style="padding-right: 35px;padding-left: 35px">Proceed To Checkout</button><br>
            <button type="submit" class="checkout btn" name="action" value="Update" style="background: royalblue;">Checkout With Paypal</button><br>
            <a href="${pageContext.request.contextPath}/product" class="checkout btn" style="padding-right: 60px;padding-left: 60px">Continue Shop</a>
        </form>
    </div>
</div>
<!-- footer section starts  -->

<section class="footer">

    <div class="box-container">

        <div class="box">
            <h3>locations</h3>
            <a href="#">india</a>
            <a href="#">japan</a>
            <a href="#">russia</a>
            <a href="#">USA</a>
            <a href="#">france</a>
        </div>

        <div class="box">
            <h3>quick links</h3>
            <a href="#">home</a>
            <a href="#">best</a>
            <a href="#">about</a>
            <a href="#">product</a>
            <a href="#">reivew</a>
        </div>

        <div class="box">
            <h3>contact info</h3>
            <a href="#">+097-940-9568</a>
            <a href="#">+111-222-3333</a>
            <a href="#">a3hteam@gmail.com</a>
            <a href="#">shopgiaya3h@gmail.com</a>
            <a href="#">HoChiMinh, VietNam - 84</a>
        </div>

        <div class="box">
            <h3>follow us</h3>
            <a href="#">facebook</a>
            <a href="#">twitter</a>
            <a href="#">instagram</a>
            <a href="#">linkedin</a>
        </div>
        <!--Start of Tawk.to Script-->
        <script type="text/javascript">
            var Tawk_API=Tawk_API||{}, Tawk_LoadStart=new Date();
            (function(){
                var s1=document.createElement("script"),s0=document.getElementsByTagName("script")[0];
                s1.async=true;
                s1.src='https://embed.tawk.to/61702f4c86aee40a5737897a/1fif3hgvu';
                s1.charset='UTF-8';
                s1.setAttribute('crossorigin','*');
                s0.parentNode.insertBefore(s1,s0);
            })();
        </script>
        <!--End of Tawk.to Script-->
    </div>

    <div class="credit"> copyright @ 2021 by <span>Shop A3hTeam</span> </div>

</section>
</body>
</html>
