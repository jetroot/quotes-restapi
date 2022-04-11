package com.springboot.redtest.controller.dislike;

import com.springboot.redtest.dto.dislike.DislikeDto;
import com.springboot.redtest.request.common.CommonRequest;
import com.springboot.redtest.service.dislike.DislikeService;
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
@RequestMapping(value = "/dislikes")
public class DislikeController {

    @Autowired
    private DislikeService dislikeService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Void> like(@Valid @RequestBody CommonRequest commonRequest) {

        DislikeDto dislikeDto = modelMapper.map(commonRequest, DislikeDto.class);

        dislikeService.dislike(dislikeDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
