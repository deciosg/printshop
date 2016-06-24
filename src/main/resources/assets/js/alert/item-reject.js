jQuery('button.item-reject').click(function() {
	var token = jQuery("meta[name='csrf-token']").attr("content");
    var header = jQuery("meta[name='csrf-header']").attr("content");
    
    var workId = jQuery(this).data('work-id');
	var itemId = jQuery(this).data('item-id');
    
	sweetAlert({
		title: 'Você tem certeza?', 
		text: 'Deseja marcar esse pagamento como recebido?', 
		type: 'warning',
		input: 'text',
		showCancelButton: true,
		cancelButtonText: 'Não',
		confirmButtonText: 'Sim',
		confirmButtonColor: '#FF4A55',
		inputValidator: function(value) {
			return new Promise(function(resolve, reject) {
				if (value) {
					resolve();
				} else {
					reject('Por favor escreva o motivo.');
				}
			});
		}
	}).then(function(reason) {
		jQuery.ajax({
			url: '/item/refugar',
			method: 'POST',
			data: {
				workId: workId,
				itemId: itemId,
				reason: reason
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function() {
				location.reload();
			},
			error: function() {
				sweetAlert({
					title: 'Erro ao rejeitar item',
					text: 'Por favor, entre em contato com o suporte.',
					type: 'error'
				});
			}
		});
	});
});