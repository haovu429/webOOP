<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 12/7/2021
  Time: 4:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>

<html lang="en">

<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="assents/img/apple-icon.png">
    <link rel="icon" type="image/png" href="assents/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
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
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
                    <div class="col-md-4 stretch-card grid-margin">
                        <div class="card bg-danger card-img-top text-white">
                            <div class="card-body">
                                <h4 class="font-weight-normal mb-3">monthly revenue
                                    <i class="nc-icon nc-chart-bar-32 float-right"></i>
                                </h4>
                                <h2 class="mb-5">$ ${revenueM.get(0)}</h2>
                                <c:set var="reve" value="${revenueM.get(0)}"></c:set>
                                <c:if test="${reve >= 0}">
                                    <h6 class="card-text">Increased by ${revenueM.get(1)}%</h6>
                                </c:if>
                                <c:if test="${reve < 0}">
                                    <h6 class="card-text">Decreased by ${revenueM.get(1)*-1}%</h6>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4 stretch-card grid-margin">
                        <div class="card bg-info card-img-top text-white">
                            <div class="card-body">
                                <h4 class="font-weight-normal mb-3">Monthly Orders <i
                                        class="nc-icon nc-app float-right"></i>
                                </h4>
                                <h2 class="mb-5">${orderM.get(0)}</h2>
                                <c:set var="ord" value="${orderM.get(0)}"></c:set>
                                <c:if test="${ord >= 0}">
                                    <h6 class="card-text">Increased by ${orderM.get(1)}%</h6>
                                </c:if>
                                <c:if test="${ord < 0}">
                                    <h6 class="card-text">Decreased by ${orderM.get(1)*-1}%</h6>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-7">
                        <div class="card">
                            <div class="card-header ">
                                <h4 class="card-title">Doanh thu từng tháng</h4>
                            </div>
                            <div class="card-body">
                                <canvas id="line-chart"></canvas>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <div class="card">
                            <div class="card-header ">
                                <h4 class="card-title">Danh mục sản phẩm được mua nhiều</h4>
                            </div>
                            <div class="card-body">
                                <canvas id="doughnut-chart"></canvas>
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
<!--   -->

<%--<div id="chart_div" style="width: 1200px; height: 500px;margin-left: 20px"></div>--%>
</body>
<script>
    new Chart(document.getElementById("doughnut-chart"), {
        type: 'doughnut',
        data: {
            // label: 'Expenditures',
            labels: [<c:forEach items="${reportDoughnut}" var="repD">
                '${repD[1]}',
                </c:forEach>],
            datasets: [{
                data: [<c:forEach items="${reportDoughnut}" var="repD">
                    ${repD[0]},
                    </c:forEach>],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.5)',
                    'rgba(54, 162, 235, 0.5)',
                    'rgba(255, 206, 86, 0.5)',
                    'rgba(75, 192, 192, 0.5)',
                    'rgba(153, 102, 255, 0.5)',
                    'rgba(255, 159, 64, 0.5)',
                    'rgba(99, 199, 245, 0.5)',
                    'rgba(224, 142, 142, 0.5)',
                    'rgba(255, 64, 217, 0.5)',
                    'rgba(253, 221, 132, 0.5)'
                ],
                borderColor: [
                    'rgba(255,99,132,1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)',
                    'rgba(99, 199, 245, 1)',
                    'rgba(224, 142, 142, 1)',
                    'rgba(255, 64, 217, 1)',
                    'rgba(253, 221, 132, 1)'
                ],
                fill: false,
            }]
        },
        options: {
            title: {
                display: true,
                text: 'Doanh thu từng tháng'
            }
        }
    });
</script>

<script>
    new Chart(document.getElementById("line-chart"), {
        type: 'line',
        data: {
            labels: [
                <c:forEach items="${reportLine}" var="repL">
                '${repL[0]}',
                </c:forEach>
            ],
            datasets: [{
                data: [
                    <c:forEach items="${reportLine}" var="repL">
                    '${repL[1]}',
                    </c:forEach>
                ],
                label: "Doanh thu ($)",
                borderColor: 'rgba(255,99,132,1)',
                fill: false
            }
            ]
        },
        options: {
            title: {
                display: true,
                text: 'Doanh thu từng tháng'
            }
        }
    });
</script>
<!--   Core JS Files   -->
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
<%--<script type="text/javascript">--%>
<%--    $(document).ready(function() {--%>
<%--        // Javascript method's body can be found in assents/js/demos.js--%>
<%--        demo.initDashboardPageCharts();--%>

<%--        demo.showNotification();--%>

<%--    });--%>
<%--</script>--%>

</html>


