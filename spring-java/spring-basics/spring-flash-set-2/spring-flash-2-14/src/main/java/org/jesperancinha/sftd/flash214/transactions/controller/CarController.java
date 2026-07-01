package org.jesperancinha.sftd.flash214.transactions.controller;

import org.jesperancinha.sftd.flash214.transactions.model.Car;
import org.jesperancinha.sftd.flash214.transactions.services.CarReadCommittedDAO;
import org.jesperancinha.sftd.flash214.transactions.services.CarReadUncommittedDAO;
import org.jesperancinha.sftd.flash214.transactions.services.CarRepeatableReadDAO;
import org.jesperancinha.sftd.flash214.transactions.services.CarSerializableDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.*;

@RestController
public class CarController {

    private final CarReadUncommittedDAO carReadUncommittedDAO;

    private final CarReadCommittedDAO carReadCommittedDAO;

    private final CarRepeatableReadDAO carRepeatableReadDAO;

    private final CarSerializableDAO carSerializableDAO;

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public CarController(CarReadUncommittedDAO carReadUncommittedDAO,
                         CarReadCommittedDAO carReadCommittedDAO,
                         CarRepeatableReadDAO carRepeatableReadDAO,
                         CarSerializableDAO carSerializableDAO, JdbcTemplate jdbcTemplate) {
        this.carReadUncommittedDAO = carReadUncommittedDAO;
        this.carReadCommittedDAO = carReadCommittedDAO;
        this.carRepeatableReadDAO = carRepeatableReadDAO;
        this.carSerializableDAO = carSerializableDAO;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping(path = "/create/uncommitted",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Car createCar(
            @RequestBody
                    Car car) {
        jdbcTemplate.execute("DELETE FROM CAR;");
        final ExecutorService executorService = Executors.newFixedThreadPool(10);
        final Callable<Car> taskCreateCar = () -> {
            try {
                final Car car1 = carReadUncommittedDAO.createCar(car);
                ORANGE.printGenericLn(car1);
                return car1;
            } catch (final RuntimeException e) {
                RED.printExpectedException("We fail on purpose to allow the dirty read to happen", e);
            }
            return null;
        };
        final Callable<Car> taskDirtyReadCar = () -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                RED.printThrowableAndExit(e);
            }
            final Car carById = carReadUncommittedDAO.getCarById(1L);
            final List<Car> allCars = carReadUncommittedDAO.getAllCars();
            ORANGE.printGenericLn(allCars);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                RED.printThrowableAndExit(e);
            }
            return carById;
        };
        executorService.submit(taskCreateCar);
        executorService.submit(taskDirtyReadCar);
        executorService.submit(taskDirtyReadCar);
        executorService.submit(taskDirtyReadCar);
        Car call1 = null;
        try {
            call1 = taskCreateCar.call();
        } catch (Exception exception) {
            RED.printThrowableAndExit(exception);
        }
        Car call2 = null;
        try {
            call2 = taskDirtyReadCar.call();
        } catch (Exception exception) {
            RED.printThrowableAndExit(exception);
        }
        GREEN.printGenericLn(call1);
        GREEN.printGenericLn(call2);
        executorService.shutdown();
        try {
            final boolean b = executorService.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            RED.printThrowableAndExit(e);
        }
        return carReadUncommittedDAO.getCarById(1L);
    }

    @PostMapping(path = "/create/committed",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Car createCarCommitted(
            @RequestBody
                    Car car) {
        jdbcTemplate.execute("DELETE FROM CAR;");
        final ExecutorService executorService = Executors.newFixedThreadPool(2);
        final Callable<Car> taskCreateCar = () -> {
            List<Long> idsToRemove = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                final Car car1 = carReadCommittedDAO.createCar(car.clone());
                idsToRemove.add(car1.getId());
            }
            for (int i = 0; i < 10; i++) {
                carReadCommittedDAO.deleteCarById(idsToRemove.get(i));
            }
            final Car car1 = carReadCommittedDAO.createCar(car.clone());
            ORANGE.printGenericLn(car);
            return car1;
        };
        final Callable<Car> taskDirtyReadCar = () -> {
            final List<Car> allCars = carReadCommittedDAO.getAllCars();
            return allCars.get(0);
        };
        executorService.submit(taskCreateCar);
        executorService.submit(taskDirtyReadCar);
        Car call1 = null;
        try {
            call1 = taskCreateCar.call();
        } catch (Exception exception) {
            RED.printThrowableAndExit(exception);
        }
        Car call2 = null;
        try {
            call2 = taskDirtyReadCar.call();
        } catch (Exception exception) {
            RED.printThrowableAndExit(exception);
        }
        GREEN.printGenericLn(call1);
        GREEN.printGenericLn(call2);
        executorService.shutdown();
        try {
            final boolean b = executorService.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            RED.printThrowableAndExit(e);
        }
        return carReadCommittedDAO.getCarById(1L);

    }

    @PostMapping(path = "/create/repeatable",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Car createCarRepeatable(
            @RequestBody
                    Car car) {
        jdbcTemplate.execute("DELETE FROM CAR;");
        final ExecutorService executorService = Executors.newFixedThreadPool(2);
        final Callable<Car> taskCreateCar = () -> {
            List<Long> idsToRemove = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                final Car car1 = carRepeatableReadDAO.createCar(car.clone());
                idsToRemove.add(car1.getId());
            }
            for (int i = 0; i < 10; i++) {
                carRepeatableReadDAO.deleteCarById(idsToRemove.get(i));
            }
            carRepeatableReadDAO.createCar(car.clone());
            final Car car1 = carRepeatableReadDAO.createCar(car.clone());
            ORANGE.printGenericLn(car);
            return car1;
        };
        final Callable<Car> taskDirtyReadCar = () -> {
            final List<Car> allCars = carRepeatableReadDAO.getAllCars();
            return allCars.get(0);
        };
        executorService.submit(taskCreateCar);
        executorService.submit(taskDirtyReadCar);
        Car call1 = null;
        try {
            call1 = taskCreateCar.call();
        } catch (Exception exception) {
            RED.printThrowableAndExit(exception);
        }
        Car call2 = null;
        try {
            call2 = taskDirtyReadCar.call();
        } catch (Exception exception) {
            RED.printThrowableAndExit(exception);
        }
        GREEN.printGenericLn(call1);
        GREEN.printGenericLn(call2);
        executorService.shutdown();
        try {
            final boolean b = executorService.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            RED.printThrowableAndExit(e);
        }
        return carRepeatableReadDAO.getCarById(1L);
    }

    @PostMapping(path = "/create/serializable",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Car createCarSerializable(
            @RequestBody
                    Car car) {
        jdbcTemplate.execute("DELETE FROM CAR;");
        final ExecutorService executorService = Executors.newFixedThreadPool(2);
        final Callable<Car> taskCreateCar = () -> {
            List<Long> idsToRemove = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                final Car car1 = carSerializableDAO.createCar(car.clone());
                idsToRemove.add(car1.getId());
            }
            for (int i = 0; i < 10; i++) {
                carSerializableDAO.deleteCarById(idsToRemove.get(i));
            }
            carSerializableDAO.createCar(car.clone());
            final Car car1 = carSerializableDAO.createCar(car.clone());
            ORANGE.printGenericLn(car);
            return car1;
        };
        final Callable<Car> taskDirtyReadCar = () -> {
            final List<Car> allCars = carSerializableDAO.getAllCars();
            return allCars.get(0);
        };
        executorService.submit(taskCreateCar);
        executorService.submit(taskDirtyReadCar);
        Car call1 = null;
        try {
            call1 = taskCreateCar.call();
        } catch (Exception exception) {
            RED.printThrowableAndExit(exception);
        }
        Car call2 = null;
        try {
            call2 = taskDirtyReadCar.call();
        } catch (Exception exception) {
            RED.printThrowableAndExit(exception);
        }
        GREEN.printGenericLn(call1);
        GREEN.printGenericLn(call2);
        executorService.shutdown();
        try {
            final boolean b = executorService.awaitTermination(20, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            RED.printThrowableAndExit(e);
        }
        return carSerializableDAO.getCarById(1L);
    }
}
