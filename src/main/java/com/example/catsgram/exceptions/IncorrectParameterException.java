package com.example.catsgram.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class IncorrectParameterException extends RuntimeException {
    private final String parametr;
}
