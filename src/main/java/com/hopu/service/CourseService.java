package com.hopu.service;

import com.hopu.domain.Course;

import java.util.List;

/**
 * @Author：YwaiX
 * @version:1.0
 * @Date：Created in 2024-08-02 09:18:59
 * @Description：
 */

public interface CourseService {
    
    /**
     * 添加学科
     * @param course
     * @return
     */
    Integer insertCourse(Course course);
    
    /**
     * 修改学科
     * @param course
     * @return
     */
    Integer updateCourse(Course course);
    
    /**
     * 删除学科
     * @param courseIds
     * @return
     */
    Integer deleteCourse(Object[] courseIds);
    
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
