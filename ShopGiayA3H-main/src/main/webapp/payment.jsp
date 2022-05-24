<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Thanh Toan</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" />

    <!-- font awesome cdn link  -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <!-- custom css file link  -->
    <link rel="stylesheet" href="css/payment.css">
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-lg-4 mb-lg-0 mb-3">
            <div class="card p-3">
                <div class="img-box"> <img src="https://www.freepnglogos.com/uploads/visa-logo-download-png-21.png" alt=""> </div>
                <div class="number"> <label class="fw-bold">**** **** **** 1060</label> </div>
                <div class="d-flex align-items-center justify-content-between"> <small><span class="fw-bold">Expiry date:</span><span>8/12</span></small> <small><span class="fw-bold">Nation:</span><span>VN</span></small> </div>
            </div>
        </div>
        <div class="col-lg-4 mb-lg-0 mb-3">
            <div class="card p-3">
                <div class="img-box"> <img src="https://www.freepnglogos.com/uploads/mastercard-png/file-mastercard-logo-svg-wikimedia-commons-4.png" alt=""> </div>
                <div class="number"> <label class="fw-bold">**** **** **** 1060</label> </div>
                <div class="d-flex align-items-center justify-content-between"> <small><span class="fw-bold">Expiry date:</span><span>8/12</span></small> <small><span class="fw-bold">Nation:</span><span>VN</span></small> </div>
            </div>
        </div>
        <div class="col-lg-4 mb-lg-0 mb-3">
            <div class="card p-3">
                <div class="img-box"> <img src="https://www.freepnglogos.com/uploads/discover-png-logo/credit-cards-discover-png-logo-4.png" alt=""> </div>
                <div class="number"> <label class="fw-bold">**** **** **** 1060</label> </div>
                <div class="d-flex align-items-center justify-content-between"> <small><span class="fw-bold">Expiry date:</span><span>8/12</span></small> <small><span class="fw-bold">Nation:</span><span>VN</span></small> </div>
            </div>
        </div>
        <div class="col-12 mt-4">
            <div class="card p-3">
                <p class="mb-0 fw-bold h4">Payment Methods</p>
            </div>
        </div>
        <div class="col-12">
            <div class="card p-3">
                <div class="card-body border p-0">
                    <p> <a class="btn btn-primary w-100 h-100 d-flex align-items-center justify-content-between" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="true" aria-controls="collapseExample"> <span class="fw-bold">PayPal</span> <span class="fab fa-cc-paypal"> </span> </a> </p>
                    <div class="collapse p-3 pt-0">
                        <div class="row">
                            <div class="col-8">
                                <p class="h4 mb-0">Summary</p>
                                <p class="mb-0"><span class="fw-bold">Product:</span><span class="c-green">: Name of product</span></p>
                                <p class="mb-0"><span class="fw-bold">Price:</span><span class="c-green">:$${totalPrice}</span></p>
                                <p class="mb-0">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Atque nihil neque quisquam aut repellendus, dicta vero? Animi dicta cupiditate, facilis provident quibusdam ab quis, iste harum ipsum hic, nemo qui!</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card-body border p-0">
                    <p> <a class="btn btn-primary p-2 w-100 h-100 d-flex align-items-center justify-content-between" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="true" aria-controls="collapseExample"> <span class="fw-bold">Credit Card</span> <span class=""> <span class="fab fa-cc-amex"></span> <span class="fab fa-cc-mastercard"></span> <span class="fab fa-cc-discover"></span> </span> </a> </p>
                    <div class="collapse show p-3 pt-0" id="collapseExample">
                        <div class="row">
                            <div class="col-lg-5 mb-lg-0 mb-3">
                                <p class="h4 mb-0">Summary</p>
                                <p class="mb-0"><span class="fw-bold">Email:</span><span class="c-green">: ${email}</span> </p>
                                <p class="mb-0"> <span class="fw-bold">Price:</span> <span class="c-green">:$${totalPrice}</span> </p>
                                <p class="mb-0">Please login paypal and pay, please check the money again before confirming payment!</p>
                            </div>
                            <div class="col-lg-7">
                                <form action="" class="form">
                                    <div class="row">
                                        <div class="col-12">
                                            <div class="form__div"> <input type="text" class="form-control" placeholder=" "> <label class="form__label">Card Number</label> </div>
                                        </div>
                                        <div class="col-6">
                                            <div class="form__div"> <input type="text" class="form-control" placeholder=" "> <label class="form__label">MM / yy</label> </div>
                                        </div>
                                        <div class="col-6">
                                            <div class="form__div"> <input type="password" class="form-control" placeholder=" "> <label class="form__label">cvv code</label> </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="form__div"> <input type="text" class="form-control" placeholder=" "> <label class="form__label">name on the card</label> </div>
                                        </div>
                                        <div class="col-12">
                                            <div class="btn btn-primary w-100">Sumbit</div><br>
                                            <a href="<c:url value = "/index.jsp"/>" style="">Comback Home</a>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-12">
            <div id="paypal-button-container" style="display: flex; justify-content: center; align-items: center;"></div>
            <!-- Include the PayPal JavaScript SDK -->
            <script src="https://www.paypal.com/sdk/js?client-id=test&currency=USD"></script>
            <script>
                // Render the PayPal button into #paypal-button-container
                paypal.Buttons({

                    // Set up the transaction
                    createOrder: function(data, actions) {
                        return actions.order.create({
                            purchase_units: [{
                                amount: {
                                    value: '${totalPrice}'
                                }
                            }]
                        });
                    },

                    // Finalize the transaction
                    onApprove: function(data, actions) {
                        return actions.order.capture().then(function(orderData) {
                            // Successful capture! For demo purposes:
                            console.log('Capture result', orderData, JSON.stringify(orderData, null, 2));
                            var transaction = orderData.purchase_units[0].payments.captures[0];
                            alert('Transaction '+ transaction.status + ': ' + transaction.id + '\n\nSee console for all available details');

                            // Replace the above to show a success message within this page, e.g.
                            // const element = document.getElementById('paypal-button-container');
                            // element.innerHTML = '';
                            // element.innerHTML = '<h3>Thank you for your payment!</h3>';
                            // Or go to another URL:  actions.redirect('thank_you.html');
                        });
                    }
                }).render('#paypal-button-container');
            </script>
        </div>
    </div>
</div>

</body>
</html>