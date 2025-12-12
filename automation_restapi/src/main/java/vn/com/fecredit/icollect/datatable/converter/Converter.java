package vn.com.fecredit.icollect.datatable.converter;

import io.cucumber.datatable.DataTable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import vn.com.fecredit.icollect.datatable.model.IModel;

public class Converter implements DataTable.TableConverter {
  private static Converter converter;
  private Map<Integer, String> mapColumnIndex;

  private Converter(Map<Integer, String> mapColumnIndex) {
    this.mapColumnIndex = mapColumnIndex;
  }

  public static Converter getInstance(Map<Integer, String> mapColumnIndex) {
    //    if (converter == null) {
    converter = new Converter(mapColumnIndex);
    //    }

    return converter;
  }

  @Override
  public <T> T convert(DataTable dataTable, Type type) {
    return null;
  }

  @Override
  public <T> T convert(DataTable dataTable, Type type, boolean b) {
    return null;
  }

  @Override
  public <T> List<T> toList(DataTable dataTable, Type type) {
    if (IModel.class.isAssignableFrom((Class<T>) type)) {
      List<T> listT = new ArrayList<T>();
      for (List<String> row : dataTable.cells()) {
        try {
          T t = (T) ((Class) type).getDeclaredConstructor().newInstance();
          ((IModel) t).initializeData(this.mapColumnIndex, row);
          listT.add(t);
        } catch (InstantiationException e) {
          throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
          throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
          throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
          throw new RuntimeException(e);
        }
      }
      return listT;
    } else {
      throw new RuntimeException("Only support sub-classes of IModel ");
    }
  }

  @Override
  public <T> List<List<T>> toLists(DataTable dataTable, Type type) {
    return null;
  }

  @Override
  public <K, V> Map<K, V> toMap(DataTable dataTable, Type type, Type type1) {
    return null;
  }

  @Override
  public <K, V> List<Map<K, V>> toMaps(DataTable dataTable, Type type, Type type1) {
    return null;
  }
}
