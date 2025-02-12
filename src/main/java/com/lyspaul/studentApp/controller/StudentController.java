package com.lyspaul.studentApp.controller;

import com.lyspaul.studentApp.model.Student;
import com.lyspaul.studentApp.service.IStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("https://student-app-ui-production.up.railway.app/")
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
     private final IStudentService studentService; 
    
   @GetMapping
   public ResponseEntity<List<Student>> getStudents() {
       return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.FOUND);
   }

   @PostMapping
   public Student addStudent(@RequestBody Student student) {
       return studentService.addStudent(student);
   }

   @PutMapping("/update/{id}")
   public Student updateStudent(@RequestBody Student student, @PathVariable Long id) {
       return studentService.updateStudent(student, id);
   }

   @DeleteMapping("/delete/{id}")
   public void deleteStudent(@PathVariable Long id) {
       studentService.deleteStudent(id);
   }

   @GetMapping("/student/{id}")
   public Student getStudent(@PathVariable Long id) {
       return studentService.getStudent(id);
   }
}
