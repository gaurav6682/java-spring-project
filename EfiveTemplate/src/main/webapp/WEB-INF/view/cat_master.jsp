
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Emerging Five</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="vendors/iconfonts/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="vendors/iconfonts/puse-icons-feather/feather.css">
<link rel="stylesheet" href="vendors/css/vendor.bundle.base.css">
<link rel="stylesheet" href="vendors/css/vendor.bundle.addons.css">
<link rel="stylesheet" href="vendors/icheck/skins/all.css">
<!-- endinject -->

<!-- inject:css -->
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/custom.css">
<!-- endinject -->
<link rel="shortcut icon" href="images/favicon.png" />

<style>
    .pagpad {
        padding: 0.6rem;
      } 
</style>
</head>

<body class="">



	<div class="container-scroller">
		<!-- partial:partials/_navbar.html -->
		<nav
			class="navbar default-layout col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
			<div
				class="text-center navbar-brand-wrapper d-flex align-items-top justify-content-center">
				<a class="navbar-brand brand-logo" href="/efive/"></a> <a
					class="navbar-brand brand-logo-mini" href="/efive/"></a>
			</div>
			<div class="navbar-menu-wrapper d-flex align-items-center">
				<!--<button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
          <span class="mdi mdi-menu"></span>
        </button>-->

				<ul class="navbar-nav navbar-nav-right">

					<li class="nav-item dropdown d-none d-xl-inline-block"><a
						class="nav-link dropdown-toggle" id="UserDropdown" href="#"
						data-toggle="dropdown" aria-expanded="false"> <span
							class="mr-3">Hello Admin !</span><img
							class="img-xs rounded-circle" src="images/faces/face1.jpg"
							alt="Profile image">
					</a>
						<div class="dropdown-menu dropdown-menu-right navbar-dropdown"
							aria-labelledby="UserDropdown">
							<a class="dropdown-item" href="/efive/"> Sign Out </a>
						</div></li>
				</ul>
				<button
					class="navbar-toggler navbar-toggler-right d-lg-none align-self-center"
					type="button" data-toggle="offcanvas">
					<span class="icon-menu"></span>
				</button>
			</div>
		</nav>
		<!-- partial -->
		<div class="container-fluid page-body-wrapper">
			<nav class="sidebar sidebar-offcanvas" id="sidebar">
				<ul class="nav">

					<!--  <li class="nav-item"> <a class="nav-link" href="#"> <i class="fa fa-bar-chart p-r-1 font-1" aria-hidden="true"></i> <span class="menu-title">Dashboard</span></a> </li>  -->
					<li class="nav-item"><a class="nav-link"
						data-toggle="collapse" href="#tables" aria-expanded="false"
						aria-controls="tables"> <i class="fa fa-book p-r-1 font-1"
							aria-hidden="true"></i> <span class="menu-title">Master</span><i
							class="menu-arrow"></i></a>
						<div class="collapse" id="tables">
							<ul class="nav flex-column sub-menu">
                                <li class="nav-item"> <a class="nav-link" href="${currentContextPath}/efive/productmaster">Product Master</a></li>
								<li class="nav-item"><a class="nav-link" href="${currentContextPath}/efive/addcategory">Category Master</a></li>
								<li class="nav-item"><a class="nav-link" href="${currentContextPath}/efive/addsubcategory">Sub-Category Master</a></li>
							</ul>
						</div></li>
				</ul>
			</nav>
			<!-- partial -->
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="card">
						<div class="card-header">
							<h4 class="card-title mb-0 float-left m-t-4">Category Master</h4>

							<a data-toggle="modal" data-target="#add_category"
								data-keyboard="false" data-backdrop="static"
								class="btn btn-info btn-fw float-right btn-padding btn_bgcolor ml-2">Add
								Category</a> 
								<a href="${currentContextPath}/efive/download/category.xlsx" class="btn btn-icons btn-success dtbtn-padding float-right btn-excel" title="download excel"><i class="fa fa-file-excel-o" aria-hidden="true"></i></a>
						</div>
						<div class="card-body">
							<div class="row">
								<div class="col-12">
									<div class="table-responsive-lg">
									 <table id="paginatedcategoryTable" class="table table-bordered cat-master">
											<thead>
												<tr class="default_bgcolor">
													<th>Category ID</th>
													<th>Category Name</th>
													<th>Description</th>
													<th>Active</th>
													<th class="text-center">Actions</th>
												</tr>
											</thead>
											  <tbody>
							                    </tbody>
										</table>
										<%
										if(request.getAttribute("error")!=null){
											%>
											<script>
											alert("* Category Allready Exist.!!! \n* Please Enter UNIQUE Category Name.!!!");
											</script>
											<%}%>
										
									</div>
                                    
								</div>
							</div>
						</div>
					</div>
				</div>
				
				
			</h1>
				<!-- content-wrapper ends -->

				<!-- partial -->
			</div>
			<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->       
	<div class="modal fade text-xs-left " id="add_category" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content" >
				<div class="modal-header default_bgcolor">
					<h5 class="modal-title" id="exampleModalLabel-2">Add Category
						Master</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
				<form name="categoryForm" class="edit-category" id="categoryForm" enctype="multipart/form-data">
					<div class="row">
						<div class="col-12">
							
							<div class="form-group">
              				<input type="hidden" name="categoryid" id="categoryid" value="${categoryMaster.categoryid}">
              			
              			
							<label for="exampleInputEmail1">Category Name</label>
                  			<input type="text" class="form-control categoryname" name="categoryname" id="categoryname" placeholder="Enter Category Name"  value="${categoryMaster.categoryname}">
                  			</div>
						</div>
						

						<div class="col-12">
							<div class="form-group">
								<label for="exampleInputEmail1">Description</label>
								<textarea id="categorydescription" class="form-control"
									placeholder="Enter Description" name="categorydescription" value="${categoryMaster.categorydescription}"></textarea>
							</div>
						</div>

						<div class="form-group">
                        <div class="form-check m-t-5">
                        <label class="form-check-label">
                        
                          <input type="checkbox" class="form-check-input" id="active" name="active" value="Active" <c:if test="${fn:contains(categorymaster.active , 'Active') eq true}"><c:out value="checked"/></c:if>>
                          Active
                        </label>
                        </div>
                    </div>
					</div>
				</div>
				<div class="modal-footer">
				<div class="col-md-12" align="center">                    
                <button type="button" class="btn btn-success btn-padding save-category"   id="addcatbtn">save</button>
                <a href="${currentContextPath}/efive/addcategory"  class="btn btn-danger btn-padding" data-dismiss="modal">Cancel</a>
          </div>
				</div>
			</div>
		</div>
	</div>

	<!-- plugins:js -->
	<script src="vendors/js/vendor.bundle.base.js"></script>
	<script src="vendors/js/vendor.bundle.addons.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page-->
	<!-- End plugin js for this page-->
	<!-- inject:js -->
	<script src="js/off-canvas.js"></script>
	<script src="js/hoverable-collapse.js"></script>
	<script src="js/misc.js"></script>
	<script src="js/settings.js"></script>
	<script src="js/todolist.js"></script>
	<script src="js/iCheck.js"></script>
	<!-- endinject -->
	<!-- Custom js for this page-->
	 
	
	
	
	
	  //<script src="${currentContextPath}/efive/jspJs/product.js"></script>
	  <script src="${currentContextPath}/efive/jspJs/category.js"></script>
	  <script src="${currentContextPath}/efive/js/paginate.js"></script> 
	  <script src="${currentContextPath}/efive/jspJS/Datatable.js"></script> 
	<!-- End custom js for this page-->
</body>

</html>
