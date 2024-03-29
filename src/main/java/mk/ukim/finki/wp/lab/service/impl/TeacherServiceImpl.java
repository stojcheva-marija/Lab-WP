package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.data.TeacherFullName;
import mk.ukim.finki.wp.lab.model.Teacher;
//import mk.ukim.finki.wp.lab.repository.impl.TeacherRepositoryImpl;
import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Optional<Teacher> findById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher save(String name, String surname) {
        TeacherFullName fullName = new TeacherFullName(name,surname);
        return teacherRepository.save(new Teacher(fullName));
    }
}
