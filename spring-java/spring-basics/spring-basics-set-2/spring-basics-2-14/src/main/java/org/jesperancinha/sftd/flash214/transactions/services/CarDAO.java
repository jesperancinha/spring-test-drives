package org.jesperancinha.sftd.flash214.transactions.services;

import org.jesperancinha.sftd.flash214.transactions.model.Car;

import java.util.List;

public interface CarDAO {
    Car createCar(Car car);

    Car getCarById(Long id);

    List<Car> getAllCars();

    boolean deleteCarById(Long id);
}
