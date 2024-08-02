package com.hopu.dao.impl;

import com.hopu.dao.ScoreDao;
import com.hopu.domain.Score;
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
 * @Date：Created in 2024-08-02 09:21:05
 * @Description：
 */

public class ScoreDaoImpl implements ScoreDao {
    
    /**
     * 添加分数
     *
     * @param score
     * @return
     */
    @Override
    public Integer insertScore(Score score) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            //获取连接
            connection = DruidUtil.getConnection();
            
            //sql语句
            @Language("sql")
            String sql = "insert into score values (?,?,?)";
            
            //sql预编译
            preparedStatement = connection.prepareStatement(sql);
            
            //sql语句添加数据
            preparedStatement.setObject(1, score.getStudentId());
            preparedStatement.setObject(2, score.getCourseId());
            preparedStatement.setObject(3, score.getScore());
            
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
     * 修改成绩
     *
     * @param score
     * @return
     */
    @Override
    public Integer updateScore(Score score) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            //获取连接
            connection = DruidUtil.getConnection();
            
            //sql语句
            @Language("sql")
            String sql = "update score set score = ? where student_id = ? and course_id = ?";
            
            //sql预编译
            preparedStatement = connection.prepareStatement(sql);
            
            //sql语句添加数据
            preparedStatement.setObject(1, score.getScore());
            preparedStatement.setObject(2, score.getStudentId());
            preparedStatement.setObject(3, score.getCourseId());
            
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
     * 删除成绩
     *
     * @param score
     * @return
     */
    @Override
    public Integer deleteScore(Score score) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            //获取连接
            connection = DruidUtil.getConnection();
            
            //sql语句
            @Language("sql")
            String sql = "delete from score where student_id = ? and course_id = ?";
            
            //sql预编译
            preparedStatement = connection.prepareStatement(sql);
            
            //sql语句添加数据
            preparedStatement.setObject(1, score.getStudentId());
            preparedStatement.setObject(2, score.getCourseId());
            
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
     * 批量删除单个学生的多个成绩
     *
     * @param studentId
     * @param courseIds
     * @return
     */
    @Override
    public Integer deleteScores(Integer studentId, Object[] courseIds) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            //获取连接
            connection = DruidUtil.getConnection();
            
            //sql语句
            @Language("sql")
            String sql = "delete from score where student_id = ? and course_id in (";
            for (int i = 0; i < courseIds.length; i++) {
                sql += "?";
                sql += (i == courseIds.length - 1) ? ")" : ",";
            }
            
            System.out.println(sql);
            
            //sql预编译
            preparedStatement = connection.prepareStatement(sql);
            
            //sql语句添加数据
            preparedStatement.setObject(1, studentId);
            for (int i = 0; i < courseIds.length; i++) {
                preparedStatement.setObject(i + 2, courseIds[i]);
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
     * 根据学生id删除成绩
     *
     * @param studentIds
     */
    @Override
    public void deleteScoreByStudentId(Object[] studentIds) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            //获取连接
            connection = DruidUtil.getConnection();
            
            //sql语句
            @Language("sql")
            String sql = "delete from score where student_id in (";
            for (int i = 0; i < studentIds.length; i++) {
                sql += "?";
                sql += (i == studentIds.length - 1) ? ")" : ",";
            }
            
            //sql预编译
            preparedStatement = connection.prepareStatement(sql);
            
            //sql语句添加数据
            for (int i = 0; i < studentIds.length; i++) {
                preparedStatement.setObject(i+1, studentIds[i]);
            }
            
            
            //执行sql
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(null, preparedStatement, connection);
        }
        
    }
    
    /**
     * 根据学科id删除成绩
     *
     * @param courseIds
     */
    @Override
    public void deleteScoreByCourseId(Object[] courseIds) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            //获取连接
            connection = DruidUtil.getConnection();
            
            //sql语句
            @Language("sql")
            String sql = "delete from score where course_id in (";
            
            for (int i = 0; i < courseIds.length; i++) {
                sql += "?";
                sql += (i == courseIds.length - 1) ? ")" : ",";
            }
            
            //sql预编译
            preparedStatement = connection.prepareStatement(sql);
            
            //sql语句添加数据
            for (int i = 0; i < courseIds.length; i++) {
                preparedStatement.setObject(i + 1, courseIds[i]);
            }
            
            
            //执行sql
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(null, preparedStatement, connection);
        }
        
    }
    
    /**
     * 获取成绩列表
     *
     * @return
     */
    @Override
    public List<Score> getScoreList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            //获取连接
            connection = DruidUtil.getConnection();
            
            //sql语句
            @Language("sql")
            String sql = "select * from score";
            
            //sql预编译
            preparedStatement = connection.prepareStatement(sql);
            
            //执行sql
            resultSet = preparedStatement.executeQuery();
            
            //用集合存储数据
            List<Score> scores = new ArrayList<>();
            while (resultSet.next()) {
                scores.add(new Score(
                        resultSet.getInt("student_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getInt("score")
                ));
            }
            
            return scores;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(null, preparedStatement, connection);
        }
        
        return null;
    }
    
    /**
     * 查询单个学生的所有成绩
     *
     * @param score
     * @return
     */
    @Override
    public List<Score> getScoreByStudentId(Score score) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            //获取连接
            connection = DruidUtil.getConnection();
            
            //sql语句
            @Language("sql")
            String sql = "select * from score where student_id = ?";
            
            //sql预编译
            preparedStatement = connection.prepareStatement(sql);
            
            //sql语句添加数据
            preparedStatement.setObject(1, score.getStudentId());
            
            //执行sql
            resultSet = preparedStatement.executeQuery();
            
            //将查询的数据存储到集合
            List<Score> scores = new ArrayList<>();
            while (resultSet.next()) {
                scores.add(new Score(
                        resultSet.getInt("student_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getInt("score")
                ));
            }
            
            return scores;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(resultSet, preparedStatement, connection);
        }
        
        return null;
    }
    
    /**
     * 判断成绩是否存在
     *
     * @param studentId
     * @param courseId
     * @return
     */
    @Override
    public List<Score> checkScore(Integer studentId, Integer courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            //获取连接
            connection = DruidUtil.getConnection();
            
            //sql语句
            @Language("sql")
            String sql = "select * from score where student_id = ? and course_id = ?";
            
            //sql预编译
            preparedStatement = connection.prepareStatement(sql);
            
            //sql语句添加数据
            preparedStatement.setObject(1, studentId);
            preparedStatement.setObject(2, courseId);
            
            //执行sql
            resultSet = preparedStatement.executeQuery();
            
            //通过集合存储成绩信息
            List<Score> scores = new ArrayList<>();
            while (resultSet.next()) {
                scores.add(new Score(
                        resultSet.getInt("student_id"),
                        resultSet.getInt("course_id"),
                        resultSet.getInt("score")
                ));
            }
            
            return scores;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DruidUtil.close(resultSet, preparedStatement, connection);
        }
        
        return null;
    }
}
