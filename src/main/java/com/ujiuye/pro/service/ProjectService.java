package com.ujiuye.pro.service;

import com.ujiuye.cus.bean.Customer;
import com.ujiuye.cus.mapper.CustomerMapper;
import com.ujiuye.emp.bean.Employee;
import com.ujiuye.emp.bean.EmployeeExample;
import com.ujiuye.emp.mapper.EmployeeMapper;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.bean.ProjectExample;
import com.ujiuye.pro.mapper.ProjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService {
    @Resource
    private ProjectMapper projectMapper;

    @Resource
    private EmployeeMapper employeeMapper;

    @Resource
    private CustomerMapper customerMapper;

    //查询所有信息
    public List<Project> selectAllPro() {
        return  projectMapper.selectAllPro();
    }

    //添加项目
    public void addPro(Project project) {
        projectMapper.insertSelective(project);
    }

    //根据id查看项目详情信息
    public Project lookProByPid(Integer pid) {
        return projectMapper.lookProByPid(pid);
    }

    //保存修改后的信息
    public void updatePro(Project project) {
        projectMapper.updateByPrimaryKeySelective(project);
    }

    //删除选中的错误
    public void deletePro(String pids) {
        projectMapper.deletePro(pids);
    }

    //条件模糊查询
    public List<Project> searchPro(Integer cid, String keyword, Integer orderby) {
        //根据姓名的模糊查询，得到所有的Employee，然后遍历获取eid
        EmployeeExample employeeEmp = new EmployeeExample();
        EmployeeExample.Criteria criteriaEmp = employeeEmp.createCriteria();
        criteriaEmp.andEnameLike("%"+keyword+"%");
        List<Employee> employees = employeeMapper.selectByExample(employeeEmp);
        List<Integer> list = new ArrayList<Integer>();
        System.out.println(employees);
        for (Employee e:employees) {
            System.out.println(e.getEid());
            Integer i = e.getEid();
            list.add(i);
        }
        System.out.println(list);

        ProjectExample example = new ProjectExample();
        ProjectExample.Criteria criteria1 = example.createCriteria();
        if(cid==1){
            criteria1.andPnameLike("%"+keyword+"%");
        }else if(cid==2){
            criteria1.andEmpFk1In(list);
        }else{
            criteria1.andPnameLike("%"+keyword+"%");
            ProjectExample.Criteria criteria2 = example.createCriteria();
            criteria2.andEmpFk1In(list);
            example.or(criteria2);
        }
        if(orderby==1){
            example.setOrderByClause("buildtime asc");
        }else if(orderby==2){
            example.setOrderByClause("endtime asc");
        }

        List<Project> projects = projectMapper.selectByExample(example);

        for (Project project:projects) {
            Integer comname = project.getComname();
            Customer customer = customerMapper.selectByPrimaryKey(comname);
            project.setComNames(customer.getComname());

            Integer empFk = project.getEmpFk();
            Employee employee = employeeMapper.selectByPrimaryKey(empFk);
            project.setEname(employee.getEname());
        }
        System.out.println("------"+projects);
        return projects;
    }

    public void delFile(String ids) {
        projectMapper.delFile(ids);
    }
}
