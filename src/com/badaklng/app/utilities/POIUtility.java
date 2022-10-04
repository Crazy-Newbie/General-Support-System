package com.badaklng.app.utilities;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class POIUtility {

	// Excel Style Definitions Routines
	public static CellStyle getTitleStyle(XSSFWorkbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 14);
//        style.setAlignment(HorizontalAlignment.CENTER);
		style.setAlignment(CellStyle.VERTICAL_CENTER);
		style.setFont(font);
		return style;
	}

	public static CellStyle getTableHeaderStyle(XSSFWorkbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setBold(true);
//        style.setBorderTop(BorderStyle.MEDIUM);
//        style.setBorderBottom(BorderStyle.MEDIUM);
//        style.setBorderLeft(BorderStyle.THIN);
//        style.setBorderRight(BorderStyle.THIN);
//        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setBorderTop(CellStyle.BORDER_MEDIUM);
		style.setBorderBottom(CellStyle.BORDER_MEDIUM);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(IndexedColors.LEMON_CHIFFON.getIndex());
		style.setWrapText(false);
		style.setFont(font);
		return style;
	}

	public static CellStyle getThinBorderStyle(XSSFWorkbook wb) {
		CellStyle style = wb.createCellStyle();
//		style.setBorderTop(BorderStyle.THIN);
//		style.setBorderBottom(BorderStyle.THIN);
//		style.setBorderLeft(BorderStyle.THIN);
//		style.setBorderRight(BorderStyle.THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		return style;
	}

	public static CellStyle getLabelStyle(XSSFWorkbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setBold(true);
		style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
//		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		style.setWrapText(false);
		style.setFont(font);
		return style;
	}

	public static CellStyle toBoldStyle(XSSFWorkbook wb, CellStyle cs) {
		Font bold = wb.createFont();
		bold.setBold(true);
		cs.setFont(bold);
		return cs;
	}

	public static CellStyle toNumberStyle(XSSFWorkbook wb, CellStyle cs) {
		cs.setDataFormat(wb.createDataFormat().getFormat("###,###,###,##0"));
		return cs;
	}

	public static void removeEmptyRow(Sheet sh) {
		int lastindex = sh.getLastRowNum();

		for (int i = 0; i < lastindex; i++) {
			if (sh.getRow(i).getCell(0) == null && sh.getRow(i).getCell(1) == null) {
				sh.shiftRows(i + 1, lastindex, -1);
				lastindex -= 1;
			}
		}
	}

}
