/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package airports;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author rimi
 */
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportServiceTest {

    @Autowired
    AirportService airportService;

    @Autowired
    AirportRepository airportRepository;

    public AirportServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
        airportRepository.deleteAll();
    }

    @Test
    public void testCreateAddsAirport() {
        String identifier = "AAR";
        String name = "Aarhus";

        assertEquals(0, airportRepository.count());
        airportService.create(identifier, name);
        assertEquals(1, airportRepository.count());
        Airport airport = airportRepository.findAll().get(0);
        assertEquals(identifier, airport.getIdentifier());
        assertEquals(name, airport.getName());
    }

    @Test
    public void testCreateDoesNotCreateDuplicates() {
        String identifier = "AAR";
        String name = "Aarhus";

        assertEquals(0, airportRepository.count());
        airportService.create(identifier, name);
        assertEquals(1, airportRepository.count());
        airportService.create(identifier, name);
        assertEquals(1, airportRepository.count());
        airportService.create(identifier, "differentname");
        assertEquals(1, airportRepository.count());
        airportService.create("differentid", name);
        assertEquals(1, airportRepository.count());
    }

    @Test
    public void testListReturnsAllAirports() {
        List<Airport> expResult = new ArrayList<>();
        expResult.add(new Airport("HEL", "Helsinki"));
        expResult.add(new Airport("AAR", "Aarhus"));
        expResult.add(new Airport("AR", "Arlanda"));

        assertEquals(0, airportRepository.count());
        airportRepository.saveAll(expResult);
        List<Airport> result = airportService.list();
        assertEquals(expResult, result);
    }

}
