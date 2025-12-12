package starter.storage;

import java.util.HashMap;
import java.util.Map;

public class WebStorage {
  private Map<String, Object> webStorageMap = new HashMap<>();

  private static WebStorage webStorage;

  private WebStorage() {}

  public static WebStorage getInstance() {
    if (webStorage == null) {
      webStorage = new WebStorage();
    }

    return webStorage;
  }

  public void setWebStorage(Map<String, Object> webStorageMap) {
    this.webStorageMap = webStorageMap;
  }

  public Map<String, Object> getWebStorage() {
    return this.webStorageMap;
  }
}
