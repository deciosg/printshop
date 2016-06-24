package cc.printshop.util;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import cc.printshop.model.Payment;
import cc.printshop.model.StatusPayment;

public class PaymentUtil {
	private final static int IN_CASH = 0;
	private final static int ENTRY_50 = 1;
	
	public static final List<Payment> payment(Integer type, Double total){
		List<Payment> pay = new LinkedList<>();
		if (type == IN_CASH) {
			pay.add(new Payment(total, new Date(), StatusPayment.RECEIVED));
		} else if (type == ENTRY_50) {
			pay.add(new Payment(total * 0.5, new Date(), StatusPayment.RECEIVED));
			pay.add(new Payment(total * 0.5, StatusPayment.PENDING));
		}
		return pay;
	}
}
