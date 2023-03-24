package douglas.develop.exception.resources;

import douglas.develop.exception.ConstraintViolationException;
import douglas.develop.exception.DataIntegrityViolationException;
import douglas.develop.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandarsError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        StandarsError err = new StandarsError(HttpStatus.NOT_FOUND.value(),e.getMessage(), System.currentTimeMillis() );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandarsError> objectNotFound(ConstraintViolationException e, HttpServletRequest request){
        StandarsError err = new StandarsError(HttpStatus.NOT_FOUND.value(),e.getMessage(), System.currentTimeMillis() );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandarsError> objectNotFound(DataIntegrityViolationException e, HttpServletRequest request){
        StandarsError err = new StandarsError(HttpStatus.NOT_FOUND.value(),e.getMessage(), System.currentTimeMillis() );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
