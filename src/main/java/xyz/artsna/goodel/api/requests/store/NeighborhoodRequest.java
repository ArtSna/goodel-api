package xyz.artsna.goodel.api.requests.store;

import lombok.Getter;

import java.math.BigDecimal;

public class NeighborhoodRequest {

    @Getter
    public static class Create {
        private String name;
        private BigDecimal deliveryFee;
    }

    @Getter
    public static class Update {
        private String name;
        private BigDecimal deliveryFee;
    }
}
