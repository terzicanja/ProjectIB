$(document).ready(function(){
	console.log('login');

	$('#loginbtn').on('click', function(event){
		
		console.log('kliknuto dugme');
		var userNameInput = $("input[name='username']");
		var passwordInput = $("input[name='password']");
		var username=userNameInput.val();
		var password=passwordInput.val();
		console.log("username i pass su: "+username+password)
		
		var user = {
				'username': username,
				'password': password
		}
		
		$.ajax({
			type : "POST",
			contentType : "application/json",
			url :"https://localhost:8443/auth/login",
//			data : user,
			data : JSON.stringify(user),
			dataType : 'json',
			success : function(data) {
				alert('uspesno ste se ulogovali');
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