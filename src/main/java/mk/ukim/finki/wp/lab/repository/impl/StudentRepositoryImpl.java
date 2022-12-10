//package mk.ukim.finki.wp.lab.repository.impl;
//
//import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
//import mk.ukim.finki.wp.lab.model.Student;
//import org.springframework.stereotype.Repository;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Repository
//public class StudentRepositoryImpl {
//
//    public List<Student> findAllStudents(){
//        return DataHolder.students;
//    }
//
//    public List<Student> findAllByNameOrSurname(String text){
//        return DataHolder.students.stream().filter(s -> s.getName().toLowerCase().contains(text.toLowerCase())||
//                        s.getSurname().toLowerCase().contains(text.toLowerCase()) ||
//                                s.getUsername().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());
//    }
//
//    public Student findByUsername(String text){
//        return DataHolder.students.stream().filter(s -> s.getUsername().equals(text)).findFirst().orElse(null);
//    }
//
//    public Student save(Student student){
//        if (student.getUsername() == null || student.getUsername().isEmpty()
//                || student.getPassword() == null || student.getPassword().isEmpty() ||
//                student.getName() == null || student.getName().isEmpty() ||
//                student.getSurname() == null || student.getSurname().isEmpty()){
//            return null;
//        }
//        DataHolder.students.removeIf(s -> s.getUsername().equals(student.getUsername()));
//        DataHolder.students.add(student);
//        return student;
//    }
//}
