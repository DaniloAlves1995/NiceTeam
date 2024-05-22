package com.football.NiceTeam.controllers;

import com.football.NiceTeam.models.Team;
import com.football.NiceTeam.services.TeamService;
import jakarta.validation.Valid;
import org.hibernate.FetchNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

/**
 * REST Controller to handle with requests of the teams.
 */

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamService teamService;

    /**
     * Retrieves a list of teams.
     *
     * @param pageable the pagination and sorting information (Ex: page=1&size=4&sort=name,asc)
     * @return a page of teams
     */
    @GetMapping
    public Page<Team> getAllTeams(Pageable pageable) {
        return teamService.getAllTeams(pageable);
    }

    /**
     * Add a new team.
     *
     * @param team the team to add
     * @return the added team
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Team addTeam(@Valid @RequestBody Team team) {
        return teamService.addTeam(team);
    }

    /**
     * Retrieves a team by the ID.
     *
     * @param id the ID of the team to retrieve
     * @return the requested team
     */
    @GetMapping("/{id}")
    public ResponseEntity getTeamById(@PathVariable Long id) {
        Optional<Team> team = teamService.getTeamById(id);
        if (team.isPresent()) {
            return new ResponseEntity<>(team.get(), HttpStatus.OK);
        } else {
            logger.error("Team not found with id: " + id);
            return new ResponseEntity<>("Team not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Updates an existing team.
     *
     * @param id the ID of the team to update
     * @param newTeam the new team to update
     * @return the updated team
     */
    @PutMapping("/{id}")
    public ResponseEntity editTeam(@PathVariable Long id, @Valid @RequestBody Team newTeam) {
        try{
            Team editedTeam = teamService.editTeam(id, newTeam);
            return new ResponseEntity<>(editedTeam, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), e instanceof FetchNotFoundException ?
                    HttpStatus.NOT_FOUND : HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Deletes a team by ID.
     *
     * @param id the ID of the team to delete
     * @return a response entity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTeam(@PathVariable Long id) {
        try{
            teamService.deleteTeam(id);
            return new ResponseEntity<>("Deleted with Success!", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage(), e instanceof FetchNotFoundException ?
                    HttpStatus.NOT_FOUND : HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
