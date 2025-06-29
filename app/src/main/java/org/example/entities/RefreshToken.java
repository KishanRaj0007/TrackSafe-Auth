package org.example.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.time.Instant;

@Entity
@Table(name = "tokens")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder //objects with a more readable and flexible syntax, especially helpful when constructors have many parameters.
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class) //converts Java camelCase field names into snake_case in JSON

public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // assigns primary key values in an auto-incrementing sequence
    private int id;
    private String token;
    private Instant expiryDate;

    // in reference token class we are interested in user information
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "user_id")
    private UserInfo userInfo;
}
