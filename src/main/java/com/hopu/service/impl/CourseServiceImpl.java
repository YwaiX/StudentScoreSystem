package com.hopu.service.impl;

import com.hopu.dao.CourseDao;
import com.hopu.dao.ScoreDao;
import com.hopu.dao.impl.CourseDaoImpl;
import com.hopu.dao.impl.ScoreDaoImpl;
import com.hopu.domain.Course;
import com.hopu.service.CourseService;

import java.util.List;

/**
 * @Author：YwaiX
 * @version:1.0
 * @Date：Created in 2024-08-02 09:19:16
 * @Description：
 */

public class CourseServiceImpl implements CourseService {
    private CourseDao courseDao = new CourseDaoImpl();
    private ScoreDao scoreDao = new ScoreDaoImpl();
    
    /**
     * 添加学科
     *
     * @param course
     * @return
     */
    @Override
    public Integer insertCourse(Course course) {
        
        //直接调用dao接口添加学科
        return courseDao.insertCourse(course);
    }
    
    /**
     * 修改学科
     *
     * @param course
     * @return
     */
    @Override
    public Integer updateCourse(Course course) {
        
        //直接调用dao接口修改学科
        return courseDao.insertCourse(course);
    }
    
    /**
     * 删除学科
     *
     * @param courseId
     * @return
     */
    @Override
    public Integer deleteCourse(Integer courseId) {
        //先删除学科再删除成绩
        Integer i = courseDao.deleteCourse(courseId);
        //直接调用dao接口实现删除学科
        Integer i1 = scoreDao.deleteScoreByCourseId(courseId);
        return i == 1 && i1 == 1 ? 1 : 0;
    }
    
    /**
     * 批量删除学科
     *
     * @param courseIds
     * @return
     */
    @Override
    public Integer deleteCourses(Object[] courseIds) {
        
        //直接调用dao批量删除学科
        return courseDao.deleteCourses(courseIds);
    }
    
    /**
     * 获取所有学科信息
     *
     * @return
     */
    @Override
    public List<Course> getCourseList() {
        
        //直接调用dao接口获取所有学科信息
        return courseDao.getCourseList();
    }
    
    /**
     * 获取学科信息
     *
     * @param courseId
     * @return
     */
    @Override
    public Course getCourseById(Integer courseId) {
        
        //直接调用dao接口获取学科信息
        return courseDao.getCourseById(courseId);
    }
    
    /**
     * 获取所有学科id
     *
     * @return
     */
    @Override
    public List<Integer> getCourseIdList() {
        
        //直接调用dao接口获取所有学科id
        return courseDao.getCourseIdList();
    }
}
