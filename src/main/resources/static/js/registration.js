$(document).ready(function(){
	console.log('registracijaa');

	$('#registerbtn').on('click', function(event){
		
		console.log('kliknuto dugme');
		var userNameInput = $("input[name='username']");
		var passwordInput = $("input[name='password']");
		var username=userNameInput.val();
		var password=passwordInput.val();
		console.log("username i pass su: "+username+password)
		
		var user = {
				'email': username,
				'password': password
		}
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url :"https://localhost:8443/api/users/create",
			data : JSON.stringify(user),
			dataType : 'json',
			success : function(data) {
				alert('uspesno ste se registrovali');
				
				window.location.replace("https://localhost:8443/");
			},
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		
//		$.post("https://localhost:8443/auth/login", {}, function(data){
//			console.log(data);
			
			
		});
		
		event.preventDefault();
		return false;
	});
	
	
	
});