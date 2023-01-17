package adp.test.exchange.model;

import adp.test.exchange.constants.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponeBase {

    ExceptionCode code;
    String message;

}
