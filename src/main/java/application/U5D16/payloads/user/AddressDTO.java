package application.U5D16.payloads.user;

import jakarta.validation.constraints.NotEmpty;

public record AddressDTO(@NotEmpty(message = "Please add via") String via,
                         @NotEmpty(message = "Please add a cap") int cap,
                         @NotEmpty(message = "Please include Comune") String comune) {
}
