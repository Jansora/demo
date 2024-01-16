package com.jansora.demo.rule.drools.pom.delay;

import com.jansora.demo.rule.pom.Project;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @description:
 * @author: jansora (zhang.yangyuan)
 * @date: 2024-01-15 13:56:13
 */
@Data
@Accessors(chain = true)
public class DelayProject extends Project {

    long delayDays;

    public DelayProject(String projectName, LocalDateTime expectedDate, LocalDateTime completedDate, Color color) {

        super(projectName, expectedDate, completedDate, color.name());
        this.delayDays = Duration.between(completedDate, expectedDate).toDays();
        System.out.println("DelayProject: " + projectName + " delayDays: " + delayDays);
    }
}
