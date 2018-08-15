package com.fun.template;

import com.fun.template.exception.TemplateException;
import java.io.StringWriter;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * @author xulujun
 * @date 2018/08/15
 */
public enum TemplateLoader {

  INST;

  private static final String DEFAULT_CAHRSET = "utf-8";
  private static final String TEMPLATE_PATH_PREFIX = "templates/";
  private VelocityEngine ve;

  TemplateLoader() {
    ve = new VelocityEngine();
    ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
    ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
    ve.init();
  }

  public static TemplateLoader getInstance() {
    return INST;
  }

  public String vm2String(String path) throws TemplateException {
    return vm2String(path, null);
  }

  public String vm2String(String path, Map<String, Object> params) throws TemplateException {
    if (StringUtils.isBlank(path)) {
      throw new TemplateException("invalid path");
    }
    try {
      if (!path.startsWith(TEMPLATE_PATH_PREFIX)) {
        path = TEMPLATE_PATH_PREFIX + path;
      }
      Template template = ve.getTemplate(path, DEFAULT_CAHRSET);
      VelocityContext context = new VelocityContext();
      if (params != null && !params.isEmpty()) {
        params.entrySet().stream().forEach(entry -> context.put(entry.getKey(), entry.getValue()));
      }
      StringWriter writer = new StringWriter();
      template.merge(context, writer);
      return writer.toString();
    } catch (Exception ex) {
      throw new TemplateException(ex);
    }
  }

  public Object readSolve() {
    return INST;
  }

}
