package ca.mcgill.ecse321.gitfit.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.gitfit.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Customer findCustomerByUsername(String username);
}
