package com.springboot.redtest.service.quote;

import com.springboot.redtest.dto.quote.QuoteDto;
import com.springboot.redtest.request.common.CommonRequest;

import java.util.List;

public interface QuoteService {
    /**
     * Create new quote
     * @param publicUserId
     * @param quoteDto (quote, quoteSource)
     */
    void createQuote(String publicUserId, QuoteDto quoteDto);

    /**
     * Update a quote
     * @param publicQuoteId
     * @param quoteDto
     */
    void updateQuote(String publicQuoteId, QuoteDto quoteDto);

    /**
     * Delete a quote
     * @param commonRequest
     */
    void deleteQuote(CommonRequest commonRequest);

    /**
     * Get all quotes
     * @param page
     * @return
     */
    List<QuoteDto> getAllQuotes(String page);

    /**
     * Get a single quote
     * @param publicQuoteId
     * @return
     */
    QuoteDto getQuote(String publicQuoteId);

    /**
     * Check if string is a number
     * @param str
     * @return
     */
    boolean isNumber(String str);
}
