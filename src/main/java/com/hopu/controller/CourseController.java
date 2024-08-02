package com.hopu.controller;

import com.hopu.domain.Course;
import com.hopu.service.CourseService;
import com.hopu.service.impl.CourseServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author：YwaiX
 * @version:1.0
 * @Date：Created in 2024-08-02 09:18:05
 * @Description：
 */

public class CourseController {
    private static CourseService courseService = new CourseServiceImpl();
    Scanner userInput = new Scanner(System.in);
    
    /**
     * 添加学科
     */
    public void insertCourse() {
        Course course = new Course();
        
        System.out.print("请输入学科名称:");
        course.setCourseName(userInput.next());
        
        //直接调用service接口添加学科
        int i = courseService.insertCourse(course);
        
        //如果i == 1则添加成功,否则添加失败
        if (i == 1) {
            System.out.println("添加成功!!!\n");
        } else {
            System.out.println("添加失败!!!\n");
        }
        
    }
    
    /**
     * 修改学科
     */
    public void updateCourse() {
        List<Integer> courseIdList = courseService.getCourseIdList();
        if (courseIdList.isEmpty()) {
            System.out.println("当前暂无学科!!!\n");
            return;
        }
        
        //修改之前先显示所有学科
        getCourseList();
        
        Course course = new Course();
        
        while (true) {
            System.out.print("请输入学科id:");
            Integer courseId = userInput.nextInt();
            if (checkCourseId(courseId)) {
                course.setCourseId(courseId);
                break;
            } else {
                System.out.println("请输入正确的学科id!!!\n");
            }
        }
        
        System.out.print("请输入学科名称:");
        course.setCourseName(userInput.next());
        
        //调用service接口修改学科信息
        int i = courseService.updateCourse(course);
        
        //如果i== 1说明修改成功,否则修改失败
        if (i == 1) {
            System.out.println("修改成功!!!\n");
        } else {
            System.out.println("修改失败!!!\n");
        }
    }
    
    
    /**
     * 删除学科
     */
    public void deleteCourse() {
        List<Integer> courseIdList = courseService.getCourseIdList();
        if (courseIdList.isEmpty()) {
            System.out.println("当前暂无学科!!!\n");
            return;
        }
        
        //删除之前先显示所有学科
        getCourseList();
        
        //循环控制删除的学科id存在
        while (true) {
            System.out.print("请输入学科id:");
            Integer courseId = userInput.nextInt();
            //调用判断学号方法
            if (checkCourseId(courseId)) {
                //调用service接口实现删除学科
                int i = courseService.deleteCourse(courseId);
                //如果i==1说明删除成功,否则删除失败
                if (i == 1) {
                    System.out.println("删除成功!!!\n");
                } else {
                    System.out.println("删除失败!!!\n");
                }
                break;
            } else {
                System.out.println("您输入的学科id不存在!!!\n");
            }
        }
    }
    
    /**
     * 批量删除学科
     */
    public void deleteCourses() {
        List<Integer> courseIdList = courseService.getCourseIdList();
        if (courseIdList.isEmpty()) {
            System.out.println("当前暂无学科!!!\n");
            return;
        }
        
        //删除之前先显示所有学科
        getCourseList();
        
        //使用集合存放需要删除的学科id
        List<Integer> courseIdLists = new ArrayList<>();
        
        //循环控制输入学科id存在
        while (true) {
            System.out.print("请输入学科id(-1结束输入):");
            Integer courseId = userInput.nextInt();
            
            if (courseId == -1) {
                break;
            }
            
            if (checkCourseId(courseId)) {
                courseIdLists.add(courseId);
            } else {
                System.out.println("您输入的学科id不存在!!!\n");
            }
        }
        
        //将集合转为数组
        Object[] courseIds = courseIdLists.toArray();
        
        //调用service接口实现批量删除
        int i = courseService.deleteCourses(courseIds);
        
        //如果i == courseIds.length说明全部删除成功,否则删除失败
        if (i == courseIds.length) {
            System.out.println("全部删除成功!!!\n");
        } else {
            System.out.println("成功删除" + i + "条记录,未删除" + (courseIds.length - i) + "条记录\n");
        }
    }
    
    /**
     * 获取所有学科信息
     */
    public void getCourseList() {
        //直接调用service接口获取所有学科信息
        List<Course> courses = courseService.getCourseList();
        if (courses.isEmpty()) {
            System.out.println("当前暂无学科!!!\n");
            return;
        }
        
        for (Course course : courses) {
            System.out.println(course);
        }
    }
    
    /**
     * 获取学科信息
     */
    public void getCourseById() {
        List<Integer> courseIdList = courseService.getCourseIdList();
        if (courseIdList.isEmpty()) {
            System.out.println("当前暂无学科!!!\n");
            return;
        }
        
        //显示所有学科信息
        getCourseList();
        
        Integer courseId;
        //循环控制输入的学科id存在
        while (true) {
            System.out.print("请输入学科id:");
            courseId = userInput.nextInt();
            //调用方法判断学科id是否存在
            if (checkCourseId(courseId)) {
                break;
            }else {
                System.out.println("您输入的学科id不存在!!!\n");
            }
        }
        
        //调用service接口获取学科信息
        Course course = courseService.getCourseById(courseId);
        System.out.println(course);
        
        
    }
    
    /**
     * 判断学科是否村咋
     *
     * @param courseId
     * @return
     */
    public Boolean checkCourseId(Integer courseId) {
        //直接调用service接口获取所有学科id
        List<Integer> courseIdList = courseService.getCourseIdList();
        for (int i = 0; i < courseIdList.size(); i++) {
            if (courseId.equals(courseIdList.get(i))) {
                return true;
            }
        }
        return false;
    }
}
