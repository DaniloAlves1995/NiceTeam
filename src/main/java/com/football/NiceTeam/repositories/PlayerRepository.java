package com.football.NiceTeam.repositories;

import com.football.NiceTeam.models.Player;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing {@link Player} model.
 * <p>
 * This interface has CRUD operations for the Player entity by JpaRepository.
 * </p>
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
