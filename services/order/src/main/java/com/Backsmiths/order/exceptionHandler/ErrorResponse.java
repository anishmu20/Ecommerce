package com.Backsmiths.order.exceptionHandler;

import java.util.Map;

public record ErrorResponse
        (
                Map<String,String> errors
        )
{

}
