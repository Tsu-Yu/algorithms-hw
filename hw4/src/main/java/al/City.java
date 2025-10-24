package al;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

// Task 1: City Model
public class City {
    private final String name;
    private final long population;

    // use hashset to avoid duplicate neighbors
    private final Set<City> neighbors = new HashSet<>();

    public City (String name, long population) {
        this.name = Objects.requireNonNull(name).trim();
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    public Set<City> getNeighbors() {
        return neighbors;
    }
    
    // Bi-directed graph: add each other as neighbors
    public void addNeighbor(City other) {
        // Check if other is null or same as this
        if (other == null || other == this)  return;
        this.neighbors.add(other);
        other.neighbors.add(this);
    }

    @Override
    public boolean equals(Object o) {
        // Two cities are equal if their names are equal
        if (this == o) return true;
        // Check if the object is an instance of City
        if (!(o instanceof City)) return false;
        // Then, we can safely transform o to City
        City city = (City) o;
        // Compare names for equality
        return name.equals(city.name);
    }

    @Override
    // same objects have same hashcode
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    // for debugging
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", population=" + population +
                '}';
    }
}
