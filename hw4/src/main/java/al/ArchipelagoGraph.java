package al;

import java.util.*;

public class ArchipelagoGraph {
    private ArchipelagoGraph() {}

    // Task 3: Count connected components (islands) in the graph
    public static int countIslands(Collection<City> cities) {
        return getConnectedComponents(cities).size();
    }

    // Task 4: Get the population of each connected component in the graph
    public static List<Long> islandPopulations(Collection<City> cities){
        // connected components
        List<List<City>> components = getConnectedComponents(cities);
        // sum populations for each component
        List<Long> sums = new ArrayList<>(components.size());
        // for each component
        for (List<City> component : components) {
            long sum = 0L;
            // sum populations of cities in this component
            for (City city : component) {
                sum += city.getPopulation();
            }
            // add to result list
            sums.add(sum);
        }
        return sums;
    }

    // Task 5: Get minimum number of highways between two cities and distance - BFS
    public static int minHighwaysBetween(City start, City target) {
        // If start and target are the same, distance is 0
        if(start.equals(target)) return 0;

        // BFS setup
        Map<City, Integer> distance = new HashMap<>();
        Deque<City> queue = new ArrayDeque<>();
        queue.add(start);
        distance.put(start, 0);

        // BFS loop
        while (!queue.isEmpty()) {
            // Dequeue a city
            City current = queue.poll();
            // Get current distance
            int currentDistance = distance.get(current);

            // Explore neighbors
            for (City neighbor : current.getNeighbors()) {
                // If neighbor already visited, skip
                if (distance.containsKey(neighbor)) continue;
                // Mark neighbor with distance and enqueue
                distance.put(neighbor, currentDistance + 1);
                // If neighbor is the target, return distance
                if (neighbor.equals(target)) return currentDistance + 1;
                // Enqueue neighbor
                queue.add(neighbor);
            }
        }
        return -1;
    }

    // Helper method to get all connected components using BFS
    private static List<List<City>> getConnectedComponents(Collection<City> cities) {
        // components: list of connected components
        List<List<City>> components = new ArrayList<>();
        // visited: set of visited cities
        Set<City> visited = new HashSet<>();

        // For each city, if not visited, perform BFS to find all connected cities
        for (City city : cities) {
            if (visited.contains(city)) continue;

            List<City> component = new ArrayList<>();
            Deque<City> queue = new ArrayDeque<>();
            queue.add(city);
            visited.add(city);

            // BFS
            while (!queue.isEmpty()) {
                // Dequeue a city
                City current = queue.poll();
                // Add to current component
                component.add(current);

                // Enqueue unvisited neighbors
                for (City neighbor : current.getNeighbors()) {
                    if (visited.add(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
            // Add the found component to components list
            components.add(component);
        }
        return components;
    }
}
