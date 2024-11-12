package xyz.artsna.goodel.infra.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class WebException extends RuntimeException {

    public WebException(@NotNull String message) {
        super(message);
    }

}
