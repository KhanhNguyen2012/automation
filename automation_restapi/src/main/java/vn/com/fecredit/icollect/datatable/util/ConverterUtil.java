package vn.com.fecredit.icollect.datatable.util;

import io.cucumber.datatable.DataTable;
import java.util.*;
import java.util.stream.Collectors;
import vn.com.fecredit.icollect.datatable.converter.Converter;

public class ConverterUtil {
  public static <T> List<T> convert(Class<T> tClass, List<Map<String, String>> data) {
    Set<String> uniqueColumns =
        data.stream().flatMap(map -> map.keySet().stream()).collect(Collectors.toSet());

    List<String> columns = new ArrayList<>(uniqueColumns);
    List<List<String>> dataTableValue = new ArrayList<>();
    for (Map<String, String> row : data) {
      uniqueColumns.addAll(row.keySet());
      List<String> objValues = new ArrayList<>();
      for (String columnName : columns) {
        objValues.add(ReplaceName.replaceNameWithDate(Objects.toString(row.get(columnName), "")));
      }
      dataTableValue.add(objValues);
    }

    Map<Integer, String> mapColumns = new HashMap<>();
    for (int i = 0; i < columns.size(); i++) {
      mapColumns.put(i, columns.get(i));
    }
    DataTable dataTable = DataTable.create(dataTableValue, Converter.getInstance(mapColumns));
    return dataTable.asList(tClass);
  }
}
