package com.ujiuye.cus.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.ujiuye.cus.bean.Customer;
import com.ujiuye.cus.service.CustomerService;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("cus")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //查询显示所有客户信息
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public ModelAndView selectAllCus(){
        ModelAndView mv = new ModelAndView("customer");
        List<Customer> customers = customerService.selectAllCus();
        mv.addObject("customers",customers);
        return mv;
    }

    //添加客户信息
    @RequestMapping(value = "add",method = RequestMethod.POST)
    public String addCus(Customer customer){
        customerService.addCus(customer);
        return "redirect:/cus/list";
    }

    //修改客户的个人信息
    @RequestMapping(value = "",method = RequestMethod.PUT)
    public ModelAndView editByIdCus(Customer customer){
        System.out.println(customer.getId());
        ModelAndView mv = new ModelAndView("customer-edit");
        return mv;
    }

    //根据id查找客户信息
    @RequestMapping(value = "/look/{id}",method = RequestMethod.GET)
    public  ModelAndView selectByIdCus(@PathVariable("id") Integer id){
        Customer customer = customerService.selectByIdCus(id);
        ModelAndView mv = new ModelAndView("customer-look");
        mv.addObject("cus",customer);
        return mv;
    }

    //根据id查找客户信息，进行回填修改
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public ModelAndView updateByIdCus(@PathVariable("id")Integer id){
        ModelAndView mv = new ModelAndView("customer-edit");
        Customer customer = customerService.selectByIdCus(id);
        mv.addObject("cus",customer);
        return mv;
    }

    //根据id修改客户信息
    @RequestMapping(value = "update",method = RequestMethod.PUT)
    public String updateByIdCus(Customer customer){
        customerService.updateByIdCus(customer);
        return "redirect:/cus/list";
    }

    //删除用户信息
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deleteCus(String ids){
        customerService.deleteCus(ids);
        Map<String,Object> map = new HashMap<String, Object>();
        return map;
    }

    //条件查询
    @RequestMapping(value = "select",method = RequestMethod.GET)
    public ModelAndView selectChoose(Integer cid,String keyword,Integer orderby){
        ModelAndView mv = new ModelAndView("customer");
        List<Customer> customers = customerService.selectAllCus(cid,keyword,orderby);
        mv.addObject("customers",customers);
        return mv;
    }

    //查询出所有的公司名称
    @RequestMapping(value = "selectCo",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> selectCoName(){
        return customerService.selectAllCus();
    }

    //根据公司所在id查找客户姓名
    @RequestMapping(value = "/getNameCus/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Customer getNameCus(@PathVariable("id") Integer id){
        return customerService.getNameCus(id);
    }

    //导出xlsx
    @RequestMapping(value = "outCus",method = RequestMethod.GET)
    @ResponseBody
    public String outCus(){
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("客户附件");
        Row row = sheet.createRow(0);
        Cell[] cells = new XSSFCell[5];
        for(int i=0 ; i<cells.length ; i++){
            cells[i] = row.createCell(i);
        }
        cells[0].setCellValue("序号");
        cells[1].setCellValue("联系人");
        cells[2].setCellValue("公司名称");
        cells[3].setCellValue("添加时间");
        cells[4].setCellValue("联系电话");
        List<Customer> customers = customerService.selectAllCus();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0 ; i<customers.size() ; i++){
            Row row1 = sheet.createRow(i+1);
            for(int j=0 ; j<cells.length ; j++){
                cells[j] = row1.createCell(j);
            }
            Date addtime = customers.get(i).getAddtime();
            String format = sdf.format(addtime);
            cells[0].setCellValue(i+1);
            cells[1].setCellValue(customers.get(i).getCompanyperson());
            cells[2].setCellValue(customers.get(i).getComname());
            cells[3].setCellValue(format);
            cells[4].setCellValue(customers.get(i).getComphone());
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(new File("D:\\Desktop\\cus.xlsx"));
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //xlsx的上传
    @RequestMapping(value = "upload")
    @ResponseBody
    public List<Customer> xlsxUpload(MultipartFile file){
        /*ModelAndView mv = new ModelAndView();
        String filename = file.getOriginalFilename();
        System.out.println(filename);*/
        List<Customer> customers = customerService.selectAllCus();
        return customers;
    }

    @RequestMapping(value = "aaa/{msg}")
    public void asd(@PathVariable("msg")List<Customer> customers){
        System.out.println(customers);
    }
}
