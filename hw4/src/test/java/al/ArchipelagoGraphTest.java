package al;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ArchipelagoGraphTest {

    @TempDir Path tempDir;

    @Test
    void endToEnd_twoIslands_countsAndPopsAndDistances() throws IOException {
        // 準備 city_population.txt
        String cityPop = String.join("\n",
                "City A: 1000",
                "City B: 2000",
                "City C: 1500",
                "City D:  500",
                "City E: 3000"
        );
        Path popFile = tempDir.resolve("city_population.txt");
        Files.writeString(popFile, cityPop);

        // 準備 road_network.txt
        // 島1：A-B-C  （A-B, B-C）
        // 島2：D-E    （D-E）
        String roads = String.join("\n",
                "City A: City B",
                "City B: City C",
                "City D: City E"
        );
        Path roadFile = tempDir.resolve("road_network.txt");
        Files.writeString(roadFile, roads);

        // 讀檔 + 建圖
        Map<String, City> cities = GraphIO.readCities(popFile);
        GraphIO.readRoadsAndLink(roadFile, cities);

        // Task-3：島的數量
        int islands = ArchipelagoGraph.countIslands(cities.values());
        assertEquals(2, islands);

        // Task-4：各島人口（順序依探索順序，這裡檢查「內容」即可）
        List<Long> pops = ArchipelagoGraph.islandPopulations(cities.values());
        // 島1人口 = 1000 + 2000 + 1500 = 4500
        // 島2人口 = 500 + 3000 = 3500
        assertTrue(pops.containsAll(List.of(4500L, 3500L)));
        assertEquals(2, pops.size());

        // Task-5：最短公路數
        City A = cities.get("City A");
        City C = cities.get("City C");
        City D = cities.get("City D");
        City E = cities.get("City E");

        // A -> C: A-B, B-C => 2
        assertEquals(2, ArchipelagoGraph.minHighwaysBetween(A, C));

        // D -> E: D-E => 1
        assertEquals(1, ArchipelagoGraph.minHighwaysBetween(D, E));

        // A -> E: 不同島 => -1
        assertEquals(-1, ArchipelagoGraph.minHighwaysBetween(A, E));
    }

    @Test
    void parser_trimsSpaces_and_handlesCommas() throws IOException {
        String cityPop = String.join("\n",
                "  Alpha  :  1,200  ",
                "Beta:300"
        );
        Path popFile = tempDir.resolve("city_population.txt");
        Files.writeString(popFile, cityPop);

        String roads = "Alpha: Beta\n";
        Path roadFile = tempDir.resolve("road_network.txt");
        Files.writeString(roadFile, roads);

        Map<String, City> cities = GraphIO.readCities(popFile);
        GraphIO.readRoadsAndLink(roadFile, cities);

        assertEquals(1200, cities.get("Alpha").getPopulation());
        assertEquals(300, cities.get("Beta").getPopulation());
        assertTrue(cities.get("Alpha").getNeighbors().contains(cities.get("Beta")));
        assertTrue(cities.get("Beta").getNeighbors().contains(cities.get("Alpha")));
    }

    @Test
    void distance_zero_when_same_city() {
        City x = new City("X", 10);
        assertEquals(0, ArchipelagoGraph.minHighwaysBetween(x, x));
    }
}
