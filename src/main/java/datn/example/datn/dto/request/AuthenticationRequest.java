package datn.example.datn.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthenticationRequest {

    @NotNull
    private String login;

    @NotNull
    private String password;

}
