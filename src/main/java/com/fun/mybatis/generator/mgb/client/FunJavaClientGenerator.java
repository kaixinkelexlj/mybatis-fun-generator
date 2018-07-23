package com.fun.mybatis.generator.mgb.client;

import com.fun.mybatis.generator.FunTableContext;
import com.fun.mybatis.generator.MyBatisFunGenerator.GeneratorProperties;
import com.fun.mybatis.generator.mgb.FunIntrospectedTableImpl;
import com.google.common.collect.Lists;
import java.util.List;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.javamapper.SimpleJavaClientGenerator;

/**
 * @author xulujun
 * @date 2018/07/20
 */
public class FunJavaClientGenerator extends SimpleJavaClientGenerator {

  public FunJavaClientGenerator() {
    super(false);
  }

  @Override
  public List<CompilationUnit> getCompilationUnits() {
    FunTableContext context = getIntrospectedTable().getFunTableContext();
    context.setDao(getDAO());
    context.setConverter(getConverter());
    return Lists.newArrayList(context.getDao(), context
        .getConverter());
  }

  @Override
  public FunIntrospectedTableImpl getIntrospectedTable() {
    return (FunIntrospectedTableImpl) introspectedTable;
  }

  private Interface getDAO() {
    /*Interface dao = new Interface(context.getJavaClientGeneratorConfiguration
        ().getTargetPackage() + ".dao." + getIntrospectedTable().getFunTableContext()
        .getJavaTableName() + "DAO");*/
    Interface dao = new Interface("dao." + getIntrospectedTable().getFunTableContext()
        .getJavaTableName() + "DAO");
    dao.setVisibility(JavaVisibility.PUBLIC);

    //add list method
    addListMethod(dao);

    addDeleteByPrimaryKeyMethod(dao);
    addInsertMethod(dao);
    addSelectByPrimaryKeyMethod(dao);
    addSelectAllMethod(dao);
    addUpdateByPrimaryKeyMethod(dao);

    context.getCommentGenerator().addJavaFileComment(dao);
    return dao;
  }

  private TopLevelClass getConverter() {
    /*TopLevelClass converter = new TopLevelClass(context.getJavaClientGeneratorConfiguration()
        .getTargetPackage() + ".common.converter." + getIntrospectedTable().getFunTableContext()
        .getDomain().getType().getShortName() + "Converter");*/
    TopLevelClass converter = new TopLevelClass(
        "converter." + getIntrospectedTable().getFunTableContext()
            .getDomain().getType().getShortName() + "Converter");
    converter.setVisibility(JavaVisibility.PUBLIC);
    context.getCommentGenerator().addJavaFileComment(converter);
    return converter;
  }

  private void addListMethod(Interface dao){
    Method method = new Method(GeneratorProperties.LIST_QUERY_ID);

    FullyQualifiedJavaType domainType = new FullyQualifiedJavaType(introspectedTable
        .getBaseRecordType());
    FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
    returnType.addTypeArgument(domainType);
    method.setReturnType(returnType);

    method.addParameter(new Parameter(getIntrospectedTable().getFunTableContext().getQuery()
        .getType(), "query"));

    dao.addMethod(method);
  }

  @Override
  public AbstractXmlGenerator getMatchedXMLGenerator() {
    throw new UnsupportedOperationException();
  }
}
