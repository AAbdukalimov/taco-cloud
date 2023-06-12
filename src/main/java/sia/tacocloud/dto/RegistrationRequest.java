package sia.tacocloud.dto;

import lombok.Data;

@Data
public class RegistrationRequest {

    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

}