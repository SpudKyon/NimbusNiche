package com.spud.nimbus.common.database.config;

import cn.hutool.core.util.StrUtil;
import com.spud.nimbus.common.constant.Auth;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Component;

/**
 * @author spud
 * @date 2024/1/25
 */
@Slf4j
@Component
@ConditionalOnClass({ RequestInterceptor.class, GlobalTransactional.class })
public class SeataRequestInterceptor implements RequestInterceptor {

	@Override
	public void apply(RequestTemplate template) {
		String currentXid = RootContext.getXID();
		if (StrUtil.isNotBlank(currentXid) && !template.url().startsWith(Auth.CHECK_TOKEN_URI)
				&& !template.url().startsWith(Auth.CHECK_RBAC_URI)) {
			template.header(RootContext.KEY_XID, currentXid);
		}
	}

}
