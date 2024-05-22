package com.football.NiceTeam.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Model of a soccer player.
 */
@Getter
@Setter
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, message = "Name must be higher than 2")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Position is mandatory")
    @Size(min = 2, message = "Name must be higher than 2")
    @Column(nullable = false)
    private String position;
}
