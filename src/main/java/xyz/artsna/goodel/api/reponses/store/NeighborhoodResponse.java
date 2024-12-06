package xyz.artsna.goodel.api.reponses.store;

import com.fasterxml.jackson.annotation.JsonRootName;
import xyz.artsna.goodel.domain.models.Neighborhood;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class NeighborhoodResponse {

    @JsonRootName("neighborhood")
    public static class Single {

        public UUID id;
        public String name;
        public BigDecimal deliveryFee;

        public Single(Neighborhood neighborhood) {
            this.id = neighborhood.getId();
            this.name = neighborhood.getName();
            this.deliveryFee = neighborhood.getDeliveryFee();

        }
    }

    @JsonRootName("neighborhoods")
    public static class Multiple {

        public Iterable<NeighborhoodResponse.Single> neighborhoods;
        public int count;

        public Multiple(List<Neighborhood> neighborhoods) {
            this.neighborhoods = neighborhoods.stream().map(NeighborhoodResponse.Single::new).toList();
            this.count = neighborhoods.size();
        }

    }
}