package com.football.NiceTeam;

import com.football.NiceTeam.models.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Integration tests for the TeamController class.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamControllerIntegration {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Returns the base API URL
     *
     * @return the base URL
     */
    private String getUrl() {
        return "http://localhost:" + port + "/api/teams";
    }

    /**
     * Test the getAllTeams endpoint to check if it returns a list of teams
     */
    @Test
    public void testGetAllTeams() {
        ResponseEntity<String> response = restTemplate.getForEntity(getUrl(), String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Test the addTeam endpoint to check the insert functionality
     */
    @Test
    public void testAddTeam() {
        Team team = new Team();
        team.setName("Nice");
        team.setAcronym("OGC");
        team.setBudget(1000000.0);

        ResponseEntity<Team> response = restTemplate.postForEntity(getUrl(), team, Team.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Nice", response.getBody().getName());
    }

    /**
     * Test the getTeamById endpoint
     */
    @Test
    public void testGetTeamById() {
        Team team = new Team();
        team.setName("Nice");
        team.setAcronym("OGC");
        team.setBudget(1000000.0);

        Team createdTeam = restTemplate.postForEntity(getUrl(), team, Team.class).getBody();
        Assertions.assertNotNull(createdTeam);

        String url = getUrl() + "/" + createdTeam.getId();
        ResponseEntity<Team> response = restTemplate.getForEntity(url, Team.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Nice", response.getBody().getName());
    }

    /**
     * Test the editTeam endpoint to check if it edits correctly
     */
    @Test
    public void testEditTeam() {
        Team team = new Team();
        team.setName("Lyon");
        team.setAcronym("LY");
        team.setBudget(1000000.0);

        Team createdTeam = restTemplate.postForEntity(getUrl(), team, Team.class).getBody();
        Assertions.assertNotNull(createdTeam);

        Team editedTeam = new Team();
        editedTeam.setName("Nice");
        editedTeam.setAcronym("OGC");
        editedTeam.setBudget(200000000.0);

        String url = getUrl() + "/" + createdTeam.getId();
        HttpEntity<Team> requestUpdate = new HttpEntity<>(editedTeam);
        ResponseEntity<Team> response = restTemplate.exchange(url, HttpMethod.PUT, requestUpdate, Team.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("Nice", response.getBody().getName());
    }

    /**
     * Tests the deleteTeam endpoint
     */
    @Test
    public void testDeleteTeam() {
        Team team = new Team();
        team.setName("Nice");
        team.setAcronym("OGC");
        team.setBudget(10000000.0);

        Team createdTeam = restTemplate.postForEntity(getUrl(), team, Team.class).getBody();
        Assertions.assertNotNull(createdTeam);

        String url = getUrl() + "/" + createdTeam.getId();
        restTemplate.delete(url);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
