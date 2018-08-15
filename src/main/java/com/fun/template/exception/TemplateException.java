package com.fun.template.exception;

/**
 * @author xulujun
 * @date 2018/08/15
 */
public class TemplateException extends RuntimeException{

  private static final long serialVersionUID = 6503752719906768206L;

  public TemplateException(String message) {
    super(message);
  }

  public TemplateException(String message, Throwable cause) {
    super(message, cause);
  }

  public TemplateException(Throwable cause) {
    super(cause);
  }
}
