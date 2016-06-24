jQuery('button.receive-payment').click(function() {
	var token = jQuery("meta[name='csrf-token']").attr("content");
    var header = jQuery("meta[name='csrf-header']").attr("content");
    
    var paymentId = jQuery(this).data('payment-id');
    
	sweetAlert({
		title: 'Você tem certeza?', 
		text: 'Deseja marcar esse pagamento como recebido?', 
		type: 'warning',
		showCancelButton: true,
		cancelButtonText: 'Não',
		confirmButtonText: 'Sim',
		confirmButtonColor: '#FF4A55',
	}).then(function() {
		jQuery.ajax({
			url: '/pagamentos/receber',
			method: 'POST',
			data: {
				paymentId: paymentId
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function() {
				location.reload();
			},
			error: function() {
				sweetAlert({
					title: 'Erro ao receber pagamento',
					text: 'Por favor, entre em contato com o suporte.',
					type: 'error'
				});
			}
		});
	});
});