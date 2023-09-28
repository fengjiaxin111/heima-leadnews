package com.heima.freemarkerdemo.controller;


import com.heima.freemarkerdemo.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author 冯佳鑫
 * @Package：com.heima.freemarkerdemo.controller
 * @Project：heima-leadnews
 * @name：HelloController
 * @Date：2023/9/25 22:37
 * @Filename：HelloController
 */
@Controller
public class HelloController {
    @GetMapping("/basic")
    public String test(Model model){
        model.addAttribute("name","kobe");
        Student student = new Student();
        student.setName("jack");
        student.setAge(12);
        model.addAttribute("stu",student);
        return "01-basic";
    }
    @GetMapping("/list")
    public String test02(Model model){
//        model.addAttribute("name","kobe");
        Student student = new Student();
        student.setName("jack");
        student.setAge(12);
        student.setMoney(1000F);
        Student student2 = new Student();
        student2.setName("honey");
//        student2.setAge(122);
        student2.setMoney(1200F);
        ArrayList<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student2);

        model.addAttribute("stus",students);
        HashMap<String, Student> stumap = new HashMap<>();
        stumap.put("stu1",student);
        stumap.put("stu2",student2);
        model.addAttribute("stumap",stumap);
        model.addAttribute("todat",new Date());
        return "02-list";
    }
}
