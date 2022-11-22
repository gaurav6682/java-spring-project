	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">

<head>
	
	
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Emerging Five</title>
  <s:eval
	expression="@environment.getProperty('server.servlet.context-path')"
	var="currentContextPath"></s:eval>
  <!-- plugins:css -->
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
  <link rel="shortcut icon" href="${currentContrextPath}/images/favicon.png" />
    <style>
        .p-l-9 {
            padding-left: 9px !important;
        }
        .pagpad {
        padding: 0.6rem;
      } 
    </style>
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
          <li class="nav-item active">
            <a class="nav-link" data-toggle="collapse" href="#tables" aria-expanded="true" aria-controls="tables"> <i class="fa fa-book p-r-1 font-1" aria-hidden="true"></i> <span class="menu-title">Master</span><i class="menu-arrow"></i></a>
            <div class="collapse show" id="tables">
              <ul class="nav flex-column sub-menu">
                <li class="nav-item"> <a class="nav-link active" href="${currentContextPath}/productmaster">Product Master</a></li>
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
              			<c:set var="productMaster" value="${editproduct}"></c:set>
	          			    <c:set var="pageTitle" value="Add Product Master"></c:set>
	           				<c:if test="${!empty productMaster}">
	              				<c:set var="pageTitle" value="Update Product Master"></c:set>
	              			</c:if>
                    <h4 class="card-title mb-0 float-left m-t-4">${pageTitle}</h4>                  
                    <a href="/efive/productmaster" class="btn btn-info btn-fw float-right btn-padding btn_bgcolor ml-2">Back</a>
              </div>
            <div class="card-body">
              <form name="productForm" id="productForm" enctype="multipart/form-data">
                <div class="row">
                			<div class="col-6">
                            	<div class="form-group">
                          			<label>Product Name</label>
                          			<c:if test="${!empty productMaster.productid}">
                          				<input type="hidden" name="productid" value="${productMaster.productid}">
                          			</c:if>
                          			<input type="text" class="form-control" name="productname" id="productname" placeholder="Enter Product Name"  value="${productMaster.productname}">
                        		</div>
                            </div>
                              
                            <div class="col-6">
                            	<div class="form-group">
                          			<label>Description</label>
                          			<textarea id="productdescription" class="form-control" placeholder="Enter Description" name="productdescription">${productMaster.productdescription}</textarea>
                        		</div>
                            </div>
                                                        
                           </div>
                
                        <div class="row">
                            <div class="col-3">
                                <div class="form-group">
                          			<label>Category</label>
                          			<select class="js-example-basic-single" style="width: 100%"
												id="categoryid" name="categoryid">
												<option value="-1">--Select Category--</option>
												<c:forEach items="${categoryList}" var="cat">
													<option value="${cat[0]}" ${(productMaster.categoryid eq cat[0]) ? 'selected' : '' }>${cat[3]}</option>
												</c:forEach>
											</select>
                        		</div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                          			<label>Sub Category</label>
                          			<select class="js-example-basic-single" style="width:100%" id="subcategoryid" name="subcategoryid">
                          				<option value="-1">--Select Sub-Category--</option>
												<c:forEach items="${subCategoryList}" var="subcat">
													<option value="${subcat[0]}" ${(productMaster.subcategoryid eq subcat[0]) ? 'selected' : '' }>${subcat[1]}</option>
												</c:forEach>
                        			</select>
                        		</div>
                            </div>
                			<div class="col-3">
                                <div class="form-group">
                          			<label>Price</label>
                          			<input type="text" class="form-control text-right" id="productprice" name="productprice" placeholder="Enter Price" value="${productMaster.productprice}">
                        		</div>
                            </div>
                            
                            <div class="col-3">
                                <div class="form-group">
                                    <label>Manufacture Date</label>
                                    <div id="datepicker-popup" class="input-group date datepicker">
                                        <input type="text" class="form-control" name="manufacturedate" id="manufacturedate" value="${productMaster.manufacturedate}">
                                        <span class="input-group-addon input-group-append border-left">
                                          <span class="mdi mdi-calendar input-group-text"></span>
                                        </span>
                                      </div>
                                </div>
                            </div>
                       </div>
                
                        <div class="row">
                            <div class="col-3">
                                <div class="form-group">
                          			<label>Product Serial No.</label>
                          			<input type="text" class="form-control" id="productserialno" name="productserialno" placeholder="Enter Serial No." value="${productMaster.productserialno}">
                        		</div>
                            </div>
                            
                            <div class="col-4">
                            	<div class="form-group">
                          			<label>Warranty &amp; Support</label>
                          			<textarea id="productwarrenty" class="form-control" placeholder="Enter Warranty & Support" name="productwarrenty">${productMaster.productwarrenty}</textarea>
                        		</div>
                            </div>
                            <div class="col-3">
                                <div class="form-group">
                          			<label>Image Upload</label>
                          			<label id="projectinput8" class="file" style="display: block;margin-top: 10px;">
											<input type="file" id="productimage" name="productimagedata" style="line-height: 20px;">
											<span class="file-custom"></span>
								    </label>
                        		</div>
                            </div>
                            <div class="col-2" >
                            
                            	<img src="/${(empty productMaster.productimage)? 'images/empty1.png' : productMaster.productimage}" id="imgPreview" alt="image" class="img-responsive" style="width: 100px; height: 100px;">
                            </div>
                        </div>
                
                        <div class="row">
                            <div class="col-4">
                                <div class="form-group">
                          			<label>Product Condition</label>
                          			<div class="row">
                                    <div class="col-3">
                          			<div class="form-radio m-t-5">
                                        <label class="form-check-label">
                                          <input type="radio" class="form-check-input" name="productcondition" id="productcondition1" value="Old" <c:if test="${fn:contains(productMaster.productcondition, 'Old') eq true}"><c:out value="checked"/></c:if>>
                                          Old
                                        </label>
                                    </div>
                                    </div>
                                    <div class="col-3">
                                    <div class="form-radio m-t-5">
                                        <label class="form-check-label">
                                          <input type="radio" class="form-check-input" name="productcondition" id="productcondition2" value="New" <c:if test="${fn:contains(productMaster.productcondition, 'New') eq true}"><c:out value="checked"/></c:if>>
                                          New
                                        </label>
                                    </div>
                                    </div>
                                    <div class="col-6">
                                    <div class="form-radio m-t-5">
                                        <label class="form-check-label">
                                          <input type="radio" class="form-check-input" name="productcondition" id="productcondition3" value="Refurbished" <c:if test="${fn:contains(productMaster.productcondition, 'Refurbished') eq true}"><c:out value="checked"/></c:if>>
                                          Refurbished
                                        </label>
                                    </div>
                                    </div>
                                    </div>
                        		</div>
                            </div>
                            
                            <div class="col-6">
                                <div class="form-group">
                          			<label>Product Colour</label>
                                    <div class="row">
                                    <div class="col-3">
                          			<div class="form-check m-t-5">
                                    <label class="form-check-label">
                                      <input type="checkbox" class="form-check-input" name="productcolor" id="productcolor" value="Red" <c:if test="${fn:contains(productMaster.productcolor, 'Red') eq true}"><c:out value="checked"/> </c:if>>
                                      Red
                                    </label>
                                    </div>
                                    </div>
                                    <div class="col-3">
                                    <div class="form-check m-t-5">
                                    <label class="form-check-label">
                                      <input type="checkbox" class="form-check-input" name="productcolor" id="productcolor" value="Black" <c:if test="${fn:contains(productMaster.productcolor, 'Black') eq true}"><c:out value="checked"/> </c:if>>
                                      Black
                                    </label>
                                    </div>
                                    </div>
                                    <div class="col-3">
                                    <div class="form-check m-t-5">
                                    <label class="form-check-label">
                                      <input type="checkbox" class="form-check-input" name="productcolor" id="productcolor" value="Gold" <c:if test="${fn:contains(productMaster.productcolor, 'Gold') eq true}"><c:out value="checked"/> </c:if>>
                                      Gold
                                    </label>
                                    </div>
                                    </div>
                                    <div class="col-3">
                                    <div class="form-check m-t-5">
                                    <label class="form-check-label">
                                      <input type="checkbox" class="form-check-input" name="productcolor" id="productcolor" value="Blue" <c:if test="${fn:contains(productMaster.productcolor, 'Blue') eq true}"><c:out value="checked"/> </c:if>>
                                      Blue
                                    </label>
                                    </div>
                                    </div>
                                    </div>
                        		</div>
                            </div>
                        </div>
                <div class="row m-b-10 rowheader">
                    <div class="col-md-12"><h5 class="mb-0 m-t-5">Product Discount</h5></div>
                </div>                
                                                                                              
                <div class="row">
                <div class="col-2">
                    <div class="form-group">
                        <label>Discount</label>
                        <input type="text" class="form-control text-right" id="productdiscount" name="productdiscount" placeholder="Enter Discount" value="${productMaster.productdiscount}">
                    </div>
                </div>
                <div class="col-3">
                    <div class="form-group">
                        <label>Valid From</label>
                        <div id="datepicker-popup-new" class="input-group date datepicker">
                            <input type="text" class="form-control" name="productvalidDate" id="productvalidDate" value="${productMaster.productvalidDate}">
                            <span class="input-group-addon input-group-append border-left">
                              <span class="mdi mdi-calendar input-group-text"></span>
                            </span>
                          </div>
                    </div>
                </div>
                
                  <div class="col-3">
                    <div class="form-group">
                        <label>Valid To</label>
                        <div id="datepicker-popup-to" class="input-group date datepicker">
                            <input type="text" class="form-control" name="productvalidtoDate" id="productvalidtoDate" value="${productMaster.productvalidtoDate}">
                            <span class="input-group-addon input-group-append border-left">
                              <span class="mdi mdi-calendar input-group-text"></span>
                            </span>
                          </div>
                    </div>
                </div>  
                <div class="col-2">
                    <div class="form-group">
                        <label></label>
                        <div class="form-check m-t-5">
                        <label class="form-check-label">
                          <input type="checkbox" class="form-check-input" name="active" value="Active" <c:if test="${fn:contains(productMaster.active, 'Active') eq true}"><c:out value="checked"/></c:if>>
                          Active
                        </label>
                        </div>
                    </div>
                </div>
              </div>
                
                <div class="row btnall">
              
                    <div class="col-md-12" align="center">                    
                          <button type="button" class="btn btn-success btn-padding save-product"  data-dismiss="modal" id="addprobtn">save</button>
                          <a href="/efive/productmaster"  class="btn btn-danger btn-padding" data-dismiss="modal">Cancel</a>
                    </div>
                </div>
                </form>
            </div>
          </div>
        </div>
        <!-- content-wrapper ends -->
        <!-- partial:partials/_footer.html -->
        <!--<footer class="footer">
          <div class="container-fluid clearfix">
            <span class="text-muted d-block text-center text-sm-left d-sm-inline-block">Copyright � 2018 <a href="http://www.urbanui.com/" target="_blank">Urbanui</a>. All rights reserved.</span>
            <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hand-crafted & made with <i class="mdi mdi-heart text-danger"></i></span>
          </div>
        </footer>-->
        <!-- partial -->
      </div>
      <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>

  <!-- plugins:js -->
  
  <script src="${currentContextPath}/vendors/js/vendor.bundle.base.js"></script>
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
  <script src="${currentContextPath}/js/formpickers.js"></script>
  <script src="${currentContextPath}/js/select2.js"></script>
  <!-- endinject -->
  <!-- Custom js for this page-->
  <script src="${currentContextPath}/jspJs/product.js"></script>
  <script src="${currentContextPath}/jspJs/category.js"></script>
  
  
  <!-- End custom js for this page-->
  <script>
  $(document).ready(function () {
  function getSubCategory(categoryid) {
		$.ajax({
			method : "get",
			url : "./getSubCategory",
			data : {
				categoryid : categoryid
			},
			dataType : "json",
			success : function(response) {
				if(response != null && response != undefined && response != "") {
					var subCategoryList = response;
					$("#subcategoryid").empty();
					var options = $("<option>").attr("value", "-1").text("--Select Sub-Category--");
					$("#subcategoryid").append(options);
					for(x in subCategoryList) {
						var category = subCategoryList[x];
						options = $("<option>").attr("value", category[0]).text(category[1]);
						$("#subcategoryid").append(options);
					}
				}

			}, error: function(message) {
				console.log(message);
			}
		});
	}

	$(document).on(
			"change",
			"#categoryid",
			function() {
				var categoryid = $(this).val();
				if (categoryid == null || categoryid == undefined
						|| categoryid == "" || categoryid <= 0) {
					$.toast({
						heading : "Required",
						text : "Invalid category.",
						showHideTransition : 'slide',
						icon : "error",
						loaderBg : "#fff",
						position : "top-right"
					});
				} else {
					getSubCategory(categoryid);
				}
			});
  });
  </script>
</body>

</html>
