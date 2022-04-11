package com.springboot.redtest.serviceimpl.quoteImpl;

import com.springboot.redtest.common.Utility;
import com.springboot.redtest.dto.category.CategoryDto;
import com.springboot.redtest.dto.quote.QuoteDto;
import com.springboot.redtest.dto.user.UserDto;
import com.springboot.redtest.entity.category.CategoryEntity;
import com.springboot.redtest.entity.quote.QuoteEntity;
import com.springboot.redtest.entity.user.UserEntity;
import com.springboot.redtest.exception.UserException;
import com.springboot.redtest.repository.category.CategoryRepository;
import com.springboot.redtest.repository.quote.QuoteRepository;
import com.springboot.redtest.repository.user.UserRepository;
import com.springboot.redtest.request.common.CommonRequest;
import com.springboot.redtest.service.quote.QuoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService {
    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    private Utility utility;

    @Value("${quotesPerPage}")
    int quotesPerPage;

    /**
     * Create new quote
     * @param publicUserId
     * @param quoteDto (quote, quoteSource)
     */
    @Override
    public void createQuote(String publicUserId, QuoteDto quoteDto) {
        // Search for a user
        UserEntity userEntity = userRepository.findByPublicUserId(publicUserId);

        // if public user id does not exists
        if (userEntity == null) throw new UserException("User id does not exists !");

        // Transfer data to UserDto
        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

        // Set user to quote dto
        quoteDto.setUser(userDto);

        // Search for category
        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(quoteDto.getCategoryName());

        // If category does not exist
        if (categoryEntity == null) throw new UserException("Category does not exists");

        // Transfer data to CategoryDto
        CategoryDto categoryDto = modelMapper.map(categoryEntity, CategoryDto.class);

        // Set category to quote dto
        quoteDto.setCategory(categoryDto);

        // Transfer data to QuoteEntity
        QuoteEntity quoteEntity = modelMapper.map(quoteDto, QuoteEntity.class);

        // Set publicQuoteId
        quoteEntity.setPublicQuoteId(utility.UUID16());

        // save data to database
        quoteRepository.save(quoteEntity);

    }

    /**
     * Update a quote
     * @param publicQuoteId
     * @param quoteDto
     */
    @Override
    public void updateQuote(String publicQuoteId, QuoteDto quoteDto) {
        // Search for a quote in database
        QuoteEntity quoteEntity = quoteRepository.findByPublicQuoteId(publicQuoteId);

        // If quote does not exists
        if (quoteEntity == null) throw new UsernameNotFoundException("User with Id: " + publicQuoteId + " not found !");

        // Check quote is not blank
        if (quoteDto.getQuote() != null && !quoteDto.getQuote().isBlank()) {
            quoteEntity.setQuote(quoteDto.getQuote());
        }

        // Check quote source is not blank
        if (quoteDto.getQuoteSource() != null && !quoteDto.getQuoteSource().isBlank()) {
            quoteEntity.setQuoteSource(quoteDto.getQuoteSource());
        }

        // Check quote category name is not blank
        if (quoteDto.getCategoryName() != null && !quoteDto.getCategoryName().isBlank()) {

            // Search for category
            CategoryEntity categoryEntityDb = categoryRepository.findByCategoryName(quoteDto.getCategoryName());

            // Check if category is exists
            if (categoryEntityDb == null) throw new UserException("Category does not exists");

            // Set category
            quoteEntity.setCategory(categoryEntityDb);
        }

        // save data to database
        quoteRepository.save(quoteEntity);
    }

    /**
     * Delete a quote
     * @param commonRequest
     */
    @Override
    public void deleteQuote(CommonRequest commonRequest) {
        // Search if quote exists in database
        QuoteEntity quoteEntity = quoteRepository.findByPublicQuoteId(commonRequest.getPublicQuoteId());

        // Check if quote exists
        if (quoteEntity == null) throw new UserException("Quote with Id: " + commonRequest.getPublicQuoteId() + " not found !");

        // search for user by quote
        UserEntity userEntity = userRepository.findByPublicUserId(commonRequest.getPublicUserId());

        // Check if user exists
        if (userEntity == null) throw new UserException("User with Id: " + commonRequest.getPublicUserId() + " not found !");

        if (userEntity.getId() == quoteEntity.getUser().getId()) {
            // delete the quote
            quoteRepository.delete(quoteEntity);

            // stop
            return;
        }

        // throw an exception
        throw new UserException("Cannot remove this quote");

    }

    /**
     * Get all quotes
     * @param page
     * @return list of quotes
     */
    @Override
    public List<QuoteDto> getAllQuotes(String page) {
        // check if page is not a numeric value
        if (!isNumber(page)) throw new UserException("Page not found");

        // convert page string to integer
        int pageInt = Integer.parseInt(page);

        // Get rid of 0
        if (pageInt > 0) pageInt -= 1;

        // Store list of quotesDto
        List<QuoteDto> listQuotesDto = new ArrayList<>();

        // Define which page and the limit of quotes
        // that will be shown in that page
        Pageable pageable = PageRequest.of(pageInt, quotesPerPage);

        // Find all quotes that defined in the pageable AKA (page, limit)
        Page<QuoteEntity> quotePage = quoteRepository.findAll(pageable);

        // Get the content of quote page
        List<QuoteEntity> quotes = quotePage.getContent();

        // Loop through quotes
        for (QuoteEntity quoteEntity : quotes) {

            // Transfer data to QuoteDto
            QuoteDto quote = modelMapper.map(quoteEntity, QuoteDto.class);

            // Add each quote to the listQuotesDto
            listQuotesDto.add(quote);
        }

        // If the listQuotesDto is empty throw exception
//        if (listQuotesDto.isEmpty()) throw new UserException("Page not found");

        // return the listQuotesDto
        return listQuotesDto;
    }

    /**
     * Get a single quote
     * @param publicQuoteId
     * @return
     */
    @Override
    public QuoteDto getQuote(String publicQuoteId) {
        // Search for a quote
        QuoteEntity quoteEntity = quoteRepository.findByPublicQuoteId(publicQuoteId);

        // check if quote exists
        if (quoteEntity == null) throw new UserException("Quote does not exists");

        // Transfer data to QuoteDto
        QuoteDto quoteDto = modelMapper.map(quoteEntity, QuoteDto.class);

        return quoteDto;
    }

    /**
     * Check if string is a number
     * @param str
     * @return
     */
    public boolean isNumber(String str) {
        try {
            Integer.valueOf(str);

        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
