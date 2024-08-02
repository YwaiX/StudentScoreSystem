package com.hopu.controller;

import com.hopu.domain.Student;
import com.hopu.service.StudentService;
import com.hopu.service.impl.StudentServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author：YwaiX
 * @version:1.0
 * @Date：Created in 2024-08-02 09:17:55
 * @Description：
 */

public class StudentController {
    private static StudentService studentService = new StudentServiceImpl();
    Scanner userInput = new Scanner(System.in);
    /**
     * 添加学生
     */
    public void insertStudent() {
        Student student = new Student();
        
        student = inputStudent(student);
        
        //调用service接口实现添加学生
        int i = studentService.insertStudent(student);
        
        //如果i等于1说明添加成功,否则添加失败
        if (i == 1){
            System.out.println("添加成功!!!\n");
        }else {
            System.out.println("添加失败!!!\n");
        }
    }
    
    /**
     * 修改学生信息
     */
    public void updateStudent() {
        //获取所有学生id,如果没有学生id则说明没有学生,无法修改
        List<Integer> studentIdList = studentService.getStudentIdList();
        if (studentIdList.isEmpty()){
            System.out.println("当前没有学生!!!\n");
            return;
        }
        
        //修改学生信息之前先输出所有学生信息
        getStudentList();
        
        Student student = new Student();
        
        //循环控制输入的学生id存在
        while (true){
            System.out.print("请输入学生id:");
            int studentId = userInput.nextInt();
            if (checkStudentId(studentId)){
                student.setStudentId(studentId);
                break;
            }else {
                System.out.println("请输入正确的学生id!!!\n");
            }
        }
        
        //调用输入学生信息的方法
        student = inputStudent(student);
        
        //调用service接口修改学生
        int i = studentService.updateStudent(student);
        
        //如果i等于1说明修改成功,否则修改失败
        if (i == 1){
            System.out.println("修改学生成功!!!\n");
        }else {
            System.out.println("修改学生失败!!!\n");
        }
    }
    
    /**
     * 删除学生
     */
    public void deleteStudent() {
        //获取所有学生id,如果没有学生id则说明没有学生,无法修改
        List<Integer> studentIdList = studentService.getStudentIdList();
        if (studentIdList.isEmpty()){
            System.out.println("当前没有学生!!!\n");
            return;
        }
        
        //删除学生之前先打印所有学生
        getStudentList();
        
        //循环控制输入的学号正确
        while (true){
            System.out.print("请输入学生学号:");
            Integer studentId = userInput.nextInt();
            if (checkStudentId(studentId)){
                //直接调用service删除学生
                int i = studentService.deleteStudent(studentId);
                //如果i == 1说明删除成功,否则删除失败
                if (i == 1){
                    System.out.println("删除成功!!!\n");
                }else {
                    System.out.println("删除失败!!!\n");
                }
                break;
            }else {
                System.out.println("请输入正确的学生学号!!!\n");
            }
        }
    }
    
    /**
     * 批量删除学生
     */
    public void deleteStudents() {
        //获取所有学生id,如果没有学生id则说明没有学生,无法修改
        List<Integer> studentIdLists = studentService.getStudentIdList();
        if (studentIdLists.isEmpty()){
            System.out.println("当前没有学生!!!\n");
            return;
        }
        
        //删除之前先打印所有学生信息
        getStudentList();
        
        //循环控制输入的学号存在
        List<Integer> studentIdList = new ArrayList<>();
        while (true){
            System.out.print("请输入学生id(-1结束输入):");
            Integer studentId = userInput.nextInt();
            
            //输入-1表示结束输入,直接break
            if (studentId == -1){
                break;
            }
            
            //如果学号存在,将学号存入集合
            if (checkStudentId(studentId)){
                studentIdList.add(studentId);
            }else {
                System.out.println("请输入正确的学生id!!!\n");
            }
        }
        
        //将集合转成数组
        Object[] studentIds = studentIdList.toArray();
        
        //调用service接口批量删除学生
        int i = studentService.deleteStudents(studentIds);
        
        //如果i == studentIds.length说明全部删除成功
        if (i == studentIds.length){
            System.out.println("全部删除成功!!!\n");
        }else {
            System.out.println("成功删除"+i+"条记录,未删除"+(studentIds.length-i)+"条记录\n");
        }
    }
    
    /**
     * 学生列表
     */
    public void getStudentList() {
        //调用service接口获取所有学生信息
        List<Student> students = studentService.getStudentList();
        
        if (students.isEmpty()){
            System.out.println("当前还没有学生!!!\n");
            return;
        }
        
        //遍历输出
        for (Student student : students) {
            System.out.println(student);
        }
    }
    
    /**
     * 查询学生信息
     */
    public void getStudentById() {
        //获取所有学生id,如果没有学生id则说明没有学生,无法修改
        List<Integer> studentIdList = studentService.getStudentIdList();
        if (studentIdList.isEmpty()){
            System.out.println("当前没有学生!!!\n");
            return;
        }
        
        //查询单个学生信息之前先打印所有学生信息
        getStudentList();
        
        Integer studentId;
        //循环控制输入的学生学号存在
        while (true){
            System.out.print("请输入学生学号:");
            studentId = userInput.nextInt();
            if (checkStudentId(studentId)){
                break;
            }else {
                System.out.println("您输入的学号不存在,请重新输入!!!\n");
            }
        }
        
        //调用service接口实现查询单个学生信息
        Student student = studentService.getStudentById(studentId);
        System.out.println(student);
    }
    
    /**
     * 输入学生信息
     * @param student
     * @return
     */
    private static Student inputStudent(Student student){
        Scanner userInput = new Scanner(System.in);
        
        System.out.print("请输入学生姓名:");
        student.setStudentName(userInput.next());
        
        System.out.print("请输入学生年龄:");
        student.setStudentAge(userInput.nextInt());
        
        //死循环控制输入的性别是否正确
        while (true){
            System.out.print("请输入学生性别:");
            String studentSex = userInput.next();
            if (studentSex.equals("男") || studentSex.equals("女")){
                student.setStudentSex(studentSex);
                break;
            }else {
                System.out.println("请输入正确的性别!!!\n");
            }
        }
        return student;
    }
    
    /**
     * 判断学号是否存在
     * @param studentId
     * @return
     */
    public Boolean checkStudentId(Integer studentId){
        //调用service接口获取所有学生信息
        List<Integer> studentIdList = studentService.getStudentIdList();
        for (int i = 0; i < studentIdList.size(); i++) {
            if (studentId.equals(studentIdList.get(i))){
                return true;
            }
        }
        return false;
    }
}
