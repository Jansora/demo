package com.jansora.demo.mysql.lib.mapper;

import com.jansora.app.repo.core.payload.dto.KVDto;
import com.jansora.demo.mysql.lib.model.AccountDo;
import io.mybatis.mapper.Mapper;

import java.util.List;

/**
 * <Description> Description for AccountMapper <br>
 *
 * @author jansora (zhang.yangyuan) <br>
 * @version 1.0 <br>
 * @email zhangyue1936@gmail.com
 * @transId null
 * @CreateDate 2022/7/29 PM02:05 <br>
 * @since 1.0 <br>
 */
public interface AccountMapper extends Mapper<AccountDo, Long> {

    List<KVDto<String>> count();


}
