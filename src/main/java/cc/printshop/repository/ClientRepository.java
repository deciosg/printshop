package cc.printshop.repository;

import org.springframework.data.repository.CrudRepository;

import cc.printshop.model.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{
	public Client findByCpf(String cpf);
}
