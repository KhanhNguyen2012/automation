package starter.excel;

import com.google.gson.Gson;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
  private List<Map<String, String>> data;
  private static ExcelReader excelReader;

  private ExcelReader(List<Map<String, String>> data) {
    this.data = data;
  }

  public static ExcelReader getInstance() {
    if (excelReader == null) {
      excelReader = new ExcelReader(readSheet("Primary"));
    }

    return excelReader;
  }

  public static List<Map<String, String>> readSheet(String sheetName) {
    try {
      String filePath = "src/test/resources/case-detail/CaseDetailData.xlsx";
      List<Map<String, String>> rows = new ArrayList<>();

      try (FileInputStream fis = new FileInputStream(filePath);
          Workbook workbook = new XSSFWorkbook(fis)) {

        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
          throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in " + filePath);
        }

        Iterator<Row> rowIterator = sheet.iterator();
        Row headerRow = rowIterator.next();

        List<String> headers = new ArrayList<>();
        for (Cell cell : headerRow) {
          headers.add(cell.getStringCellValue());
        }

        while (rowIterator.hasNext()) {
          Row row = rowIterator.next();
          Map<String, String> rowData = new LinkedHashMap<>();

          for (int i = 0; i < headers.size(); i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            String cellValue = getCellValue(cell);
            rowData.put(headers.get(i), cellValue);
          }
          rows.add(rowData);
        }
      }
      System.out.println(new Gson().toJson(rows));
      return rows;
    } catch (IOException e) {
      System.out.println(e.toString());
    }
    return new ArrayList<>();
  }

  private static String getCellValue(Cell cell) {
    switch (cell.getCellType()) {
      case STRING:
        return cell.getStringCellValue();
      case NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) {
          return cell.getDateCellValue().toString();
        }
        return String.valueOf(cell.getNumericCellValue());
      case BOOLEAN:
        return String.valueOf(cell.getBooleanCellValue());
      case FORMULA:
        return cell.getCellFormula();
      case BLANK:
        return "";
      default:
        return "";
    }
  }

  public List<Map<String, String>> getData() {
    return this.data;
  }
}
