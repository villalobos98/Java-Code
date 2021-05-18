package com.mindex.challenge.controller;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompensationRepository compensationRepository;


    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private CompensationService compensationService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }

    @GetMapping("/employee/{id}/reporting_structure")
    public Employee reportingStructure(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        Employee employee = employeeService.read(id);
        ReportingStructure reportingStructure = new ReportingStructure();
        reportingStructure.setNumberOfReports(employee);
        return reportingStructure;
    }

    @GetMapping("/employee/{id}/read_compensation")
    public Compensation read_compensation(@PathVariable String id) {
        LOG.debug("Received compensation create request for id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        Compensation compensation = compensationService.read(employee.getEmployeeId());
        return compensation;
    }

    @PostMapping("/employee/{id}/create_compensation")
    public Compensation create_compensation(@PathVariable String id) {
        LOG.debug("Received compensation create request for id [{}]", id);

        LocalDate date = LocalDate.now(); // Gets the current date
        int salary = (int)Math.round(Math.random());
        Employee employee = employeeService.read(id);
        Compensation compensation = new Compensation(employee, salary, date);
        return compensationService.create(compensation);
    }

    @PutMapping("/employee/{id}/update_compensation")
    public Compensation update(@PathVariable String id, @RequestBody Compensation compensation) {
        LOG.debug("Received compensation create request for id [{}] and compensation [{}]", id, compensation);

        return compensationService.update(compensation);
    }


}
