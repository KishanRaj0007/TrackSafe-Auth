package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Marks this class as a JPA entity, meaning it's mapped to a database table.
@Table() // name of table
@Data //Generates boilerplate code: getters, setters, toString(), equals(), and hashCode() methods.
@NoArgsConstructor
@AllArgsConstructor
public class UserRole {
    @Id // specifies the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatically generates the default primary key value
    @Column(name = "role_id")
    private long roleId;
    private String name;
}
