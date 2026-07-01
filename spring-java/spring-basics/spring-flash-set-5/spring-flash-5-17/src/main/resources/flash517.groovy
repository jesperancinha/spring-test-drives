import org.jesperancinha.sftd.flash517.context.Fish
import org.jesperancinha.sftd.flash517.context.FishService

beans {
    fish(Fish) {
        name = "Cod"
    }
    fishService(FishService) {
        fish = fish
    }
}