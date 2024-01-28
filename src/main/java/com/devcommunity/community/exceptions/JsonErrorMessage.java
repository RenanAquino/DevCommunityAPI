package com.devcommunity.community.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JsonErrorMessage {
    private HttpStatus status;
    private String message;
}
