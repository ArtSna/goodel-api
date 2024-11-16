package xyz.artsna.goodel.api.requests.store;

import lombok.Getter;

public class ClientRequest {

    @Getter
    public static class Create {
        private String firstName;
    }

    @Getter
    public static class Update {
        private String firstName;
    }
}
