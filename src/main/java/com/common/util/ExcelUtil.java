package com.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 导出Excel文档工具类 [@author](http://my.oschina.net/arthor) 那位先生
 * [@date](http://my.oschina.net/u/2504391) 2014-8-6
 */
public class ExcelUtil {

	/**
	 * 创建excel文档， [@param](http://my.oschina.net/u/2303379) list 数据
	 * 
	 * @param keys
	 *            list中map的key数组集合
	 * @param columnNames
	 *            excel的列名
	 */
	public static Workbook createWorkBook(Workbook wb, String sheetName, List<Map<String, Object>> list, String[] keys,
			String columnNames[]) {
		// 创建excel工作簿
		if (wb == null) {
			wb = new HSSFWorkbook();
		}
		// Workbook wb = new HSSFWorkbook();
		// 创建第一个sheet（页），并命名
		Sheet sheet = wb.createSheet(StringUtils.defaultIfBlank(sheetName, "sheet" + UUID.randomUUID()));
		// 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
		for (int i = 0; i < keys.length; i++) {
			sheet.setColumnWidth((short) i, (short) (35.7 * 150));
		}

		refactorSheetFirstRow(wb, columnNames, sheet);

		Font f2 = wb.createFont();
		// 创建第二种字体样式（用于值）
		f2.setFontHeightInPoints((short) 10);
		f2.setColor(IndexedColors.BLACK.getIndex());
		CellStyle cs2 = wb.createCellStyle();
		// 设置第二种单元格的样式（用于值）
		cs2.setFont(f2);
		cs2.setBorderLeft(CellStyle.BORDER_THIN);
		cs2.setBorderRight(CellStyle.BORDER_THIN);
		cs2.setBorderTop(CellStyle.BORDER_THIN);
		cs2.setBorderBottom(CellStyle.BORDER_THIN);
		cs2.setAlignment(CellStyle.ALIGN_CENTER);
		
		// 设置每行每列的值
		int sheetCount = 1;
		int resetRowCount = 0;
		for (int i = 0; i < list.size(); i++) {
			//System.out.println(">>>"+i);			
			if(i!=0 && i% 65534 == 0){
				//wb.createSheet(sheetName, "sheet"+sheetCount+"-" + UUID.randomUUID());
				sheetCount++;
				//System.out.println(wb.getNumberOfSheets());
				//System.out.println(sheet.getLastRowNum());
				Sheet sheetMore = wb.createSheet(StringUtils.defaultIfBlank(sheetCount+"-"+sheetName, "sheet"+sheetCount+"-" + UUID.randomUUID()));
				refactorSheetFirstRow(wb, columnNames, sheetMore);
				//System.out.println(wb.getNumberOfSheets());
				sheet = sheetMore;
				System.out.println(sheet.getLastRowNum());
				resetRowCount=0;
			}
			
			// Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
			// 创建一行，在页sheet上
			Row row1 = sheet.createRow(resetRowCount + 1);
			// 在row行上创建一个方格
			for (short j = 0; j < keys.length; j++) {
				Cell cell = row1.createCell(j);
				Map<String, Object> map = list.get(i);
				boolean tag = false;
				for (Entry<String, Object> entry : map.entrySet()) {
					// System.out.println("key= " + entry.getKey() + " and
					// value= " + entry.getValue());
					if (StringUtils.equals(keys[j], StringUtils.trim(entry.getKey()))) {
						cell.setCellValue(StringUtils.isEmpty(String.valueOf(entry.getValue()))
								|| String.valueOf(entry.getValue()) == "null" ? " " : String.valueOf(entry.getValue()));
						tag = true;
					}
				}
				if (!tag) {
					cell.setCellValue(" ");
				}

				// cell.setCellValue(list.get(i).get(keys[j]) == null?" ":
				// list.get(i).get(keys[j]).toString());
				cell.setCellStyle(cs2);
			}
			resetRowCount++;
			
		}
		return wb;
	}

	private static void refactorSheetFirstRow(Workbook wb, String[] columnNames, Sheet sheet) {
		// 创建第一行
		Row row = sheet.createRow((short) 0);
		// 创建两种单元格格式
		CellStyle cs = wb.createCellStyle();
		// 创建两种字体
		Font f = wb.createFont();
		// 创建第一种字体样式（用于列名）
		f.setFontHeightInPoints((short) 10);
		f.setColor(IndexedColors.BLACK.getIndex());
		f.setBoldweight(Font.BOLDWEIGHT_BOLD);
		// 设置第一种单元格的样式（用于列名）
		cs.setFont(f);
		cs.setBorderLeft(CellStyle.BORDER_THIN);
		cs.setBorderRight(CellStyle.BORDER_THIN);
		cs.setBorderTop(CellStyle.BORDER_THIN);
		cs.setBorderBottom(CellStyle.BORDER_THIN);
		cs.setAlignment(CellStyle.ALIGN_CENTER);
		// 设置列名
		for (int i = 0; i < columnNames.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(columnNames[i]);
			cell.setCellStyle(cs);
		}
	}

	public static List<Workbook> createWorkBooks(List<Map<String, Object>> list) {

		List<Workbook> resultList = new ArrayList<Workbook>();
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		Workbook wb = null;
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
	
			String sheetName = (String) map.get("sheetName");
			String[] keys = (String[]) map.get("keys");
			String[] columnNames = (String[]) map.get("columnNames");
			List<Map<String, Object>> dataList = (List<Map<String, Object>>) map.get("data");
			if (dataList == null) {
				continue;
			}
			wb = createWorkBook(wb, sheetName, dataList, keys, columnNames);
			resultList.add(wb);
		}

		System.out.println(resultList.size());
		return resultList;

	}

	public static void refactorExcel(HttpServletResponse response, List<Map<String, Object>> resultList,
			String fileName) throws UnsupportedEncodingException, IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			List<Workbook> list = ExcelUtil.createWorkBooks(resultList);
			if (CollectionUtils.isEmpty(list)) {
				return;
			}
			for (int i = 0; i < list.size(); i++) {
				list.get(i).write(os);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String((fileName + ".xls").getBytes(), "iso-8859-1"));
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		ServletOutputStream out = response.getOutputStream();
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
			response.flushBuffer();
		}
	}

}