package org.jesperancinha.sftd.mastery1.french.music.dao;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.mastery1.french.music.domain.Member;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.*;
import static org.springframework.transaction.TransactionDefinition.ISOLATION_SERIALIZABLE;
import static org.springframework.transaction.TransactionDefinition.PROPAGATION_REQUIRES_NEW;


@Component
public class MemberBean {

    private final PlatformTransactionManager platformTransactionManager;
    private final MemberDao memberDao;

    public MemberBean(PlatformTransactionManager platformTransactionManager, MemberDao memberDao) {
        this.platformTransactionManager = platformTransactionManager;
        this.memberDao = memberDao;
    }

    public void persistMemberRollback(final Member member) {
        BLUE.printGenericTitleLn("Beginning Member transaction to persist and rollback");
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.setIsolationLevel(ISOLATION_SERIALIZABLE);
        transactionTemplate.setPropagationBehavior(PROPAGATION_REQUIRES_NEW);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    final var id = memberDao.create(member);
                    ConsolerizerComposer.out(" ")
                            .brightGreen("A member has been created with id")
                            .green(id)
                            .brightGreen(". The details are")
                            .green(id).toConsoleLn();
                    ConsolerizerComposer.out(" ")
                            .blue("The status of the transaction is")
                            .brightBlue(transactionStatus)
                            .blue(".")
                            .toConsoleLn();
                    transactionStatus.setRollbackOnly();
                } catch (Exception e) {
                    RED.printThrowableAndExit(e);
                }
            }
        });

        BLUE.printGenericTitleLn("Finishing Member transaction");
        RED.printGenericTitleLn("Nothing happened!");
    }

    public void persistMember(final Member member) {
        BLUE.printGenericTitleLn("Beginning Member transaction to persist");
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.setIsolationLevel(ISOLATION_SERIALIZABLE);
        transactionTemplate.setPropagationBehavior(PROPAGATION_REQUIRES_NEW);
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus transactionStatus) {
                try {
                    final var id = memberDao.create(member);
                    ConsolerizerComposer.out(" ")
                            .brightGreen("A member has been created with id")
                            .green(id)
                            .brightGreen(". The details are")
                            .green(id).toConsoleLn();
                    ConsolerizerComposer.out(" ")
                            .blue("The status of the transaction is")
                            .brightBlue(transactionStatus)
                            .blue(".")
                            .toConsoleLn();
                } catch (Exception e) {
                    RED.printThrowableAndExit(e);
                }
            }
        });

        BLUE.printGenericTitleLn("Finishing Member transaction");
        GREEN.printGenericTitleLn("It's in! It Happened!");
    }
}
