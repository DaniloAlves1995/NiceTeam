package com.football.NiceTeam;

import com.football.NiceTeam.models.Team;
import com.football.NiceTeam.repositories.TeamRepository;
import com.football.NiceTeam.services.TeamService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.Optional;

@SpringBootTest
public class TeamServiceUnitTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    /**
     *
     * Initializing mocks.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test the getAllTeams method to check if it returns a paginated list
     */
    @Test
    public void testGetAllTeams() {
        // Prepare the request pageable and the returned page
        PageRequest pageable = PageRequest.of(0, 10);
        Page<Team> page = new PageImpl<>(Collections.singletonList(new Team()));

        Mockito.when(teamRepository.findAll(pageable)).thenReturn(page);

        Page<Team> result = teamService.getAllTeams(pageable);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getTotalElements());
    }

    /**
     * Test the addTeam method
     */
    @Test
    public void testAddTeam() {
        // Team to be added
        Team team = new Team();
        team.setName("Lens");
        team.setAcronym("RC Lens");
        team.setBudget(1000000.0);

        Mockito.when(teamRepository.save(team)).thenReturn(team);

        Team result = teamService.addTeam(team);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Lens", result.getName());
    }

    /**
     * Test the getTeamById method
     */
    @Test
    public void testGetTeamById() {
        // Team to be returned
        Team team = new Team();
        team.setId(1L);
        team.setName("Bordeaux");
        team.setAcronym("Girondins de Bordeaux");
        team.setBudget(7000000.0);

        Mockito.when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        Optional<Team> result = teamService.getTeamById(1L);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Bordeaux", result.get().getName());
    }

    /**
     * Test the editTeam method
     */
    @Test
    public void testEditTeam() {
        // The old team
        Team team = new Team();
        team.setId(1L);
        team.setName("Lyon");

        // The new team with values to update
        Team newTeam = new Team();
        newTeam.setName("Nice");
        newTeam.setAcronym("OGC");
        newTeam.setBudget(10000000.0);

        Mockito.when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        Mockito.when(teamRepository.save(ArgumentMatchers.any(Team.class))).thenReturn(newTeam);

        Team result = teamService.editTeam(1L, newTeam);
        Assertions.assertNotNull(result);
        Assertions.assertEquals("Nice", result.getName());
    }

    /**
     * Test the deleteTeam method
     */
    @Test
    public void testDeleteTeam() {
        // Team to be deleted
        Team team = new Team();
        team.setId(1L);
        team.setName("Nice");

        Mockito.when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        teamService.deleteTeam(1L);
        Mockito.verify(teamRepository, Mockito.times(1)).delete(team);
    }

}
