package com.jansora.demo.mysql.lib;

import com.jansora.app.repo.core.function.DoSomethingWithThrowable;
import com.jansora.demo.mysql.lib.mapper.AccountMapper;
import com.jansora.demo.mysql.lib.model.AccountDo;
import com.jansora.repo.demo.AbstractDemoFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <Description> Description for OverBeanDemo <br>
 *
 * @author jansora (zhang.yangyuan) <br>
 * @version 1.0 <br>
 * @email zhangyue1936@gmail.com
 * @transId null
 * @CreateDate 2022/7/21 AM09:27 <br>
 * @since 1.0 <br>
 */
@Component
public class MysqlDemo extends AbstractDemoFactory implements ApplicationContextAware {

    private static ApplicationContext context;

    private static AccountMapper accountMapper;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        accountMapper = context.getBean(AccountMapper.class);
    }

    @Override
    public DoSomethingWithThrowable doSomething(String[] args) throws Throwable {
        return () -> {
            System.out.println(accountMapper.count());
            System.out.println(accountMapper.selectByPrimaryKey(1L));
            AccountDo accountDo = new AccountDo();
            accountDo.setCreatedAt(new Date());
//            accountMapper.m

            accountMapper.insert(accountDo);
            System.out.println(accountDo);
        };
    }
}
