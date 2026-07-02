package org.jesperancinha.sftd.flash214.transactions.services;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash214.transactions.model.Car;
import org.jesperancinha.sftd.flash214.transactions.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.RED;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.YELLOW;

@Service
public class CarReadCommittedDAO implements CarDAO {

    private final CarRepository carRepository;

    public CarReadCommittedDAO(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional(propagation = Propagation.REQUIRED,
            isolation = Isolation.READ_COMMITTED,
            rollbackFor = RuntimeException.class)
    @Override
    public Car createCar(Car car) {
        final var savedCar = this.carRepository.save(car);
        ConsolerizerComposer.outSpace()
                .cyan("Saving car:")
                .jsonPrettyPrint(savedCar)
                .reset();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            RED.printThrowableAndExit(e);
        }
        return savedCar;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED)
    @Override
    public Car getCarById(Long id) {
        final Car car = carRepository.findById(id).orElse(null);
        YELLOW.printGenericLn("This is the car I get -> %s", car);
        return car;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW,
            isolation = Isolation.READ_COMMITTED)
    public List<Car> getAllCars() {
        for (int i = 0; i < 10; i++) {
            final List<Car> allCars1 = carRepository.findAll();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                RED.printThrowableAndExit(e);
            }
            ConsolerizerComposer.outSpace()
                    .green("There are still %d cars available!", allCars1.size())
                    .reset();
        }
        return carRepository.findAll();
    }

    @Override
    public boolean deleteCarById(Long id) {
        carRepository.deleteById(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            RED.printThrowableAndExit(e);
        }
        ConsolerizerComposer.outSpace().red("Deleted car with id %d", id).reset();
        return true;
    }
}
