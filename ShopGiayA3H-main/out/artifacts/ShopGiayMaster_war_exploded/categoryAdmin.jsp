<%--
  Created by IntelliJ IDEA.
  User: HieuNguyen
  Date: 12/4/2021
  Time: 9:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="assents/img/apple-icon.png">
    <link rel="icon" type="image/png" href="assents/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Light Bootstrap Dashboard - Free Bootstrap 4 Admin Dashboard by Creative Tim</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
    <!--     Fonts and icons     -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />
    <!-- CSS Files -->
    <link href="assents/cssadmin/bootstrap.min.css" rel="stylesheet" />
    <link href="assents/cssadmin/light-bootstrap-dashboard.css?v=2.0.0 " rel="stylesheet" />
    <!-- CSS Just for demo purpose, don't include it in your project -->
    <link href="assents/cssadmin/demo.css" rel="stylesheet" />
</head>

<body>
<div class="wrapper">
    <c:import url="/sidebar.jsp"/>
    <div class="main-panel">
        <!-- Navbar -->
        <c:import url="/headerAdmin.jsp"/>
        <!-- End Navbar -->
        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Category</h4>
                                </p>
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th id="hello">Name</th>
                                        <th style="width: 120px;"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${category}" var="cate">
<%--                                        <c:out value='${cate.cname}'/>--%>
                                        <tr onclick="setNamecate(${cate.cid}, '${cate.cname}')">
                                            <td><span id="td-name">${cate.cname}</span></td>
                                            <td style="width: 120px;">
                                                <a href="filterProduct?cateid=${cate.cid}" class="btn btn-info btn-round btn-fill">Show</a>
                                            </td>
                                            <td style="width: 120px;">
                                                <a class="btn btn-danger btn-round btn-fill" href="" onclick="showMess(${cate.cid})" >Delete</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-6" style="height: 300px;">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Detail</h4>
                                <form action="createCategory" method="post">
                                    <div class="form-group">
                                        <input type="hidden" name="cid" id="cid">
                                        <label>Name</label>
                                        <input type="text" class="form-control" id="cname" placeholder="Name" name="cname">
                                    </div>
                                    <input type="submit" class="btn btn-success btn-fill"name="action" value="Create">
                                    <input type="submit" class="btn btn-warning btn-fill"name="action" value="Update">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer class="footer">
            <div class="container-fluid">
                <nav>
                    <ul class="footer-menu">
                        <li>
                            <a href="#">
                                Home
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Company
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Portfolio
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                Blog
                            </a>
                        </li>
                    </ul>
                    <p class="copyright text-center">
                        ©
                        <script>
                            document.write(new Date().getFullYear())
                        </script>
                        <a href="http://www.creative-tim.com">Creative Tim</a>, made with love for a better web
                    </p>
                </nav>
            </div>
        </footer>
    </div>
</div>
</body>
<!--   Core JS Files   -->

<script>
    function showMess(cid){
        var option = confirm('Are you sure to delete');
        if(option === true){
            window.location.href='deleteCategory?id='+cid;
        }
    }
</script>
<script type="text/javascript">
    function setNamecate(id, name){
        document.getElementById("cid").value = id;
        document.getElementById("cname").value = name;
    }
</script>

<script src="assents/js/core/jquery.3.2.1.min.js" type="text/javascript"></script>
<script src="assents/js/core/popper.min.js" type="text/javascript"></script>
<script src="assents/js/core/bootstrap.min.js" type="text/javascript"></script>
<!--  Plugin for Switches, full documentation here: http://www.jque.re/plugins/version3/bootstrap.switch/ -->
<script src="assents/js/plugins/bootstrap-switch.js"></script>
<!--  Google Maps Plugin    -->
<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY_HERE"></script>
<!--  Chartist Plugin  -->
<script src="assents/js/plugins/chartist.min.js"></script>
<!--  Notifications Plugin    -->
<script src="assents/js/plugins/bootstrap-notify.js"></script>
<!-- Control Center for Light Bootstrap Dashboard: scripts for the example pages etc -->
<script src="assents/js/light-bootstrap-dashboard.js?v=2.0.0 " type="text/javascript"></script>
<!-- Light Bootstrap Dashboard DEMO methods, don't include it in your project! -->
<script src="assents/js/demo.js"></script>

</html>

