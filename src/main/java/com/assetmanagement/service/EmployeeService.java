package com.assetmanagement.service;

import com.assetmanagement.modal.Employee;
import com.assetmanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /***
     *  to add an new employee.
     * @param employee  employee object which need to be inserted in database.
     * @return  status message.
     */
    public String addEmployee(Employee employee){
        try{
            employeeRepository.save(employee);
            return "successfully added "+employee.getFullName();
        }catch (Exception e){
            return "something went wrong : "+e.getMessage();
        }


    }
}
