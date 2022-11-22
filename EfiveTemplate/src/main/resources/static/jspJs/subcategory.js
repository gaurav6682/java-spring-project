function validateCatForm() {

	var subcategoryname = $("#subcategoryname").val();
	var description = $("#subcategorydescription").val();



	if (/^[a-zA-Z]+$/.test(subcategoryname)) {
		alert("Sub-Category Name Requerd OR Accept Only Alphabets.!!!");
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


$("#addsubcat").click(function(event) {
	event.preventDefault();
	if (validateCatForm() == true) {
		var proForm = $("#subcategoryForm")[0];
		proForm.method = "post";
		proForm.action = "/efive/savesubcategoryData";
		proForm.submit();
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
			"url": "./getsubcategoryData",
			"data": function(d) {
				d.extraMsg = "Hello Message";
			},
			"dataSrc": "subcatList"
		},
		"columns": [
			{
				"data": "0", "name": "subcategoryid", "title": "SUB-CATEGORY ID"
			},
			{
				"data": "1", "name": "categoryname", "title": "CATEGORY NAME"
			},
			{
				"data": "2", "name": "subcategoryname", "title": "SUB-CATEGORY NAME"
			},
			{
				"data": "3", "name": "subcategorydescription", "title": "DESCRIPTION"
			},
			{
				"data": "4", "name": "active", "title": "ACTIVE"
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
				targets: 5,
				className: "text-center",

				render: function(full) {

					var editBtn = $("<a>").addClass("btn btn-icons btn-success dtbtn-padding edit-subcategory").attr("subcategoryid", full[0]).append($("<i>").addClass("fa fa-pencil").attr("aria-hidden", true));
					var delBtn = $("<button>").addClass("btn btn-icons btn-danger mx-1 dtbtn-padding delete-subcategory").attr("subcategoryid", full[0]).append($("<i>").addClass("fa fa-trash").attr("aria-hidden", true));
					return editBtn.get(0).outerHTML + delBtn.get(0).outerHTML;
				}
			}
		]
	}
	)

});






//DELETE CATEGORY
$(document).on("click", ".delete-subcategory", function() {
	var subcategoryid = $(this).attr("subcategoryid");
	//alert(subcategoryid);
	swal({
		title: "Are you sure?",
		text: "Subcategory data will be deleted permanently.!",
		icon: "warning",
		buttons: true,
		dangerMode: true,
	})
		.then((willDelete) => {
			if (willDelete) {
				deletesubcategory(subcategoryid);
				$(this).closest("tr").remove();
			}
		});
});

function deletesubcategory(subcategoryid) {
	console.log(subcategoryid);
	$.ajax({
		url: "/efive/deletesubcategory/" + subcategoryid,
		method: "post",
		success: function(response) {
			if (response != null && response != undefined && response != "") {
				const serverResponse = response;
				if (serverResponse.status == "200") {
					swal("Success! Sub-Category has been deleted!", {
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




//EDIT SUB-CATEGORY

$(document).on("click", ".edit-subcategory", function() {
	$("#add_subcategory").modal('show');

	const subcategoryid = $(this).attr("subcategoryid");

	if (subcategoryid != null && subcategoryid != undefined) {
		$.ajax({
			url: "/efive/editsubcategory/" + subcategoryid,
			method: "get",
			success: function(response) {
				if (response != null && response != undefined && response != "") {
					const serverResponse = response;
					if (serverResponse.status == "200") {
						var subcategorymaster = response.dataMap.subcategorymasterlist;
						console.log(subcategorymaster);
						$("#subcategoryid").val(subcategorymaster.subcategoryid);
						$("select#categoryname").val(subcategorymaster.categoryname).change();
						$("#subcategoryname").val(subcategorymaster.subcategoryname);
						$("#subcategorydescription").val(subcategorymaster.subcategorydescription);
						let active = (subcategorymaster.active);
						//alert(active);
						if (active == "Active") {
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



