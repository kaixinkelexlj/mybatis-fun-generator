package com.fun.mybatis.generator;

import com.fun.mybatis.generator.MyBatisFunGenerator.DBProperties;
import com.fun.mybatis.generator.MyBatisFunGenerator.GeneratorProperties;
import org.junit.Before;
import org.junit.Test;

/**
 * @author xulujun
 * @date 2018/07/20
 */
public class MyBatisFunGeneratorTest {

  private MyBatisFunGenerator myBatisFunGenerator;

  @Before
  public void setUp() {
    DBProperties dbProperties = new DBProperties();
    dbProperties.setUserName("root");
    dbProperties.setPassword("root");
    dbProperties.setJdbcConnectUrl("jdbc:mysql://localhost:3306/ifun?autoReconnect=true"
        + "&useUnicode=true&characterEncoding=UTF8");

    GeneratorProperties generatorProperties = new GeneratorProperties();

    myBatisFunGenerator = new MyBatisFunGenerator(dbProperties, generatorProperties);
  }

  @Test
  public void test() throws Exception {
    myBatisFunGenerator.generate("tb_user");
  }

}