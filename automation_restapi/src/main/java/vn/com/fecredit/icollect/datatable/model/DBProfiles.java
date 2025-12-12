package vn.com.fecredit.icollect.datatable.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DBProfiles extends AbstractModel {
  private String url;
  private String schema;
  private String username;
  private String password;
}
