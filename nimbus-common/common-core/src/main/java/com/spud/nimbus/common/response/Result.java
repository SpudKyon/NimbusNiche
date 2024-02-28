package com.spud.nimbus.common.response;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author spud
 * @since 2021/1/23
 */
@Slf4j
@Data
@ToString
public class Result<T> implements Serializable {

	/**
	 * 状态码
	 */
	private String code;

	/**
	 * 信息
	 */
	private String msg;

	/**
	 * 数据
	 */
	private T data;

	public boolean isSuccess() {
		return Objects.equals(ResultCode.OK.value(), this.code);
	}

	public static <T> Result<T> success(T data) {
		Result<T> result = new Result<>();
		result.setData(data);
		result.setCode(ResultCode.OK.value());
		if (data != null) {
			result.setData(data);
		}
		return result;
	}

	/**
	 * 前端显示失败消息
	 * @param msg 失败消息
	 * @return Result
	 */
	public static <T> Result<T> showFailMsg(String msg) {
		log.error(msg);
		Result<T> result = new Result<>();
		result.setMsg(msg);
		result.setCode(ResultCode.SHOW_FAIL.value());
		return result;
	}

	public static <T> Result<T> fail(ResultCode code, T data) {
		log.error(code.toString());
		Result<T> result = new Result<>();
		result.setMsg(code.getMsg());
		result.setCode(code.value());
		if (data != null) {
			result.setData(data);
		}
		return result;
	}

	/**
	 * 转换
	 * @param oldResult 旧的结果
	 * @return Result
	 */
	public static <T> Result<T> transform(Result<?> oldResult) {
		Result<T> result = new Result<>();
		result.setMsg(oldResult.getMsg());
		result.setCode(oldResult.getCode());
		log.error(result.toString());
		return result;
	}

}
