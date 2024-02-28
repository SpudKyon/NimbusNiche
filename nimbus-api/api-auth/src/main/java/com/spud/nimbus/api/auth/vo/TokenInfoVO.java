package com.spud.nimbus.api.auth.vo;

import com.spud.nimbus.common.vo.BaseVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;

/**
 * token信息，该信息用户返回给前端，前端请求携带accessToken进行用户校验
 *
 * @author spud
 * @date 2024/1/28
 */
@Data
@ToString
public class TokenInfoVO extends BaseVO {

	@Schema(description = "accessToken")
	private String accessToken;

	@Schema(description = "refreshToken")
	private String refreshToken;

	@Schema(description = "在多少秒后过期")
	private Integer expiresIn;

}
