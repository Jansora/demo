package com.jansora.demo.rule.drools;

import com.jansora.demo.rule.drools.pom.delay.DelayProject;
import com.jansora.demo.rule.pom.Project;
import jakarta.annotation.Resource;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DroolsTest implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    public KieBase kieBase;

    @Override
    public void run(String... args) throws Exception {
        while(true){
            KieSession kieSession = kieBase.newKieSession();

            DelayProject project = new DelayProject(
                    "drools 流程引擎测试", LocalDateTime.now().minusDays(15L),LocalDateTime.now(), Project.Color.GREEN);
//            project.setProjectName("流程引擎测试");
//            project.setCompletedDate(LocalDateTime.now());
//            project.setExpectedDate(LocalDateTime.now().minusDays(15L));
            kieSession.insert(project);

            kieSession.fireAllRules();
            kieSession.dispose();
            Thread.sleep(1000);
        }
    }
}
