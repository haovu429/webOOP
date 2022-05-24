<%@ page import="com.dao.ProductDao" %>
<%@ page import="com.entities.ProductEntity" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 10/9/2021
  Time: 8:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%
    ProductDao productDao = new ProductDao();
    List<ProductEntity> product = productDao.getListProduct();
    request.setAttribute("product", product);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Shop</title>

    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />

    <!-- font awesome cdn link  -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    <!-- custom css file link  -->
    <link rel="stylesheet" href="css/stylehome.css">

</head>
<body>

<!-- header section starts      -->

<header>

    <a href="#" class="logo"><i class="fab fa-linode"></i>A3HTeam.</a>

    <nav class="navbar">
        <a class="active" href="#home">home</a>
        <a href="#dishes">best</a>
        <a href="<c:url value = "/about.jsp"/>">about</a>
        <a href="<c:url value = "/product"/>">product</a>
        <a href="#review">review</a>
    </nav>

    <div class="icons">
        <i class="fas fa-bars" id="menu-bars"></i>
        <i class="fas fa-search" id="search-icon"></i>
        <a href="" class="fas fa-heart"></a>
        <a href="<c:url value = "/cart.jsp"/>" class="fas fa-shopping-cart"></a>
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

<!-- home section starts  -->

<section class="home" id="home">
    <div class="swiper-container home-slider">
        <div class="swiper-wrapper wrapper">
            <c:forEach items="${product}" var="pro" begin="${product.size() - 5}" end="${product.size()}" step="1">
            <div class="swiper-slide slide">
                <div class="content">
                    <span>Featured Products</span>
                    <h3>${ pro.name }</h3>
                    <p>${ pro.description }</p>
                    <a href="<c:url value = "/detailproductbyid?id=${pro.id}"/>" class="btn">order now</a>
                </div>
                <div class="image">
                    <img src="${ pro.image }" alt="">
                </div>
            </div>
            </c:forEach>
        </div>

        <div class="swiper-pagination"></div>
    </div>

</section>

<!-- home section ends -->

<!-- dishes section starts  -->

<section class="dishes" id="dishes">

    <h3 class="sub-heading"> our dishes </h3>
    <h1 class="heading"> popular dishes </h1>

    <div class="box-container">
        <c:forEach items="${product}" var="pro" begin="${product.size() - 8}" end="${product.size()}" step="1">
            <form method="POST" action="CartController">
            <div class="box">
            <a href="#" class="fas fa-heart"></a>
            <a href="<c:url value = "/detailproductbyid?id=${pro.id}"/>" class="fas fa-eye"></a>
            <img src="${pro.image}" alt="">
            <h3>${pro.name}</h3>
            <div class="stars">
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star"></i>
                <i class="fas fa-star-half-alt"></i>
            </div>
            <span>$${pro.price}</span>
                <input type="hidden" name="productId" value="${pro.id}">
                <input type="hidden" name="pion" value="${pro.name}">
                <input type="hidden" name="quantity" value="1" style="width: 30px; height: auto; margin-left: 120px; background: #1A73E8">
                <input type="hidden" name="description" value="${pro.name}">
                <input type="hidden" name="price" value="${pro.price}">
                <input type="hidden" name="image" value="${pro.image}">
                <input type="submit" name="action" class="btn" value="Add To Cart">
            </div>
            </form>
        </c:forEach>
    </div>

</section>

<!-- dishes section ends -->

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

<!-- footer section ends -->



<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>

<!-- custom js file link  -->
<script src="js/script.js"></script>

</body>
</html>
