$('#reset').click(function() {
	$('.showformfill').hide();
})
$('#cancel').click(function() {
	$('.showformfill').hide();
})



var ansarr = [];
$(document).ready(function() {

	$('#searchbtn').click(function() {

		var formid = $("#searchform").val();

		if (formid < 0) {
			$('.showformfill').hide();
			alert("Please Select Form Title.!!!")

		} else {

			if (formid != null && formid != undefined) {
				$.ajax({
					url: "/efiveform/editform/" + formid,
					method: "get",
					success: function(response) {
						if (response != null && response != undefined && response != "") {
							const serverResponse = response;
							/*console.log(serverResponse);*/
							if (serverResponse.status == "200") {
								var formheader = response.dataMap.formheaderlist;
								var formquestiondetail = response.dataMap.quedatalist;
								var formoption = response.dataMap.queoptionlist;
								console.log(formheader);
								console.log(formquestiondetail);
								console.log(formoption);


								$("#formtitle").text(formheader.titletext);
								/*alert(formheader.titletext)*/


								$("#description").text(formheader.textfield);
								/*alert(formheader.textfield)*/


								var queshadow = $('.queshadow');
								queshadow.empty();
								var displayoption = $('displayoption');
								displayoption.empty();




								for (let i = 0; i < formquestiondetail.length; i++) {

									let anstypeid = formquestiondetail[i].anstypeid;



									switch (anstypeid) {

										case 1:

											queshadow.append(`<div class='card mb-2'><div class='card-body'><div class='row pl-2 pr-2'><div class='col-xl-1 col-lg-1 col-sm-2 col-xs-12 
									colmspadding'><span class='question'> Q : ${formquestiondetail[i].quedetailid}</span></div>
									<div class='col-xl-11 col-lg-11 col-sm-10 col-xs-12 colmspadding'><div class='form-group mb-0 text-justify'>
									<p class='font-weight-700 mb-1 text-justify'>${formquestiondetail[i].quename}</p>
									<p class='mb-1'>${formquestiondetail[i].quedescription}</p></div> <div class='form-group mb-0'><div class='row pl-2 pr-2'>

												<div class="col-xl-7 col-lg-12 col-sm-12 col-xs-12 colmspadding">
									<span class='text-danger'>NO Answer Required..!</span>
									</div>

											</div></div>`)

											break;

										case 2:
											for (let j = 0; j < formoption.length; j++) {

												if (formquestiondetail[i].quedetailid == formoption[j].quedetailid) {
													var optarr = formoption[j].optionname.split(",");
													console.log(optarr);


													let str = `<div class='card mb-2'><div class='card-body'><div class='row pl-2 pr-2'><div class='col-xl-1 col-lg-1 col-sm-2 col-xs-12 
									colmspadding'><span class='question'> Q : ${formquestiondetail[i].quedetailid}</span></div>
									<div class='col-xl-11 col-lg-11 col-sm-10 col-xs-12 colmspadding'><div class='form-group mb-0 text-justify'>
									<p class='font-weight-700 mb-1 text-justify'><span class='text-danger'>*</span>${formquestiondetail[i].quename}</p>
									<p class='mb-1'>${formquestiondetail[i].quedescription}</p></div> 
									<div class='form-group mb-0'><div class='row pl-2 pr-2'>`;

													for (let i of optarr) {
														str += `<div class='col-xl-3 col-lg-3 col-sm-3 col-xs-12 colmspadding'><div class='custom-control custom-radio'>
									<input type='radio' id='${i}' name='choicetwo' class='custom-control-input radiobtn' value='${i}'> 
									<label class='custom-control-label font-weight-300 m-t-5' for='${i}'> ${i} </label></div></div>`
													}

													str += `</div></div>`;

													queshadow.append(str);
												}

											}
											break;


										case 3:
											for (let j = 0; j < formoption.length; j++) {

												if (formquestiondetail[i].quedetailid == formoption[j].quedetailid) {
													var optarr = formoption[j].optionname.split(",");
													console.log(optarr);


													let str = `<div class='card mb-2'><div class='card-body'><div class='row pl-2 pr-2'><div class='col-xl-1 col-lg-1 col-sm-2 col-xs-12 
									colmspadding'><span class='question'> Q : ${formquestiondetail[i].quedetailid}</span></div>
									<div class='col-xl-11 col-lg-11 col-sm-10 col-xs-12 colmspadding'><div class='form-group mb-0 text-justify'>
									<p class='font-weight-700 mb-1 text-justify'><span class='text-danger'>*</span>${formquestiondetail[i].quename}</p>
									<p class='mb-1'>${formquestiondetail[i].quedescription}</p></div> <div class='form-group mb-0'><div class='row pl-2 pr-2'>`;

													for (let i of optarr) {
														str += `<div class='col-xl-3 col-lg-3 col-sm-3 col-xs-12 colmspadding'><div class="custom-control custom-checkbox displayblock">
									 <input type="checkbox" name='choicethree'class="custom-control-input" id="multi${i}" value="${i}">
									 <label class="custom-control-label font-weight-300 m-t-5" for="multi${i}"> ${i} </label></div></div>`
													}

													str += `</div></div>`;

													queshadow.append(str);
												}

											}
											break;


										case 4:
											queshadow.append(`<div class='card mb-2'><div class='card-body'><div class='row pl-2 pr-2'><div class='col-xl-1 col-lg-1 col-sm-2 col-xs-12 
									colmspadding'><span class='question'> Q : ${formquestiondetail[i].quedetailid}</span></div>
									<div class='col-xl-11 col-lg-11 col-sm-10 col-xs-12 colmspadding'><div class='form-group mb-0 text-justify'>
									<p class='font-weight-700 mb-1 text-justify'><span class='text-danger'>*</span>${formquestiondetail[i].quename}</p>
									<p class='mb-1'>${formquestiondetail[i].quedescription}</p></div> <div class='form-group mb-0'><div class='row pl-2 pr-2'>

												<div class="col-xl-7 col-lg-12 col-sm-12 col-xs-12 colmspadding">
									 <input type="text" class="form-control singletextbox" placeholder="Enter Your Answer">
									</div>

											</div></div>`);

											break;


										case 5:
											queshadow.append(`<div class='card mb-2'><div class='card-body'><div class='row pl-2 pr-2'><div class='col-xl-1 col-lg-1 col-sm-2 col-xs-12 
									colmspadding'><span class='question'> Q : ${formquestiondetail[i].quedetailid}</span></div>
									<div class='col-xl-11 col-lg-11 col-sm-10 col-xs-12 colmspadding'><div class='form-group mb-0 text-justify'>
									<p class='font-weight-700 mb-1 text-justify'><span class='text-danger'>*</span>${formquestiondetail[i].quename}</p>
									<p class='mb-1'>${formquestiondetail[i].quedescription}</p></div> <div class='form-group mb-0'><div class='row pl-2 pr-2'>

												<div class="col-xl-7 col-lg-12 col-sm-12 col-xs-12 colmspadding">
									<textarea class="form-control textareasize"
                                                    placeholder="Enter Your Answer"></textarea>
									</div>

											</div></div>`)

											break;



										case 6:
											for (let j = 0; j < formoption.length; j++) {

												if (formquestiondetail[i].quedetailid == formoption[j].quedetailid) {
													var optarr = formoption[j].optionname.split(",");
													console.log(optarr);


													let str = `<div class='card mb-2'><div class='card-body'><div class='row pl-2 pr-2'><div class='col-xl-1 col-lg-1 col-sm-2 col-xs-12 
								colmspadding'><span class='question'> Q : ${formquestiondetail[i].quedetailid}</span></div>
								<div class="col-xl-11 col-lg-11 col-sm-10 col-xs-12 colmspadding"><div class="form-group mb-0">
								<p class="font-weight-700 mb-1 text-justify">${formquestiondetail[i].quename}</p>
								<p class="mb-1 text-justify">${formquestiondetail[i].quedescription}</p></div> 
								<div class='form-group mb-0'><div class='row pl-2 pr-2'>
								<div class="col-xl-7 col-lg-12 col-sm-12 col-xs-12 colmspadding">
								<select class="selectpicker" id="singledropdown" data-style="lineheight12 bg-transfer"
                                                    data-live-search="true">
                                                    <option value="">Select</option>`;

													for (let i of optarr) {
														str += `<option value='${i}'>${i}</option>`
													}
													str += `</select></div></div></div>`;

													queshadow.append(str);
													$('.selectpicker').selectpicker('refresh');
												}

											}
											break;



										case 7:
											for (let j = 0; j < formoption.length; j++) {

												if (formquestiondetail[i].quedetailid == formoption[j].quedetailid) {
													var optarr = formoption[j].optionname.split(",");
													console.log(optarr);


													let str = `<div class='card mb-2'><div class='card-body'><div class='row pl-2 pr-2'><div class='col-xl-1 col-lg-1 col-sm-2 col-xs-12 
								colmspadding'><span class='question'> Q : ${formquestiondetail[i].quedetailid}</span></div>
								<div class="col-xl-11 col-lg-11 col-sm-10 col-xs-12 colmspadding"><div class="form-group mb-0">
								<p class="font-weight-700 mb-1 text-justify">${formquestiondetail[i].quename}</p>
								<p class="mb-1 text-justify">${formquestiondetail[i].quedescription}</p></div> 
								<div class='form-group mb-0'><div class='row pl-2 pr-2'>
								<div class="col-xl-7 col-lg-12 col-sm-12 col-xs-12 colmspadding">
								 <select class="selectpicker" id="multiselect" multiple="multiple" data-selected-text-format="count"
                                                    data-style="btn-light bg-transfer" data-actions-box="true">
                                                    <option value="">Select</option>`;

													for (let i of optarr) {
														str += `<option value='${i}'>${i}</option>`
													}
													str += `</select></div></div></div>`;

													queshadow.append(str);
													$('.selectpicker').selectpicker('refresh');
												}

											}
											break;


										case 8:
											let str = `<div class='card mb-2'><div class='card-body'><div class='row pl-2 pr-2'><div class='col-xl-1 col-lg-1 col-sm-2 col-xs-12 
								colmspadding'><span class='question'> Q : ${formquestiondetail[i].quedetailid}</span></div>
								<div class="col-xl-11 col-lg-11 col-sm-10 col-xs-12 colmspadding"><div class="form-group mb-0">
								<p class="font-weight-700 mb-1 text-justify">${formquestiondetail[i].quename}</p>
								<p class="mb-1 text-justify">${formquestiondetail[i].quedescription}</p></div> 
								<div class='form-group mb-0'><div class='row pl-2 pr-2'>
								<div class="col-xl-3 col-lg-12 col-sm-12 col-xs-12 colmspadding"><div class="input-group date">
								<input type="text" class="form-control" placeholder="dd/mm/yyyy"
                                                        id="allpreview_date"><span class="input-group-addon inputgroups">
                                                        <i class="mdi mdi-calendar"></i> </span></div></div></div></div></div></div> `;


											queshadow.append(str);

											$('#ans_date').closest('div').datepicker({
												autoclose: true,
												todayHighlight: true,
												format: "dd/mm/yyyy",
												clearBt: true
											});

											$('#allpreview_date').closest('div').datepicker({
												autoclos: true,
												todayHighlight: true,
												format: "dd/mm/yyyy",
												clearBtn: true
											});

											$('#date_from').closest('div').datepicker({
												autoclose: true,
												todayHighlight: true,
												format: "dd/mm/yyyy",
												clearBtn: true
											});
											break;



										default:
											alert('Invalid Answer Type..!');
									}

								}

							}

						}



						/*SUBMITTING FORM & ANS*/



						$('#submit').click(function() {


							var FillFormHeader = {};

							var usertype = localStorage.getItem("username");
							if (usertype == "admin") {
								FillFormHeader.userid = 1;
							} else {
								FillFormHeader.userid = 2;
							}


							var today = new Date();
							var dd = today.getDate();

							var mm = today.getMonth() + 1;
							var yyyy = today.getFullYear();
							if (dd < 10) {
								dd = '0' + dd;
							}

							if (mm < 10) {
								mm = '0' + mm;
							}
							today = dd + '/' + mm + '/' + yyyy;
							console.log(today);


							FillFormHeader.compliteddate = today;

							FillFormHeader.formid = formheader.formid;

							for (let i = 0; i < formquestiondetail.length; i++) {

								var ans = {};


								var quedetailid = formquestiondetail[i].quedetailid;


								let anstypeid = formquestiondetail[i].anstypeid;

								var ansname = '';
								switch (anstypeid) {

									case 1:

										var noans = "No Answer Requierd";
										ans.ansname = noans;
										break;

									case 2:

										var radio = document.querySelector('input[name="choicetwo"]:checked').value;
										ans.ansname = radio;
										break;

									case 3:

										var checkedValueArr = []
										$("input:checkbox[name=choicethree]:checked").each(function() {
											checkedValueArr.push($(this).val());
										});

										var checkedValue = checkedValueArr.toString();
										ans.ansname = checkedValue;
										break;

									case 4:

										var singletext = $(".singletextbox").val();
										ans.ansname = singletext;
										break;

									case 5:

										var multitext = $(".textareasize").val();
										ans.ansname = multitext;
										break;




									case 6:

										var singleselect = $('#singledropdown').val();
										ans.ansname = singleselect;
										break;




									case 7:

										var multiselect = $("#multiselect").val() || [];
										ans.ansname = multiselect.join(',');
										break;


									case 8:

										var date = $("#allpreview_date").val();
										ans.ansname = date;
										break;


									default:
										alert('Please Fill All que..!');

								}

								if (usertype == "admin") {
									ans.userid = 1;
								} else {
									ans.userid = 2;
								}

								ans.formid = formheader.formid;
								ans.quedetailid = quedetailid;

								ansarr.push(ans);


							}



							var formdata = {
								fillformheader: FillFormHeader,
								fillform: ansarr
							};

							console.log(formdata)
							var FillFormData = JSON.stringify(formdata);
							console.log(FillFormData);
							$.ajax({
								url: '/efiveform/savefillform',
								method: 'POST',
								data: FillFormData,
								contentType: "application/json; charset=utf-8",
								success: function() {
									alert("Form-Header Saved successfully!");
								},
								error: function(error) {
									alert(error);
								}

							})

							window.location.reload();
						})


					},


					error: function(reponse) {
						console.log("Error: " + reponse);
					}

				})

			}


		}

	})
})



