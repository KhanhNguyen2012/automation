package vn.com.fecredit.icollect.datatable.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestHeaders extends AbstractModel {
  private String header;
  private String value;
}
