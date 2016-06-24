package cc.printshop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cc.printshop.model.Client;
import cc.printshop.model.Work;

public interface WorkRepository extends CrudRepository<Work, Long> {
	public Integer total();

	public List<Work> showByClient(Client client);

	public List<Work> requestedDateDesc();
}
