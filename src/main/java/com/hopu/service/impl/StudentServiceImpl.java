package com.hopu.service.impl;

import com.hopu.dao.ScoreDao;
import com.hopu.dao.StudentDao;
import com.hopu.dao.impl.ScoreDaoImpl;
import com.hopu.dao.impl.StudentDaoImpl;
import com.hopu.domain.Student;
import com.hopu.service.StudentService;

import java.util.List;

/**
 * @Author：YwaiX
 * @version:1.0
 * @Date：Created in 2024-08-02 09:18:44
 * @Description：
 */

public class StudentServiceImpl implements StudentService {
    private StudentDao studentDao = new StudentDaoImpl();
    private ScoreDao scoreDao = new ScoreDaoImpl();
    
    
    /**
     * 添加学生
     *
     * @param student
     * @return
     */
    @Override
    public Integer insertStudent(Student student) {
        
        //直接调用dao接口添加学生
        return studentDao.insertStudent(student);
    }
    
    /**
     * 修改学生
     *
     * @param student
     * @return
     */
    @Override
    public Integer updateStudent(Student student) {
        
        //直接调用dao接口修改学生
        return studentDao.insertStudent(student);
    }
    
    /**
     * 删除学生
     *
     * @param studentId
     * @return
     */
    @Override
    public Integer deleteStudent(Integer studentId) {
        //先删除学生再删除成绩
        Integer i = studentDao.deleteStudent(studentId);
        //直接调用dao接口删除学生
        Integer i1 = scoreDao.deleteScoreByStudentId(studentId);
        return i == 1 && i1 == 1 ? 1 : 0;
    }
    
    /**
     * 批量删除学生
     *
     * @param studentIds
     * @return
     */
    @Override
    public Integer deleteStudents(Object[] studentIds) {
        
        //直接调用dao接口批量删除学生
        return studentDao.deleteStudents(studentIds);
    }
    
    /**
     * 学生列表
     *
     * @return
     */
    @Override
    public List<Student> getStudentList() {
        
        //直接调用dao接口获取学生列表
        return studentDao.getStudentList();
    }
    
    /**
     * 获取单个学生信息
     *
     * @param studentId
     * @return
     */
    @Override
    public Student getStudentById(Integer studentId) {
        
        //直接调用dao接口获取单个学生信息
        return studentDao.getStudentById(studentId);
    }
    
    /**
     * 获取所有学生id
     *
     * @return
     */
    @Override
    public List<Integer> getStudentIdList() {
        
        //直接调用dao接口获取学生id
        return studentDao.getStudentIdList();
    }
}
