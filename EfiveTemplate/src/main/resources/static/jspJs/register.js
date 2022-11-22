function validateRegForm() {

	var username = $("#username").val();
	var email = $("#email").val();
	var password = $("#password").val();
	var confpassword = $("#conformpassword").val();

	if (username == null || username == undefined
		|| username == "") {

		alert("username Name Requerd...!");
		$("#username").focus();
		return false;
	}


	var atposition = email.indexOf("@")
	var dotposition = email.lastIndexOf(".");
	if (atposition < 1 || dotposition < atposition + 2 || dotposition + 2 >= email.length) {
		alert("Please enter a valid E-mail address \n Email must contain @ Sign.");
		return false;
	}


	if (password == null || password == undefined
		|| password == "" || password.length < 5) {
		alert("password Requerd...! \nPassword must be at least 5 char long");
		$("#password").focus();
		return false;
	}


	if (password != confpassword) {
		
		alert("Conform Your Password \npassword must be same!");
		return false;
	}


	return true;
}




$(document).ready(function() {

	$("#btnSubmit").click(function(event) {
		event.preventDefault();
		if (validateRegForm() == true) {
			var userName = $("#username").val();
			var email = $("#email").val();
			var password = $("#password").val();
			var password = $("#password").val();

			alert(userName);
			alert(email);
			alert(password);

			var arr = [];
			var user = {};
			user.name = userName;
			user.email = email;
			user.password = password;

			arr.push(user);
			console.log(JSON.stringify(arr));
			console.log(JSON.stringify(user));

			$.ajax({
				url: "./save",
				type: "POST",
				data: JSON.stringify(user),
				contentType: "application/json; charset=utf-8",
				success: function(response) {
					var serverResponse = JSON.parse(response);
					if (serverResponse.status == "200") {
						window.location.href = "/efive/" + serverResponse.redirectUrl;

					} else if (serverResponse.status == "404") {
						alert(serverResponse.message)
					}
				},
				error: function(e) {
					console.log(e);
				}


			});
		}
	});
});