package starter.utils;

import net.serenitybdd.screenplay.Actor;

public class AuthenUtil {
  public static String getKeyActorApi(Actor actor, String endPoint, String method) {
    return actor.getName() + endPoint + method;
  }
}
