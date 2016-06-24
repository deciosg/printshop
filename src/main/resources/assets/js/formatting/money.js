$('.money-format').each(function(i, e) {
	var money = $(e).data('money-format');
	$(e).html(accounting.formatMoney(money, "R$ ", 2, ".", ","));
});