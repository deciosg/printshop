package cc.printshop.repository;

import org.springframework.data.repository.CrudRepository;

import cc.printshop.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

}
