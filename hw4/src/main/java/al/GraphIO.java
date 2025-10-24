package al;

import java.util.Map;
import java.util.HashMap;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;

// Task 2: Parse files and build the graph
public class GraphIO {
    private GraphIO() {}

    public static Map<String, City> readCities(Path cityPopulationFile) throws IOException {
        // key: city name, value: City object
        Map<String, City> map = new HashMap<>();
        // Read each line from the file
        for (String raw : Files.readAllLines(cityPopulationFile)) {
            // remove leading/trailing whitespace
            String line = raw.trim();  
            // Skip empty lines
            if (line.isEmpty()) continue;
            // Split line into name and population
            String[] parts = line.split(":");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Bad population line: " + line);
            }
            String name = parts[0].trim();
            // Remove commas from population string and parse to long (prevent number like 1,000)
            String popStr = parts[1].trim().replaceAll(",", "");
            long pop = Long.parseLong(popStr);

            // Create City object and put into map
            map.put(name, new City(name, pop));
        }
        return map;
    }

    public static void readRoadsAndLink(Path roadNetworkFile, Map<String, City> cities) throws IOException {
        // Read each line from the file
        for (String raw : Files.readAllLines(roadNetworkFile)) {
            // remove leading/trailing whitespace
            String line = raw.trim();
            // Skip empty lines
            if (line.isEmpty()) continue;
            // Split line into two city names
            String[] parts = line.split(":");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Bad road line: " + line);
            }
            String cityAName = parts[0].trim();
            String cityBName = parts[1].trim();

            // Get this two Cities objects from the map
            City cityA = cities.get(cityAName);
            City cityB = cities.get(cityBName);
            // If either city is not found, throw exception
            if (cityA == null || cityB == null) {
                throw new IllegalArgumentException("Unknown city in road line: " + line);
            }
            // Link the two cities as neighbors (bi-directional)
            cityA.addNeighbor(cityB);
        }
    }
    
}
