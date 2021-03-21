$(document).ready(function() {
	$("button").click(function() {
		console.log("bye");
		$.ajax({
			type : 'POST',
			url : 'https://url.shrtn/spring/',
			data : JSON.stringify({
				"full_url" : $("#urlinput").val()
			}),
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				console.log("Hai");
				$("#shorturltext").val(data.short_url);
			}
		});
	});
});