jQuery('.calendar-format').each(function(i, e) {
	var time = moment(jQuery(e).data('date-format'));
	console.log(time.calendar());
	jQuery(e).html(time.calendar());
});
