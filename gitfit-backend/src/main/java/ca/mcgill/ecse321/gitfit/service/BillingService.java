package ca.mcgill.ecse321.gitfit.service;

import ca.mcgill.ecse321.gitfit.dao.BillingRepository;
import ca.mcgill.ecse321.gitfit.dao.CustomerRepository;
import ca.mcgill.ecse321.gitfit.exception.SportCenterException;
import ca.mcgill.ecse321.gitfit.model.Billing;
import ca.mcgill.ecse321.gitfit.model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class BillingService {
    @Autowired
    BillingRepository  billingRepository;
    @Autowired
    CustomerRepository customerRepository;

    /**
     * Create or update billing information for a customer
     *
     * @author Li Yang Lei (LeiLiYang)
     * @param country
     * @param state
     * @param postalCode
     * @param cardNumber
     * @param address
     * @param username
     * @throws SportCenterException if the customer does not exist or the billing information fields are not completed
     * @return persisted billing object
     */
    @Transactional
    public Billing createOrUpdateBilling(String country, String state, String postalCode, String cardNumber, String address, String username) {

        //input validation
        if (country==null || state==null || postalCode==null || cardNumber==null || address==null || username==null ||country=="" || state=="" || postalCode=="" || cardNumber=="" || address=="" || username=="") {
            throw new SportCenterException(HttpStatus.BAD_REQUEST, "The billing information fields must be completed.");
        }
        Customer customer = customerRepository.findCustomerByUsername(username);
        if (customer==null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND ,"The customer does not exist.");
        }
        Billing ExistingBilling = billingRepository.findBillingByCustomer(customer);
        if (ExistingBilling!=null) {
            ExistingBilling.setCountry(country);
            ExistingBilling.setState(state);
            ExistingBilling.setPostalCode(postalCode);
            ExistingBilling.setCardNumber(cardNumber);
            ExistingBilling.setAddress(address);
            return billingRepository.save(ExistingBilling);
        } else {
            Billing billing = new Billing(country,state,postalCode,cardNumber,address, customer);
            return billingRepository.save(billing);
        }
    }

    /**
     * Get billing information for a customer
     * @author Li Yang Lei (LeiLiYang)
     * @param username
     * @throws SportCenterException if the customer does not exist or the customer does not have billing set up
     * @return billing object
     */

    @Transactional
    public Billing getBilling(String username) {
        Customer customer = customerRepository.findCustomerByUsername(username);
        if (customer==null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND ,"The customer does not exist.");
        }

        Billing billing = billingRepository.findBillingByCustomer(customer);
        if (billing==null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND ,"The customer does not have billing set up.");
        }
        return billing;
    }

    /**
     * Delete billing information for a customer
     *
     * @auther Li Yang Lei (LeiLiYang)
     * @param username
     * @throws SportCenterException if the customer does not exist or the customer does not have billing set up
     */

    @Transactional
    public void deleteBilling(String username){
        Customer customer = customerRepository.findCustomerByUsername(username);
        if (customer==null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND ,"The customer does not exist.");
        }
        Billing billing = billingRepository.findBillingByCustomer(customer);
        if (billing==null) {
            throw new SportCenterException(HttpStatus.NOT_FOUND ,"The customer does not have billing set up.");
        }
        billingRepository.deleteById(billing.getId());
    }

}
