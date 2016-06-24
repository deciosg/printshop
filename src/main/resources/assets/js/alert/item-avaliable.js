jQuery('button.item-avaliable').click(function() {
	var token = jQuery("meta[name='csrf-token']").attr("content");
    var header = jQuery("meta[name='csrf-header']").attr("content");
    
	var workId = jQuery(this).data('work-id');
	var itemId = jQuery(this).data('item-id');
	
	sweetAlert({
		title: 'Você tem certeza?', 
		text: 'Tem certeza de que deseja disponibilizar este item para retirada?', 
		type: 'warning',
		showCancelButton: true,
		cancelButtonText: 'Não',
		confirmButtonText: 'Sim',
		confirmButtonColor: '#3472F7'
	}).then(function() {
		jQuery.ajax({
			url: '/item/disponivel-para-retirada',
			method: 'POST',
			data: {
				workId: workId,
				itemId: itemId
			},
			beforeSend : function(xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function() {
				location.reload();
			},
			error: function() {
				sweetAlert({
					title: 'Erro ao disponibilizar item para retirada',
					text: 'Por favor, entre em contato com o suporte.',
					type: 'error'
				});
			}
		});
	});
});