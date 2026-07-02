package org.jesperancinha.sftd.flash517.context;

public class FishService {

    private Fish fish;

    public FishService() {
    }

    public FishService(Fish fish) {
        this.fish = fish;
    }

    public Fish getFish() {
        return fish;
    }

    public void setFish(Fish fish) {
        this.fish = fish;
    }

    @Override
    public String toString() {
        return "FishService{" +
                "fish=" + fish +
                '}';
    }
}
