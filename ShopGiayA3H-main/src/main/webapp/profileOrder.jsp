<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <!-- Custom Style -->
    <link rel="stylesheet" href="css/styleprofileoder.css">

    <title>Bill A3H</title>
</head>

<body>
<div class="my-5 page" size="A4">
    <div class="p-5">
        <section class="top-content bb d-flex justify-content-between">
            <div class="logo">
                <img src="logo.png" alt="" class="img-fluid">
            </div>
            <div class="top-left">
                <div class="graphic-path">
                    <p>Invoice</p>
                </div>
                <div class="position-relative">
                    <p>Invoice No. <span>8484</span></p>
                </div>
            </div>
        </section>

        <section class="store-user mt-5">
            <div class="col-10">
                <div class="row bb pb-3">
                    <div class="col-7">
                        <p>Supplier,</p>
                        <h2>Store A3H</h2>
                        <p class="address"> So 1 Vo Van Ngan, <br> Phuong Linh Chieu, <br>Thanh Pho Thu Duc, Viet Nam </p>
                        <div class="txn mt-2">MST: 9284212</div>
                    </div>
                    <div class="col-5">
                        <p>Client,</p>
                        <h2>${order.email}</h2>
                        <p class="address"> ${order.address}, <br>Viet Nam </p>
                        <div class="txn mt-2">OID: ${order.oid}</div>
                    </div>
                </div>
                <div class="row extra-info pt-3">
                    <div class="col-7">
                        <p>Payment Method: <span>Paypal</span></p>
                        <p>Order Number: <span>#${order.oid}</span></p>
                    </div>
                    <div class="col-5">
                        <p>Deliver Date: <span>${order.purchaseDate}</span></p>
                    </div>
                </div>
            </div>
        </section>

        <section class="product-area mt-4">
            <table class="table table-hover">
                <thead>
                <tr>
                    <td>Item Description</td>
                    <td>Price</td>
                    <td>Quantity</td>
                    <td>Total</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${listItem}" var="item">
                <tr>
                    <td>
                        <div class="media">
                            <div class="media-body">
                                <p class="mt-0 title">${item.productEntity.name}</p>
                                    ${item.productEntity.description}
                            </div>
                        </div>
                    </td>
                    <td>${item.productEntity.price}$</td>
                    <td>${item.quantity}</td>
                    <td>${item.productEntity.price * item.quantity}$</td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
        </section>

        <section class="balance-info">
            <div class="row">
                <div class="col-8">
                    <p class="m-0 font-weight-bold"> Note: </p>
                    <p>Your product will be delivered as soon as possible, thank you for your purchase.</p>
                </div>
                <div class="col-4">
                    <table class="table border-0 table-hover">
                        <tr>
                            <td>Sub Total:</td>
                            <td>${order.totalMoney - 50}$</td>
                        </tr>
                        <tr>
                            <td>Deliver:</td>
                            <td> 50.0$</td>
                        </tr>
                        <tfoot>
                        <tr>
                            <td>Total:</td>
                            <td>${order.totalMoney}$</td>
                        </tr>
                        </tfoot>
                    </table>

                    <!-- Signature -->
                    <div class="col-12">
                        <img src="signature.png" class="img-fluid" alt="">
                        <p class="text-center m-0"> Director Signature </p>
                    </div>
                </div>
            </div>
        </section>

        <!-- Cart BG -->
        <img src="cart.jpg" class="img-fluid cart-bg" alt="">

        <footer>
            <hr>
            <p class="m-0 text-center">
                View THis Invoice Online At - <a href="#!"> invoice/saburbd.com/#868 </a>
            </p>
            <div class="social pt-3">
                    <span class="pr-2">
                        <i class="fas fa-mobile-alt"></i>
                        <span>0123456789</span>
                    </span>
                <span class="pr-2">
                        <i class="fas fa-envelope"></i>
                        <span>a3h@gmail.com</span>
                    </span>
                <span class="pr-2">
                        <i class="fab fa-facebook-f"></i>
                        <span>/a3h.7264</span>
                    </span>
                <span class="pr-2">
                        <i class="fab fa-youtube"></i>
                        <span>/ShopGiayA3H</span>
                    </span>
                <span class="pr-2">
                        <i class="fab fa-github"></i>
                        <span>/ShopGiayA3H</span>
                    </span>
            </div>
        </footer>
    </div>
</div>
</body></html>