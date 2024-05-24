package dev.seyma.repository;

import dev.seyma.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    List<Customer> findByName(String name);
    List<Customer> findByNameAndMailAndPhone(String name, String mail, String phone);
}
