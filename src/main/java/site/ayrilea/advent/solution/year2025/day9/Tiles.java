package site.ayrilea.advent.solution.year2025.day9;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Tiles {

    private final Set<Tile> perimeterTiles;
    private final List<Tile> redTiles;

    Tiles(Set<Tile> greenTiles, List<Tile> redTiles) {
        Set<Tile> perimeterTiles = new HashSet<>();
        perimeterTiles.addAll(greenTiles);
        perimeterTiles.addAll(redTiles);

        this.perimeterTiles = perimeterTiles;
        this.redTiles = redTiles;
    }

    Set<Tile> getPerimeterTiles() {
        return perimeterTiles;
    }

    List<Tile> getRedTiles() {
        return redTiles;
    }
}
