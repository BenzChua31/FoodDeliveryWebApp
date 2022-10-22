<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js" integrity="sha384-ODmDIVzN+pFdexxHEHFBQH3/9/vQ9uori45z4JjnFsRydbmQbmL5t1tQ0culUzyK" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-3.6.1.min.js"
            integrity="sha256-o88AwQnZB+VDvE9tvIXrMQaPlFFSUTR+nldQm1LuPXQ="
            crossorigin="anonymous">
    </script>
    <%-- Dynamically set the base path for the entire page --%>
    <base href ="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <title>Coupon</title>
    <script type = "text/javascript">

        $(()=>{
            //Register button click event
            $("#page-close-btn").click(()=>{
                location.href = "index.jsp";
            })
        })
    </script>
</head>
<body>
    <!-- TopBar -->
    <div id="topBar" style="height: 55px; background-color: #29bfc2; width: 100%;">
        <!-- Text in topBar -->
        <div style="position: absolute; top: 12px; left: 80px; font-size: 20px; color: whitesmoke">Coupon Management</div>
        <!-- Close Button in topBar -->
        <div style="position: absolute; top: 14px; right: 11px;">
            <button type="button" id = "page-close-btn" class="btn-close" aria-label="Close"></button>
        </div>
    </div>

    <!-- All content below topBar-->
    <div style="position: absolute;top: 58px; bottom: 30px; left: 0px; right: 0px;">
        <div class = "row">
            <!-- Navbar -->
            <div class="col-3">
                <div class="nav navbar-light bg-light flex-column nav-pills me-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                    <button class="nav-link active" id="v-pills-create-tab" data-bs-toggle="pill" data-bs-target="#v-pills-create" type="button" role="tab" aria-controls="v-pills-create" aria-selected="true">Create Coupon</button>
                    <button class="nav-link" id="v-pills-list-tab" data-bs-toggle="pill" data-bs-target="#v-pills-list" type="button" role="tab" aria-controls="v-pills-list" aria-selected="false">Coupon Lists</button>
                    <button class="nav-link" id="v-pills-distribution-tab" data-bs-toggle="pill" data-bs-target="#v-pills-distribution" type="button" role="tab" aria-controls="v-pills-distribution" aria-selected="false">Distribute Coupon</button>
                    <button class="nav-link" id="v-pills-state-tab" data-bs-toggle="pill" data-bs-target="#v-pills-state" type="button" role="tab" aria-controls="v-pills-state" aria-selected="false">All Coupon State</button>
                </div>
            </div>
            <div class="col-9">
                <div class="tab-content" id="v-pills-tabContent">
                    <div class="tab-pane fade show active" id="v-pills-create" role="tabpanel" aria-labelledby="v-pills-create-tab" tabindex="0">
                        <form action="coupon/create" method="post">
                            <div class="row mb-3">
                                <label for="inputCouponName" class="col-sm-2 col-form-label">&#160;Coupon&#160;Name</label>
                                <div class="col-sm-4">
                                    <input type="email" class="form-control" id="inputCouponName" required>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label for="inputCouponScope" class="col-sm-2 col-form-label">&#160;Coupon&#160;Scope</label>
                                <div class="col-sm-4">
                                    <input type="email" class="form-control" id="inputCouponScope" required>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label for="inputCouponMoney" class="col-sm-2 col-form-label">&#160;Coupon&#160;Min Money</label>
                                <div class="col-sm-4">
                                    <input type="email" class="form-control" id="inputCouponMoney" required>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label for="inputCouponValue" class="col-sm-2 col-form-label">&#160;Coupon&#160;Value</label>
                                <div class="col-sm-4">
                                    <input type="email" class="form-control" id="inputCouponValue" required>
                                </div>
                            </div>
                            <div class="row mb-3">
                                <label for="inputCouponDescription" class="col-sm-2 col-form-label">&#160;Coupon&#160;Description</label>
                                <div class="col-sm-4">
                                    <input type="email" class="form-control" id="inputCouponDescription" required>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane fade" id="v-pills-list" role="tabpanel" aria-labelledby="v-pills-list-tab" tabindex="0">list</div>
                    <div class="tab-pane fade" id="v-pills-distribution" role="tabpanel" aria-labelledby="v-pills-distribution-tab" tabindex="0">distribute</div>
                    <div class="tab-pane fade" id="v-pills-state" role="tabpanel" aria-labelledby="v-pills-state-tab" tabindex="0">state</div>
                </div>
           </div>
        </div>
    </div>
</body>
</html>
