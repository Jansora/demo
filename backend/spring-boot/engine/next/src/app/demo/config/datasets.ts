export const droolsRuleDatasets = [
  {
    "name": "默认规则",
    "payload": `

package rules;

import com.jansora.demo.rule.drools.pom.delay.DelayProject
import java.time.Duration
import java.time.LocalDateTime
import com.jansora.demo.rule.pom.Project

function Void markColor(DelayProject project, String color) {
    project.setColor(color);
    System.out.println("mark 888999[" + project.getProjectName() + project.getColor());
    return null;
}

rule "green"
//    no-loop true
    lock-on-active true //锁住规则，防止重复触发
    when
        $project: DelayProject(
            Duration.between(expectedDate, completedDate).toDays() < 0
        )
    then
        markColor($project, Project.Color.GREEN.name());
end


rule "blue"
    no-loop true
    lock-on-active true //锁住规则，防止重复触发
    when
        $project: DelayProject(
            Duration.between(expectedDate, completedDate).toDays() > 0
            && Duration.between(expectedDate, completedDate).toDays() < 30
        )
    then
        markColor($project, Project.Color.BLUE.name());
end

rule "yellow"
    no-loop true
    lock-on-active true //锁住规则，防止重复触发
    when
        $project: DelayProject(
            Duration.between(expectedDate, completedDate).toDays() > 30
            && Duration.between(expectedDate, completedDate).toDays() < 60
        )
    then
        markColor($project, Project.Color.YELLOW.name());
end

rule "red"
    no-loop true
    lock-on-active true //锁住规则，防止重复触发
    when
        $project: DelayProject(
            Duration.between(expectedDate, completedDate).toDays() > 60
            && Duration.between(expectedDate, completedDate).toDays() < 90
        )
    then
        markColor($project, Project.Color.RED.name());
end

rule "black"
    no-loop true
    lock-on-active true //锁住规则，防止重复触发
    when
        $project: DelayProject(
            Duration.between(expectedDate, completedDate).toDays() > 90
        )
    then
        markColor($project, Project.Color.BLACK.name());
//        $project.setColor("black");
//        System.out.println("mark[" + $project.getProjectName() + $project.getColor());
end
    
    
    `,

  },
  {
    "name": "默认规则2",
    "payload": `
    `
  }
]

