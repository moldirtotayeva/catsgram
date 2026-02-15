package com.example.catsgram.controller;

import com.example.catsgram.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse hundleINcorrectParametr(final IncorrectParameterException e) {
         switch (e.getParametr()) {
            case ("sort") -> {
                return new ErrorResponse(
                        "Параметр сортировки указан неверно.",
                        "Используйте 'asc' для сортировки по возрастанию или 'desc' для сортировки по убыванию.");
            }
            case("page") -> {
                return new ErrorResponse(
                        "Некорректный параметр страницы",
                        "Параметр 'page' должен быть больше или равен 0."
                );
            }
            case("size") -> {
                return  new ErrorResponse(
                        "Некорректный параметр страницы",
                        "Параметр 'size' должен быть больше 0."
                );
            }
            case("friends") -> {
                return new ErrorResponse(
                        "Некорректные данные",
                        "Список друзей не должен быть пустым.");
            }
        }
        return null;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse hundleUserNotFound(final UserNotFoundException e) {
        return new ErrorResponse("error", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse hundleUserNotFound(final PostNotFoundException e) {
        return new ErrorResponse("error", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse hundleUserAlradyExist(final UserAlreadyExistException e){
        return new ErrorResponse("error", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse hundleInvalidEmail(final InvalidEmailException e) {
        return new ErrorResponse("error", e.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse hundle(final Throwable e) {
        return new ErrorResponse("error", e.getMessage());
    }
}
