package com.hopu.service;

import com.hopu.domain.Student;

import java.util.List;

/**
 * @Author：YwaiX
 * @version:1.0
 * @Date：Created in 2024-08-02 09:18:31
 * @Description：
 */

public interface StudentService {
    
    /**
     * 添加学生
     * @param student
     * @return
     */
    Integer insertStudent(Student student);
    
    /**
     * 修改学生
     * @param student
     * @return
     */
    Integer updateStudent(Student student);
    
    /**
     * 删除学生
     * @param studentId
     * @return
     */
    Integer deleteStudent(Integer studentId);
    
    /**
     * 批量删除学生
     * @param studentIds
     * @return
     */
    Integer deleteStudents(Object[] studentIds);
    
    /**
     * 学生列表
     * @return
     */
    List<Student> getStudentList();
    
    /**
     * 获取单个学生信息
     * @param studentId
     * @return
     */
    Student getStudentById(Integer studentId);
    
    /**
     * 获取所有学生id
     * @return
     */
    List<Integer> getStudentIdList();
}
