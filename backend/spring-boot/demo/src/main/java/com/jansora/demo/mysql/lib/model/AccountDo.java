package com.jansora.demo.mysql.lib.model;

import com.jansora.app.repo.core.payload.model.BaseDo;
import io.mybatis.provider.Entity;

/**
 * <Description> Description for AccountDo <br>
 *
 * @author jansora (zhang.yangyuan) <br>
 * @version 1.0 <br>
 * @email zhangyue1936@gmail.com
 * @transId null
 * @CreateDate 2022/7/29 PM02:02 <br>
 * @since 1.0 <br>
 */
@Entity.Table(value = "account", remark = "系统用户", autoResultMap = true)
public class AccountDo extends BaseDo {


}
