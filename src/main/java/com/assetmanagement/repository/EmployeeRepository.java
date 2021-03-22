package com.assetmanagement.repository;

import com.assetmanagement.modal.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee,Long> {

    Employee findByFullName(String empName);

}
