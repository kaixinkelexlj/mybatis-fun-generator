package com.fun.mybatis.generator;

import com.fun.mybatis.generator.mgb.FunIntrospectedTableImpl;
import com.fun.mybatis.generator.mgb.client.FunJavaClientGenerator;
import com.fun.mybatis.generator.mgb.comment.FunCommentGenerator;
import com.google.common.base.CaseFormat;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaClientGeneratorConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;

/**
 * @author xulujun
 * @date 2018/07/19
 */
public class MyBatisFunGenerator {

  private AtomicInteger contextCounter = new AtomicInteger(10000);

  private DBProperties dbProperties;
  private GeneratorProperties generatorProperties;

  public MyBatisFunGenerator(DBProperties dbProperties,
      GeneratorProperties generatorProperties) {
    this.dbProperties = dbProperties;
    this.generatorProperties = generatorProperties;
  }

  public void generate(
      String... tableList) throws Exception {
    Context context = new Context(null);
    context.setId("context-" + contextCounter.incrementAndGet());
    context.setTargetRuntime(FunIntrospectedTableImpl.class.getName());

    JavaTypeResolverConfiguration javaTypeResolverConfiguration = new
        JavaTypeResolverConfiguration();
    javaTypeResolverConfiguration.addProperty("forceBigDecimals", "false");
    context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);

    JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
    jdbcConnectionConfiguration.setConnectionURL(dbProperties.getJdbcConnectUrl());
    jdbcConnectionConfiguration.setDriverClass(DBProperties.MYSQL_DRIVER_CLASS);
    jdbcConnectionConfiguration.setUserId(dbProperties.getUserName());
    jdbcConnectionConfiguration.setPassword(dbProperties.getPassword());
    context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

    JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new
        JavaModelGeneratorConfiguration();
    javaModelGeneratorConfiguration
        .setTargetProject(generatorProperties.getTargetProjectOrDefault());
    javaModelGeneratorConfiguration
        .setTargetPackage(generatorProperties.getModelPackageOrDefault());
    context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

    JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new
        JavaClientGeneratorConfiguration();
    javaClientGeneratorConfiguration
        .setTargetProject(generatorProperties.getTargetProjectOrDefault());
    javaClientGeneratorConfiguration
        .setTargetPackage(generatorProperties.getJavaClientPackageOrDefault());
    javaClientGeneratorConfiguration.setConfigurationType(FunJavaClientGenerator.class.getName());
    context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);

    SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
    sqlMapGeneratorConfiguration.setTargetProject(generatorProperties.getTargetProjectOrDefault());
    sqlMapGeneratorConfiguration.setTargetPackage(generatorProperties.getTargetProjectOrDefault());
    context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

    Stream.of(tableList).forEach(table -> buildTableConfiguration(context, table));

    CommentGeneratorConfiguration commentGeneratorConfiguration = new
        CommentGeneratorConfiguration();
    commentGeneratorConfiguration
        .setConfigurationType(FunCommentGenerator.class.getName());
    context.setCommentGeneratorConfiguration(commentGeneratorConfiguration);

    Configuration configuration = new Configuration();
    configuration.addContext(context);

    new MyBatisGenerator(configuration, null, null).generate(null);
  }


  private TableConfiguration buildTableConfiguration(Context context, String tableName) {
    TableConfiguration tableConfiguration = new TableConfiguration(context);
    context.addTableConfiguration(tableConfiguration);
    tableConfiguration.setTableName(tableName);
    tableConfiguration.setCountByExampleStatementEnabled(true);
    tableConfiguration.setUpdateByExampleStatementEnabled(true);
    tableConfiguration.setSelectByExampleStatementEnabled(true);
    tableConfiguration.setSelectByPrimaryKeyStatementEnabled(true);
    tableConfiguration.setDeleteByExampleStatementEnabled(true);
    tableConfiguration.setDeleteByPrimaryKeyStatementEnabled(true);
    tableConfiguration.setDomainObjectName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL,
        tableName + "DO"));
    tableConfiguration.setMapperName("mapper_" + tableName);
    return tableConfiguration;
  }

  public static class DBProperties {

    public static final String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private String jdbcConnectUrl;
    private String userName;
    private String password;

    public DBProperties() {
    }

    public DBProperties(String jdbcConnectUrl, String userName, String password) {
      this.jdbcConnectUrl = jdbcConnectUrl;
      this.userName = userName;
      this.password = password;
    }

    public String getJdbcConnectUrl() {
      return jdbcConnectUrl;
    }

    public void setJdbcConnectUrl(String jdbcConnectUrl) {
      this.jdbcConnectUrl = jdbcConnectUrl;
    }

    public String getUserName() {
      return userName;
    }

    public void setUserName(String userName) {
      this.userName = userName;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }

  public static class GeneratorProperties {

    public static final String QUERY_TYPE_PATTERN = "query.%sQuery";
    public static final String DAO_TYPE_PATTERN = "dao.%sDAO";
    public static final String BASE_QUERY_TYPE = "com.didichuxing.dps.common.support.dao.BaseQuery";
    public static final String LIST_QUERY_ID = "list";
    public static final String DEFAULT_MODEL_PACKAGE = "domain";
    public static final String DEFAULT_MAPPER_PACKAGE = "mapper";
    public static final String DEFAULT_TRAGET_PROJECT = "target";
    public static final String DEFAULT_JAVA_CLIENT_PACKAGE = "client";

    private String modelPackage;
    private String javaClientPackage;
    private String mapperPackage;
    private String targetProject;

    public String getModelPackage() {
      return modelPackage;
    }

    public String getModelPackageOrDefault() {
      return Optional.ofNullable(modelPackage).orElse(DEFAULT_MODEL_PACKAGE);
    }

    public void setModelPackage(String modelPackage) {
      this.modelPackage = modelPackage;
    }

    public String getMapperPackage() {
      return mapperPackage;
    }

    public String getMapperPackageOrDefault() {
      return Optional.ofNullable(mapperPackage).orElse(DEFAULT_MAPPER_PACKAGE);
    }

    public void setMapperPackage(String mapperPackage) {
      this.mapperPackage = mapperPackage;
    }

    public String getTargetProject() {
      return targetProject;
    }

    public String getTargetProjectOrDefault() {
      return Optional.ofNullable(targetProject).orElse(DEFAULT_TRAGET_PROJECT);
    }

    public void setTargetProject(String targetProject) {
      this.targetProject = targetProject;
    }

    public String getJavaClientPackage() {
      return javaClientPackage;
    }

    public String getJavaClientPackageOrDefault() {
      return Optional.ofNullable(javaClientPackage).orElse(DEFAULT_JAVA_CLIENT_PACKAGE);
    }

    public void setJavaClientPackage(String javaClientPackage) {
      this.javaClientPackage = javaClientPackage;
    }
  }

}


