package com.curves.tool.services.manager.service.impl.file;

import com.curves.framework.commons.utils.FreemarkerUtils;
import com.curves.tool.commons.CFileHelper;
import com.curves.tool.services.dto.DataBaseDTO;
import com.curves.tool.services.dto.DuridDTO;
import com.curves.tool.services.dto.ProjectDTO;
import com.curves.tool.services.entity.TableDO;
import com.curves.tool.services.manager.GeneratorManager;
import com.curves.tool.services.manager.service.IGeneratorDisplay;
import com.curves.tool.services.manager.service.IGeneratorObserver;

import java.util.HashMap;
import java.util.Map;

public class XmlManager implements IGeneratorObserver, IGeneratorDisplay {

    private GeneratorManager generatorManager;

    public XmlManager(GeneratorManager generatorManager) {
        this.generatorManager = generatorManager;
        generatorManager.registerObserver(this);
    }

    private TableDO tableDO;
    private ProjectDTO projectDTO;
    private DuridDTO duridDTO;
    private DataBaseDTO dataBaseDTO;
    private String filePath;

    @Override
    public void display() {
        try {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("project", projectDTO);
            dataMap.put("table", tableDO);
            dataMap.put("columns", columns());
            dataMap.put("insert", insert());
            dataMap.put("selectByPrimaryKey", selectByPrimaryKey());
            dataMap.put("deleteByPrimaryKey", deleteByPrimaryKey());
            dataMap.put("deleteByPrimaryKeys", deleteByPrimaryKeys());
            dataMap.put("updateByPrimaryKey", updateByPrimaryKey());

            FreemarkerUtils.generateFile("xml.flt", CFileHelper.getCodeFile(filePath, tableDO.getClassName(), "Mapper.xml"), dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化目录结构
     */
    @Override
    public void init() {
        filePath = CFileHelper.getMyBatisMapperPath(projectDTO);
        CFileHelper.create(filePath);
    }

    /**
     * 数据封装
     */
    @Override
    public void generator(DataBaseDTO dataBaseDTO, TableDO tableDO, ProjectDTO projectDTO, DuridDTO duridDTO) {
        this.dataBaseDTO = dataBaseDTO;
        this.tableDO = tableDO;
        this.projectDTO = projectDTO;
        this.duridDTO = duridDTO;
        init();
        display();
    }

    private String columns() {
        StringBuilder builder = new StringBuilder();
        this.tableDO.getColumns().forEach(columnDO -> {
            builder.append(" ");
            builder.append(columnDO.getColumnsName());
            builder.append(",");
        });
        return builder.toString().substring(0, builder.length() - 1);
    }

    private String insert() {
        StringBuilder builder = new StringBuilder();

        StringBuilder parame = new StringBuilder();
        StringBuilder values = new StringBuilder();

        this.tableDO.getColumns().forEach(columnDO -> {
            parame.append("            <if test=\"").append(columnDO.getAttributeName()).append(" != null\">");
            parame.append("\n");
            parame.append("              ").append(columnDO.getColumnsName());
            parame.append("\n");
            parame.append("            </if>");
            parame.append("\n");



            values.append("            <if test=\"").append(columnDO.getAttributeName()).append(" != null\">");
            values.append("\n");
            if (columnDO.getColumnKey().equals("PRI")) {
                values.append("              UUID(),");
            } else {
                values.append("              #{").append(columnDO.getAttributeName()).append("},");
            }
            values.append("\n");
            values.append("            </if>");
            values.append("\n");
        });





        builder.append("insert into ").append(this.tableDO.getTableName());
        builder.append("\n");
        builder.append("        <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        builder.append("\n");
        builder.append(parame.toString());
        builder.append("        </trim>");
        builder.append("\n");
        builder.append("        <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\">");
        builder.append("\n");
        builder.append(values.toString());
        builder.append("\n");
        builder.append("        </trim>");





        return builder.toString();
    }

    private String selectByPrimaryKey() {
        StringBuilder builder = new StringBuilder();
        builder.append("select <include refid=\"Base_Column_List\" /> from ").append(tableDO.getTableName()).append(" where uuid = #{uuid}");
        return builder.toString();
    }

    private String deleteByPrimaryKey() {
        StringBuilder builder = new StringBuilder();
        builder.append("delete from ").append(tableDO.getTableName()).append(" where uuid = #{uuid}");
        return builder.toString();
    }

    private String deleteByPrimaryKeys() {
        StringBuilder builder = new StringBuilder();
        builder.append("delete from ").append(tableDO.getTableName());
        builder.append("\n");
        builder.append("        <where> uuid in");
        builder.append("\n");
        builder.append("            <foreach collection=\"array\" item=\"arr\" index=\"no\" open=\"(\" separator=\",\" close=\")\">");
        builder.append("\n");
        builder.append("                #{arr}");
        builder.append("\n");
        builder.append("            </foreach>");
        builder.append("\n");
        builder.append("        </where>");
        return builder.toString();
    }

    public String updateByPrimaryKey() {
        StringBuilder builder = new StringBuilder();
        builder.append("update ").append(tableDO.getTableName()).append(" ");
        builder.append("\n");
        builder.append("        <set>");
        tableDO.getColumns().forEach(columnDO -> {
            builder.append("\n");
            builder.append("            <if test=\""+columnDO.getAttributeName()+" != null\">");
            builder.append("\n");
            builder.append("                "+columnDO.getColumnsName()+" = #{"+columnDO.getAttributeName()+"},");
            builder.append("\n");
            builder.append("            </if>");
        });
        builder.append("\n");
        builder.append("        </set>");
        builder.append("\n");
        builder.append("        where uuid = #{uuid}");
        return builder.toString();
    }
}