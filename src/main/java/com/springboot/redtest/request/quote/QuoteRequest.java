package com.springboot.redtest.request.quote;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
public class QuoteRequest {

    @NotBlank(message = "Quote cannot be blank")
    @Size(min = 3, max = 700, message = "Minimum characters 3 and maximum 700 for quote")
    private String quote;

    @NotBlank(message = "Quote source cannot be blank")
    @Size(min = 3, max = 100, message = "Minimum characters 3 and maximum 100 for quote source")
    private String quoteSource;

    @NotBlank(message = "Quote category cannot be blank")
    private String categoryName;

}
