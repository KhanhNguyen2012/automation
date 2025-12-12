package vn.com.fecredit.icollect.datatable.model;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

public interface IModel {
  default void initializeData(Map<Integer, String> columns, List<String> data) {
    try {
      for (Map.Entry<Integer, String> column : columns.entrySet()) {
        String fieldName = normalizeFieldName(column.getValue());
        Field field = this.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(this, data.get(column.getKey()));
      }
    } catch (NoSuchFieldException e) {
      throw new RuntimeException(e);
    } catch (IllegalAccessException e) {
      throw new RuntimeException(e);
    }
  }

  String normalizeFieldName(String aFieldName);
}
