package ca.mcgill.ecse321.gitfit.dao;

import ca.mcgill.ecse321.gitfit.model.Billing;
import ca.mcgill.ecse321.gitfit.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BillingRepository extends CrudRepository<Billing,Integer> {
    Billing findBillingById(int id);
    Billing findBillingByCustomer(Customer customer);
}
