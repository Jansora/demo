package com.jansora.demo.rule.drools;

import com.jansora.demo.rule.drools.pom.delay.DelayProject;
import com.jansora.demo.rule.pom.Project;
import jakarta.annotation.Resource;
import org.drools.core.impl.InternalKnowledgeBase;
import org.kie.api.KieBase;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderErrors;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.jansora.demo.rule.drools.DroolsAutoConfiguration.getRuleFiles;

@Component
public class DroolsTest implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    public KieBase kieBase;
    @Resource
    public KieFileSystem kieFileSystem;

    @Resource
    KieContainer kieContainer;

    @Override
    public void run(String... args) throws Exception {
        while(true){
            // 新增或者更新
            KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
            for (String filePath : getRuleFiles()) {
                kBuilder.add(ResourceFactory.newFileResource(filePath), ResourceType.DRL);
            }
            if (kBuilder.hasErrors()) {
                KnowledgeBuilderErrors errors = kBuilder.getErrors();
                log.error(errors.toString());
                return;
            }

            InternalKnowledgeBase knowledgeBase = (InternalKnowledgeBase) kieContainer.getKieBase();
            knowledgeBase.addPackages(kBuilder.getKnowledgePackages());
            System.out.println("-----------------------------reload rule -----------------------------");



            KieSession kieSession = kieBase.newKieSession();

            System.out.println("-----------------------------start:" +  LocalDateTime.now() + "-----------------------------");
            DelayProject project_15 = new DelayProject( "drools 流程引擎测试 -15 day",
                    LocalDateTime.now().minusDays(15L),LocalDateTime.now(),
                    Project.Color.DEFAULT);
            kieSession.insert(project_15);
            DelayProject project15 = new DelayProject( "drools 流程引擎测试 15 day",
                    LocalDateTime.now().plusDays(15L),LocalDateTime.now(),
                    Project.Color.DEFAULT);
            kieSession.insert(project15);
            DelayProject project35 = new DelayProject( "drools 流程引擎测试 35 day",
                    LocalDateTime.now().plusDays(35L),LocalDateTime.now(),
                    Project.Color.DEFAULT);
            kieSession.insert(project35);
            DelayProject project65 = new DelayProject( "drools 流程引擎测试 65 day",
                    LocalDateTime.now().plusDays(65L),LocalDateTime     .now(),
                    Project.Color.DEFAULT);
            kieSession.insert(project65);
            DelayProject project95 = new DelayProject( "drools 流程引擎测试 95 day",
                    LocalDateTime.now().plusDays(95L),LocalDateTime.now(),
                    Project.Color.DEFAULT);
            kieSession.insert(project95);

            kieSession.fireAllRules();
            kieSession.dispose();

            System.out.println("-----------------------------end:" +  LocalDateTime.now() + "-----------------------------");
            Thread.sleep(10000);
        }
    }
}
