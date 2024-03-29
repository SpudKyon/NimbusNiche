package com.spud.nimbus.common.handler;

import cn.hutool.core.util.StrUtil;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.response.Result;
import com.spud.nimbus.common.response.ResultCode;
import io.seata.core.context.RootContext;
import io.seata.core.exception.TransactionException;
import io.seata.tm.api.GlobalTransactionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author spud
 * @date 2024/1/28
 */
@Slf4j
@RestController
@RestControllerAdvice
public class DefaultExceptionHandlerConfig {

	@ExceptionHandler({ MethodArgumentNotValidException.class, BindException.class })
	public ResponseEntity<Result<List<String>>> methodArgumentNotValidExceptionHandler(Exception e) {
		log.error("methodArgumentNotValidExceptionHandler", e);
		List<FieldError> fieldErrors = null;
		if (e instanceof MethodArgumentNotValidException) {
			fieldErrors = ((MethodArgumentNotValidException) e).getBindingResult().getFieldErrors();
		}
		if (e instanceof BindException) {
			fieldErrors = ((BindException) e).getBindingResult().getFieldErrors();
		}
		if (fieldErrors == null) {
			return ResponseEntity.status(HttpStatus.OK).body(Result.fail(ResultCode.METHOD_ARGUMENT_NOT_VALID, null));
		}

		List<String> defaultMessages = new ArrayList<>(fieldErrors.size());
		for (FieldError fieldError : fieldErrors) {
			defaultMessages.add(fieldError.getField() + ":" + fieldError.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(Result.fail(ResultCode.METHOD_ARGUMENT_NOT_VALID, defaultMessages));
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class })
	public ResponseEntity<Result<List<FieldError>>> methodArgumentNotValidExceptionHandler(
			HttpMessageNotReadableException e) {
		log.error("methodArgumentNotValidExceptionHandler", e);
		return ResponseEntity.status(HttpStatus.OK).body(Result.fail(ResultCode.HTTP_MESSAGE_NOT_READABLE, null));
	}

	@ExceptionHandler(NimbusException.class)
	public ResponseEntity<Result<Object>> nimbusExceptionHandler(NimbusException e) {
		log.error("nimbusExceptionHandler", e);
		ResultCode responseEnum = e.getResultCode();
		// 失败返回失败消息 + 状态码
		if (responseEnum != null) {
			return ResponseEntity.status(HttpStatus.OK).body(Result.fail(responseEnum, e.getObject()));
		}
		// 失败返回消息 状态码固定为直接显示消息的状态码
		return ResponseEntity.status(HttpStatus.OK).body(Result.showFailMsg(e.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Result<Object>> exceptionHandler(Exception e) throws TransactionException {
		log.error("exceptionHandler", e);
		log.info("RootContext.getXID(): " + RootContext.getXID());
		if (StrUtil.isNotBlank(RootContext.getXID())) {
			GlobalTransactionContext.reload(RootContext.getXID()).rollback();
		}
		return ResponseEntity.status(HttpStatus.OK).body(Result.fail(ResultCode.EXCEPTION, null));
	}

}
