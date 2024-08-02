package com.hopu.dao;

import com.hopu.domain.Course;

import java.util.List;

/**
 * @Author：YwaiX
 * @version:1.0
 * @Date：Created in 2024-08-02 09:20:05
 * @Description：
 */

public interface CourseDao {
    
    /**
     * 添加修改学科
     * @param course
     * @return
     */
    Integer insertCourse(Course course);
    
    /**
     * 删除学科
     * @param courseId
     * @return
     */
    Integer deleteCourse(Integer courseId);
    
    /**
     * 批量删除学科
     * @param courseIds
     * @return
     */
    Integer deleteCourses(Object[] courseIds);
    
    /**
     * 获取所有学科信息
     * @return
     */
    List<Course> getCourseList();
    
    /**
     * 获取学科信息
     * @param courseId
     * @return
     */
    Course getCourseById(Integer courseId);
    
    /**
     * 获取所有学科id
     * @return
     */
    List<Integer> getCourseIdList();
}
