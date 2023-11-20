package application.U5D16.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFound(NotFoundException e){return new ErrorDTO(e.getMessage(), new Date());}

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorListDTO handleBadRequest(BadRequestException e){
        if(e.getErrorList() != null){
            List<String> errorsList = e.getErrorList().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new ErrorListDTO(e.getMessage(), new Date(), new ArrayList<>());
        }else {
            return new ErrorListDTO(e.getMessage(), new Date(), new ArrayList<>());
        }
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleUnauthorized(UnauthorizedException e){return new ErrorDTO(e.getMessage(), new Date());}

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorDTO handleAccesDenied(AccessDeniedException e){return new ErrorDTO(e.getMessage(), new Date());}

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleGenericError(Exception e){ return new ErrorDTO(e.getMessage(), new Date());}
}
