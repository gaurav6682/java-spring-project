function validateLoginForm() {


	var email = $("#email").val();
	var password = $("#password").val();



	var atposition = email.indexOf("@")
	var dotposition = email.lastIndexOf(".");
	if (atposition < 1 || dotposition < atposition + 2 || dotposition + 2 >= email.length) {
		alert("Please enter a valid E-mail address \n Email must contain @ Sign.");
		return false;
	}


	if (password == null || password == undefined
		|| password == "" || password.length < 5) {
		alert("Please Enter correct password ...!");
		$("#password").focus();
		return false;
	}




	return true;
}





$(document).ready(function() {

	$("#btnlogin").click(function(event) {
		event.preventDefault();
		if (validateLoginForm() == true) {
			var Email = $("#email").val();
			var password = $("#password").val();
			var arr = [];
			var user = {};
			user.email = Email;
			user.password = password;
			arr.push(user);
			//alert(JSON.stringify(user));
			//alert(JSON.stringify(arr));
			$.ajax({
				url: "./signin",
				type: "POST",
				data: JSON.stringify(user),
				contentType: "application/json; charset=utf-8",
				success: function(response) {
					var serverResponse = JSON.parse(response);
					if (serverResponse.status == "200") {
						window.location.href = "/efive/" + serverResponse.redirectUrl;
					} else if (serverResponse.status === "404") {
						alert(serverResponse.message);
					}
				},
			});
		}
	});
});
