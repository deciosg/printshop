$("#client-form").validate({
	rules: {
		name: {
			required: true,
			minlength: 6,
		},
		cpf: {
			required: true,
			cpf: true
		},
		"address.street": {
			required: true
		},
		"address.borough": {
			required: true
		},
		"address.city": {
			required: true
		},
		"address.state": {
			required: true
		},
		email: {
			required: true,
			email: true
		},
		landline: {
			required: true
		},
		cellular: {
			required: true
		}
	},
	messages: {
		name: {
			required: "Por favor insira um nome",
			minlength: "O Nome deve conter no mínimo 6 caracteres"
		},
		cpf: {
			required: "Por favor insira um CPF",
			cpf: "Por favor insira um CPF válido"
		},
		"address.street": {
			required: "Por favor insira um logradouro"
		},
		"address.borough": {
			required: "Por favor insira um bairro"
		},
		"address.city": {
			required: "Por favor insira uma cidade"
		},
		"address.state": {
			required: "UF"
		},
		email: {
			required: "Por favor insira um e-mail",
			email: "Por favor insira um e-mal válido"
		}
	}
});