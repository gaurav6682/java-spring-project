
function validateCatForm() {
	var categoryname = $("#categoryname").val();
	var description = $("#categorydescription").val();

	if (!/^[A-Za-z]+$/.test(categoryname)) {
		alert("Category Name Requerd OR Accept Only Alphabets.!!!");
		$("#categoryname").focus();
		window.onbeforeunload();
		return false;
	}

	if (description == null || description == undefined
		|| description == "") {
		alert("Category Description Requerd...!");
		window.onbeforeunload();

		$("#description").focus();
		return false;
	}
	return true;
}



/*$('#addcatbtn').on('click', function() {
  var name = $("#categoryname").val();
  alert(name);
$.ajax({
	type: "post",
	url:"/efive/savecategoryData",
	 data: {name:name},
		    
	success: function(data) {
	console.log(response);
			if (response != null && response != undefined && response != "" && validateCatForm() == true) {
				const serverResponse = response;
				alert("HELLO")
				console.log(serverResponse);
				if (serverResponse.status == "404") {
					console.log(response.message);
					alert(response.message);
				}
			}
		},
		error: function(reponse) {
			console.log("Error: " + reponse);
		}
});
$(document).ajaxStop(function() {
			setTimeout("window.location = 'addcategory'", 50);
		})
})*/



$("#addcatbtn").click(function(event) {
	event.preventDefault();
	if (validateCatForm() == true) {
		var catForm = $("#categoryForm")[0];
		catForm.method = "post";
		catForm.action = "/efive/savecategoryData";
		catForm.submit();
	}


});









$(function($) {
	var table = $("#paginatedcategoryTable").DataTable({
		"processing": true,
		"serverSide": true,
		"destroy": true,
		"lengthMenu": [[5, 7, 10], [5, 7, 10]],
		"displayLength": 5,
		"order": [[0, "asc"]],
		"StateSave": false,
		"searchable": true,
		"ajax": {
			"url": "./getcategoryData",
			"data": function(d) {
				d.extraMsg = "Hello Message";
			},
			"dataSrc": "categoryList"
		},
		"columns": [
			{
				"data": "0", "name": "categoryid", "title": "CATEGORY ID"
			},
			{
				"data": "1", "name": "categoryname", "title": "CATEGORY NAME"
			},
			{
				"data": "2", "name": "categorydescription", "title": "DESCRIPTION"
			},

			{
				"data": "3", "name": "active", "title": "ACTIVE"
			},
			{
				"data": null, "className": "center", "title": "ACTION",
			}

		],
		columnDefs: [
			{
				'targets': [-1],
				'orderable': false
			}, {
				'className': 'text-left',
				'targets': [1, 2]
			},
			{
				'targets': 4,
				'className': "text-center",

				render: function(full) {
					var editBtn = $("<a>").addClass("btn btn-icons btn-success dtbtn-padding edit-category").attr("categoryid", full[0]).append($("<i>").addClass("fa fa-pencil").attr("aria-hidden", true));
					var delBtn = $("<button>").addClass("btn btn-icons btn-danger mx-1 dtbtn-padding delete-category").attr("categoryid", full[0]).append($("<i>").addClass("fa fa-trash").attr("aria-hidden", true));
					return editBtn.get(0).outerHTML + delBtn.get(0).outerHTML;
				}
			}
		]
	}
	)

});






//DELETE CATEGORY

$(document).on("click", ".delete-category", function() {
	var categoryid = $(this).attr("categoryid");
	//alert(categoryid);
	swal({
		title: "Are you sure?",
		text: "category data will be deleted permanently.!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				deletecategory(categoryid);
				$(this).closest("tr").remove();
			}
		});
});

function deletecategory(categoryid) {
	//console.log(categoryid);
	$.ajax({
		url: "/efive/deletecategory/" + categoryid,
		method: "post",
		success: function(response) {
			if (response != null && response != undefined && response != "") {
				const serverResponse = response;
				if (serverResponse.status == "200") {
					swal("Success! Category has been deleted!", {
						icon: "success",
					});
				}
			}
		},
		error: function(reponse) {
			console.log("Error: " + reponse);
		}
	});

}







//------------EDIT CATEGORY


$(document).on("click", ".edit-category", function() {
	$("#add_category").modal('show');
	const categoryid = $(this).attr("categoryid");
	//alert(categoryid);
	if (categoryid != null && categoryid != undefined) {

		$.ajax({
			url: "/efive/editcategory/" + categoryid,
			method: "get",
			success: function(response) {
				if (response != null && response != undefined && response != "") {
					const serverResponse = response;
					if (serverResponse.status == "200") {
						var categorymaster = response.dataMap.categorymasterlist;
						$("#categoryid").val(categorymaster.categoryid);
						$("#categoryname").val(categorymaster.categoryname);
						$("#categorydescription").val(categorymaster.categorydescription);
						let active = (categorymaster.active);
							if(active == "Active"){
								 $("#active").prop("checked", true);
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




