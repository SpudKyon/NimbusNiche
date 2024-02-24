package com.spud.nimbus.leaf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author spud
 * @date 2024/2/24
 */
@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class LeafServerException extends RuntimeException {

	public LeafServerException(String msg) {
		super(msg);
	}

}
