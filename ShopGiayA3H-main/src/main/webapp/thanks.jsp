<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Thanks</title>
<%--    <link rel="stylesheet" href="styles/main.css" type="text/css"/>--%>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>

<body>
    <div class="container" style="padding-top: 50px">
        <div class="row" style="margin-top: 80px">
            <div class="col-lg-6 pt-2" style="margin: auto; box-shadow: rgba(60, 64, 67, 0.3) 0px 1px 2px 0px, rgba(60, 64, 67, 0.15) 0px 2px 6px 2px;" >
                <div class="text-center mt-5">
                    <p class="h1" style="font-size: 35px;color: teal;">Thank You For Your Purchase</p>
                    <p>Here is the information that you entered:</p>
                    <form onsubmit="preventDefault()"\ action="<%=request.getContextPath()%>/emailList" method="get">
                        <p>Cảm ơn bạn đã mua hàng của Shop chúng tôi. Bạn vui lòng kiểm tra Email để xem chi tiết đơn hàng. Xin cảm ơn</p>
                            <input type="hidden" name="action" value="join" style="background: green">
                        <a href="<c:url value = "/index1"/>" class="btn btn-primary">Quay Lại Shop</a>
                        <div class="form-group" action="" method="post">
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>