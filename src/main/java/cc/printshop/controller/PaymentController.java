package cc.printshop.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.printshop.model.Payment;
import cc.printshop.model.StatusPayment;
import cc.printshop.repository.PaymentRepository;

@Controller
@RequestMapping("/pagamentos")
public class PaymentController {
	@Autowired
	private PaymentRepository paymentRepository;

	@PreAuthorize("hasRole('ADMIN') or hasRole('ATTENDANT')")
	@RequestMapping(value = "/receber", method = RequestMethod.POST)
	public @ResponseBody String receivePayment(@RequestParam("paymentId") Long id, Payment payment) {
		payment = paymentRepository.findOne(id);
		payment.setPaidIn(new Date());
		payment.setStatus(StatusPayment.RECEIVED);
		paymentRepository.save(payment);
		return "recebido";
	}
}
