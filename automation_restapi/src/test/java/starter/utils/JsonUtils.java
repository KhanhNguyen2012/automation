package starter.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class JsonUtils {
  private static final ObjectMapper OBJECT_MAPPER =
      new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  private static final GsonBuilder GSON_BUILDER = new GsonBuilder();
  private static final Gson GSON;

  static {
    GSON = GSON_BUILDER.create();
  }

  private JsonUtils() {}

  public static ObjectMapper getObjectMapper() {
    return OBJECT_MAPPER;
  }

  public static Gson getGson() {
    return GSON;
  }

  public static String writeValueAsStringNoException(Object val) {
    try {
      return JsonUtils.getObjectMapper().writeValueAsString(val);
    } catch (JsonProcessingException e) {
      return null;
    }
  }
}
