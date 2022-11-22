<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html lang="en">

<head>

<title>Form</title>
<meta charset='utf-8' />
<meta name='viewport' content='width=device-width, initial-scale=1.0'>
<meta http-equiv='Cache-Control'
	content='no-cache, no-store, must-revalidate' />
<meta http-equiv='X-UA-Compatible' content='IE=edge' />
<meta http-equiv='Pragma' content='no-cache'>

<!-- App favicon -->
<link rel="shortcut icon" href="assets/images/favicon.png">

<!-- Common css -->
<script src="assets/custom/plugins/theme/mytheme.js"></script>
<link rel="stylesheet" type="text/css"
	href="assets/custom/css/import.css">
<link rel="stylesheet" type="text/css"
	href="assets/custom/css/responsive.css">

<link rel="stylesheet" type="text/css"
	href="assets/plugins/jquery-ui/jquery-ui.min.css">

<script src="assets/js/modernizr.min.js"></script>
<!-- SWAL ALERT -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.9/sweetalert2.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.6.9/sweetalert2.min.js"></script>

</head>



<body
	class="background-image-body background1 square scrollbar-dusty-grass square thin">

	/*Data connection START*/

	<sql:setDataSource var="snapshot" driver="com.mysql.cj.jdbc.Driver"
		url="jdbc:mysql://localhost:3306/formdb" user="root" password="root" />

	/*MODULE*/
	<sql:query dataSource="${snapshot}" var="module">
   SELECT * from mst_module;
</sql:query>

	/*CHARACTERISTICS*/
	<%-- <sql:query dataSource="${snapshot}" var="characteristics">
SELECT * from mst_characteristics;
</sql:query> --%>

	/*SUB-CHARACTERISTICS*/
	<sql:query dataSource="${snapshot}" var="sub_characteristics">
SELECT * from mst_subcharacteristics;
</sql:query>

	/*RECURRENCE*/
	<sql:query dataSource="${snapshot}" var="recurrance">
SELECT * from mst_recurrance;
</sql:query>

	/*MONTH*/
	<sql:query dataSource="${snapshot}" var="month">
SELECT * from mst_month;
</sql:query>

	/*MAPPING TABLE*/
	<sql:query dataSource="${snapshot}" var="map">
SELECT * from mst_module_characteristics_mapping;
</sql:query>

	/*FORM ID*/
	<sql:query dataSource="${snapshot}" var="formid">
SELECT MAX(formid) AS formid FROM form_header;
</sql:query>

	/*FORM DATA*/
	<sql:query dataSource="${snapshot}" var="formdata">
SELECT * FROM form_header;
</sql:query>


	/*QUESTION FORM*/ /*FORM DATA*/
	<sql:query dataSource="${snapshot}" var="anstype">
SELECT * FROM mst_answer_type;
</sql:query>





	/*Data connection END*/


	<div class="preloader"></div>
	<!-- Navigation Bar-->
	<header id="topnav">
		<script src="assets/custom/js/header.js"></script>
		<!-- end topbar-main -->

		<script src="assets/custom/js/menu.js"></script>
	</header>
	<!-- End Navigation Bar-->

	<div class="wrapper wrapper-top">
		<div class="container-fluid">

			<div class="fixed-plugin">
				<div class="dropdown show-dropdown">
					<a href="#" data-toggle="dropdown"> <i class="fa fa-cog fa-2x">
					</i>
					</a>

					<ul class="dropdown-menu">
						<li class="header-title">theme settings</li>
						<li class="adjustments-line"><a href="javascript:void(0)"
							class="switch-trigger active-color">
								<div class="badge-colors ml-auto mr-auto text-center"
									id="themeColorName">
									<span class="badge filter badge-azure purplelable"
										id="bluecolor" title="Blue"></span> <span
										class="badge filter badge-warning yellowlable"
										id="yellowcolor" title="Yellow"></span> <span
										class="badge filter badge-green dbluelable" id="greencolor"
										title="Green"></span> <span
										class="badge filter badge-danger bluelable" id="redcolor"
										title="Red"></span>
								</div>
								<div class="clearfix"></div>
						</a></li>

						<li class="header-title mb-0">Background</li>
						<li class="adjustments-line more-height"><a
							href="javascript:void(0)" class="switch-trigger active-color">
								<div class="badge-colors ml-auto mr-auto text-center"
									id="imagechanges">
									<span class="image-change-button1"> <img
										src="assets/custom/images/theme-background/background1.jpg"
										class="imgactive" alt="BG1">
									</span> <span class="image-change-button2"> <img
										src="assets/custom/images/theme-background/background2.jpg"
										alt="BG2">
									</span> <span class="image-change-button3"> <img
										src="assets/custom/images/theme-background/background3.jpg"
										alt="BG3">
									</span> <span class="image-change-button4"> <img
										src="assets/custom/images/theme-background/background4.jpg"
										alt="BG4">
									</span>
								</div>
						</a></li>
					</ul>
				</div>
			</div>

			<div id="portfolio_details">
				<div class="row">
					<div class="col-xl-12 col-lg-12 col-sm-12 colmspadding">
						<div class="card">
							<div class="card-header">
								<div class="row">
									<div class="col-xl-8 col-lg-8 col-sm-8">
										<h5 class="mb-0 font-bold m-t-5">Form</h5>
									</div>
									<div class="col-xl-4 col-lg-4 col-sm-4">
										<a href="javascript:void(0)"
											class="btn btn-warning waves-effect float-right btn-padding client_add_btn"><i
											class="fa fa-plus"></i> Add Form</a>
									</div>
								</div>
							</div>

							<div class="card-body">
								<div class="row">
									<div class="col-xl-12 col-lg-12 col-sm-12">
										<table id="form_datatable"
											class="table nowrap table-custom-hover">
											<thead>
												<tr>
													<th>Form #</th>
													<th>Form Title</th>
													<th>Active</th>
													<th class="text-center">Action</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="row" items="${formdata.rows}">
													<tr>
														<td><c:out value="${row.formid}" /></td>
														<td><c:out value="${row.titletext}" /></td>
														<td><c:out
																value="${(row.active == '1') ? 'YES' : 'NO'}" /></td>

														<td class="text-center"><a href="javascript:void(0)"
															data-toggle="tooltip" data-placement="bottom" title=""
															data-original-title="Edit"
															class="text-success fa-size client_add_btn editform"
															id="${row.formid}"><i class="fa fa-pencil"></i></a> <span
															data-toggle="modal" data-target="#all_question_preview"><a
																href="javascript:void(0)" data-toggle="tooltip"
																data-placement="bottom" title=""
																data-original-title="Preview"
																class="text-info fa-size preview previewform"
																id="${row.formid}"><i class="fa fa-eye"></i></a></span> <span
															class="delete-user-alert"><a
																href="javascript:void(0)"
																class="text-danger fa-size deleteform"
																id="${row.formid}" data-toggle="tooltip"
																data-placement="bottom" data-original-title="Delete"><i
																	class="fa fa-trash"></i></a></span></td>

													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="row" id="portfolio_add_detail" style="display: none;">
				<div class="col-xl-12 col-lg-12 col-sm-12 colmspadding">
					<div class="card mb-0">
						<div class="card-header">
							<div class="row">
								<div class="col-xl-8 col-lg-8 col-sm-8">
									<h5 class="mb-0 font-bold m-t-5">Add Form</h5>
								</div>
								<div class="col-xl-4 col-lg-4 col-sm-4">
									<a
										class="show_port_table btn btn-warning float-right btn-padding"><i
										class="fa fa-undo mr-1"></i> Move Back</a>
								</div>
							</div>
						</div>

						<div class="card-body p-1">
							<div class="card-content">
								<form class="form" id="FormHeader">
									<div class="form-body mb-0">
										<div class="row pl-3 pr-3">
											<div
												class="col-xl-2 col-lg-2 col-sm-3 col-xs-12 colmspadding">
												<div class="form-group">
													<label>Form #</label>
													<c:forEach var="row" items="${formid.rows}">
														<input type="text" class="form-control" name="formid"
															id="formid" readonly="" value="${row.formid+1}">
													</c:forEach>
												</div>
											</div>
											<div
												class="col-xl-3 col-lg-5 col-sm-4 col-xs-12 colmspadding">
												<div class="form-group">
													<label>Title Text (English) <span
														class="text-danger">*</span></label> <input type="text"
														class="form-control" name="titletext" id="titletext">
												</div>
											</div>


											<div
												class="col-xl-2 col-lg-2 col-sm-3 col-xs-12 colmspadding">
												<div class="form-group">
													<label>Alias Name <span class="text-danger">*</span></label>
													<input type="text" class="form-control" name="aliasname"
														id="aliasname">
												</div>
											</div>


										</div>

										<div class="row pl-3 pr-3">
											<div
												class="col-xl-12 col-lg-12 col-sm-12 col-xs-12 colmspadding">
												<h5
													class="mt-1 border-bottom font-weight-600 pb-1 mb-2 text-info">Form
													Attributes</h5>
											</div>
										</div>

										<div class="row pl-3 pr-3 pt-1">


											<div
												class="col-xl-2 col-lg-3 col-sm-4 col-xs-12 colmspadding">
												<div class="form-group">
													<label>Module <span class="text-danger">*</span></label> <select
														class="selectpicker" data-style="lineheight12 bg-transfer"
														data-live-search="true" name="module" id="module">
														<option value="0" selected="selected">Select</option>

														<c:forEach var="row" items="${module.rows}">
															<option value="${row.moduleid}"><c:out
																	value="${row.modulename}" /></option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div
												class="col-xl-2 col-lg-3 col-sm-4 col-xs-12 colmspadding">
												<div class="form-group">
													<label>Characteristic <span class="text-danger">*</span></label>
													<select class="selectpicker"
														data-style="lineheight12 bg-transfer"
														data-live-search="true" name="characteristic"
														id="characteristic">
														<option value="0" selected="selected">Select</option>
													</select>
												</div>
											</div>

											<div
												class="col-xl-2 col-lg-3 col-sm-4 col-xs-12 colmspadding">
												<div class="form-group">
													<label>Sub-Characteristic <span class="text-danger">*</span></label>
													<select class="selectpicker"
														data-style="lineheight12 bg-transfer"
														data-live-search="true" name="subcharacteristic"
														id="subcharacteristic">
														<option value="0" selected="selected">Select</option>

													</select>
												</div>
											</div>

											<div
												class="col-xl-2 col-lg-3 col-sm-4 col-xs-12 colmspadding">
												<div class="form-group">
													<label>Recurrence <span class="text-danger">*</span></label>
													<select class="selectpicker"
														data-style="lineheight12 bg-transfer"
														data-live-search="true" name="recurrance" id="recurrance">
														<option value="" selected="selected">Select</option>

														<c:forEach var="row" items="${recurrance.rows}">
															<option value="${row.recurrancename}"><c:out
																	value="${row.recurrancename}" /></option>
														</c:forEach>

													</select>
												</div>
											</div>

											<div
												class="col-xl-2 col-lg-2 col-sm-4 col-xs-12 colmspadding">
												<div class="form-group">
													<label>Start Month <span class="text-danger">*</span></label>
													<select class="selectpicker"
														data-style="lineheight12 bg-transfer"
														data-live-search="true" name="startmonth" id="startmonth">
														<option value="" selected="selected">Select</option>

														<c:forEach var="row" items="${month.rows}">
															<option value="${row.monthname}"><c:out
																	value="${row.monthname}" /></option>
														</c:forEach>

													</select>
												</div>
											</div>
										</div>

										<div class="row pl-3 pr-3">


											<div
												class="col-xl-4 col-lg-4 col-sm-5 col-xs-12 colmspadding">
												<div class="row pl-2 pr-2">
													<div class="col-xl-6 col-lg-6 col-sm-6 colmspadding">
														<div class="form-group">
															<label>Compliance Period <span
																class="text-danger">*</span></label> <input type="text"
																class="form-control" placeholder="In Months"
																name="complianceperiod" id="complianceperiod">
														</div>
													</div>

													<div class="col-xl-6 col-lg-6 col-sm-6 colmspadding">
														<div class="form-group">
															<label>Effective Date <span class="text-danger">*</span></label>
															<div class="input-group date">
																<input type="text" class="form-control"
																	placeholder="dd/mm/yyyy" id="date_from"
																	name="date_from"> <span
																	class="input-group-addon inputgroups"> <i
																	class="mdi mdi-calendar"></i>
																</span>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div class="col-xl-2 col-lg-6 col-sm-6 colmspadding">
												<div class="form-group">
													<label>&nbsp;</label>
													<div class="custom-control custom-checkbox displayblock"
														id="activeDiv">
														<input type="checkbox" class="custom-control-input"
															value="1" name="active" id="active" checked> <label
															class="custom-control-label mt-1" for="active">Active</label>
													</div>
												</div>
											</div>
										</div>

										<div class="row pl-3 pr-3 pt-1">
											<div class="col-xl-6 col-lg-6 col-sm-6 colmspadding">
												<div class="form-group">
													<label>Text (English) <span class="text-danger">*</span></label>
													<span data-toggle="modal" data-target=".historymodal"><a
														href="javascript:void(0)" class="text-info float-right"
														data-toggle="tooltip" data-placement="bottom" title=""
														data-original-title="View History"><i
															class="fa fa-history text-info fa-size action"></i></a></span>
													<textarea class="form-control textareasize"
														name="textfield" id="textfield"></textarea>
												</div>
											</div>


										</div>

										<div class="row pl-3 pr-3 pt-1 mb-1">
											<div class="col-xl-12 col-lg-12 col-sm-12 colmspadding">
												<h5
													class="mt-1 border-bottom font-weight-600 pb-1 mb-2 text-info">Form
													Question</h5>
												<div
													class="table-responsive mb-0 square scrollbar-dusty-grass square thin">
													<table id="formquestion_datatable"
														class="table table-custom-hover nowrap">
														<thead>
															<tr>
																<th>Question #</th>
																<th>Question Name</th>
																<th>Answer Type</th>
																<th>Required</th>
																<th class="text-center">Action</th>
															</tr>
														</thead>

														<tbody id="tbodyid">
														</tbody>
													</table>
												</div>
											</div>
										</div>

										<div class="form-x	actions m-t-5">
											<div class="text-center">
												<a
													class="save_port_details btn btn-success text-white btn-padding ml-1"
													id="addform"><i class="fa fa-floppy-o mr-2"></i>Save</a> <a
													class="show_port_table btn btn-danger text-white btn-padding ml-1"><i
													class="fa fa-times mr-2"></i>Cancel</a>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade historymodal" tabindex="-1">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>x</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">History</h4>
				</div>

				<div class="modal-body">
					<div class="row">
						<div class="col-xl-12 col-lg-12 col-sm-12">
							<div
								class="table-responsive mb-1 square scrollbar-dusty-grass square thin">
								<table class="table table-striped table-bordered nowrap mb-1">
									<thead>
										<tr class="thbgcolor">
											<th>Date</th>
											<th>Time</th>
											<th>Updated by</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>08-05-2016</td>
											<td>12:34 PM</td>
											<td>Leon Sebastian</td>
										</tr>
										<tr>
											<td>01-03-2019</td>
											<td>1:37 AM</td>
											<td>Leon Sebastian</td>
										</tr>
									</tbody>
								</table>
							</div>
							<p class="mt-0 mb-0">Asbestos in the Workplace: A Guide to
								Assessment & Management of Asbestos in the Workplace</p>
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<a class="btn btn-danger text-white btn-padding float-right"
						data-dismiss="modal"><i class="fa fa-times mr-2"></i>Close</a>
				</div>
			</div>
		</div>
	</div>

	<div class="modal modal-right fade" id="all_question_preview"
		tabindex="-1">
		<div class="modal-dialog modal-right-width">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">Question Preview</h4>
					<button type="button" class="close" data-dismiss="modal">
						<span>x</span>
					</button>
				</div>

				<div class="modal-body" style="background-color: #F3F3F3;">
					<div class="detailsbg">
						<div class="row pr-2 pl-2">
							<div class="col-xl-12 col-lg-12 col-sm-12 col-xs-12 colmspadding">
								<p class="mb-1 font-weight-600">
									<span class="font-weight-700">Form Title:</span> <span
										id="formtitle"></span>
								</p>

								<p class="mb-0 font-weight-600">
									<span class="font-weight-700">Description:</span> <span
										id="description"></span>
								</p>
							</div>
						</div>
					</div>
					<div class="card mb-2 queshadow"></div>

				</div>
				<div class="modal-footer bg-white">
					<a class="btn btn-success float-right btn-padding"
						data-dismiss="modal"><i class="fa fa-times mr-2"></i>Close</a> <a
						class="btn btn-success float-right btn-padding mr-2"
						data-dismiss="modal"><i class="fa fa-floppy-o mr-2"></i>Save</a> <a
						class="btn btn-success float-right btn-padding mr-2"
						data-dismiss="modal"><i class="fa fa-print mr-2"></i>Print</a>
				</div>
			</div>
		</div>
	</div>

	<div class="modal fade addformquestion" id="MyModal" tabindex="-1">
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>x</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">Add/Edit Form Question</h4>
				</div>
				<div class="modal-body">
					<div class="row justify-content-md-center pl-2 pr-2">
						<div class="col-xl-12 col-lg-12 col-sm-12 colmspadding">
							<div class="surveydesign">
								<form id="MyForm">
									<table class="table table-striped nowrap mb-0" id="quetable">
										<tbody>
											<tr>
												<td class="border-0">
													<div class="form-group mb-0">
														<input type="hidden" class="form-control"
															name="quedetailid" id="quedetailid" value="">
													</div>
												</td>
											</tr>

											<tr>
												<td width="16%" class="border-0"><label
													class="mt-1 mb-0">Question Label<span
														class="text-danger ml-1">*</span></label></td>
												<td class="border-0">
													<div class="form-group mb-0">
														<input type="text" class="form-control"
															name="quelablename" id="quelablename"
															placeholder="Enter Your Question Label in English">
													</div>
												</td>

											</tr>

											<tr>
												<td width="16%" class="border-0"><label
													class="mt-1 mb-0">Question Name<span
														class="text-danger ml-1">*</span></label></label></td>
												<td class="border-0">
													<div class="form-group mb-0">
														<input type="text" class="form-control" name="quename"
															id="quename" placeholder="Enter Your Question in English">
													</div>
												</td>

											</tr>

											<tr>
												<td class="border-0"><label class="mt-1 mb-0">Description</label></td>
												<td class="border-0"><textarea
														class="form-control textareasize"
														placeholder="Enter Description in English"
														name="quedescription" id="quedescription"
														style="min-height: 45px !important;"></textarea></td>

											</tr>

											<tr>
												<td class="border-0"><label class="mt-1 mb-0">Answer
														Type <span class="text-danger ml-1">*</span>
												</label></label></td>
												<td class="border-0"><select
													class="selectpicker anstypecombo" name="anstypeid"
													id="anstypeid" data-style="lineheight12 bg-transfer"
													data-live-search="true" data-title="Select Answer Type">
														<c:forEach var="row" items="${anstype.rows}">
															<option value="${row.answertypeid}"><c:out
																	value="${row.answertypename}-----${row.answertypeid}" /></option>
														</c:forEach>
												</select></td>
											</tr>

										</tbody>
									</table>

									<div class="singlechoicedata" style="display: none;">
										<hr class="mb-2 mt-2">
										<table
											class="table table-striped nowrap mb-0 singlechoicetable">
											<tbody>
												<tr>
													<td class="text-center border-0" width="5%"><i
														class="fa fa-arrow-right"></i></td>
													<td class="border-0 p-1">
														<div class="form-group mb-0">
															<input type="text" class="form-control"
																id="singlechoicedata" name="singlechoicedata"
																placeholder="Enter an answer choice in English">
														</div>
													</td>

													<td class="text-center border-0 p-0" width="3%"><a
														href="javascript:void(0)" class="singlechoiceadd"> <i
															class="fa fa-plus-square-o font_20 m-t-5 text-default"></i>
													</a></td>
													<td class="text-center border-0 p-0" width="3%"><a
														href="javascript:void(0)" class=""> <i
															class="fa fa-minus-square-o font_20 m-t-5 text-default"></i>
													</a></td>
												</tr>
											</tbody>
										</table>
									</div>

									<div class="multichoicedata" style="display: none;">
										<hr class="mb-2 mt-2">
										<table
											class="table table-striped nowrap mb-0 multichoicetable">
											<tbody>
												<tr>
													<td class="text-center border-0" width="5%"><i
														class="fa fa-arrow-right"></i></td>
													<td class="border-0 p-1">
														<div class="form-group mb-0">
															<input type="text" class="form-control"
																id="multichoicedata" name="multichoicedata"
																placeholder="Enter an answer choice in English">
														</div>
													</td>

													<td class="text-center border-0 p-0" width="3%"><a
														href="javascript:void(0)" class="multichoiceadd"> <i
															class="fa fa-plus-square-o font_20 m-t-5 text-default"></i>
													</a></td>
													<td class="text-center border-0 p-0" width="3%"><a
														href="javascript:void(0)" class=""> <i
															class="fa fa-minus-square-o font_20 m-t-5 text-default"></i>
													</a></td>
												</tr>
											</tbody>
										</table>
									</div>

									<div class="singleselectdata" style="display: none;">
										<hr class="mb-2 mt-2">
										<table
											class="table table-striped nowrap mb-0 singleselecttable">
											<tbody>
												<tr>
													<td class="text-center border-0" width="5%"><i
														class="fa fa-arrow-right"></i></td>
													<td class="border-0 p-1">
														<div class="form-group mb-0">
															<input type="text" class="form-control"
																id="singleselectdata"
																placeholder="Enter an answer choice in English">
														</div>
													</td>

													<td class="text-center border-0 p-0" width="3%"><a
														href="javascript:void(0)" class="singleselectadd"> <i
															class="fa fa-plus-square-o font_20 m-t-5 text-default"></i>
													</a></td>
													<td class="text-center border-0 p-0" width="3%"><a
														href="javascript:void(0)" class=""> <i
															class="fa fa-minus-square-o font_20 m-t-5 text-default"></i>
													</a></td>
												</tr>
											</tbody>
										</table>
									</div>

									<div class="multiselectdata" style="display: none;">
										<hr class="mb-2 mt-2">
										<table
											class="table table-striped nowrap mb-0 multiselecttable">
											<tbody>
												<tr>
													<td class="text-center border-0" width="5%"><i
														class="fa fa-arrow-right"></i></td>
													<td class="border-0 p-1">
														<div class="form-group mb-0">
															<input type="text" class="form-control" id="multiselectdata"
																placeholder="Enter an answer choice in English">
														</div>
													</td>

													<td class="text-center border-0 p-0" width="3%"><a
														href="javascript:void(0)" class="multiselectadd"> <i
															class="fa fa-plus-square-o font_20 m-t-5 text-default"></i>
													</a></td>
													<td class="text-center border-0 p-0" width="3%"><a
														href="javascript:void(0)" class=""> <i
															class="fa fa-minus-square-o font_20 m-t-5 text-default"></i>
													</a></td>
												</tr>
											</tbody>
										</table>
									</div>
								</form>
							</div>
						</div>

						<div
							class="col-xl-12 col-lg-12 col-sm-12 colmspadding mt-2 noansdisplaynone">
							<div class="surveydesign pl-3"
								style="padding: 5px 14px 5px 14px;">
								<div class="row">
									<div class="col-xl-6 col-lg-6 col-sm-5 colmspadding">
										<div class="custom-control custom-checkbox displayblock">
											<input type="checkbox" class="custom-control-input"
												id="iscompulsory" name="iscompulsory"> <label
												class="custom-control-label font-weight-300 m-t-5"
												for="iscompulsory">Require an Answer to This
												Question</label>
										</div>
									</div>

									<div
										class="col-xl-6 col-lg-6 col-sm-7 colmspadding hidedatevalidation"
										style="display: none;">
										<div class="form-group mb-0 row pl-2 pr-2">
											<label class="mt-1 mb-0 col-md-3 colmspadding">Date
												Format :</label>
											<div class="col-md-4 colmspadding">
												<input type="text" class="form-control"
													placeholder="DD/MM/YYYY" readonly>
											</div>
										</div>
									</div>
								</div>

								<div class="row">
									<div
										class="col-xl-5 col-lg-5 col-sm-5 colmspadding hidetextvalidation"
										style="display: none;">
										<div class="custom-control custom-checkbox displayblock">
											<input type="checkbox" class="custom-control-input"
												id="validatans" name="validatans"> <label
												class="custom-control-label font-weight-300 m-t-5"
												for="validatans">Validate Answer for a Specific
												Format</label>
										</div>
									</div>

									<div
										class="col-xl-6 col-lg-6 col-sm-12 colmspadding showanswershouldbe"
										style="display: none;">
										<div class="row pr-2 pl-2">
											<div class="col-xl-5 col-lg-4 col-sm-6 colmspadding">
												<div class="form-group">
													<select class="selectpicker answercombo"
														data-style="lineheight12 bg-transfer"
														data-live-search="true" data-title="Answer Should Be"
														id="formateoption" name="formateoption">
														<option value="0">All Character</option>
														<option value="1">Only Character</option>
														<option value="2">Only Alphabet</option>
														<option value="3">Alphabet & Number</option>
													</select>
												</div>
											</div>


										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<a class="btn btn-danger text-white btn-padding float-right ml-1"
						data-dismiss="modal"><i class="fa fa-times mr-2"></i>Close</a> <a
						class="btn btn-success text-white btn-padding float-right"
						name="queformsave" id="queformsave"><i
						class="fa fa-floppy-o mr-2"></i>Save</a>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade formsorting" tabindex="-1">
		<div class="modal-dialog modal-md modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span>x</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title">Sort Form Question</h4>
				</div>

				<div class="modal-body">
					<div class="row">
						<div class="col-xl-12 col-lg-12 col-sm-12">
							<ul id="sortable" name="sortorder">
								<li class="ui-state-default queshadow"><span
									class="ui-icon ui-icon-arrowthick-2-n-s"></span>FS-TRI-OPS-03 :
									Fire Department Access</li>
								<li class="ui-state-default queshadow"><span
									class="ui-icon ui-icon-arrowthick-2-n-s"></span>FS-TRI-OPS-04 :
									Fire Separations</li>
								<li class="ui-state-default queshadow"><span
									class="ui-icon ui-icon-arrowthick-2-n-s"></span>FS-TRI-OPS-01 :
									Emergency Power Systems & Lighting</li>
								<li class="ui-state-default queshadow"><span
									class="ui-icon ui-icon-arrowthick-2-n-s"></span>FS-TRI-OPS-02 :
									Fire Alarm & Voice Communication</li>
								<li class="ui-state-default queshadow"><span
									class="ui-icon ui-icon-arrowthick-2-n-s"></span>FS-TRI-OPS-05 :
									Portable Extinguishers</li>

							</ul>
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<a class="btn btn-danger text-white btn-padding float-right"
						data-dismiss="modal"><i class="fa fa-times mr-2"></i>Close</a>
				</div>
			</div>
		</div>
	</div>

	<script src="assets/custom/js/footer.js"></script>

	<!-- jQuery  -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/plugins/jquery-ui/jquery-ui.min.js"></script>
	<script src="assets/js/popper.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/waves.js"></script>
	<script src="assets/js/jquery.slimscroll.js"></script>

	<!-- Required datatable js -->
	<script
		src="assets/custom/plugins/datatable/js/jquery.dataTables.min.js"></script>
	<script src="assets/custom/plugins/datatable/js/fixcolumn.js"></script>
	<script
		src="assets/custom/plugins/datatable/Responsive/js/dataTables.responsive.js"></script>

	<script src="assets/plugins/bootstrap-select/js/bootstrap-select.js"></script>

	<script src="assets/custom/plugins/typeahead/js/bootstrap-typeahead.js"></script>
	<script src="assets/plugins/moment/moment.js"></script>
	<script
		src="assets/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script
		src="assets/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>

	<!-- App js -->
	<script src="assets/js/jquery.core.js"></script>
	<script src="assets/js/jquery.app.js"></script>

	<script src="assets/custom/js/custom.js"></script>
	<script src="assets/custom/plugins/image_change/image-change.js"></script>
	<script
		src="assets/custom/plugins/jquery_confirm_v3/jquery-confirm.min.js"></script>
	<script
		src="assets/custom/plugins/jquery_confirm_v3/jquery-confirm-custom.js"></script>
	<script src="jspjs/master_form.js"></script>

	<script
		src="assets/custom/plugins/jasny-bootstrap/dist/js/jasny-bootstrap.min.js"></script>

	<script src="assets/custom/js/commonmodal.js"></script>

	<script>
		$(function() {
			$("#sortable").sortable();
			$("#sortable").disableSelection();
		});

		var $input = $(".formtypeahead");
		$input.typeahead({
			source : [ {
				id : "someId1",
				name : "General Management - Provide ENV Reports"
			}, {
				id : "someId2",
				name : "Compliance Transfer"
			}, {
				id : "someId3",
				name : "AIR-BC-RES-001"
			}, {
				id : "someId4",
				name : "AIR-MB-01"
			}, {
				id : "someId5",
				name : "NEWAIR-BB-10"
			} ],
			autoSelect : true
		});

		$('#ans_date').closest('div').datepicker({
			autoclose : true,
			todayHighlight : true,
			format : "dd/mm/yyyy",
			clearBtn : true
		});

		$('#allpreview_date').closest('div').datepicker({
			autoclose : true,
			todayHighlight : true,
			format : "dd/mm/yyyy",
			clearBtn : true
		});

		$('#date_from').closest('div').datepicker({
			autoclose : true,
			todayHighlight : true,
			format : "dd/mm/yyyy",
			clearBtn : true
		});

		/* $('.delete-user-alert').on('click', function() {
			$.confirm({
				title : 'Delete Record..!',
				content : 'Please be sure before deleting record',
				theme : 'material',
				icon : 'fa fa-warning',
				type : 'red',
				buttons : {
					omg : {
						text : 'Delete',
						btnClass : 'btn-red',
					},
					close : function() {
					}
				}
			});
		}); */

		$('#form_datatable').DataTable({
			destroy : true,
			scrollX : true,
			"bAutoWidth" : true,
			paging : true,
			"bLengthChange" : false,
			"columnDefs" : [ {
				"targets" : 3,
				"orderable" : false
			} ],
			"pageLength" : 10,
			fixedColumns : {
				rightColumns : 1,
				leftColumns : 0
			},
			language : {
				paginate : {
					next : '<i class="fa fa-angle-double-right">',
					previous : '<i class="fa fa-angle-double-left">'
				}
			},
			"dom" : '<"top"pif>rt<"clear">'
		});

		$('#formquestion_datatable')
				.DataTable(
						{
							paging : true,
							"bLengthChange" : false,
							"columnDefs" : [ {
								"targets" : 4,
								"orderable" : false
							} ],
							"pageLength" : 10,
							language : {
								paginate : {
									next : '<i class="fa fa-angle-double-right">',
									previous : '<i class="fa fa-angle-double-left">'
								}
							},
							dom : "<'row'<'col-xl-6 col-lg-6 col-sm-5'pi><'col-xl-5 col-lg-4 col-sm-5'f><'col-xl-1 col-lg-2 col-sm-2 colmspadding text-left'<'toolbar1'>>>"
									+ "<'row'<'col-md-12'tr>>",
							fnInitComplete : function() {
								$('div.toolbar1')
										.html(
												'<a href="javascript:void(0)" data-toggle="modal" data-target=".formsorting" class="btn btn-warning btn-padding mb-1 mr-1"><i class="fa fa-sort"></i></a><a href="javascript:void(0)" data-toggle="modal" data-target=".addformquestion" class="btn btn-warning btn-padding mb-1"><i class="fa fa-plus"></i> Add</a>');
							},
						});

		$("#searchbtn").click(function() {
			$("#searchcollapse").trigger('click');
		});

		$(".anstypecombo").change(function() {

			if (this.value === '1') {
				$('.multiselectdata').hide();
				$('.multichoicedata').hide();
				$('.singlechoicedata').hide();
				$('.singleselectdata').hide();
				$('.hidetextvalidation').hide();
				$('.showanswershouldbe').hide();
				$('.hidedatevalidation').hide();
				$('.noansdisplaynone').hide();
			}

			else if (this.value === '2') {
				$('.singlechoicedata').show();
				$('.multichoicedata').hide();
				$('.singleselectdata').hide();
				$('.multiselectdata').hide();
				$('.hidetextvalidation').hide();
				$('.showanswershouldbe').hide();
				$('.hidedatevalidation').hide();
				$('.noansdisplaynone').show();
			} else if (this.value === '3') {
				$('.multichoicedata').show();
				$('.singlechoicedata').hide();
				$('.singleselectdata').hide();
				$('.multiselectdata').hide();
				$('.hidetextvalidation').hide();
				$('.showanswershouldbe').hide();
				$('.hidedatevalidation').hide();
				$('.noansdisplaynone').show();
			} else if (this.value === '4') {
				$('.multichoicedata').hide();
				$('.singlechoicedata').hide();
				$('.singleselectdata').hide();
				$('.multiselectdata').hide();
				$('.hidetextvalidation').show();
				$('.showanswershouldbe').hide();
				$('.hidedatevalidation').hide();
				$('.noansdisplaynone').show();
			} else if (this.value === '5') {
				$('.multichoicedata').hide();
				$('.singlechoicedata').hide();
				$('.singleselectdata').hide();
				$('.multiselectdata').hide();
				$('.hidetextvalidation').show();
				$('.showanswershouldbe').hide();
				$('.hidedatevalidation').hide();
				$('.noansdisplaynone').show();
			} else if (this.value === '6') {
				$('.singleselectdata').show();
				$('.multichoicedata').hide();
				$('.singlechoicedata').hide();
				$('.multiselectdata').hide();
				$('.hidetextvalidation').hide();
				$('.showanswershouldbe').hide();
				$('.hidedatevalidation').hide();
				$('.noansdisplaynone').show();
			} else if (this.value === '7') {
				$('.multiselectdata').show();
				$('.multichoicedata').hide();
				$('.singlechoicedata').hide();
				$('.singleselectdata').hide();
				$('.hidetextvalidation').hide();
				$('.showanswershouldbe').hide();
				$('.hidedatevalidation').hide();
				$('.noansdisplaynone').show();
			} else if (this.value === '8') {
				$('.multiselectdata').hide();
				$('.multichoicedata').hide();
				$('.singlechoicedata').hide();
				$('.singleselectdata').hide();
				$('.hidetextvalidation').hide();
				$('.showanswershouldbe').hide();
				$('.hidedatevalidation').show();
				$('.noansdisplaynone').show();
			}
		});

		$(".answercombo").change(function() {
			if (this.value === '1') {
				$('.charformat').css("visibility", "visible");
			} else if (this.value === '2') {
				$('.charformat').css("visibility", "hidden");
			} else if (this.value === '3') {
				$('.charformat').css("visibility", "hidden");
			} else if (this.value === '4') {
				$('.charformat').css("visibility", "hidden");
			}
		});

		$('#validatans').change(function() {
			if (this.checked) {
				$(".showanswershouldbe").show();
			} else {
				$(".showanswershouldbe").hide();
			}
		});

		// Start Singlechoice add table

		$(".singlechoicetable").on("click", ".singlechoiceremove", function() {
			$(this).closest("tr").remove();
		});
		$(document)
				.on(
						"click",
						".singlechoiceadd",
						function() {
							var addNewRow = "<tr><td class='text-center border-0' width='5%'><i class='fa fa-arrow-right' aria-hidden='true'></i></td><td class='border-0 p-1'><div class='form-group mb-0'><input type='text' id='singlechoicedata' class='form-control' placeholder='Enter an answer choice in English'></div></td><td class='text-center border-0 p-0' width='3%'><a href='javascript:void(0)' class='singlechoiceadd'><i class='fa fa-plus-square-o font_20 m-t-5 text-default' aria-hidden='true'></i></a></td><td class='text-center border-0 p-0' width='3%'><a href='javascript:void(0)' class='singlechoiceremove'><i class='fa fa-minus-square-o font_20 m-t-5 text-default' aria-hidden='true'></i></a></td></tr>";

							$('table.singlechoicetable').append(addNewRow);
						});
		// End Singlechoice add table

		// Start Multichoice add table
		$(".multichoicetable").on("click", ".multichoiceremove", function() {
			$(this).closest("tr").remove();
		});

		$(document)
				.on(
						"click",
						".multichoiceadd",
						function() {
							var addNewRow = "<tr><td class='text-center border-0' width='5%'><i class='fa fa-arrow-right' aria-hidden='true'></i></td><td class='border-0 p-1'><div class='form-group mb-0'><input type='text' id='multichoicedata' class='form-control' placeholder='Enter an answer choice in English'></div></td><td class='text-center border-0 p-0' width='3%'><a href='javascript:void(0)' class='multichoiceadd'><i class='fa fa-plus-square-o font_20 m-t-5 text-default' aria-hidden='true'></i></a></td><td class='text-center border-0 p-0' width='3%'><a href='javascript:void(0)' class='multichoiceremove'><i class='fa fa-minus-square-o font_20 m-t-5 text-default' aria-hidden='true'></i></a></td></tr>";

							$('table.multichoicetable').append(addNewRow);
						});
		// End Multichoice add table

		// Start Singleselect add table
		$(".singleselecttable").on("click", ".singleselectremove", function() {
			$(this).closest("tr").remove();
		});

		$(document)
				.on(
						"click",
						".singleselectadd",
						function() {
							var addNewRow = "<tr><td class='text-center border-0' width='5%'><i class='fa fa-arrow-right' aria-hidden='true'></i></td><td class='border-0 p-1'><div class='form-group mb-0'><input type='text' class='form-control'id='singleselectdata' placeholder='Enter an answer choice in English'></div></td><td class='text-center border-0 p-0' width='3%'><a href='javascript:void(0)' class='singleselectadd'><i class='fa fa-plus-square-o font_20 m-t-5 text-default' aria-hidden='true'></i></a></td><td class='text-center border-0 p-0' width='3%'><a href='javascript:void(0)' class='singleselectremove'><i class='fa fa-minus-square-o font_20 m-t-5 text-default' aria-hidden='true'></i></a></td></tr>";

							$('table.singleselecttable').append(addNewRow);

						});
		// End Singleselect add table

		// Start Multiselect add table
		$(".multiselecttable").on("click", ".multiselectremove", function() {
			$(this).closest("tr").remove();
		});

		$(document)
				.on(
						"click",
						".multiselectadd",
						function() {
							var addNewRow = "<tr><td class='text-center border-0' width='5%'><i class='fa fa-arrow-right' aria-hidden='true'></i></td><td class='border-0 p-1'><div class='form-group mb-0'><input type='text' class='form-control' id='multiselectdata' placeholder='Enter an answer choice in English'></div></td><td class='text-center border-0 p-0' width='3%'><a href='javascript:void(0)' class='multiselectadd'><i class='fa fa-plus-square-o font_20 m-t-5 text-default' aria-hidden='true'></i></a></td><td class='text-center border-0 p-0' width='3%'><a href='javascript:void(0)' class='multiselectremove'><i class='fa fa-minus-square-o font_20 m-t-5 text-default' aria-hidden='true'></i></a></td></tr>";

							$('table.multiselecttable').append(addNewRow);
						});
		// End Multiselect add table
	</script>

</body>

</html>
