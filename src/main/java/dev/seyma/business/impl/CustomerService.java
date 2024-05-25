package dev.seyma.business.impl;

import dev.seyma.business.abstracts.ICustomerService;
import dev.seyma.core.config.ConvertEntityToResponse;
import dev.seyma.core.config.modelMapper.IModelMapperService;
import dev.seyma.core.exception.DataAlreadyExistException;
import dev.seyma.core.exception.NotFoundException;
import dev.seyma.core.result.ResultData;
import dev.seyma.core.utilies.Msg;
import dev.seyma.core.utilies.ResultHelper;
import dev.seyma.repository.CustomerRepo;
import dev.seyma.dto.CursorResponse;
import dev.seyma.dto.request.customer.CustomerSaveRequest;
import dev.seyma.dto.request.customer.CustomerUpdateRequest;
import dev.seyma.dto.response.customer.CustomerResponse;
import dev.seyma.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {
    // Injected dependencies
    private final CustomerRepo customerRepo;
    private final IModelMapperService modelMapperService;
    private final ConvertEntityToResponse<Customer, CustomerResponse> convert;

    @Override
    public ResultData<CustomerResponse> save(CustomerSaveRequest customerSaveRequest) {
        // Map request to customer entity
        Customer saveCustomer = this.modelMapperService.forRequest().map(customerSaveRequest, Customer.class);
        // Check for existing customers with the same name, mail, and phone
        List<Customer> getByNamePhoneMail = this.findByNameAndMailAndPhone(
                saveCustomer.getName(),
                saveCustomer.getMail(),
                saveCustomer.getPhone());
        if (!getByNamePhoneMail.isEmpty()){
            throw new DataAlreadyExistException(Msg.getEntityForMsg(Customer.class));
        }
        // Save the customer and return the response
        return ResultHelper.created(this.modelMapperService.forResponse().map(this.customerRepo.save(saveCustomer), CustomerResponse.class));
    }

    @Override
    public Customer get(int id) {
        // Get customer by ID or throw not found exception
        return this.customerRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public ResultData<CursorResponse<CustomerResponse>> cursor(int page, int pageSize) {
        // Create pageable request
        Pageable pageable = PageRequest.of(page, pageSize);
        // Get paginated result of customers
        Page<Customer> customerPage = this.customerRepo.findAll(pageable);
        // Map customers to response objects
        Page<CustomerResponse> customerResponsePage = customerPage.map(customer -> this.modelMapperService.forResponse().map(customer, CustomerResponse.class));
        return ResultHelper.cursor(customerResponsePage);
    }

    @Override
    public ResultData<CustomerResponse> update(CustomerUpdateRequest customerUpdateRequest) {
        // Get customer by ID to ensure it exists
        this.get(customerUpdateRequest.getId());
        // Map update request to customer entity and save updated customer
        Customer updateCustomer = this.modelMapperService.forRequest().map(customerUpdateRequest, Customer.class);
        return ResultHelper.success(this.modelMapperService.forResponse().map(this.customerRepo.save(updateCustomer), CustomerResponse.class));
    }

    @Override
    public ResultData<List<CustomerResponse>> findByName(String name) {
        // Find customers by name
        List<Customer> customerList = this.customerRepo.findByName(name);
        // Convert customers to response objects
        List<CustomerResponse> customerResponseList = this.convert.convertToResponseList(customerList, CustomerResponse.class);
        return ResultHelper.success(customerResponseList);
    }

    @Override
    public List<Customer> findByNameAndMailAndPhone(String name, String mail, String phone) {
        // Find customers by name, mail, and phone
        return this.customerRepo.findByNameAndMailAndPhone(name, mail, phone);
    }

    @Override
    public boolean delete(int id) {
        // Get customer by ID to ensure it exists and delete
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return true;
    }
}
