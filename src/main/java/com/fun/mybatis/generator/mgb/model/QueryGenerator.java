package com.fun.mybatis.generator.mgb.model;

import com.fun.mybatis.generator.FunTableContext;
import com.fun.mybatis.generator.mgb.FunIntrospectedTableImpl;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

/**
 * @author xulujun
 * @date 2018/07/20
 */
public class QueryGenerator extends AbstractJavaGenerator {

  @Override
  public List<CompilationUnit> getCompilationUnits() {
    if (!(introspectedTable instanceof FunIntrospectedTableImpl)) {
      throw new UnsupportedOperationException("FunIntrospectedTableImpl expected");
    }
    FunTableContext funTableContext = ((FunIntrospectedTableImpl) introspectedTable)
        .getFunTableContext();

    TopLevelClass queryClass = new TopLevelClass(String.format());


    funTableContext.setQuery(queryClass);
    List<CompilationUnit> list = new ArrayList<>();
    list.add(queryClass);
    return list;
  }
}
