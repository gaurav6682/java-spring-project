
function validateProForm() {

	var productname = $("#productname").val();
	var description = $("#productdescription").val();
	var category = $("#categoryid").val();
	var subcategory = $("#subcategoryid").val();
	var price = $("#productprice").val();
	var manufacturedate = $("#manufacturedate").val();
	var serialno = $("#productserialno").val();
	var warranty = $("#productwarrenty").val();
	var discount = $("#productdiscount").val();
	var validfrom = $("#productvalidDate").val();
	var validto = $("#productvalidtoDate").val();



	if (productname == null || productname == undefined
		|| productname == "") {

		alert("Product Name Requerd...!");
		$("#productname").focus();
		return false;
	}

	if (description == null || description == undefined
		|| description == "") {
		alert("Product Description Requerd...!");
		$("#description").focus();
		return false;
	}

	if (category == null || category == undefined
		|| category == -1 || category <= 0) {
		alert("Product Category Requerd...!");
		$("#categoryid").focus();
		return false;
	}
	if (subcategory == null || subcategory == undefined
		|| subcategory == -1 || subcategory <= 0) {
		alert("Product Subcategory Requerd...!");
		$("#subcategoryid").focus();
		return false;
	}

	var regex = /^(\$|)([1-9]\d{0,2}(\,\d{3})*|([1-9]\d*))(\.\d{2})?$/;
	var passed = price.match(regex);
	if (passed == null) {
		alert("Enter price only. For example: 523.36 or $523.36");
		textBoxId.Value = "";
		return false;
	}
	if (manufacturedate == null
		|| manufacturedate == undefined
		|| manufacturedate == "") {
		alert("Product manufacturedate Requerd ...!");

		return false;
	}
	if (!/^\d+$/.test(serialno)) {
		// Contain numbers only
		alert("Product serialno Requerd & Containg Numeric Only ...!");
		$("#productserialno").focus();
		return false;
	}
	if (warranty == null || warranty == undefined
		|| warranty == "") {
		alert("Product warranty Requerd ...!");
		$("#productwarrenty").focus();
		return false;
	}


	if ($('input:radio').filter(':checked').length < 1) {
		alert("Please Select Any Condition ...!");
		return false;

	}

	if ($('input:checkbox').filter(':checked').length < 1) {
		alert("Please Select Any Color ...!");
		return false;

	}

	if (!/^\d+$/.test(discount)) {
		alert("Discount is required or Only Number.");
		$("#productdiscount").focus();
		return false;
	}
	if (validfrom == null || validfrom == undefined
		|| validfrom == "") {
		alert("ValidFrom is required...!");
		$("#productvaliddate").focus();
		return false;
	}


	if (validto == null || validto == undefined
		|| validto == "") {
		alert("ValidTo is required...!");
		$("#productvalidtodate").focus();
		return false;
	}

	var eDate = new Date(validto);
	var sDate = new Date(validfrom);
	if (validfrom != '' && validfrom != '' && sDate > eDate) {
		alert("Please ensure that the Valid-TO Date is greater than or equal to the Valid-From Date.");
		return false;
	}

	return true;
}



$("#addprobtn").click(function(event) {
	event.preventDefault();
	if (validateProForm() == true) {
		var proForm = $("#productForm")[0];
		proForm.method = "post";
		proForm.action = "/efive/saveData";
		proForm.enctype = "multipart/form-data";
		proForm.submit();
	}
});


$(function($) {
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#imgPreview').attr('src', e.target.result);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}
	$("#file").change(function() {
		readURL(this);
	});
});

var imageFile = document.getElementById("productimage");

$(document).on("change", "#productimage", function() {
	var files = imageFile.files;
	var blob = files[0];
	var mime = blob.type;

	var type = mime.substr(mime.lastIndexOf("/") + 1);

	if (type == "jpeg" || type == "png") {

	} else {
		$(this).val("");
		$.toast({
			heading: "Required",
			text: "Please select only [.jpeg, .png, .jpg] files.",
			showHideTransition: 'slide',
			icon: "error",
			loaderBg: "#fff",
			position: "top-right"
		});
	}
});



