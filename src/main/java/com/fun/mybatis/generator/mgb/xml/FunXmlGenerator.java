package com.fun.mybatis.generator.mgb.xml;

import com.fun.mybatis.generator.MyBatisFunGenerator.GeneratorProperties;
import com.fun.mybatis.generator.mgb.FunIntrospectedTableImpl;
import com.google.common.base.CaseFormat;
import java.util.stream.Collectors;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.XmlConstants;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.elements.AbstractXmlElementGenerator;

/**
 * @author xulujun
 * @date 2018/07/20
 */
public class FunXmlGenerator extends AbstractXmlGenerator {


  @Override
  public Document getDocument() {
    Document document = new Document(XmlConstants.MYBATIS3_MAPPER_PUBLIC_ID, XmlConstants
        .MYBATIS3_MAPPER_SYSTEM_ID);
    if (!context.getPlugins().sqlMapDocumentGenerated(document, introspectedTable)) {
      document = null;
      return document;
    }
    document.setRootElement(getRootElement());
    return document;

  }

  private XmlElement getRootElement() {
    //FullyQualifiedTable fullyQualifiedTable = introspectedTable.getFullyQualifiedTable();
    FunIntrospectedTableImpl funIntrospectedTable = (FunIntrospectedTableImpl) introspectedTable;
    XmlElement rootElement = new XmlElement("mapper");
    rootElement
        .addAttribute(new Attribute("namespace", funIntrospectedTable.getFunTableContext().getDao()
            .getType().getFullyQualifiedName()));
    context.getCommentGenerator().addRootComment(rootElement);
    addAllColumns(rootElement);
    addListQuery(rootElement);
    return rootElement;
  }

  private void addAllColumns(XmlElement parent) {
    AllColumnsGenerator allColumnsGenerator = new AllColumnsGenerator();
    allColumnsGenerator.addElements(parent);
  }

  private void addListQuery(XmlElement parent) {
    ListQueryGenerator listQueryGenerator = new ListQueryGenerator();
    listQueryGenerator.addElements(parent);
  }

  private FunIntrospectedTableImpl getFunIntrospectedTableImpl() {
    return (FunIntrospectedTableImpl) introspectedTable;
  }

  private class AllColumnsGenerator extends InnerAbstractXmlElementGenerator {

    public AllColumnsGenerator() {
      super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
      XmlElement allColumns = new XmlElement("sql");
      allColumns.addAttribute(new Attribute("id", "all-columns"));
      allColumns.addElement(new TextElement(getAllColumnsText()));
      parentElement.addElement(allColumns);
      introspectedTable.setBaseColumnListId("all-columns");
    }

    private String getAllColumnsText() {
      return introspectedTable.getAllColumns()
          .stream().map(col -> col.getActualColumnName() + " AS " + CaseFormat.LOWER_UNDERSCORE
              .to(CaseFormat.LOWER_UNDERSCORE, col.getActualColumnName()) + "\n")
          .collect(Collectors.joining("\t,"));
    }

  }

  private class ListQueryGenerator extends InnerAbstractXmlElementGenerator {

    public ListQueryGenerator() {
      super();
    }

    @Override
    public void addElements(XmlElement parentElement) {
      XmlElement listAll = new XmlElement("select");
      listAll.addAttribute(new Attribute("id", GeneratorProperties.LIST_QUERY_ID));
      listAll.addAttribute(new Attribute("parameterType",
          getFunIntrospectedTableImpl().getFunTableContext().getQuery().getType()
              .getFullyQualifiedName()));
      FullyQualifiedJavaType resultType = new FullyQualifiedJavaType(introspectedTable
          .getBaseRecordType());
      listAll.addAttribute(new Attribute("resultType", resultType.getFullyQualifiedName()));
      listAll.addElement(new TextElement("select"));
      listAll.addElement(this.getBaseColumnListElement());
      listAll.addElement(new TextElement(
          "\tfrom " + introspectedTable.getFullyQualifiedTableNameAtRuntime()
              + "\n"
              + "where 1 = 1"));
      parentElement.addElement(listAll);
    }

  }

  private abstract class InnerAbstractXmlElementGenerator extends AbstractXmlElementGenerator {

    public InnerAbstractXmlElementGenerator() {
      this.context = FunXmlGenerator.this.context;
      this.introspectedTable = FunXmlGenerator.this.introspectedTable;
      this.progressCallback = FunXmlGenerator.this.progressCallback;
      this.warnings = FunXmlGenerator.this.warnings;
    }
  }


}
