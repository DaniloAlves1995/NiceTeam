package com.football.NiceTeam.repositories;

import com.football.NiceTeam.models.Player;
import com.football.NiceTeam.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository for managing {@link Team} model.
 * <p>
 * This interface has CRUD operations for the Team entity by JpaRepository.
 * </p>
 */
public interface TeamRepository extends JpaRepository<Team, Long> {
}
