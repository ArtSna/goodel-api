package xyz.artsna.goodel.api.requests.store;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

public class NeighborhoodRequest {

    @Getter
    public static class Create {
        @NotBlank(message = "Name must be not blank")
        private String name;
        @NotNull(message = "Delivery Fee must be not null")
        @JsonProperty("delivery_fee")
        private BigDecimal deliveryFee;
    }

    @Getter
    public static class Update {
        private String name;
        @JsonProperty("delivery_fee")
        private BigDecimal deliveryFee;
    }
}
