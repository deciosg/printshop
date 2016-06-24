$('a.delete-user').click(function() {
	var userId = $(this).data("user-id");
	var locationUrl = $(this).data("location");
	swal({
		title: "Você tem certeza?", 
		text: "Tem certeza de que deseja excluir?", 
		type: "warning",
		showCancelButton: true,
		cancelButtonText: "Não",
		confirmButtonText: "Sim",
		confirmButtonColor: "#FF4A55"
	}).then(function(isConfirm) {
		if (isConfirm) {
			window.location.href = window.location.pathname + '/deletar/' + userId
		}
	});
});