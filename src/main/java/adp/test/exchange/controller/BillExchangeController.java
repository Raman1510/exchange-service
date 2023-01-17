package adp.test.exchange.controller;

import adp.test.exchange.model.CoinsHolder;
import adp.test.exchange.model.ExceptionResponeBase;
import adp.test.exchange.service.BillExchangeService;
import adp.test.exchange.utils.RequestValidationUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exchange")
public class BillExchangeController {

    BillExchangeService exchangeService;

    RequestValidationUtil requestValidationUtil;

    public BillExchangeController(BillExchangeService exchangeService, RequestValidationUtil requestValidationUtil) {
        this.exchangeService = exchangeService;
        this.requestValidationUtil = requestValidationUtil;
    }

    @GetMapping(path = "/v1/coin-change/{bill}")
    @Operation(summary = "Get change for the Input Bill")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the Change for the Bill",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = CoinsHolder.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Input ! Please enter Valid input",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponeBase.class))}),
            @ApiResponse(responseCode = "422", description = "Not Enough Coins left for exchange to be happened",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponeBase.class))})})
    public CoinsHolder getChangeInCoins(@PathVariable(value = "bill") Integer bill,
                                        @RequestParam(required = false, value = "getMaximumCoins") boolean getMaximumCoins) {
        requestValidationUtil.validateInput(bill);
        return exchangeService.getChangeInCoins(bill.doubleValue(), getMaximumCoins);
    }


}
