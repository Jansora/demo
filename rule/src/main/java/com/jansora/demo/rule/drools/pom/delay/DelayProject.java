package com.jansora.demo.rule.drools.pom.delay;

import com.jansora.demo.rule.pom.Project;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2024-01-15 13:56:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DelayProject extends Project {

    long delayDays;

    /**
     * 处理状态
     */
    boolean status;

    public DelayProject(String projectName, LocalDateTime expectedDate, LocalDateTime completedDate, Color color) {

        super(projectName, expectedDate, completedDate, color.name());

        this.status = false;

    }
}
