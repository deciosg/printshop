jQuery('.datetime-format').each(function(i, e) {
	var date = jQuery(e).data('date-format');
	var time;
	if (date != null) {
		time = moment(date).format('hh:mm:ss DD/MM/YYYY');
	} else {
		time = '&nbsp;';
	}
	jQuery(e).html(time);
});
