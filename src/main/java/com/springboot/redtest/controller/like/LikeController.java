package com.springboot.redtest.controller.like;

import com.springboot.redtest.dto.like.LikeDto;
import com.springboot.redtest.request.common.CommonRequest;
import com.springboot.redtest.service.like.LikeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/likes")
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Like a quote
     * @param commonRequest
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> like(@Valid @RequestBody CommonRequest commonRequest) {

        LikeDto likeDto = modelMapper.map(commonRequest, LikeDto.class);

        likeService.like(likeDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
