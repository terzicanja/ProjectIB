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
//		headers: {'Authorization': 'Bearer'+token},
		contentType: 'application/json',
		crossDomain: true,
		dataType: 'json',
		success:function(data){
			console.log(data);
			for(var i=0; i<data.length; i++){
				$('#users').append(''+
//						'<tr><th>username</th><th>role</th></tr>'+
						'<tr><td>'+data[i].email+'</td><td>'+'rolee'+'</td>'+
						'<td><button>dwnld jks</button></td>'+
						'<td><button>dwnld sert</button></td>'+
						'<td><button>send email</button></td></tr>'+
						''+
//						"<p>"+data[i].email+"</p>" +
								'')
			}
		}
	});
	
	$.ajax({
		url: 'https://localhost:8443/api/users/inactive',
		type: 'GET',
		headers: {'Authorization': 'Bearer'+token},
		contentType: 'application/json',
//		crossDomain: true,
		dataType: 'json',
		success:function(data){
			console.log(data);
			for(var i=0; i<data.length; i++){
				$('#inactive').append(''+
//						'<tr><th>username</th><th>role</th></tr>'+
						'<tr><td>'+data[i].email+'</td><td>'+'rolee'+'</td>'+
//						'<td><button type="button" id="activatebtn" name="'+data[i].email+'">activate user</button></td></tr>'+
						'<td><input type="button" value="activate" id="activatebtn" name="'+data[i].id+'"></td></tr>'+
						''+
//						"<p>"+data[i].email+"</p>" +
								'')
			}
		}
		
		
	});
	
	$(document).on('click','input[type=button]#activatebtn',function(){
		var userId = jQuery(this).attr('name');
//		var userId = $(this).attr('name');
		console.log('aktiviram korisnika: ' + userId);
		
		$.ajax({
			url: 'https://localhost:8443/api/users/update/'+userId,
			type: 'PUT',
			contentType: 'application/json',
			dataType: 'json',
			success:function(data){
				console.log('uspesno ste aktivirali korisnika: '+data);
				
			}
		});
		
	});
	
	
	$('#searchbtn').on('click', function(){
		var search = '#searchinput';
		var s = $(search).val();
		var table = document.getElementById("users");
		$.ajax({
			url: 'https://localhost:8443/api/users/search/'+s,
			type: 'GET',
			contentType: 'application/json',
			dataType: 'json',
			success:function(data){
				console.log('uspesno ste pretrazili: '+data);
				
//				while(table.rows.length > 0) {
//					table.deleteRow(0);
//				}
				
				$('#users').empty();
				
				for(var i=0; i<data.length; i++){
					$('#users').append(''+
//							'<tr><th>username</th><th>role</th></tr>'+
							'<tr><td>'+data[i].email+'</td><td>'+'rolee'+'</td>'+
							'<td><button>dwnld jks</button></td>'+
							'<td><button>dwnld sert</button></td>'+
							'<td><button>send email</button></td></tr>'+
							''+
//							"<p>"+data[i].email+"</p>" +
									'')
				}
			}
		});
		
	});
	
	
//	$('button[type=button]#activatebtn').on('click', function(event){
//		var userId = $(this).attr('name');
//		console.log('aktiviram korisnika: ' + userId);
//		
//		
//		
//		event.preventDefault();
//		return false;
//	});
	
	
	
	
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