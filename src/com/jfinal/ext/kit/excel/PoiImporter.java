package com.jfinal.ext.kit.excel;

import com.demo.web.admin.model.SysUser;
import com.google.common.collect.Lists;
import com.jfinal.ext.kit.JaxbKit;
import com.jfinal.ext.kit.Reflect;
import com.jfinal.ext.kit.excel.convert.CellConvert;
import com.jfinal.ext.kit.excel.filter.RowFilter;
import com.jfinal.ext.kit.excel.validate.CellValidate;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.DateFormatConverter.DateFormatTokenizer;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.io.File;
import java.text.DateFormat;
import java.util.List;

import javax.swing.text.DateFormatter;

public class PoiImporter {

    public static List<List<String>> readExcel(File file, Rule rule) {
        int start = rule.getStart();
        int end = rule.getEnd();
        List<List<String>> sheetList = Lists.newArrayList();
        Workbook wb;
        try {
            wb = WorkbookFactory.create(file);
        } catch (Exception e) {
            throw new ExcelException(e);
        }
        Sheet sheet = wb.getSheetAt(rule.getSheetNo());
        int rows = sheet.getLastRowNum();
        if (start <= sheet.getFirstRowNum()) {
            start = sheet.getFirstRowNum();
        }
        if (end >= rows) {
            end = rows;
        } else if (end <= 0) {
            end = rows + end;
        }
        for (int rowIndex = start; rowIndex <= end; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null)
                continue;
            List<String> columns = Lists.newArrayList();
            int cellNum = row.getLastCellNum();
            for (int cellIndex = row.getFirstCellNum(); cellIndex < cellNum; cellIndex++) {
                Cell cell = row.getCell(cellIndex);
                if (cell == null)
                    continue;
                int cellType = cell.getCellType();
                String column = "";
                switch (cellType) {
                    case Cell.CELL_TYPE_NUMERIC:
                    	if (DateUtil.isCellDateFormatted(cell)) {
                    		column =  new DataFormatter().formatCellValue(cell);
                        } else {
                            column = NumberToTextConverter.toText(cell.getNumericCellValue());  
                        }
                        break;
                    case Cell.CELL_TYPE_STRING:
                        column = cell.getStringCellValue();
                        break;
                    case Cell.CELL_TYPE_BOOLEAN:
                        column = cell.getBooleanCellValue() + "";
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                        column = cell.getCellFormula();
                        break;
                    case Cell.CELL_TYPE_ERROR:
                    	;
                    case Cell.CELL_TYPE_BLANK:
                        column = " ";
                        break;
                    default:
                }
                columns.add(column.trim());
            }

            List<Boolean> rowFilterFlagList = Lists.newArrayList();
            List<RowFilter> rowFilterList = Lists.newArrayList();
            for (int k = 0; k < rowFilterList.size(); k++) {
                RowFilter rowFilter = rowFilterList.get(k);
                rowFilterFlagList.add(rowFilter.doFilter(rowIndex, columns));
            }
            if (!rowFilterFlagList.contains(false)) {
                sheetList.add(columns);
            }
        }
        return sheetList;
    }

    public static List<List<String>> readSheet(File file, Rule Rule) {
        return readExcel(file, Rule);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List<Model<?>> processSheet(File file, Rule Rule, Class clazz) {
        List<List<String>> srcList = readSheet(file, Rule);
        List<Model<?>> results = Lists.newArrayList();
        for (int i = 0; i < srcList.size(); i++) {
            List<String> list = srcList.get(i);
            Model<?> model = fillModel(clazz, list, Rule);
            results.add(model);
        }
        return results;
    }

    public static Model<?> fillModel(Class<?> clazz, List<String> list, Rule rule) {
        Model<?> model = Reflect.on(clazz).create().get();
        String[] values = list.toArray(new String[] {});
        String message = "";
        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            Rule.Cell cell = matchCell(rule, i);
            if(cell == null) continue;
            String name = cell.getAttribute();
            String validateClassName = cell.getValidate();
            boolean valid = true;
            if (StrKit.notBlank(validateClassName)) {
                CellValidate cellValidate = Reflect.on(validateClassName).create().get();
                valid = cellValidate.validate(value);
                if (!valid) {
                    message = message + "value(" + value + ") is invalid in column " + cell.getIndex() + "</br>";
                }
            }
            if (valid) {
                Object convertedValue = value;
                String convertClassName = cell.getConvert();
                if (StrKit.notBlank(convertClassName)) {
                    CellConvert cellConvert = Reflect.on(convertClassName).get();
                    convertedValue = cellConvert.convert(value, model);
                }
                model.set(name, convertedValue);
            }
        }
        if (StrKit.notBlank(message)) {
            throw new ExcelException(message);
        }
        return model;
    }

    public static Rule.Cell matchCell(Rule rule, int index) {
        List<Rule.Cell> cells = rule.getCells();
        for (int i = 0; i < cells.size(); i++) {
            Rule.Cell cell = cells.get(i);
            if (index + 1 == cell.getIndex())
                return cell;
        }
        return null;
    }

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\思湖物业台账.xlsx");
        File xmlFile = new File(PathKit.getRootClassPath()+"\\rule2.xml");
        Rule rule = JaxbKit.unmarshal(xmlFile , Rule.class);
        System.out.println(PoiImporter.processSheet(file, rule,SysUser.class).get(0).getStr("transferBank"));
       
        
    }
}
