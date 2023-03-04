package com.example.checkers.checkers.exceptions;

import com.example.checkers.checkers.bussiness.MoveComments;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalMoveException extends RuntimeException {
        public IllegalMoveException(MoveComments message) {
            super(String.valueOf(message));
        }
}
