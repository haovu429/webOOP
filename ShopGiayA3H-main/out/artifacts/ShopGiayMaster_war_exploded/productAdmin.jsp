<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="assents/img/apple-icon.png">
    <link rel="icon" type="image/png" href="assents/img/favicon.ico">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Product</title>
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
                    <div class="col-lg-5 grid-margin stretch-card" style="height: 570px; overflow: auto;">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Product</h4>
                                </p>
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th style="width: 120px;"></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${product}" var="pro">
                                    <tr onclick="setname(${pro.id}, '${pro.name}', ${pro.price}, '${pro.title}',${pro.cateId.cid}, '${pro.description}', '${pro.image}', ${pro.saleId})">
                                        <td>${pro.name}</td>
                                        <td>${pro.price}</td>
                                        <td style="width: 120px;">
                                            <form action="delete">
                                                <input type="hidden" name="id" value="${pro.id}">
                                                <a class="btn btn-danger btn-round btn-fill" href="" onclick="showMess(${pro.id})" >Delete</a>
                                            </form>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 grid-margin stretch-card">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Detail</h4>
                                <form class="forms-sample" action="create" method="post">
                                    <div style="display: flex;">
                                        <div style="width: 380px;">
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input type="hidden" id="pid" name="pid">
                                                <input type="text" class="form-control" id="name" placeholder="Name" name="name">
                                            </div>
                                            <div class="form-group">
                                                <label>Title</label>
                                                <input type="text" class="form-control" id="title" placeholder="title" name="title">
                                            </div>
                                            <div class="form-group">
                                                <label>Cate ID</label>
                                                <select name="cid" class="form-control" id="cid">
                                                    <c:forEach items="${categories}" var="cate">
                                                        <option value="${cate.cid}">${cate.cname}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label>Sale ID</label>
                                                <input type="text" class="form-control" id="saleID" placeholder="saleID" name="saleID">
                                            </div>
                                            <div class="form-group">
                                                <label>Price</label>
                                                <input type="text" class="form-control" id="price" placeholder="Price" name="price">
                                            </div>
                                            <div class="form-group">
                                                <label>Description</label>
                                                <textarea name="description" id="description" class="form-control" cols="30" rows="5"
                                                          placeholder="Description"></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <input type="hidden" id="images" name="images">
                                    <input class="btn btn-success btn-fill" type="submit" name="action" value="Create">
                                    <input class="btn btn-warning btn-fill" type="submit" name="action" value="Update">
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-3">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Image</h4>
                                <input type="hidden" name="" id="namebox" width="180px"> <label id="extlab" style="display: none"></label><br>
                                <img id="myimg" width="250px" height="250px">
                                <div style="margin-top: 10px">
                                    <button id="selbtn" class="btn btn-info btn-fill">Choose</button>
                                    <button id="upbtn" class="btn btn-info btn-fill">Save</button> <label id="upprogress"></label>
                                </div>
                                <div>

                                </div>
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



<script type="module">
    import { initializeApp } from "https://www.gstatic.com/firebasejs/9.6.0/firebase-app.js";

    const firebaseConfig = {
        apiKey: "AIzaSyAAb7hR7fD7_hjoo_aCB6uVyKiB4uI-Iz8",
        authDomain: "web-servlet.firebaseapp.com",
        databaseURL: "https://web-servlet-default-rtdb.firebaseio.com",
        projectId: "web-servlet",
        storageBucket: "web-servlet.appspot.com",
        messagingSenderId: "770967001928",
        appId: "1:770967001928:web:1b5b7a250e6d659b58717d"
    };

    // Initialize Firebase
    const app = initializeApp(firebaseConfig);

    import {getStorage, ref as sRef, uploadBytesResumable, getDownloadURL }
        from "https://www.gstatic.com/firebasejs/9.6.0/firebase-storage.js";

    //Firebase Realtime Database
    import { getDatabase, ref, set, child, get, update, remove }
        from "https://www.gstatic.com/firebasejs/9.6.0/firebase-database.js";
    const realdb = getDatabase();


    // ------------------VARIABLES $ REFERENCES---------------------------
    var files = [];
    var reader = new FileReader();

    var namebox = document.getElementById('namebox');
    var extlab = document.getElementById('extlab');
    var myimg = document.getElementById('myimg');
    var proglab = document.getElementById('upprogress');
    var SelBtn = document.getElementById('selbtn');
    var UpBtn = document.getElementById('upbtn');
    var DownBtn = document.getElementById('downbtn');
    var imagetext = document.getElementById('images');

    var input = document.createElement('input');
    input.type = 'file';

    input.onchange = e =>{
        files = e.target.files;

        var extention = GetFileExt(files[0]);
        var name = GetFileName(files[0]);

        namebox.value = name;
        extlab.innerHTML = extention;

        reader.readAsDataURL(files[0]);

    }

    reader.onload = function(){
        myimg.src = reader.result;
    }

    // -----------SELECTION-----------------------------------------

    SelBtn.onclick = function(){
        input.click();
    }

    function GetFileExt(file) {
        var temp = file.name.split('.');
        var ext = temp.slice((temp.length-1),(temp.length));
        return '.' + ext[0];
    }

    function GetFileName(file) {
        var temp = file.name.split('.');
        var fname = temp.slice(0,-1).join('.');
        return fname;
    }

    // -----------------UPLOAD PROCESS----------------------
    async function UploadProcess() {
        var ImgToUpload = files[0];

        var ImgName = namebox.value + extlab.innerHTML;

        if(!ValidateName()){
            alert('name cannot contain ".", "#", "$", "[", or "]"');
            return;
        }

        const metaData = {
            contentType: ImgToUpload.type
        }

        const storage = getStorage();

        const strorageRef = sRef(storage, "Images/"+ImgName);

        const UploadTask = uploadBytesResumable(strorageRef, ImgToUpload, metaData);

        UploadTask.on('state-changed', (snapshot)=>{
                var progess = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
                proglab.innerHTML = "Upload" + progess + "%";
            },

            (error) =>{
                alert("error: image not uploaded!");
            },

            ()=>{
                getDownloadURL(UploadTask.snapshot.ref).then((downloadURL)=>{
                    SaveURLtoRealtimeDB(downloadURL);
                    GetUrlfromRealtimeDB();
                });
            }
        );
    }

    // ----------Functions for REALTIME DATABASE ----------

    function SaveURLtoRealtimeDB(URL) {
        var name = namebox.value;
        var ext = extlab.innerHTML;

        set(ref(realdb,"ImageLinks/"+name),{
            ImageName: (name+ext),
            ImgUrl: URL
        });
    }

    function GetUrlfromRealtimeDB() {
        var name = namebox.value;

        var dbRef = ref(realdb);

        get(child(dbRef, "ImageLinks/"+name)).then((snapshot)=>{
            if(snapshot.exists()){
                myimg.src = snapshot.val().ImgUrl;
                imagetext.value = snapshot.val().ImgUrl;
            }

            // console.log(snapshot.val().ImgUrl);
        })
    }

    function ValidateName(){
        var regex = /[\.#$\[\]]/
        return !(regex.test(namebox.value));
    }

    UpBtn.onclick = UploadProcess;
    DownBtn.onclick = GetUrlfromRealtimeDB;
</script>

<script>
    function showMess(id){
        var option = confirm('Are you sure to delete');
        if(option === true){
            window.location.href='delete?pid='+id;
        }
    }
</script>

<script>
    function setname(id, name, price, title, cateid, description, image, saleid){
        document.getElementById('name').value = name;
        document.getElementById('price').value = price;
        document.getElementById('title').value = title;
        document.getElementById('description').value = description;
        document.getElementById('myimg').setAttribute('src',image);
        document.getElementById('images').value = image;
        document.getElementById('saleID').value = saleid;
        document.getElementById('cid').value = cateid;
        document.getElementById('pid').value = id;
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
