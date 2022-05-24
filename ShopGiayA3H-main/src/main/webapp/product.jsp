<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 10/9/2021
  Time: 9:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.entities.ProductEntity" %>
<%@ page import="com.dao.ProductDao" %>
<%@ page import="java.util.List" %>
<%@ page import="com.dao.CategoryDao" %>
<%@ page import="com.entities.CategoryEntity" %>
<%--<%@ page import="com.dao.ProductDao" %>--%>
<%--<%--%>
<%--    ProductEntity product = (ProductEntity) session.getAttribute("product");--%>

<%--%>--%>
<%
    CategoryDao categoryDao = new CategoryDao();
    List<CategoryEntity> category = categoryDao.getListCategory();
    request.setAttribute("category", category);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products</title>

    <link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css" />

    <!-- font awesome cdn link  -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

    <!-- custom css file link  -->
    <link rel="stylesheet" href="css/stylehome.css">

</head>
<body>

<!-- header section starts      -->

<header>

    <a href="<c:url value = "/index.jsp"/>" class="logo"><i class="fas fa-utensils"></i>resto.</a>

    <nav class="navbar" style="min-height: 0px; margin-bottom: 0px;">
                <ul class="nav navbar-nav">
                    <li><a href="<c:url value = "/index1"/>">Home</a></li>
                    <li><a href="<c:url value = "/index1#dishes"/>">best</a></li>
                    <li><a href="<c:url value = "/about.jsp"/>">About</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="<c:url value = "/product"/>">Products
                            <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <c:forEach items="${category}" var="cat">
                                <li><a href="<c:url value = "/productbyid?cateid=${cat.cid}"/>">${cat.cname}</a></li>
                            </c:forEach>
                        </ul>
                    </li>
                    <li><a href="#">review</a></li>
                </ul>
    </nav>

    <div class="icons">
        <i class="fas fa-bars" id="menu-bars"></i>
        <i class="fas fa-search" id="search-icon"></i>
        <a href="#" class="fas fa-heart"></a>
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


<!-- menu section starts  -->

<section class="menu" id="menu">
    <br><br>
    <h3 class="sub-heading"> our product </h3>
    <h1 class="heading"> today's speciality </h1>

    <div class="box-container">

        <c:forEach items="${product}" var="pro">
            <form method="POST" action="CartController">
            <div class="box">
                <div class="image">
                    <img src="${pro.image}" alt="">
                    <a href="#" class="fas fa-heart"></a>
                    <a href="<c:url value = "/detailproductbyid?id=${pro.id}"/>" class="fas fa-eye"></a>
                </div>
                <div class="content">
                    <div class="stars">
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star"></i>
                        <i class="fas fa-star-half-alt"></i>
                    </div>
                    <input type="hidden" name="quantity" value="1" style="width: 30px; height: auto; background: #1A73E8">
                    <div>
                        <h3 style="margin: unset">${pro.name}</h3>
                        <input type="hidden" name="productId" value="${pro.id}">
                        <input type="hidden" name="pion" value="${pro.name}">
                    </div>
                    <input type="hidden" name="productId" value="${pro.id}">
                    <input type="hidden" name="description" value="${pro.name}">
                    <input type="hidden" name="price" value="${pro.price}">
                    <input type="hidden" name="image" value="${pro.image}">
                    <p>${ pro.description }</p>
                    <div style="display: flex; align-items: center">
                        <span class="price" style="margin-left: 0px">$ ${pro.price}</span>
                        <input type="submit" name="action" class="btn" value="Add To Cart" style="margin-top: 0px; margin-left: 20px">
                    </div>
                </div>
            </div>
            </form>
        </c:forEach>

    </div>

</section>

<!-- order section ends -->

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
            <a href="#">dishes</a>
            <a href="#">about</a>
            <a href="#">menu</a>
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
