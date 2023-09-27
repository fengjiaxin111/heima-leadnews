package com.heima.freemarkerdemo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
