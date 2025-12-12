package vn.com.fecredit.icollect.datatable.model;

import java.io.Serializable;
import lombok.Data;

@Data
public abstract class AbstractModel implements IModel, Serializable {

  @Override
  public String normalizeFieldName(String fieldName) {
    return fieldName;
  }
}
