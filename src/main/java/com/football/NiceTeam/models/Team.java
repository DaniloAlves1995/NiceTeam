package com.football.NiceTeam.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Model of a soccer team.
 */
@Getter
@Setter
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, message = "Name must be higher than 2 characters")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Acronym is mandatory")
    @Column(nullable = false)
    private String acronym;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players = new ArrayList<>();

    @NotNull(message = "Budget is mandatory")
    @Min(value = 0, message = "Budget must be greater than or equal to 0")
    @Column(nullable = false)
    private Double budget;

    public void setPlayers(List<Player> players) {
        // Avoid hibernate issue when update a nested object
        if (players != null) {
            this.players.clear();
            this.players.addAll(players);
        }
    }

    @Override
    public String toString() {
        return "[name=" + name + ", acronym=" + acronym + ", budget=" + budget + "]";
    }
}
