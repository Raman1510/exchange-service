package adp.test.exchange.utils;

import adp.test.exchange.exception.RequestValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RequestValidationUtil {


    @Value("#{${allowedBills}}")
    Set<Integer> allowedBills;

    public Boolean validateInput(Integer inputBill) {
        if (!allowedBills.contains(inputBill)) {
            throw new RequestValidationException("Input Bill is not allowed!");
        }
        return true;

    }


}
