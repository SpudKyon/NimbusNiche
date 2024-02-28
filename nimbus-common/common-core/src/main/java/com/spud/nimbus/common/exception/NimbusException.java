package com.spud.nimbus.common.exception;

import com.spud.nimbus.common.response.ResultCode;
import lombok.Getter;

/**
 * @author spud
 * @since 2024/1/23
 */
@Getter
public class NimbusException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Object object;

	private ResultCode resultCode;

	public NimbusException(String msg) {
		super(msg);
	}

	public NimbusException(String msg, Object object) {
		super(msg);
		this.object = object;
	}

	public NimbusException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NimbusException(ResultCode code) {
		super(code.getMsg());
		this.resultCode = code;
	}

	public NimbusException(ResultCode code, Object object) {
		super(code.getMsg());
		this.resultCode = code;
		this.object = object;
	}

}