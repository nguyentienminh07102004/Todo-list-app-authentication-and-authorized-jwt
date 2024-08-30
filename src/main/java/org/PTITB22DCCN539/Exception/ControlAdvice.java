package org.PTITB22DCCN539.Exception;

import org.PTITB22DCCN539.Exception.MyException.DataInvalidException;
import org.PTITB22DCCN539.Exception.MyException.MyException;
import org.PTITB22DCCN539.Exception.MyException.MyUnauthorizedException;
import org.PTITB22DCCN539.Model.Response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControlAdvice {

    @ExceptionHandler(value = DataInvalidException.class)
    public ResponseEntity<ResponseDTO> DataInvalidExceptionHandler(DataInvalidException exception) {
        ResponseDTO response = ResponseDTO.builder()
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = MyException.class)
    public ResponseEntity<ResponseDTO> MyExceptionHandler(MyException exception) {
        return ResponseEntity.status(400).body(ResponseDTO.builder()
                        .message(exception.getMessage())
                .build());
    }

    @ExceptionHandler(value = MyUnauthorizedException.class)
    public ResponseEntity<ResponseDTO> MyUnauthorized(MyUnauthorizedException exception) {
        return ResponseEntity.status(403).body(ResponseDTO.builder()
                .status("403")
                .message(exception.getMessage())
                .build());
    }
}
