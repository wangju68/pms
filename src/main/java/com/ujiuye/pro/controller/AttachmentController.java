package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.service.AttachmentService;
import org.apache.poi.hssf.usermodel.HSSFCell;
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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("att")
public class AttachmentController {
    @Autowired
    private AttachmentService attachmentService;

    //保存信息
    @RequestMapping(value = "saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public String saveInfo(Attachment attachment, HttpSession session, MultipartFile file){
        ServletContext servletContext = session.getServletContext();
        String realPath = servletContext.getRealPath("/upload");
        File newFile = new File(realPath);
        if (!newFile.exists()){
            newFile.mkdirs();
        }
        String originalFilename = file.getOriginalFilename();
        String realName = UUID.randomUUID().toString().replaceAll("-","")+originalFilename;
        try {
            file.transferTo(new File(realPath+"/"+realName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        attachment.setPath(realName);

        attachmentService.saveInfo(attachment);
        return null;
    }
    //查询所有的附件信息
    @RequestMapping(value = "list",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> selectAtta(){
        List<Map<String,Object>> lists = new ArrayList<Map<String, Object>>();
        lists = attachmentService.selectAtta();
        return lists;
    }

    //导出xlsx
    @RequestMapping(value = "outXlsx/{ids}",method = RequestMethod.GET)
    @ResponseBody
    public String outXlsx(@PathVariable("ids")String ids){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("附件");
        Row row = sheet.createRow(0);
        Cell[] cells = new XSSFCell[5];
        for(int i=0;i<5;i++){
            cells[i] = row.createCell(i);
        }
        cells[0].setCellValue("ID");
        cells[1].setCellValue("附件名称");
        cells[2].setCellValue("所属项目");
        cells[3].setCellValue("上传时间");
        cells[4].setCellValue("修改时间");
        List<Map<String, Object>> lists = attachmentService.selectAttaById(ids);
        for(Integer i=0;i<lists.size();i++){
            Map<String, Object> map = lists.get(i);
            Row row1 = sheet.createRow(i + 1);
            // a.`id`,a.`attname`,a.`pro_fk`,p.`buildtime`,p.`starttime`,p.`pname`
            Cell cell = row1.createCell(0);
            cell.setCellValue(i+1);
            String attname = (String) map.get("attname");
            Cell cell1 = row1.createCell(1);
            cell1.setCellValue(attname);
            String pname = (String)map.get("pname");
            Cell cell2 = row1.createCell(2);
            cell2.setCellValue(pname);
            Date buildtime = (Date) map.get("buildtime");

            Cell cell3 = row1.createCell(3);
            cell3.setCellValue(sdf.format(buildtime));
            Date starttime = (Date)map.get("starttime");
            Cell cell4 = row1.createCell(4);
            cell4.setCellValue(sdf.format(starttime));

        }
        try {
            FileOutputStream outputStream = new FileOutputStream(new File("D:\\Desktop\\a.xlsx"));
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //根据id，查询附件详情信息 lookById
    @RequestMapping(value = "lookById/{id}",method = RequestMethod.GET)

    public ModelAndView lookById(@PathVariable("id")Integer id) {
        ModelAndView mv = new ModelAndView("project-file-look");
        Map<String,Object> map = attachmentService.lookById(id);
        for(String s:map.keySet()){
            mv.addObject(s,map.get(s));
        }
        return mv;
    }
}
