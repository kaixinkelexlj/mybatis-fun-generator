package com.fun.mybatis.generator.mgb.support;

import com.fun.mybatis.generator.mgb.FunIntrospectedTableImpl;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

/**
 * @author xulujun
 * @date 2018/07/20
 */
public abstract class AbstractFunJavaGenerator extends AbstractJavaGenerator {

  public FunIntrospectedTableImpl getFunIntrospectedTableImpl() {
    if (!(introspectedTable instanceof FunIntrospectedTableImpl)) {
      throw new UnsupportedOperationException("FunIntrospectedTableImpl expected");
    }
    return (FunIntrospectedTableImpl) introspectedTable;
  }

}
