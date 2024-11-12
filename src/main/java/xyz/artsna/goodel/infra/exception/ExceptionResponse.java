package xyz.artsna.goodel.infra.exception;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonRootName("exception")
public class ExceptionResponse {

    private boolean ok = false;
    private String message;

    public ExceptionResponse(String message) {
        this.message = message;
    }

}
