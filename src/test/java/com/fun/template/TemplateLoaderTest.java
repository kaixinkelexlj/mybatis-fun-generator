package com.fun.template;

import java.io.StringWriter;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;

/**
 * @author xulujun
 * @date 2018/08/15
 */
public class TemplateLoaderTest {

  @Test
  public void test() throws Exception {
    VelocityEngine ve = new VelocityEngine();
    ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
    ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
    ve.init();
    Template template = ve.getTemplate("templates/sort-and-pager.vm");
    VelocityContext context = new VelocityContext();
    StringWriter writer = new StringWriter();
    template.merge(context, writer);
    System.out.println(writer.toString());
  }

  @Test
  public void testLoader() throws Exception{
    System.out.println(TemplateLoader.getInstance().vm2String("sort-and-pager.vm"));
  }

}