$('.date-format').each(function(i, e) {
	var time = moment($(e).data('date-format'));
	$(e).html(time.format('DD/MM/YYYY'));
});
