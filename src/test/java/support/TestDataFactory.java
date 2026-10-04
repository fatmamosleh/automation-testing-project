package support;

import java.util.UUID;

public final class TestDataFactory {
    private TestDataFactory() {
    }

    public static Customer uniqueCustomer() {
        String unique = UUID.randomUUID().toString().substring(0, 8);
        return new Customer("Fatma", "Tester", "fatma." + unique + "@example.com", "Test@12345");
    }

    public record Customer(String firstName, String lastName, String email, String password) {
    }
}
