package com.hopu.service.impl;

import com.hopu.dao.ScoreDao;
import com.hopu.dao.impl.ScoreDaoImpl;
import com.hopu.domain.Score;
import com.hopu.service.ScoreService;

import java.util.List;

/**
 * @Author：YwaiX
 * @version:1.0
 * @Date：Created in 2024-08-02 09:19:44
 * @Description：
 */

public class ScoreServiceImpl implements ScoreService {
    private ScoreDao scoreDao = new ScoreDaoImpl();
    
    /**
     * 添加分数
     * @param score
     * @return
     */
    @Override
    public Integer insertScore(Score score) {
        
        //直接调用dao接口添加分数
        return scoreDao.insertScore(score);
    }
    
    /**
     * 修改分数
     * @param score
     * @return
     */
    @Override
    public Integer updateScore(Score score) {
        
        //直接调用dao接口修改分数
        return scoreDao.updateScore(score);
    }
    
    /**
     * 删除成绩
     * @param score
     * @return
     */
    @Override
    public Integer deleteScore(Score score) {
        
        //直接调用dao接口删除成绩
        return scoreDao.deleteScore(score);
    }
    
    /**
     * 批量删除单个学生的多个成绩
     * @param studentId
     * @param courseIds
     * @return
     */
    @Override
    public Integer deleteScores(Integer studentId, Object[] courseIds) {
        
        //直接调用dao接口批量删除单个学生的多个成绩
        return scoreDao.deleteScores(studentId,courseIds);
    }
    
    /**
     * 获取成绩列表
     * @return
     */
    @Override
    public List<Score> getScoreList() {
        
        //直接调用dao接口获取车估计列表
        return scoreDao.getScoreList();
    }
    
    /**
     * 查询单个学生的所有成绩
     * @param score
     * @return
     */
    @Override
    public List<Score> getScoreByStudentId(Score score) {
        
        //直接调用dao接口查询单个学生的所有成绩
        return scoreDao.getScoreByStudentId(score);
    }
    
    /**
     * 判断成绩是否存在
     * @param studentId
     * @param courseId
     * @return
     */
    @Override
    public List<Score> checkScore(Integer studentId, Integer courseId) {
        
        //直接调用dao接口判断
        return scoreDao.checkScore(studentId,courseId);
    }
}
