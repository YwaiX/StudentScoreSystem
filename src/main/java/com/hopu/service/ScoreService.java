package com.hopu.service;

import com.hopu.domain.Score;

import java.util.List;

/**
 * @Author：YwaiX
 * @version:1.0
 * @Date：Created in 2024-08-02 09:19:31
 * @Description：
 */

public interface ScoreService {
    
    /**
     * 添加分数
     * @param score
     * @return
     */
    Integer insertScore(Score score);
    
    /**
     * 修改分数
     * @param score
     * @return
     */
    Integer updateScore(Score score);
    
    /**
     * 删除成绩
     * @param score
     */
    Integer deleteScore(Score score);
    
    /**
     * 批量删除单个学生的多个成绩
     * @param studentId
     * @param courseIds
     * @return
     */
    Integer deleteScores(Integer studentId, Object[] courseIds);
    
    /**
     * 获取成绩列表
     * @return
     */
    List<Score> getScoreList();
    
    /**
     * 查询单个学生的所有成绩
     * @param score
     * @return
     */
    List<Score> getScoreByStudentId(Score score);
    
    /**
     * 判断成绩是否存在
     * @param studentId
     * @param courseId
     * @return
     */
    List<Score> checkScore(Integer studentId, Integer courseId);
}
