package com.hopu.controller;

import com.hopu.domain.Score;
import com.hopu.service.ScoreService;
import com.hopu.service.impl.ScoreServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author：YwaiX
 * @version:1.0
 * @Date：Created in 2024-08-02 09:18:15
 * @Description：
 */

public class ScoreController {
    Scanner userInput = new Scanner(System.in);
    private static ScoreService scoreService = new ScoreServiceImpl();
    private static StudentController studentController = new StudentController();
    private static CourseController courseController = new CourseController();
    
    /**
     * 添加成绩
     */
    public void insertScore() {
        //调用方法输入学生id和学科id
        Score score = inputScore();
        
        //如果学号和学科号都存在则,直接退出
        if (!checkScore(score.getStudentId(), score.getCourseId())) {
            System.out.println("该学科有成绩!!!\n");
            return;
        }
        
        //通过循环控制分数在0-100之间
        score = inputGrade(score);
        
        //调用service接口添加成绩
        int i = scoreService.insertScore(score);
        
        //如果i == 1说明添加成功,否则添加失败
        if (i == 1) {
            System.out.println("添加成功!!!\n");
        } else {
            System.out.println("添加失败!!!\n");
        }
    }
    
    /**
     * 修改成绩
     */
    public void updateScore() {
        //调用接口获取所有成绩,如果成绩集合为空则不能修改成绩
        List<Score> scores = scoreService.getScoreList();
        if (scores.isEmpty()) {
            System.out.println("当前暂无成绩!!!\n");
            return;
        }
        
        //调用方法输入学生id和学科id
        Score score = inputScore();
        
        //如果学生id和学科id存在,则输入成绩
        if (!checkScore(score.getStudentId(), score.getCourseId())) {
            score = inputGrade(score);
        } else {
            System.out.println("您输入的学生对应的学科没有分数,不能修改!!!\n");
            return;
        }
        
        //调用service接口修改分数
        int i = scoreService.updateScore(score);
        
        //如果i== 1说明修改成功,否则修改失败
        if (i == 1) {
            System.out.println("修改成功!!!\n");
        } else {
            System.out.println("修改失败!!!\n");
        }
    }
    
    /**
     * 删除成绩
     */
    public void deleteScore() {
        //调用接口获取所有成绩,如果成绩集合为空则不能修改成绩
        List<Score> scores = scoreService.getScoreList();
        if (scores.isEmpty()) {
            System.out.println("当前暂无成绩!!!\n");
            return;
        }
        
        //调用方法输入学生id和学科id
        Score score = inputScore();
        
        //调用方法判断学生是否有该学科成绩
        if (!checkScore(score.getStudentId(), score.getCourseId())) {
            //调用service删除成绩
            int i = scoreService.deleteScore(score);
            //如果i==1说明删除成功
            if (i == 1) {
                System.out.println("删除成功!!!\n");
            } else {
                System.out.println("删除失败!!!\n");
            }
        } else {
            System.out.println("该学生没有该学科的成绩!!!\n");
        }
    }
    
    /**
     * 批量删除单个学生的多个成绩
     */
    public void deleteScores() {
        //输入学号之前先显示学生信息
        studentController.getStudentList();
        Integer studentId;
        //通过循环控制输入的学生学号存在
        while (true) {
            System.out.print("请输入学生id:");
            studentId = userInput.nextInt();
            
            //通过调用studentController的checkStudentId方法判断输入的学生学号是否存在
            if (studentController.checkStudentId(studentId)) {
                break;
            } else {
                System.out.println("您输入的学生学号有误或不存在,请重新输入!!!\n");
            }
        }
        
        //输入学科之前先显示学科
        courseController.getCourseList();
        
        //使用集合存储要删除的学科id
        List<Integer> courseList = new ArrayList<>();
        //通过循环控制输入的学科id存在
        while (true) {
            System.out.print("请输入学科id(-1结束退出):");
            Integer courseId = userInput.nextInt();
            
            if (courseId == -1){
                break;
            }
            
            //通过调用courseController的checkCourseId方法判断输入的学科id是否存在,如果存在则存放到score对象
            if (courseController.checkCourseId(courseId)) {
                courseList.add(courseId);
            } else {
                System.out.println("您输入的学科id有误或不存在,请重新输入!!!\n");
            }
        }
        
        //将集合转换为数组
        Object[] courseIds = courseList.toArray();
        
        //调用service接口批量删除单个学生的多个成绩
        Integer i = scoreService.deleteScores(studentId , courseIds);
        
        //如果i == courseIds.length 说明全部删除成功
        if (i == courseIds.length){
            System.out.println("全部删除成功!!!\n");
        }else {
            System.out.println("成功删除"+i+"条记录,未删除"+(courseIds.length-i)+"条记录");
        }
    }
    
    /**
     * 成绩列表
     */
    public void getScoreList() {
        //调用service接口获取成绩列表
        List<Score> scores = scoreService.getScoreList();
        for (int i = 0; i < scores.size(); i++) {
            System.out.println(scores);
        }
    }
    
    /**
     * 查询单个学生的所有成绩
     */
    public void getScoreByStudentId() {
        Score score = new Score();
        //输入学号之前先显示学生信息
        studentController.getStudentList();
        
        //通过循环控制输入的学生学号存在
        while (true) {
            System.out.print("请输入学生id:");
            Integer studentId = userInput.nextInt();
            
            //通过调用studentController的checkStudentId方法判断输入的学生学号是否存在,如果存在则存放到score对象
            if (studentController.checkStudentId(studentId)) {
                score.setStudentId(studentId);
                break;
            } else {
                System.out.println("您输入的学生学号有误或不存在,请重新输入!!!\n");
            }
        }
        
        //调用service接口查询单个学生的所有接口
        List<Score> scores = scoreService.getScoreByStudentId(score);
        if (scores.isEmpty()) {
            System.out.println("当前学生暂无成绩");
        }
        for (int i = 0; i < scores.size(); i++) {
            System.out.println(scores);
        }
    }
    
    /**
     * 判断成绩是否存在
     *
     * @param studentId
     * @param courseId
     * @return
     */
    public Boolean checkScore(Integer studentId, Integer courseId) {
        //调用service接口判断成绩是否存在
        List<Score> scores = scoreService.checkScore(studentId, courseId);
        
        return scores.isEmpty();
    }
    
    /**
     * 输入学生id以及学科id
     *
     * @return
     */
    public Score inputScore() {
        Score score = new Score();
        //输入学号之前先显示学生信息
        studentController.getStudentList();
        
        //通过循环控制输入的学生学号存在
        while (true) {
            System.out.print("请输入学生id:");
            Integer studentId = userInput.nextInt();
            
            //通过调用studentController的checkStudentId方法判断输入的学生学号是否存在,如果存在则存放到score对象
            if (studentController.checkStudentId(studentId)) {
                score.setStudentId(studentId);
                break;
            } else {
                System.out.println("您输入的学生学号有误或不存在,请重新输入!!!\n");
            }
        }
        
        //输入学科之前先显示学科
        courseController.getCourseList();
        
        //通过循环控制输入的学科id存在
        while (true) {
            System.out.print("请输入学科id:");
            Integer courseId = userInput.nextInt();
            
            //通过调用courseController的checkCourseId方法判断输入的学科id是否存在,如果存在则存放到score对象
            if (courseController.checkCourseId(courseId)) {
                score.setCourseId(courseId);
                break;
            } else {
                System.out.println("您输入的学科id有误或不存在,请重新输入!!!\n");
            }
        }
        
        return score;
    }
    
    /**
     * 输入分数
     *
     * @param score
     * @return
     */
    public Score inputGrade(Score score) {
        while (true) {
            System.out.print("请输入分数(0-100):");
            Integer grade = userInput.nextInt();
            
            //如果输入的分数不在0-100之间,报错
            if (grade >= 0 && grade <= 100) {
                score.setScore(grade);
                break;
            } else {
                System.out.println("您输入的分数不在范围内,请重新输入!!!\n");
            }
        }
        return score;
    }
}
