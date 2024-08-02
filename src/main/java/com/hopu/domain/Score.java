package com.hopu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author：YwaiX
 * @version:1.0
 * @Date：Created in 2024-08-02 09:03:33
 * @Description：
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    private Integer studentId;
    private Integer courseId;
    private Integer score;
}
