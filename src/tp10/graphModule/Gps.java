package tp10.graphModule;

import tp02.tp03.listModule.SimpleList;

public class Gps {
    private SimpleList<String> path;
    private int totalDistance;

    public Gps(SimpleList<String> path, int totalDistance) {
        this.path = path;
        this.totalDistance = totalDistance;
    }

    public SimpleList<String> getPath() {
        return path;
    }

    public int getTotalDistance() {
        return totalDistance;
    }
}