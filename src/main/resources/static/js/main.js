$(document).ready(function(){
	console.log('probaaaaa');

	console.log(localStorage.getItem('token'));
	
	var token = localStorage.getItem('token');
	
	$.ajaxSetup({
		beforeSend: function(xhr){
			xhr.setRequestHeader("Accept", "application/json");
			xhr.setRequestHeader("Content-Type", "application/json");
			xhr.setRequestHeader("Access-Control-Allow-Origin", "/*");
			xhr.setRequestHeader("Authorization", "Bearer" + token);
			
			console.log(xhr.getResponseHeader("Authorization"));
		}
	});
	
	$.ajax({
		url: 'https://localhost:8443/api/users',
		type: 'GET',
		headers: {'Authorization': 'Bearer'+token},
		contentType: 'application/json',
//		crossDomain: true,
		dataType: 'json',
		success:function(data){
			console.log(data);
			for(var i=0; i<data.length; i++){
				$('#test').append("<div>"+
						"<p>"+data[i].email+"</p></div>")
			}
		}
	});
	
	
	
//	$.get("https://localhost:8443/api/users", {}, function(data){
//		console.log(data);
//		for(var i=0; i<data.length; i++){
//			$('#test').append("<div>"+
//					"<p>"+data[i].email+"</p></div>")
//		}
//		
//	});
	
});


function download() {

	var xhr = new XMLHttpRequest();
	xhr.open('GET', "/api/demo/download", true);
	xhr.responseType = 'blob';

	xhr.onload = function(e) {
		if (this.status == 200) {
			var blob = this.response;
			console.log(blob);
			var a = document.createElement('a');
			var url = window.URL.createObjectURL(blob);
			a.href = url;
			a.download = xhr.getResponseHeader('filename');
			a.click();
			window.URL.revokeObjectURL(url);
		}
	};

	xhr.send();
};