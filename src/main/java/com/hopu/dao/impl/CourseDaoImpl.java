package com.hopu.dao.impl;

import com.hopu.dao.CourseDao;
import com.hopu.domain.Course;
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
 * @Date：Created in 2024-08-02 09:20:57
 * @Description：
 */

public class CourseDaoImpl implements CourseDao {
    
    /**
     * 添加修改学科
     *
     * @param course
     * @return
     */
    @Override
    public Integer insertCourse(Course course) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            //获取连接
            connection = DruidUtil.getConnection();
            
            //sql语句,如果courseId为null说名是添加,否则是修改
            @Language("sql")
            String sql = "";
            if (course.getCourseId() == null) {
                sql += "insert into course values (null,?)";
            } else {
                sql += "update course set course_name = ? where course_id = ?";
            }
            
            //sql预编译
            preparedStatement = connection.prepareStatement(sql);
            
            //给sql添加参数
            preparedStatement.setObject(1, course.getCourseName());
            //如果courseId不为null说明是修改,则需要添加第二个参数
            if (course.getCourseId() != null) {
                preparedStatement.setObject(2, course.getCourseId());
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
     * 删除学科
     *
     * @param courseId
     * @return
     */
    @Override
    public Integer deleteCourse(Integer courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            //获取连接
            connection = DruidUtil.getConnection();
            
            //sql语句
            @Language("sql")
            String sql = "delete from course where course_id = ?";
            
            //sql预编译
            preparedStatement = connection.prepareStatement(sql);
            
            //给sql添加参数
            preparedStatement.setObject(1, courseId);
            
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
     * 批量删除学科
     *
     * @param courseIds
     * @return
     */
    @Override
    public Integer deleteCourses(Object[] courseIds) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            //获取连接
            connection = DruidUtil.getConnection();
            
            //sql语句
            @Language("sql")
            String sql = "delete from course where course_id in (";
            
            for (int i = 0; i < courseIds.length; i++) {
                sql += "?";
                sql += (i == courseIds.length - 1) ? ")" : ",";
            }
            //sql预编译
            preparedStatement = connection.prepareStatement(sql);
            
            //给sql添加参数
            for (int i = 0; i < courseIds.length; i++) {
                preparedStatement.setObject(i + 1, courseIds[i]);
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
     * 获取所有学科信息
     *
     * @return
     */
    @Override
    public List<Course> getCourseList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            //获取连接
            connection = DruidUtil.getConnection();
            
            //sql语句
            @Language("sql")
            String sql = "select * from course";
            
            //sql预编译
            preparedStatement = connection.prepareStatement(sql);
            
            //执行sql
            resultSet = preparedStatement.executeQuery();
            
            //将获取的所有学科信息存放到集合
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                courses.add(new Course(
                        resultSet.getInt("course_id"),
                        resultSet.getString("course_name")
                ));
            }
            
            
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(resultSet, preparedStatement, connection);
        }
        
        return null;
    }
    
    /**
     * 获取学科信息
     *
     * @param courseId
     * @return
     */
    @Override
    public Course getCourseById(Integer courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            //获取连接
            connection = DruidUtil.getConnection();
            
            //sql语句
            @Language("sql")
            String sql = "select * from course where course_id = ?";
            
            //sql预编译
            preparedStatement = connection.prepareStatement(sql);
            
            //给sql添加参数
            preparedStatement.setObject(1, courseId);
            
            //执行sql
            resultSet = preparedStatement.executeQuery();
            
            //将获取的所有学科信息存放到集合
            Course course = null;
            while (resultSet.next()) {
                course = new Course(
                        resultSet.getInt("course_id"),
                        resultSet.getString("course_name")
                );
            }
            
            
            return course;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(resultSet, preparedStatement, connection);
        }
        
        return null;
    }
    
    /**
     * 获取所有学科id
     *
     * @return
     */
    @Override
    public List<Integer> getCourseIdList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            //获取连接
            connection = DruidUtil.getConnection();
            
            //sql语句
            @Language("sql")
            String sql = "select course_id from course";
            
            //sql预编译
            preparedStatement = connection.prepareStatement(sql);
            
            //执行sql
            resultSet = preparedStatement.executeQuery();
            
            //将获取的所有学科信息存放到集合
            List<Integer> courseIds = new ArrayList<>();
            while (resultSet.next()) {
                courseIds.add(resultSet.getInt("course_id"));
            }
            
            
            return courseIds;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(resultSet, preparedStatement, connection);
        }
        
        return null;
    }
}
