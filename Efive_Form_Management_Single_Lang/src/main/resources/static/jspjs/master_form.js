function validateFormHeader() {

	var titletext = $("#titletext").val();
	var aliasname = $("#aliasname").val();
	var module = $("#module").val();
	var characteristic = $("#characteristic").val();
	var subcharacteristic = $("#subcharacteristic").val();
	var recurrance = $("#recurrance").val();
	var startmonth = $("#startmonth").val();
	var complianceperiod = $("#complianceperiod").val();
	var effectivedate = $("#date_from").val();
	var textfield = $("#textfield").val();


	if (titletext == null || titletext == undefined
		|| titletext == "") {

		alert("titletext Name Requerd...!");
		$("#titletext").focus();
		return false;
	}

	if (aliasname == null || aliasname == undefined
		|| aliasname == "") {
		alert("aliasname Requerd...!");
		$("#aliasname").focus();
		return false;
	}

	if (module == null || module == undefined
		|| module == -1 || module <= 0) {
		alert("module Requerd...!");
		$("#module").focus();
		return false;
	}

	if (characteristic == null || characteristic == undefined
		|| characteristic == -1 || characteristic <= 0) {
		alert("characteristic Requerd...!");
		$("#characteristic").focus();
		return false;
	}

	if (subcharacteristic == null || subcharacteristic == undefined
		|| subcharacteristic == -1 || subcharacteristic <= 0) {
		alert("subcharacteristic Requerd...!");
		$("#subcharacteristic").focus();
		return false;
	}

	if (recurrance == null || recurrance == undefined
		|| recurrance == -1 || recurrance <= 0) {
		alert("recurrance Requerd...!");
		$("#recurrance").focus();
		return false;
	}

	if (startmonth == null || startmonth == undefined
		|| startmonth == -1 || startmonth <= 0) {
		alert("startmonth Requerd...!");
		$("#startmonth").focus();
		return false;
	}

	if (!/^\d+$/.test(complianceperiod)) {
		alert("Enter Month. Only Numeric. \nFor example: [0-9]");
		$("#complianceperiod").focus();
		return false;
	}

	if (effectivedate == null || effectivedate == undefined
		|| effectivedate == -1 || effectivedate <= 0) {
		alert("Please Select effectivedate...!");
		$("#date_from").focus();
		return false;
	}

	if (textfield == null
		|| textfield == undefined
		|| textfield == "") {
		alert("textfield Requred...!");
		$("#textfield").focus();
		return false;
	}

	return true;
}


/*QUE VALIDATION*/

function validateQue() {


	var quelablename = $("#quelablename").val();
	var quename = $("#quename").val();
	var quedescription = $("#quedescription").val();
	var anstypeid = $("#anstypeid").val();


	if (quelablename == null || quelablename == undefined
		|| quelablename == "") {

		alert("Question Label Name Requerd...!");
		$("#quelablename").focus();
		return false;
	}
	if (quename == null || quename == undefined
		|| quename == "") {

		alert("Question Name Requerd...!");
		$("#quename").focus();
		return false;
	}
	if (quedescription == null || quedescription == undefined
		|| quedescription == "") {

		alert("Question Description Requerd...!");
		$("#quedescription").focus();
		return false;
	}
	if (anstypeid == null || anstypeid == undefined
		|| anstypeid == "") {

		alert("Select Answer Type...!");
		$("#anstypeid").focus();
		return false;
	}


	return true;
}





$('#MyModal').on('hidden.bs.modal', function() {
	$(this).find('form')[0].reset();
});




/*======================================================dependent DropDown=======================================*/

/*======dependent DropDown for CHractoristic START=======*/

$(document).ready(function() {
	function getCharactoristics(moduleid) {
		$.ajax({
			method: "get",
			url: "./getcharactoristics",
			data: {
				moduleid: moduleid
			},
			dataType: "json",
			success: function(response) {
				console.log("RESPONSE" + response)
				if (response != null && response != undefined && response != "") {
					var charactoristic = response;
					console.log(charactoristic);
					$("#characteristic").empty();
					var options = $("<option>").attr("value", "-1").text("Select");
					$("form select[id=characteristic]").append(options);
					for (x in charactoristic) {
						var char = charactoristic[x];
						console.log(char[0])
						console.log(char[1])
						options = $("<option>").attr("value", char[0]).text(char[1]);
						$("#characteristic").append(options);
						$('.selectpicker').selectpicker('refresh');
					}
				}

			}, error: function(message) {
				console.log(message);
			}

		});

	}

	$(document).on("change", "#module", function() {
		var moduleid = $(this).val();
		if (moduleid == null || moduleid == undefined
			|| moduleid == "" || moduleid <= 0 || moduleid == "select") {

			$("#characteristic").empty();
			$('.selectpicker').selectpicker('refresh');

			$("#subcharacteristic").empty();
			$('.selectpicker').selectpicker('refresh');
			alert("Please Select Module");

		} else {
			getCharactoristics(moduleid);
		}

	});

})
/*=============dependent DropDown for CHractoristic END=============*/


/*======dependent DropDown for SUB-CHARACTORISTICS=======*/
$(document).ready(function() {
	function getSubCharactoristics(characteristicid) {
		$.ajax({
			method: "get",
			url: "./getsubcharactoristics",
			data: {
				characteristicid: characteristicid
			},
			dataType: "json",
			success: function(response) {
				console.log("SUBCHAR RES" + response)
				if (response != null && response != undefined && response != "") {
					var subcharactoristicname = response;
					console.log(subcharactoristicname);
					$("#subcharacteristic").empty();
					var options = $("<option>").attr("value", "-1").text("Select");
					$("form select[id=subcharacteristic]").append(options);

					for (var x in subcharactoristicname) {
						var subchar = subcharactoristicname[x];
						console.log(subchar[0]);
						console.log(subchar[1]);
						options = $("<option>").attr("value", subchar[0]).text(subchar[1]);
						$("#subcharacteristic").append(options);
						$('.selectpicker').selectpicker('refresh');
					}
				}

			}, error: function(message) {
				console.log(message);
			}

		});

	}

	$(document).on("change", "#characteristic", function() {
		var characteristicid = $(this).val();
		if (characteristicid == null || characteristicid == undefined
			|| characteristicid == "" || characteristicid <= 0 || characteristicid == "select") {
			$("#subcharacteristic").empty();
			$('.selectpicker').selectpicker('refresh');
			alert("Please Select characteristic")
		} else {
			getSubCharactoristics(characteristicid);
		}

	});


})
/*======dependent DropDown for SUB-CHARACTORISTICS=======*/
/*======================================================dependent DropDown-END=============================================*/





/*=================================================================QUE FORM SAVE===================================================*/
let que_arr = [];
let option_arr = [];
var rowNum = 0;

$(document).ready(function() {

	$('#queformsave').click(function() {


		if (validateQue() == true) {

			$('.addformquestion').modal('hide');
			/*QUESTION FORM OBJ*/
			const que = {};


			if (localStorage.getItem("queid") !== null) {

				rowNum = localStorage.getItem("queid");
				rowNum++
				localStorage.setItem("queid", rowNum);
			} else if (rowNum == 0) {
				rowNum++
				localStorage.setItem("queid", rowNum);
			}

			/*		var queid = $("#quedetailid").val();
					if (queid !== null || queid !== undefined
						|| queid !== "") {
							alert(queid)
						que.quedetailid = queid;
			
					} else {
						rowNum = localStorage.getItem("queid");
						rowNum++
			
						localStorage.setItem("queid", rowNum);
						que.quedetailid = rowNum;
					}*/



			console.log("When Edit" + queEditId);
			if (queEditId > 0) {

				objIndex = que_arr.findIndex((obj => obj.quedetailid == queEditId));
				console.log("Before update: ", que_arr[objIndex]);

				que_arr[objIndex].quedetailid = queEditId;
				que_arr[objIndex].formid = $("#formid").val();
				que_arr[objIndex].quelablename = $("#quelablename").val();
				que_arr[objIndex].quename = $("#quename").val();
				que_arr[objIndex].quedescription = $("#quedescription").val();
				que_arr[objIndex].anstypeid = $("#anstypeid").val();
				que_arr[objIndex].iscompulsory = $('#iscompulsory').prop('checked');
				que_arr[objIndex].hasoption = $('#validatans').prop('checked');
				que_arr[objIndex].formateoption = $("#formateoption").val();

				console.log("After update: ", que_arr[objIndex]);

				queEditId = "";
				console.log(que_arr);
			} else {

				que.quedetailid = rowNum;
				que.formid = $("#formid").val();
				que.quelablename = $("#quelablename").val();
				que.quename = $("#quename").val();
				que.quedescription = $("#quedescription").val();
				que.anstypeid = $("#anstypeid").val();
				que.iscompulsory = $('#iscompulsory').prop('checked');
				que.hasoption = $('#validatans').prop('checked');
				que.formateoption = $("#formateoption").val();

				que_arr.push(que);
				console.log(que_arr);
			}
			var tableBody = $('#formquestion_datatable tbody');
			tableBody.empty();

			for (let i = 0; i < que_arr.length; i++) {

				tableBody.append('<tr><td>' + que_arr[i].quedetailid + '</td><td>' + que_arr[i].quename + '</td><td>' + que_arr[i].anstypeid + '</td><td>' + que_arr[i].iscompulsory + "</td><td class='text-center'><span data-toggle='modal' data-target='.addformquestion' class='queedit' id=" + que_arr[i].quedetailid + "><a href='javascript:void(0)' data-toggle='tooltip' data-placement='bottom' data-original-title='Edit' class='text-success fa-size'><i class='fa fa-pencil'></i></a></span><span class='delete-user-alert quedelete' id=" + que_arr[i].quedetailid + "><a href='javascript:void(0)' class='text-danger fa-size removetd' data-toggle='tooltip' data-placement='bottom' data-original-title='Delete'><i class='fa fa-trash'></i></a></span></td></tr>");
			}

			$(".quedelete").click(function() {
				console.log(this.id);
				let quedetailid = this.id;
				console.log("QUE ID" + quedetailid);
				$(this).closest("tr").remove();

				/*DELETE FROM ARRAY*/
				for (const prop of Object.getOwnPropertyNames(que)) {
					delete que[prop];
				}

			})

			$(function() {
				$(".queedit").click(function() {
					console.log(this.id);
					queEditId = this.id

					for (let i = 0; i < que_arr.length; i++) {
						if (queEditId == que_arr[i].quedetailfformdatalistid) {
							$("#quedetailid").val(que_arr[i].quedetailid);
							$("#quelablename").val(que_arr[i].quelablename);
							$("#quename").val(que_arr[i].quename);
							$("#quedescription").val(que_arr[i].quedescription);


							if (que_arr[i].iscompulsory) {
								$("#iscompulsory").prop('checked', true);
							} else {
								$("#iscompulsory").prop('checked', false);
							}


							if (que_arr[i].hasoption) {
								$("#validatans").prop('checked', true);
							} else {
								$("#validatans").prop('checked', false);
							}


							$("#anstypeid").val(que_arr[i].anstypeid);
							$("#sortorder").val(que_arr[i].sortorder);
							$('.selectpicker').selectpicker('refresh');
						}
					}

					$.each(option_arr, function(index, item) {

						if (queEditId == item.quedetailid) {
							console.log(item.quedetailid);

							console.log("option Name: " + item.optionname);
							var optarr = item.optionname.split(",");
							console.log(optarr);
							$("#singlechoicedata").css("display", "block");

							$.each(optarr, function(i) {
								/*$('#singlechoicedata').val(optarr[i]);*/
								$('[id^=singlechoicedata]').val(optarr[i]);
								console.log(optarr[i])
							})

						}

					});
				});

			});

			formoption();
		}

	})

});




function formoption() {
	/*OPTION OBJ*/
	const option = {};
	option.formid = $("#formid").val();
	option.quedetailid = rowNum;



	var name = [];
	var anstypeid = $("#anstypeid").val();

	if (anstypeid === '2') {
		$('[id^=singlechoicedata]').each(function(i, item) {
			name.push($(item).val());
			option.optionname = name.join(",");
		});

		option_arr.push(option);
		console.log(option_arr)
	}


	else if (anstypeid === '3') {
		$('[id^=multichoicedata]').each(function(i, item) {
			name.push($(item).val());
			option.optionname = name.join(",");
		});

		option_arr.push(option);
		console.log(option_arr)
	}


	else if (anstypeid === '6') {
		$('[id^=singleselectdata]').each(function(i, item) {
			name.push($(item).val());
			option.optionname = name.join(",");
		});

		option_arr.push(option);
		console.log(option_arr)
	}


	else if (anstypeid === '7') {
		$('[id^=multiselectdata]').each(function(i, item) {
			name.push($(item).val());
			option.optionname = name.join(",");
		});

		option_arr.push(option);
		console.log(option_arr)
	}


}

/*===========================================QUE FORMHEADER SAVE WITH QUE & OPTION=================================================*/


$(document).ready(function() {
	var FormHeader = {};
	$('#addform').click(function() {

		if (validateFormHeader() == true) {


			FormHeader.formid = $("#formid").val();
			FormHeader.titletext = $("#titletext").val();
			FormHeader.aliasname = $("#aliasname").val();


			var modulename = $("#module option:selected").html();
			FormHeader.module = modulename;

			var characteristicname = $("#characteristic option:selected").html();
			FormHeader.characteristic = characteristicname;

			var subcharacteristicname = $("#subcharacteristic option:selected").html();
			FormHeader.subcharacteristic = subcharacteristicname;


			FormHeader.recurrance = $("#recurrance").val();
			FormHeader.startmonth = $("#startmonth").val();
			FormHeader.complianceperiod = $("#complianceperiod").val();
			FormHeader.date_from = $("#date_from").val();
			FormHeader.textfield = $("#textfield").val();
			FormHeader.active = $("#active").val();


			/*MERGE 3 START*/

			if (que_arr.length != 0) {
				var formdata = {
					formheader: FormHeader,
					formquestiondetail: que_arr,
					formquestionoption: option_arr
				};
				console.log(formdata);
				/*END MERGE OBJ*/

				var FormData = JSON.stringify(formdata);
				console.log(FormData);
				$.ajax({
					url: '/efiveform/saveform',
					method: 'POST',
					data: FormData,
					contentType: "application/json; charset=utf-8",
					success: function() {
						alert("Form-Header Saved successfully!");
						window.location.reload();
					},
					error: function(error) {
						alert(error);
					}

				})
			}

			else {

				alert("please add Quetions!!!");


			}
			$("#portfolio_details").hide();
			$("#portfolio_add_detail").show();
		}

	})

});





/*======================================================EDIT FORM START==============================================*/
var queEditId = "";


$(document).on("click", ".editform", function() {

	/*FORM HEADER EDIT*/
	const formid = this.id;
	que_arr = [];

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
						console.log(formheader);

						$("#formid").val(formheader.formid);
						$("#titletext").val(formheader.titletext);
						$("#aliasname").val(formheader.aliasname);


						var modulename = formheader.module;
						$("#module option:contains(" + modulename + ")").attr('selected', true).change();
						/*$("#module").val(formheader.module).change();*/


						var charname = formheader.characteristic;
						setTimeout(function() {
							$("#characteristic option:contains(" + charname + ")").attr('selected', true).change();
						}, 1000);
						/*$("#characteristic").val(formheader.characteristic).change();*/


						var subcharname = formheader.subcharacteristic;
						setTimeout(function() {
							$("#subcharacteristic option:contains(" + subcharname + ")").attr('selected', true).change();
						}, 1500);



						$("#recurrance").val(formheader.recurrance).change();
						$("#startmonth").val(formheader.startmonth).change();
						$("#complianceperiod").val(formheader.complianceperiod);
						$("#date_from").val(formheader.date_from);
						let active = (formheader.active);
						if (active == "Active") {
							$("#active").prop("checked", true);
						}
						$("#textfield").val(formheader.textfield);
						$('.selectpicker').selectpicker('refresh');

						/*END FORM HEADER EDIT*/

						/*=====================================QUETION FORM EDIT OR DELETE=============*/

						var formquestiondetail = response.dataMap.quedatalist;
						console.log(formquestiondetail);

						que_arr.push(...formquestiondetail);
						/*console.log(que_arr);*/
						var tableBody = $('#formquestion_datatable tbody');
						tableBody.empty();

						for (let i = 0; i < que_arr.length; i++) {

							tableBody.append('<tr><td>' + que_arr[i].quedetailid + '</td><td>' + que_arr[i].quename + '</td><td>' + que_arr[i].anstypeid + '</td><td>' + que_arr[i].iscompulsory + "</td><td class='text-center'><span data-toggle='modal' data-target='.addformquestion' class='queedit' id=" + que_arr[i].quedetailid + "><a href='javascript:void(0)' data-toggle='tooltip' data-placement='bottom' data-original-title='Edit' class='text-success fa-size'><i class='fa fa-pencil'></i></a></span><span class='delete-user-alert quedelete' id=" + que_arr[i].quedetailid + "><a href='javascript:void(0)' class='text-danger fa-size removetd' data-toggle='tooltip' data-placement='bottom' data-original-title='Delete'><i class='fa fa-trash'></i></a></span></td></tr>");

						}
						$(function() {
							$(".queedit").click(function() {
								console.log(this.id);
								queEditId = this.id




								for (let i = 0; i < que_arr.length; i++) {
									if (queEditId == que_arr[i].quedetailid) {

										$("#quedetailid").val(que_arr[i].quedetailid);
										$("#quelablename").val(que_arr[i].quelablename);
										$("#quename").val(que_arr[i].quename);
										$("#quedescription").val(que_arr[i].quedescription);

										if (que_arr[i].iscompulsory) {
											$("#iscompulsory").prop('checked', true);
										} else {
											$("#iscompulsory").prop('checked', false);
										}

										if (que_arr[i].hasoption) {
											$("#validatans").prop('checked', true);
										} else {
											$("#validatans").prop('checked', false);
										}

										$("#anstypeid").val(que_arr[i].anstypeid);
										$("#sortorder").val(que_arr[i].sortorder);
										$('.selectpicker').selectpicker('refresh');
									}
								}

								/*OPTION EDIT*/
								var formoption = response.dataMap.queoptionlist;
								console.log(formoption);

								$.each(formoption, function(index, item) {

									if (queEditId == item.quedetailid) {
										console.log(item.quedetailid);

										console.log("option Name: " + item.optionname);
										var optarr = item.optionname.split(",");
										console.log(optarr);
										$("#singlechoicedata").css("display", "block");

										$.each(optarr, function(i) {
											/*$('#singlechoicedata').val(optarr[i]);*/
											$('[id^=singlechoicedata]').val(optarr[i]);
											console.log(optarr[i])
										})

									}

								});

							});

						});


						/*DLETE QUESTION*/

						$(".quedelete").click(function() {
							console.log(this.id);
							var quedetailid = this.id;
							console.log("QUE ID" + quedetailid);
							var $tr = $(this).closest('tr');

							$.ajax({
								url: "/efiveform/deleteque/" + quedetailid,
								method: "post",
								success: function(response) {

									if (response != null && response != undefined && response != "") {
										const serverResponse = response;
										if (serverResponse.status == "200") {
											$tr.find('td').fadeOut(2000, function() {
												$tr.remove();
												que_arr.pop(quedetailid);
											});

											/*DELETE FROM ARRAY*/
											/*for (const prop of Object.getOwnPropertyNames(que)) {
												delete que[prop];
											}*/
											alert("Success!!! \nQuestion has been deleted.!!");
										}
									}
								},
								error: function(reponse) {
									console.log("Error: " + reponse);
								}

							});

						})

					}

				}

			},


			error: function(reponse) {
				console.log("Error: " + reponse);
			}

		})

	}

});
/*=============================================================EDIT FORM END==================================================*/








/*========================================================PREVIEW FORM STRAT====================================================================*/

$(document).on("click", ".previewform", function() {

	/*FORM PREVIEW*/
	const formid = this.id;
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
									<input type='radio' id='choice${i + 1}' name='choicetwo' class='custom-control-input' checked=''> 
									<label class='custom-control-label font-weight-300 m-t-5' for='choice${i + 1}'> ${i} </label></div></div>`
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
									 <input type="checkbox" class="custom-control-input" id="choiceckbox1${i + 1}">
									 <label class="custom-control-label font-weight-300 m-t-5" for="choiceckbox1${i + 1}"> ${i} </label></div></div>`
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
									 <input type="text" class="form-control" placeholder="Enter Your Answer">
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
								<select class="selectpicker" data-style="lineheight12 bg-transfer"
                                                    data-live-search="true">
                                                    <option value="" selected="selected">Select</option>`;

											for (let i of optarr) {
												str += `<option value=${i}>${i}</option>`
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
								 <select class="selectpicker" multiple data-selected-text-format="count"
                                                    data-style="btn-light bg-transfer" data-actions-box="true">
                                                    <option value="" selected="selected">Select</option>`;

											for (let i of optarr) {
												str += `<option value=${i}>${i}</option>`
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

			},


			error: function(reponse) {
				console.log("Error: " + reponse);
			}

		})

	}

});
/*==================================================PREVIEW FORM END===========================================================*/



/*============================================================DELETE FORM===================================================*/

$(document).on("click", ".deleteform", function() {


	/*FORM HEADER DELETE*/
	const formid = this.id;


	swal({
		title: "Are you sure?",
		text: "Form data will be deleted permanently.!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				deleteform(formid);
				$(this).closest("tr").remove();
			}

		});

});

function deleteform(formid) {
	console.log(formid);
	$.ajax({
		url: "/efiveform/deleteform/" + formid,
		method: "post",
		success: function(response) {
			if (response != null && response != undefined && response != "") {
				const serverResponse = response;
				if (serverResponse.status == "200") {
					alert("Success!!! \nForm has been deleted.!!");
					window.location.reload();
				}

			}

		},
		error: function(reponse) {
			console.log("Error: " + reponse);
		}

	});

}


