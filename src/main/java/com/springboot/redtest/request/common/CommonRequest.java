package com.springboot.redtest.request.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@NoArgsConstructor
public class CommonRequest {

    @NotBlank(message = "User Id cannot be null")
    private String publicUserId;

    @NotBlank(message = "Quote Id cannot be null")
    private String publicQuoteId;
}