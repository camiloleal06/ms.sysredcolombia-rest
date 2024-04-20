package ms.sysredcolombia.rest.modelo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

	   @ExceptionHandler(value = RuntimeException.class)
	    public ResponseEntity<ErrorDto> runtimeExceptionHandler(
	            RuntimeException exception) {
	       ErrorDto errorDto = ErrorDto.builder().code("S-500")
	                .message("Ha ocurrido un error generico")
	                .build();
	       return new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler(value = NotFoundException.class)
	    public ResponseEntity<ErrorDto> notFoundExcpetionHandler(
	            NotFoundException exception) {
	        ErrorDto errorDto = ErrorDto.builder().code(exception.getCode())
	                .message(exception.getMessage())
	                .build();
	        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
	    }
	    
	    @ExceptionHandler(value = ConflictException.class)
	    public ResponseEntity<ErrorDto> conflictExceptionHandler(
	            ConflictException exception) {
	        ErrorDto errorDto = ErrorDto.builder().code(exception.getCode())
	                .message(exception.getMessage())
	                .build();
	        return new ResponseEntity<>(errorDto, HttpStatus.CONFLICT);
	    }
	    
}