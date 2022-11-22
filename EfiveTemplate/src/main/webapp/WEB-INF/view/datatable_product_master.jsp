<%@taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Emerging Five</title>
  <!-- plugins:css -->
  <s:eval expression="@environment.getProperty('server.servlet.context-path')" var="currentContextPath"></s:eval>
  <link rel="stylesheet" href="${currentContextPath}/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
  <link rel="stylesheet" href="${currentContextPath}/vendors/iconfonts/puse-icons-feather/feather.css">
  <link rel="stylesheet" href="${currentContextPath}/vendors/css/vendor.bundle.base.css">
  <link rel="stylesheet" href="${currentContextPath}/vendors/css/vendor.bundle.addons.css">
  <link rel="stylesheet" href="${currentContextPath}/vendors/icheck/skins/all.css">
  <!-- endinject -->
  
  <!-- inject:css -->
  <link rel="stylesheet" href="${currentContextPath}/font-awesome/css/font-awesome.min.css">
  <link rel="stylesheet" href="${currentContextPath}/css/style.css">
  <link rel="stylesheet" href="${currentContextPath}/css/custom.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="${currentContextPath}/images/favicon.png" />
  <style>
    .pagpad {
        padding: 0.6rem;
      }  
  </style>
  
  <script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>

	<%-- <script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" th:src="@{js/admin.js}"></script> --%>
	
	<script type="text/javascript"
		src="http://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
  
</head>

<body class="sidebar-fixed">
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->
    <nav class="navbar default-layout col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
      <div class="text-center navbar-brand-wrapper d-flex align-items-top justify-content-center">
        <a class="navbar-brand brand-logo" href="/efive/"></a>
        <a class="navbar-brand brand-logo-mini" href="/efive/"></a>
      </div>
      <div class="navbar-menu-wrapper d-flex align-items-center">
        <!--<button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
          <span class="mdi mdi-menu"></span>
        </button>-->
        
        <ul class="navbar-nav navbar-nav-right">
      
          <li class="nav-item dropdown d-none d-xl-inline-block">
            <a class="nav-link dropdown-toggle" id="UserDropdown" href="#" data-toggle="dropdown" aria-expanded="false">
              <span class="mr-3">Hello Admin !</span><img class="img-xs rounded-circle" src="images/faces/face1.jpg" alt="Profile image">
            </a>
            <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="UserDropdown">
              
              <a class="dropdown-item" href="/efive/">
                Log Out
              </a>
            </div>
          </li>
        </ul>
        <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
          <span class="icon-menu"></span>
        </button>
      </div>
    </nav>
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      
      <nav class="sidebar sidebar-offcanvas" id="sidebar">
        <ul class="nav">          
         <!--  <li class="nav-item"> <a class="nav-link" href="#"> <i class="fa fa-bar-chart p-r-1 font-1" aria-hidden="true"></i> <span class="menu-title">Dashboard</span></a> </li>     -->      
          <li class="nav-item">
            <a class="nav-link" data-toggle="collapse" href="#tables" aria-expanded="true" aria-controls="tables"> <i class="fa fa-book p-r-1 font-1" aria-hidden="true"></i> <span class="menu-title">Master</span><i class="menu-arrow"></i></a>
            <div class="collapse" id="tables">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item"> <a class="nav-link" href="${currentContextPath}/productmaster">Product Master</a></li>
                <li class="nav-item"> <a class="nav-link" href="${currentContextPath}/addcategory">Category Master</a></li>
                <li class="nav-item"><a class="nav-link" href="${currentContextPath}/addsubcategory">Sub-Category Master</a></li>
              </ul>
            </div>
          </li>          
        </ul>
      </nav>
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="card">
              <div class="card-header">
                    <h4 class="card-title mb-0 float-left m-t-4">Product Master</h4>                  
                  <a href="addproduct" class="btn btn-info btn-fw float-right btn-padding btn_bgcolor ml-2">Add Product</a>
                  <a href="${currentContextPath}/download/product.xlsx" class="btn btn-icons btn-success dtbtn-padding float-right btn-excel" title="download excel"><i class="fa fa-file-excel-o" aria-hidden="true"></i></a>
              </div>
            <div class="card-body">
              
              <div class="row">
                <div class="col-12">
				<div class="table-responsive-lg">
                  <table id="paginatedTable" class="table table-bordered cat-master">
                    <thead>
                      <tr class="default_bgcolor">
                          <th>Product ID</th>
                          <th>Product Name</th>
                          <th>Description</th>
                          <th>Category</th>
                          <th>Price</th>
                          <th>Discount</th>
                          <th class="text-center">Image</th>
                          <th>Active</th>                          
                          <th class="text-center" >Actions</th>
                      </tr>
                    </thead>
                    <tbody>
                    </tbody>
                  </table>
				  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->

  <!-- plugins:js -->
  <script src="${currentContextPath}/vendors/js/vendor.bundle.base.js"></script>
  <script src="${currentContextPath}/js/data-table.js"></script>
  <script src="${currentContextPath}/vendors/js/vendor.bundle.addons.js"></script>
  <!-- endinject -->
  <!-- Plugin js for this page-->
  <!-- End plugin js for this page-->
  <!-- inject:js -->
  <script src="${currentContextPath}/js/off-canvas.js"></script>
  <script src="${currentContextPath}/js/hoverable-collapse.js"></script>
  <script src="${currentContextPath}/js/misc.js"></script>
  <script src="${currentContextPath}/js/settings.js"></script>
  <script src="${currentContextPath}/js/todolist.js"></script>
  <script src="${currentContextPath}/js/typeahead.js"></script>  
  <script src="${currentContextPath}/js/select2.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="${currentContextPath}/js/paginate.js"></script>
  <script src="${currentContextPath}/jspJS/Datatable.js"></script>  
  <!-- End custom js for this page-->
</body>

</html>
