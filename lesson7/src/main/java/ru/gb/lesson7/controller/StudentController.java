package ru.gb.lesson7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.lesson7.domain.Student;
import ru.gb.lesson7.repository.StudentsRepository;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentsRepository studRepo;

    public StudentController(StudentsRepository studRepo) {
        this.studRepo = studRepo;
    }

    @GetMapping
    public String mainView(Model model) {
        List<Student> students = this.studRepo.findAll();
        model.addAttribute("students", students);
        return "main";
    }

    @GetMapping("/add")
    public String addStudent(Student student) {
        return "add";
    }

    @PostMapping("/add")
    public String addFormStudent(@Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add";
        }
        this.studRepo.save(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable long id) {
        this.studRepo.deleteById(id);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable long id, Model model) {
        Student stud = this.studRepo.findById(id).orElse(new Student());
        model.addAttribute("student", stud);
        return "edit";
    }
}
