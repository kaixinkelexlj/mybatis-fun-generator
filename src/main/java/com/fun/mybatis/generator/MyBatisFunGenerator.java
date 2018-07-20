package com.fun.mybatis.generator;

import com.fun.mybatis.generator.mgb.comment.FunCommentGenerator;
import com.fun.mybatis.generator.mgb.FunIntrospectedTableImpl;
import com.google.common.base.CaseFormat;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.CommentGeneratorConfiguration;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JDBCConnectionConfiguration;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;
import org.mybatis.generator.config.JavaTypeResolverConfiguration;
import org.mybatis.generator.config.SqlMapGeneratorConfiguration;
import org.mybatis.generator.config.TableConfiguration;

/**
 * @author xulujun
 * @date 2018/07/19
 */
public class MyBatisFunGenerator {

  private static final String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";

  private AtomicInteger contextCounter = new AtomicInteger(10000);

  public void generate(String jdbcConnUrl, String userId, String password,
      String targetProject, String modelPackage, String mapperPackage,
      List<String> tableList) throws Exception {
    Context context = new Context(null);
    context.setId("context-" + contextCounter.incrementAndGet());
    context.setTargetRuntime(FunIntrospectedTableImpl.class.getName());

    JavaTypeResolverConfiguration javaTypeResolverConfiguration = new
        JavaTypeResolverConfiguration();
    javaTypeResolverConfiguration.addProperty("forceBigDecimals", "false");
    context.setJavaTypeResolverConfiguration(javaTypeResolverConfiguration);

    JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
    jdbcConnectionConfiguration.setConnectionURL(jdbcConnUrl);
    jdbcConnectionConfiguration.setDriverClass(MYSQL_DRIVER_CLASS);
    jdbcConnectionConfiguration.setUserId(userId);
    jdbcConnectionConfiguration.setPassword(password);
    context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);

    JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new
        JavaModelGeneratorConfiguration();
    javaModelGeneratorConfiguration.setTargetProject(targetProject);
    javaModelGeneratorConfiguration.setTargetPackage(modelPackage);
    context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);

    SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
    sqlMapGeneratorConfiguration.setTargetProject(targetProject);
    sqlMapGeneratorConfiguration.setTargetPackage(mapperPackage);
    context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);

    tableList.forEach(table -> buildTableConfiguration(context, table));

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

}


