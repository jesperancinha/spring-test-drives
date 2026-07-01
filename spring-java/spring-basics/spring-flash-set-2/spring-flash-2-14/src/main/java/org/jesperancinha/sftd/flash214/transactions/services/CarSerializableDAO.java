package org.jesperancinha.sftd.flash214.transactions.services;

import org.jesperancinha.sftd.flash214.transactions.model.Car;
import org.jesperancinha.sftd.flash214.transactions.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.*;
import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.outSpace;
import static org.jesperancinha.console.consolerizer.console.ConsolerizerGraphs.printRainbowFlag;

@Service
public class CarSerializableDAO implements CarDAO {

    private final CarRepository carRepository;

    public CarSerializableDAO(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car createCar(Car car) {
        final var savedCar = this.carRepository.save(car);
        outSpace()
                .cyan("Saving car:")
                .jsonPrettyPrint(savedCar)
                .reset();
        GREEN.printGenericLn("Saving car %s", savedCar);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            RED.printThrowableAndExit(e);
        }
        return savedCar;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.SERIALIZABLE)
    @Override
    public Car getCarById(Long id) {
        final Car car = carRepository.findById(id).orElse(null);
        YELLOW.printGenericLn("This is the car I get -> %s", car);
        return car;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.SERIALIZABLE)
    public List<Car> getAllCars() {
        outSpace().green("Creating empty car")
                .jsonPrettyPrint(this.carRepository.save(Car.builder().build()));
        for (int i = 0; i < 10; i++) {
            final List<Car> allCars1 = carRepository.findAll();
            try {
                sleep(50);
            } catch (InterruptedException e) {
                RED.printThrowableAndExit(e);
            }
            outSpace().green("There are still %d cars available!", allCars1.size())
                    .jsonPrettyPrint(allCars1).reset();
        }
        printRainbowFlag("Finished 20 read tries!");
        return carRepository.findAll();
    }

    @Override
    public boolean deleteCarById(Long id) {
        carRepository.deleteById(id);
        try {
            sleep(500);
        } catch (InterruptedException e) {
            RED.printThrowableAndExit(e);
        }
        outSpace().red("Deleted car with id %d", id).reset();
        return true;
    }
}
