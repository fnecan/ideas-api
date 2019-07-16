package flud.necan.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MissingIdeaOrCommentDocumentException extends RuntimeException {
    public MissingIdeaOrCommentDocumentException(String message) {
        super(message);
    }
}

