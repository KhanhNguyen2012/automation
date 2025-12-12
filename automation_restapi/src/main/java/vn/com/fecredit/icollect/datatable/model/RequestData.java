package vn.com.fecredit.icollect.datatable.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestData extends AbstractModel {
  private String body;
  private String params;
}
