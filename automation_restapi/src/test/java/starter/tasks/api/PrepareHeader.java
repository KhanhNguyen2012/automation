package starter.tasks.api;

import static starter.constants.UrlConstants.HEADER_LIST_KEY;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import vn.com.fecredit.icollect.datatable.model.RequestHeaders;

@Getter
@Setter
@AllArgsConstructor
public class PrepareHeader implements Task {

  private List<RequestHeaders> requestHeaders;

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.remember(HEADER_LIST_KEY, requestHeaders);
  }
}
