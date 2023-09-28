package com.heima.freemarkerdemo;

import com.heima.freemarkerdemo.entity.Student;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

@SpringBootTest
class FreemarkerDemoApplicationTests {
    @Autowired
    Configuration configuration;

    /**
     * 通过freemarker生成html文件
     */
    @Test
    void newhtml() throws IOException, TemplateException {
        Template template = configuration.getTemplate("02-list.ftl");
        template.process(getdata(),new FileWriter("d:/list.html"));

    }

    public HashMap getdata(){
        HashMap<String, Object> map = new HashMap<>();
        Student student = new Student();
        student.setName("jack");
        student.setAge(12);
        student.setMoney(1000F);
        Student student2 = new Student();
        student2.setName("honey");
        student2.setAge(122);
        student2.setMoney(1200F);
        ArrayList<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);


        map.put("stus",students);
        HashMap<String, Student> stumap = new HashMap<>();
        stumap.put("stu1",student);
        stumap.put("stu2",student2);

        map.put("stumap",stumap);

        map.put("todat",new Date());
      return map;
    }

}
