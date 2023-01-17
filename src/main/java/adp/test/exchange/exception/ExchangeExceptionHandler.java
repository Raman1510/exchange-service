package adp.test.exchange.exception;


import adp.test.exchange.constants.ExceptionCode;
import adp.test.exchange.model.ExceptionResponeBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
@Slf4j
public class ExchangeExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponeBase handleGenericError(Exception ex) {
        log.error("exception occured -{}", ex.getMessage());
        return ExceptionResponeBase.builder().code(ExceptionCode.TECHNICAL_EXCEPTION).message(ex.getMessage()).build();
    }

    @ExceptionHandler(value = {RequestValidationException.class, MethodArgumentTypeMismatchException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponeBase handleValidationError(Exception ex) {
        log.error("exception occured -{}", ex.getMessage());
        return ExceptionResponeBase.builder().code(ExceptionCode.INVALID_INPUT).message(ex.getMessage()).build();
    }

    @ExceptionHandler(value = {ChangeNotAvailableException.class})
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionResponeBase handleChangeError(Exception ex) {
        log.error("exception occured -{}", ex.getMessage());
        return ExceptionResponeBase.builder().code(ExceptionCode.NOT_ENOUGH_COINS).message(ex.getMessage()).build();
    }

}
