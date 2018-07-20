package com.fun.mybatis.generator.mgb.client;

import com.fun.mybatis.generator.FunTableContext;
import com.fun.mybatis.generator.mgb.FunIntrospectedTableImpl;
import com.google.common.collect.Lists;
import java.util.List;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;

/**
 * @author xulujun
 * @date 2018/07/20
 */
public class FunJavaClientGenerator extends AbstractJavaClientGenerator {

  public FunJavaClientGenerator() {
    super(false);
  }

  @Override
  public List<CompilationUnit> getCompilationUnits() {
    FunIntrospectedTableImpl funIntrospectedTable = (FunIntrospectedTableImpl) introspectedTable;
    FunTableContext context = funIntrospectedTable.getFunTableContext();
    context.setDao(getDAO());
    context.setConverter(getConverter());
    return Lists.newArrayList(context.getDomain(), context.getQuery(), context.getDao(), context
        .getConverter());
  }

  private Interface getDAO() {
    return null;
  }

  private TopLevelClass getConverter() {
    return null;
  }

  @Override
  public AbstractXmlGenerator getMatchedXMLGenerator() {
    throw new UnsupportedOperationException();
  }
}
