package com.fun.mybatis.generator.mgb;

import com.fun.mybatis.generator.FunTableContext;
import com.fun.mybatis.generator.MyBatisFunGenerator.GeneratorProperties;
import com.fun.mybatis.generator.mgb.client.FunJavaClientGenerator;
import com.fun.mybatis.generator.mgb.model.DomainGenerator;
import com.fun.mybatis.generator.mgb.model.QueryGenerator;
import com.fun.mybatis.generator.mgb.xml.FunXmlGenerator;
import com.google.common.base.CaseFormat;
import java.util.List;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.mybatis3.IntrospectedTableMyBatis3Impl;

/**
 * @author xulujun
 * @date 2018/07/19
 */
public class FunIntrospectedTableImpl extends IntrospectedTableMyBatis3Impl {

  private FunTableContext funTableContext = new FunTableContext();

  public FunIntrospectedTableImpl() {
    super();
  }

  @Override
  public void initialize() {
    super.initialize();
    funTableContext.setDbTableName(getFullyQualifiedTableNameAtRuntime());
    funTableContext.setJavaTableName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,
        getFullyQualifiedTableNameAtRuntime()));
  }

  @Override
  protected void calculateJavaModelGenerators(List<String> warnings,
      ProgressCallback progressCallback) {
    QueryGenerator queryGenerator = new QueryGenerator();
    initializeAbstractGenerator(queryGenerator, warnings, progressCallback);
    javaModelGenerators.add(queryGenerator);

    DomainGenerator domainGenerator = new DomainGenerator();
    initializeAbstractGenerator(domainGenerator, warnings, progressCallback);
    javaModelGenerators.add(domainGenerator);
  }

  @Override
  protected AbstractJavaClientGenerator calculateClientGenerators(List<String> warnings,
      ProgressCallback progressCallback) {
    if (!rules.generateJavaClient()) {
      return null;
    }
    FunJavaClientGenerator javaClientGenerator = new FunJavaClientGenerator();
    initializeAbstractGenerator(javaClientGenerator, warnings, progressCallback);
    clientGenerators.add(javaClientGenerator);
    return javaClientGenerator;
  }

  @Override
  protected void calculateXmlMapperGenerator(AbstractJavaClientGenerator javaClientGenerator,
      List<String> warnings, ProgressCallback progressCallback) {
    xmlMapperGenerator = new FunXmlGenerator();
    initializeAbstractGenerator(xmlMapperGenerator, warnings, progressCallback);
  }

  public String getQueryTypeName() {
    return String.format(GeneratorProperties.QUERY_TYPE_PATTERN, getTableClientName());
  }

  public String getDaoTypeName() {
    return String.format(GeneratorProperties.DAO_TYPE_PATTERN, getTableClientName());
  }

  public String getTableClientName() {
    return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,
        getFullyQualifiedTableNameAtRuntime());
  }

  public FunTableContext getFunTableContext() {
    return funTableContext;
  }

  public void setFunTableContext(FunTableContext funTableContext) {
    this.funTableContext = funTableContext;
  }
}
