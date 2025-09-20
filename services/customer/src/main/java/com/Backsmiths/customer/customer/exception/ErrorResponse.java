package com.Backsmiths.customer.customer.exception;


import java.util.Map;

public record ErrorResponse
        (
                Map<String,String> errors
        )
{

}
