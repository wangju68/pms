package com.ujiuye.cus.service;

import com.ujiuye.cus.bean.Customer;
import com.ujiuye.cus.bean.CustomerExample;
import com.ujiuye.cus.mapper.CustomerMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    //查询所有的客户信息
    public List<Customer> selectAllCus() {
        List<Customer> customers = customerMapper.selectByExample(new CustomerExample());
        return customers;
    }

    //添加客户信息
    public void addCus(Customer customer) {
        //后面不带Selective在进行更新的时候，会将没更新的数据，修改成null
        //带Selective在进行更新的时候，不会修改没传入的值
        customer.setAddtime(new Date());
        customerMapper.insertSelective(customer);
    }

    //根据id查找客户信息
    public Customer selectByIdCus(Integer id) {
        Customer customer = customerMapper.selectByPrimaryKey(id);
        return customer;
    }

    //根据id修改客户的信息
    public void updateByIdCus(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    //删除客户信息
    public void deleteCus(String ids) {
        /*List<Integer> integers = Arrays.asList(ids);
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(integers);
        customerMapper.deleteByExample(example);*/
        customerMapper.deleteCus(ids);

        /*CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn();
        customerMapper.deleteByExample(example);*/

    }

    //条件查询
    public List<Customer> selectAllCus(Integer cid, String keyword, Integer orderby) {
        CustomerExample example = new CustomerExample();
        CustomerExample.Criteria criteria = example.createCriteria();
        if (cid==0){
            criteria.andComaddressLike("%"+keyword+"%");
            CustomerExample.Criteria criteria1 = example.createCriteria();
            criteria1.andCompanypersonLike("%"+keyword+"%");
            example.or(criteria1);
        }else if(cid==1){
            criteria.andComaddressLike("%"+keyword+"%");
        }else{
            criteria.andCompanypersonLike("%"+keyword+"%");
        }
        if(orderby==2){
            example.setOrderByClause("id desc");
        }

        List<Customer> customers = customerMapper.selectByExample(example);
        return customers;
    }

    //根据公司所在id查找客户姓名
    public Customer getNameCus(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }
}
