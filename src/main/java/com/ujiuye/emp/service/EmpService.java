package com.ujiuye.emp.service;

import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.bean.EmployeeExample;
import com.ujiuye.emp.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EmpService {

    @Resource
    private EmployeeMapper employeeMapper;

    //查出所有的项目经理
    public List<Employee> selectLeadEmp() {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andPFkEqualTo(4);
        return employeeMapper.selectByExample (example);
    }

    //登录校验
    public Employee loginCheck(Employee employee) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(employee.getUsername());
        criteria.andPasswordEqualTo(employee.getPassword());
        List<Employee> employees = employeeMapper.selectByExample(example);
        if (employees!=null && employees.size()>0){
            return employees.get(0);
        }
        return null;
    }
}
