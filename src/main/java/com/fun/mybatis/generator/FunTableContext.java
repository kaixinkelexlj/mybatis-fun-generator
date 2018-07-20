package com.fun.mybatis.generator;

import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * @author xulujun
 * @date 2018/07/20
 */
public class FunTableContext {

  private String dbTableName;
  private String javaTableName;
  private Interface dao;
  private TopLevelClass domain;
  private TopLevelClass query;
  private TopLevelClass converter;

  public Interface getDao() {
    return dao;
  }

  public void setDao(Interface dao) {
    this.dao = dao;
  }

  public TopLevelClass getDomain() {
    return domain;
  }

  public void setDomain(TopLevelClass domain) {
    this.domain = domain;
  }

  public TopLevelClass getQuery() {
    return query;
  }

  public void setQuery(TopLevelClass query) {
    this.query = query;
  }

  public TopLevelClass getConverter() {
    return converter;
  }

  public void setConverter(TopLevelClass converter) {
    this.converter = converter;
  }

  public String getDbTableName() {
    return dbTableName;
  }

  public void setDbTableName(String dbTableName) {
    this.dbTableName = dbTableName;
  }

  public String getJavaTableName() {
    return javaTableName;
  }

  public void setJavaTableName(String javaTableName) {
    this.javaTableName = javaTableName;
  }
}
