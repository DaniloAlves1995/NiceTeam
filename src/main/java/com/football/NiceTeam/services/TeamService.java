package com.football.NiceTeam.services;

import com.football.NiceTeam.models.Team;
import com.football.NiceTeam.repositories.TeamRepository;
import org.hibernate.FetchNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service to manage teams.
 */
@Service
public class TeamService {
    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private TeamRepository teamRepository;

    /**
     * List a paginated list of teams.
     *
     * @param pageable the pagination data
     * @return a page of teams
     */
    public Page<Team> getAllTeams(Pageable pageable) {
        logger.info("Listing all teams with pagination: {}", pageable);
        return teamRepository.findAll(pageable);
    }

    /**
     * Fetch a team by its ID.
     *
     * @param id the team ID
     * @return the team found
     */
    public Optional<Team> getTeamById(Long id) {
        logger.info("Fetching team by id: {}", id);
        return teamRepository.findById(id);
    }

    /**
     * Add a new team.
     *
     * @param team the team to add
     * @return the added team
     */
    public Team addTeam(Team team) {
        logger.info("Adding new team: {}", team);
        return teamRepository.save(team);
    }

    /**
     * Edit a team.
     *
     * @param id the ID of the team to be updated
     * @param newTeam object with new values to update
     * @return the updated team
     */
    public Team editTeam(Long id, Team newTeam) {
            Team team = teamRepository.findById(id).orElseThrow(() -> new FetchNotFoundException("Team", "id = "+id));
            logger.info("Updating team with id: {} with values: {}", id, team);

            team.setName(newTeam.getName());
            team.setAcronym(newTeam.getAcronym());
            team.setBudget(newTeam.getBudget());
            team.setPlayers(newTeam.getPlayers());
            return teamRepository.save(team);
    }

    /**
     * Deletes a team by ID.
     *
     * @param id the ID of the team to delete
     */
    public void deleteTeam(Long id) {
        Team team = teamRepository.findById(id).orElseThrow(() -> new FetchNotFoundException("Team", "id = "+id));
        logger.info("Deleting team with id: {}", id);

        teamRepository.delete(team);
    }

}
