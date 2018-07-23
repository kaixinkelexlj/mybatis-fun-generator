package com.fun.mybatis.generator.mgb.comment;

import java.util.Properties;
import org.apache.commons.lang.StringUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * @author xulujun
 * @date 2018/07/19
 */
public class FunCommentGenerator implements CommentGenerator {


  @Override
  public void addConfigurationProperties(Properties properties) {

  }

  @Override
  public void addFieldComment(Field field, IntrospectedTable introspectedTable,
      IntrospectedColumn introspectedColumn) {
    String remarks = introspectedColumn.getRemarks();
    field.addJavaDocLine("/**");
    if (StringUtils.isNotBlank(remarks)) {
      field.addJavaDocLine(" * " + remarks);
    }
    field.addJavaDocLine(
        " * from " + introspectedTable.getFullyQualifiedTable() + "." + introspectedColumn
            .getActualColumnName());
    field.addJavaDocLine(" */");
  }

  @Override
  public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

  }

  @Override
  public void addModelClassComment(TopLevelClass topLevelClass,
      IntrospectedTable introspectedTable) {

  }

  @Override
  public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

  }

  @Override
  public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable,
      boolean markAsDoNotDelete) {

  }

  @Override
  public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

  }

  @Override
  public void addGetterComment(Method method, IntrospectedTable introspectedTable,
      IntrospectedColumn introspectedColumn) {

  }

  @Override
  public void addSetterComment(Method method, IntrospectedTable introspectedTable,
      IntrospectedColumn introspectedColumn) {

  }

  @Override
  public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

  }

  @Override
  public void addJavaFileComment(CompilationUnit compilationUnit) {

  }

  @Override
  public void addComment(XmlElement xmlElement) {

  }

  @Override
  public void addRootComment(XmlElement rootElement) {

  }
}
