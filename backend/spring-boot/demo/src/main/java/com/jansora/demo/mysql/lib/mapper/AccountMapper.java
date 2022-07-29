package com.jansora.demo.mysql.lib.mapper;

import com.jansora.app.repo.core.carrier.dto.KVDto;
import com.jansora.demo.mysql.lib.model.AccountDo;
import tk.mybatis.mapper.common.BaseMapper;

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
public interface AccountMapper extends BaseMapper<AccountDo> {

    List<KVDto<String>> count();


}
