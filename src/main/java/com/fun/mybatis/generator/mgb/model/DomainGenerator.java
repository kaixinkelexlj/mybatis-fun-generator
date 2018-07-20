package com.fun.mybatis.generator.mgb.model;

import com.fun.mybatis.generator.FunTableContext;
import com.fun.mybatis.generator.mgb.FunIntrospectedTableImpl;
import java.util.List;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.mybatis3.model.BaseRecordGenerator;

/**
 * @author xulujun
 * @date 2018/07/20
 */
public class DomainGenerator extends BaseRecordGenerator {

  @Override
  public List<CompilationUnit> getCompilationUnits() {
    if (!(introspectedTable instanceof FunIntrospectedTableImpl)) {
      throw new UnsupportedOperationException("FunIntrospectedTableImpl expected");
    }
    FunTableContext funTableContext = ((FunIntrospectedTableImpl) introspectedTable)
        .getFunTableContext();
    funTableContext.setDomain((TopLevelClass) super.getCompilationUnits().get(0));
    return super.getCompilationUnits();
  }
}
