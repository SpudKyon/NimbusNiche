package com.spud.nimbus.common.handler;

import cn.hutool.core.util.CharsetUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spud.nimbus.common.exception.NimbusException;
import com.spud.nimbus.common.response.Result;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author spud
 * @date 2024/1/28
 */
@Slf4j
public class HttpHandler {

  @Autowired
  private ObjectMapper objectMapper;

  public <T> void printServerResponseToWeb(Result<T> serverResponseEntity) {
    if (serverResponseEntity == null) {
      log.info("print obj is null");
      return;
    }

    ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
            .getRequestAttributes();
    if (requestAttributes == null) {
      log.error("requestAttributes is null, can not print to web");
      return;
    }
    HttpServletResponse response = requestAttributes.getResponse();
    if (response == null) {
      log.error("httpServletResponse is null, can not print to web");
      return;
    }
    log.error("response error: " + serverResponseEntity.getMsg());
    response.setCharacterEncoding(CharsetUtil.UTF_8);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    PrintWriter printWriter = null;
    try {
      printWriter = response.getWriter();
      printWriter.write(objectMapper.writeValueAsString(serverResponseEntity));
    } catch (IOException e) {
      throw new NimbusException("io 异常", e);
    }
  }

}
