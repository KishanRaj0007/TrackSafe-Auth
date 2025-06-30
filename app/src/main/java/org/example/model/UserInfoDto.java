package org.example.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.example.entities.UserInfo;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data // It will automatically create getUserName(), etc..
public class UserInfoDto {
    private String userName;

    private String password;

    private String firstName; // first_name

    private String lastName; //last_name

    private Long phoneNumber;

    private String email; // email
}
