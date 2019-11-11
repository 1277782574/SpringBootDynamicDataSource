package com.alivecaren.controller;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/read")
public class XmlReaderController {

    @RequestMapping(value = "/budget",method = RequestMethod.GET)
    public List readXml() {
        List<Map<String,String>> list=new ArrayList<>();
        try {
            SAXBuilder builder = new SAXBuilder();
            //String path = ResourceUtils.getURL("classpath:").getPath();
            //File file = new File(path,"static/budget.xml");//
            File file = new File("D:\\workspace\\IDEA\\MysqlMapperSpringBootDemo\\src\\main\\resources\\static\\budget.xml");
            Document doc = builder.build(file);
            Element foo = doc.getRootElement();

            List<Element> allChildren = foo.getChildren();
            for (Element e : allChildren) {
                List<Attribute> attributes = e.getAttributes();
                Map<String,String> map=new HashMap<>();
                for (Attribute attribute : attributes) {
                    map.put(attribute.getName(),attribute.getValue());
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


    @RequestMapping(value = "/documentsType",method = RequestMethod.GET)
    public List<String> documentsType(){
        List<String> list = new ArrayList<>();
        list.add("预算申请单");
        list.add("出差申请单");
        list.add("报销申请单");
        return list;
    }

    @RequestMapping(value = "/getSteps",method = RequestMethod.GET)
    public Map getSteps(){
        Map<String,Object> map=new HashMap<>();
        List<Map<String,String>> list = new ArrayList<>();
        Map<String,String> m1=new HashMap<>();
        m1.put("title","处长审批");
        m1.put("description","审批通过");
        list.add(m1);
        Map<String,String> m2=new HashMap<>();
        m2.put("title","副总经理审批");
        m2.put("description","金额有问题");
        list.add(m2);
        Map<String,String> m3=new HashMap<>();
        m3.put("title","总经理审批");
        m3.put("description","");
        list.add(m3);
        Map<String,String> m4=new HashMap<>();
        m4.put("title","董事长审批");
        m4.put("description","");
        list.add(m4);
        map.put("active",1);
        map.put("processStatus","error");
        map.put("list",list);
        return map;
    }
    @RequestMapping(value = "/form",method = RequestMethod.POST)
    public void form(@RequestBody Map<String,Object> map ){

        System.out.println(map);
    }

}
