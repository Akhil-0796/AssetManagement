package com.assetmanagement.controller;

import com.assetmanagement.modal.Employee;
import com.assetmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @RequestMapping( value = "/addEmployee",method = RequestMethod.POST)
    public String addEmployee(@RequestBody Employee employee){
        return  employeeService.addEmployee(employee);
    }
}
