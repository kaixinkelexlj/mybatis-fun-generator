package com.fun.mybatis.generator.mgb.model;

import com.fun.mybatis.generator.MyBatisFunGenerator.GeneratorProperties;
import com.fun.mybatis.generator.mgb.FunIntrospectedTableImpl;
import com.fun.mybatis.generator.mgb.support.AbstractFunJavaGenerator;
import com.google.common.collect.Lists;
import java.util.List;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * @author xulujun
 * @date 2018/07/20
 */
public class QueryGenerator extends AbstractFunJavaGenerator {

  @Override
  public List<CompilationUnit> getCompilationUnits() {
    FunIntrospectedTableImpl funTable = getFunIntrospectedTableImpl();

    TopLevelClass queryClass = new TopLevelClass(funTable.getQueryTypeName());
    context.getCommentGenerator().addJavaFileComment(queryClass);
    queryClass.setVisibility(JavaVisibility.PUBLIC);
    queryClass.addImportedType(GeneratorProperties.BASE_QUERY_TYPE);
    queryClass.setSuperClass(GeneratorProperties.BASE_QUERY_TYPE);

    funTable.getFunTableContext().setQuery(queryClass);
    return Lists.newArrayList(queryClass);
  }
}
