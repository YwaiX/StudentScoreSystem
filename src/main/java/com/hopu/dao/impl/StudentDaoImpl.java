package com.hopu.dao.impl;

import com.hopu.dao.StudentDao;
import com.hopu.domain.Student;
import com.hopu.util.DruidUtil;
import org.intellij.lang.annotations.Language;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author：YwaiX
 * @version:1.0
 * @Date：Created in 2024-08-02 09:20:47
 * @Description：
 */

public class StudentDaoImpl implements StudentDao {
    
    /**
     * 添加修改学生
     *
     * @param student
     * @return
     */
    @Override
    public Integer insertStudent(Student student) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            //获取数据库连接
            connection = DruidUtil.getConnection();
            
            //添加/修改学生信息
            @Language("sql")
            String sql = "";
            
            //如果学号为null表示学号不存在,直接添加学生,否则表示学号存在,使用修改
            if (student.getStudentId() == null) {
                sql = "insert into student values (null,?,?,?)";
            } else {
                sql = "update student set student_name = ? , student_age = ? , student_sex = ? where student_id = ?";
            }
            
            //预编译sql
            preparedStatement = connection.prepareStatement(sql);
            
            //给sql语句添加数据
            preparedStatement.setObject(1, student.getStudentName());
            preparedStatement.setObject(2, student.getStudentAge());
            preparedStatement.setObject(3, student.getStudentSex());
            
            //如果学号不为null表示修改学生,多给一个学号的条件
            if (student.getStudentId() != null) {
                preparedStatement.setObject(4, student.getStudentId());
            }
            
            //执行sql
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(null, preparedStatement, connection);
        }
        
        return 0;
    }
    
    /**
     * 删除学生
     * @param studentId
     * @return
     */
    @Override
    public Integer deleteStudent(Integer studentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            //获取数据库连接
            connection = DruidUtil.getConnection();
            
            //删除学生
            @Language("sql")
            String sql = "delete from student where student_id = ?";
            
            //预编译sql
            preparedStatement = connection.prepareStatement(sql);
            
            //给sql语句添加数据
            preparedStatement.setObject(1, studentId);
            
            //执行sql
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(null, preparedStatement, connection);
        }
        
        return 0;
    }
    
    /**
     * 批量删除学生
     *
     * @param studentIds
     * @return
     */
    @Override
    public Integer deleteStudents(Object[] studentIds) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            //获取数据库连接
            connection = DruidUtil.getConnection();
            
            //添加学生信息
            
            //@Language("sql")
            String sql = "delete from student where student_id in (";
            
            for (int i = 0; i < studentIds.length; i++) {
                sql += "?";
                sql += (i == studentIds.length - 1) ? ")" : ",";
            }
            
            //预编译sql
            preparedStatement = connection.prepareStatement(sql);
            
            //给sql语句添加数据
            for (int i = 0; i < studentIds.length; i++) {
                preparedStatement.setObject(i + 1, studentIds[i]);
            }
            
            
            //执行sql
            return preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(null, preparedStatement, connection);
        }
        
        return 0;
    }
    
    /**
     * 学生列表
     *
     * @return
     */
    @Override
    public List<Student> getStudentList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            //获取数据库连接
            connection = DruidUtil.getConnection();
            
            //添加学生信息
            @Language("sql")
            String sql = "select * from student";
            
            //预编译sql
            preparedStatement = connection.prepareStatement(sql);
            
            //执行sql,获取数据库所有学生集合
            resultSet = preparedStatement.executeQuery();
            
            //创建一个集合用于存储学生信息
            List<Student> students = new ArrayList<>();
            
            //循环添加学生
            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getInt("student_id"),
                        resultSet.getString("student_name"),
                        resultSet.getInt("student_age"),
                        resultSet.getString("student_sex")
                ));
            }
            
            //返回信息
            return students;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(resultSet, preparedStatement, connection);
        }
        
        return null;
    }
    
    /**
     * 获取单个学生信息
     *
     * @param studentId
     * @return
     */
    @Override
    public Student getStudentById(Integer studentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            //获取数据库连接
            connection = DruidUtil.getConnection();
            
            //添加学生信息
            @Language("sql")
            String sql = "select * from student where student_id = ?";
            
            //预编译sql
            preparedStatement = connection.prepareStatement(sql);
            
            //给sql语句添加数据
            preparedStatement.setObject(1, studentId);
            
            //执行sql,获取数据库所有学生集合
            resultSet = preparedStatement.executeQuery();
            
            //创建一个集合用于存储学生信息
            Student student = null;
            while (resultSet.next()) {
                student = new Student(
                        resultSet.getInt("student_id"),
                        resultSet.getString("student_name"),
                        resultSet.getInt("student_age"),
                        resultSet.getString("student_sex")
                );
            }
            
            
            //返回信息
            return student;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(resultSet, preparedStatement, connection);
        }
        
        return null;
    }
    
    /**
     * 获取所有学生id
     *
     * @return
     */
    @Override
    public List<Integer> getStudentIdList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            //获取数据库连接
            connection = DruidUtil.getConnection();
            
            //添加学生信息
            @Language("sql")
            String sql = "select student_id from student";
            
            //预编译sql
            preparedStatement = connection.prepareStatement(sql);
            
            //执行sql,获取数据库所有学生集合
            resultSet = preparedStatement.executeQuery();
            
            //创建一个集合用于存储学生信息
            List<Integer> studentIds = new ArrayList<>();
            
            //循环添加学生
            while (resultSet.next()) {
                studentIds.add(resultSet.getInt("student_id"));
            }
            
            //返回信息
            return studentIds;
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(resultSet, preparedStatement, connection);
        }
        
        return null;
    }
}
