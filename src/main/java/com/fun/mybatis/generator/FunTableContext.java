package com.fun.mybatis.generator;

import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * @author xulujun
 * @date 2018/07/20
 */
public class FunTableContext {

  public static interface GlobalConfigration {

    String QUERY_TYPE_PATTERN = "query.%sQuery";
    String DAO_TYPE_PATTERN = "dao.%sDAO";
  }

  private String tableName;
  private Interface dao;
  private TopLevelClass daoTest;
  private TopLevelClass domain;
  private TopLevelClass query;
  private TopLevelClass converter;

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public Interface getDao() {
    return dao;
  }

  public void setDao(Interface dao) {
    this.dao = dao;
  }

  public TopLevelClass getDaoTest() {
    return daoTest;
  }

  public void setDaoTest(TopLevelClass daoTest) {
    this.daoTest = daoTest;
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
}
