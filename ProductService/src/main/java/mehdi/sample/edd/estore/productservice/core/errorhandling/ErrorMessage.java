package mehdi.sample.edd.estore.productservice.core.errorhandling;

import java.util.Date;

public record ErrorMessage(Date timestamp, String message,Integer errorCode) {
}
