package org.jesperancinha.sftd.flash214.transactions.repository;

import org.jesperancinha.sftd.flash214.transactions.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED,
        isolation = Isolation.READ_UNCOMMITTED)
public interface CarRepository extends JpaRepository<Car, Long> {
}
