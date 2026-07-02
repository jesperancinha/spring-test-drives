package org.jesperancinha.sftd.flash216.platformtransactionmanagement.service;

import org.jesperancinha.sftd.flash216.platformtransactionmanagement.domain.Award;

import java.time.LocalDateTime;
import java.util.List;

public interface AwardDao {

    void createTables();

    void resetDatabase();

    Award create(String artist, String award, LocalDateTime awardDateTime);

    Award createRollback(String artist, String award, LocalDateTime awardDateTime);

    Award createFailRollback(String artist, String award, LocalDateTime awardDateTime);

    Award createNoTransaction(String artist, String award, LocalDateTime awardDateTime);

    List<Award> listAwards();
}
