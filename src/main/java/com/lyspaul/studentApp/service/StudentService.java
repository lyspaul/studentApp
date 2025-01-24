package com.lyspaul.studentApp.service;

import com.lyspaul.studentApp.exception.StudentExistsException;
import com.lyspaul.studentApp.exception.StudentNotFoundException;
import com.lyspaul.studentApp.model.Student;
import com.lyspaul.studentApp.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;

    //This method returns all students in the repository
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student addStudent(Student student) {
        if(studentExists(student.getEmail())){
            throw new StudentExistsException(student.getEmail() + " already exists in the system!");
        }
        return studentRepository.save(student);
    }




    @Override
    public Student updateStudent(Student student, Long id) {
        return studentRepository.findById(id).map(st -> {
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            return studentRepository.save(st);
        }).orElseThrow(() -> new StudentNotFoundException("Sorry, this student could not be found"));
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Sorry, the student with the following id " + id + " could not be found"));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)){
            throw new StudentNotFoundException("Sorry, this student could not be found");
        } else
            studentRepository.deleteById(id);

    }

    private boolean studentExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }
}
