package dev.seyma.business.abstracts;

import dev.seyma.core.result.ResultData;
import dev.seyma.dto.CursorResponse;
import dev.seyma.dto.request.customer.CustomerSaveRequest;
import dev.seyma.dto.request.customer.CustomerUpdateRequest;
import dev.seyma.dto.response.customer.CustomerResponse;
import dev.seyma.entity.Customer;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICustomerService {
    ResultData<CustomerResponse> save(CustomerSaveRequest customerSaveRequest);
    Customer get(int id);
    ResultData<CursorResponse<CustomerResponse>> cursor(int page, int pageSize);
    ResultData<CustomerResponse> update(CustomerUpdateRequest customerUpdateRequest);
    ResultData<List<CustomerResponse>> findByName(String name);
    List<Customer> findByNameAndMailAndPhone(String name, String mail, String phone);
    boolean delete(int id);
}
