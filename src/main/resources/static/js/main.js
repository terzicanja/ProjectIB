$(document).ready(function(){
	console.log('probaaaaa');

	
	$.get("https://localhost:8443/api/users", {}, function(data){
		console.log(data);
		for(var i=0; i<data.length; i++){
			$('#test').append("<div>"+
					"<p>"+data[i].email+"</p></div>")
		}
		
	});
	
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