package com.fun.mybatis.generator.mgb;

import com.fun.mybatis.generator.FunTableContext;
import com.fun.mybatis.generator.FunTableContext.GlobalConfigration;
import com.fun.mybatis.generator.mgb.model.DomainGenerator;
import com.fun.mybatis.generator.mgb.model.QueryGenerator;
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
    return super.calculateClientGenerators(warnings, progressCallback);
  }

  @Override
  protected void calculateXmlMapperGenerator(AbstractJavaClientGenerator javaClientGenerator,
      List<String> warnings, ProgressCallback progressCallback) {
    super.calculateXmlMapperGenerator(javaClientGenerator, warnings, progressCallback);
  }

  public String getQueryTypeName() {
    return String.format(GlobalConfigration.QUERY_TYPE_PATTERN, getTableClientName());
  }

  public String getDaoTypeName() {
    return String.format(GlobalConfigration.DAO_TYPE_PATTERN, getTableClientName());
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
