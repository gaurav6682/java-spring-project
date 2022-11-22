
/*========================================================PREVIEW FILL - FORM STRAT====================================================================*/

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
					console.log(serverResponse);
					if (serverResponse.status == "200") {
						var formheader = response.dataMap.formheaderlist;
						var formquestiondetail = response.dataMap.quedatalist;
						var formoption = response.dataMap.queoptionlist;
						var fillformans = response.dataMap.fillformans;
						var fillformheader = response.dataMap.fillformheader;
						console.log(formheader);
						console.log(formquestiondetail);
						console.log(formoption);
						console.log(fillformans);
						console.log(fillformheader);


						$("#compliteddate").text(fillformheader[0]);
						/*$("#compliteddate").text(fillformheader.compliteddate);*/
						/*alert(fillformheader[0])*/

						$("#formtitle").text(formheader.titletext);

						$("#description").text(formheader.textfield);
						/*alert(formheader.textfield)*/


						var queshadow = $('.queshadow');
						queshadow.empty();
						var displayoption = $('displayoption');
						displayoption.empty();




						for (let i = 0; i < formquestiondetail.length; i++) {

							

									queshadow.append(`<div class='card mb-2'><div class='card-body'><div class='row pl-2 pr-2'><div class='col-xl-1 col-lg-1 col-sm-2 col-xs-12 
									colmspadding'><span class='question'> Q : ${formquestiondetail[i].quedetailid}</span></div>
									<div class='col-xl-11 col-lg-11 col-sm-10 col-xs-12 colmspadding'><div class='form-group mb-0 text-justify'>
									<p class='font-weight-700 mb-1 text-justify'>${formquestiondetail[i].quename}</p>
									<p class='mb-1'>${formquestiondetail[i].quedescription}</p></div> <div class='form-group mb-0'><div class='row pl-2 pr-2'>

												                                          <div class="col-xl-12 col-lg-12 col-sm-12 colmspadding">
                                             <p class="font-weight-700 mb-1 text-justify">Answer</p>
                                             <p class="mb-1 text-justify">${fillformans[i].ansname}</p>
                                          </div>

											</div></div>`)

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
