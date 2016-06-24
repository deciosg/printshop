var elixir = require("laravel-elixir");

elixir.config.assetsPath = "assets";
elixir.config.publicPath = "static";

elixir(function(mix) {
	mix.styles
	([ "../../node_modules/bootstrap/dist/css/bootstrap.css",
	   "login.css",
	   "loading.css"
	   ], "static/css/login.css");

	mix.styles
	([ "../../node_modules/bootstrap/dist/css/bootstrap.css",
	   "../../node_modules/font-awesome/css/font-awesome.css",
	   "../../node_modules/sweetalert2/dist/sweetalert2.css",
	   "form-input.css",
	   "theme.css",
	   "loading.css"
	   ], "static/css/app.css");
	
	mix.scripts
	([ "../../node_modules/jquery/dist/jquery.js",
	   "../../node_modules/jquery-mask-plugin/dist/jquery.mask.js",
	   "../../node_modules/jquery-validation/dist/jquery.validate.js",
	   "../../node_modules/bootstrap/dist/js/bootstrap.js",
	   "../../node_modules/moment/moment.js",
	   "../../node_modules/moment/locale/pt-br.js",
	   "../../node_modules/accounting/accounting.js",
	   "../../node_modules/sweetalert2/dist/sweetalert2.js",
	   "../../node_modules/vue/dist/vue.js",
	   "../../node_modules/underscore/underscore.js",
	   "alert/employee-delete.js",
	   "alert/item-analysis.js",
	   "alert/item-approve.js",
	   "alert/item-avaliable.js",
	   "alert/item-cancel.js",
	   "alert/item-production.js",
	   "alert/item-reject.js",
	   "alert/payment-receive.js",
	   "formatting/calendar.js",
	   "formatting/date.js",
	   "formatting/datetime.js",
	   "formatting/money.js",
	   "validate/conf/bootstrap.conf.js",
	   "validate/module/cpf.js",
	   "validate/client-form.js",
	   "theme/theme.js"
	  ], "static/js/app.js")
	
	mix.copy("node_modules/font-awesome/fonts", "static/fonts");
});