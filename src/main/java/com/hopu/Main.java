package com.hopu;

import com.hopu.controller.CourseController;
import com.hopu.controller.ScoreController;
import com.hopu.controller.StudentController;

import java.util.Scanner;

/**
 * @Author：YwaiX
 * @version:1.0
 * @Date：Created in 2024-08-02 09:04:29
 * @Description：
 */

public class Main {
    public static void main(String[] args) {
        Scanner userInput = new Scanner(System.in);
        StudentController studentController = new StudentController();
        CourseController courseController = new CourseController();
        ScoreController scoreController = new ScoreController();
        
        while (true) {
            System.out.println("1.学生信息\t\t2.学科信息\t\t3.成绩信息\t\t4.退出系统");
            int code = userInput.nextInt();
            switch (code) {
                case 1:
                    STUDENT:
                    while (true) {
                        System.out.println("1.添加学生\t\t2.修改学生\t\t3.删除学生\t\t4.批量删除学生\t\t5.学生列表\t\t6.查询学生信息\t\t7.退出");
                        code = userInput.nextInt();
                        switch (code) {
                            case 1:
                                //添加学生
                                studentController.insertStudent();
                                break;
                            case 2:
                                //修改学生
                                studentController.updateStudent();
                                break;
                            case 3:
                                //删除学生
                                studentController.deleteStudent();
                                break;
                            case 4:
                                //批量删除学生
                                studentController.deleteStudents();
                                break;
                            case 5:
                                //学生列表
                                studentController.getStudentList();
                                break;
                            case 6:
                                //查询学生信息
                                studentController.getStudentById();
                                break;
                            case 7:
                                //退出
                                break STUDENT;
                            default:
                                System.out.println("请输入正确的编号!!!\n");
                        }
                    }
                    break;
                case 2:
                    COURSE:
                    while (true) {
                        System.out.println("1.添加学科\t\t2.修改学科\t\t3.删除学科\t\t4.批量删除学科\t\t5.学科列表\t\t6.查询学科信息\t\t7.退出");
                        code = userInput.nextInt();
                        switch (code) {
                            case 1:
                                //添加学科
                                courseController.insertCourse();
                                break;
                            case 2:
                                //修改学科
                                courseController.updateCourse();
                                break;
                            case 3:
                                //删除学科
                                courseController.deleteCourse();
                                break;
                            case 4:
                                //批量删除学科
                                courseController.deleteCourses();
                                break;
                            case 5:
                                courseController.getCourseList();
                                //学科列表
                                break;
                            case 6:
                                //查询学科信息
                                courseController.getCourseById();
                                break;
                            case 7:
                                //退出
                                break COURSE;
                            default:
                                System.out.println("请输入正确的编号!!!\n");
                        }
                    }
                    break;
                case 3:
                    SCORE:
                    while (true) {
                        System.out.println("1.添加成绩\t\t2.修改成绩\t\t3.删除成绩\t\t4.批量删除单个学生成绩\t\t5.成绩列表\t\t6.查询成绩\t\t7.退出");
                        code = userInput.nextInt();
                        switch (code) {
                            case 1:
                                //添加成绩
                                scoreController.insertScore();
                                break;
                            case 2:
                                //修改成绩
                                scoreController.updateScore();
                                break;
                            case 3:
                                //删除成绩
                                scoreController.deleteScore();
                                break;
                            case 4:
                                //批量删除单个学生的成绩
                                scoreController.deleteScores();
                                break;
                            case 5:
                                //成绩列表
                                scoreController.getScoreList();
                                break;
                            case 6:
                                //查询成绩
                                scoreController.getScoreByStudentId();
                                break;
                            case 7:
                                //退出
                                break SCORE;
                            default:
                                System.out.println("请输入正确的编号!!!\n");
                        }
                    }
                    break;
                case 4:
                    System.out.println("拜拜");
                    System.exit(0);
                default:
                    System.out.println("请输入正确的编号");
            }
        }
    }
}
