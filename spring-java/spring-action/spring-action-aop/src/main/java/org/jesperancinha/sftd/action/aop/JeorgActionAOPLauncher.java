package org.jesperancinha.sftd.action.aop;

import org.jesperancinha.sftd.action.aop.catchers.*;
import org.jesperancinha.sftd.action.aop.catchers.cod.CodCatcher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:bean.xml")
public class JeorgActionAOPLauncher implements ApplicationRunner {

    private final BonitoCatcher bonitoCatcher;
    private final CodCatcher codCatcher;
    private final GambaFoodCatcher gambaFoodCatcher;
    private final MackerelCatcher mackerelCatcher;
    private final MegaTunaCatcher megaTunaCatcher;
    private final SardineCatcher sardineCatcher;
    private final ShrimpCatcher shrimpCatcher;
    private final TunaCatcher tunaCatcher;

    public JeorgActionAOPLauncher(
            @Qualifier("bonitoCatcher")
            final BonitoCatcher bonitoCatcher,
            @Qualifier("codCatcher")
            final CodCatcher codCatcher,
            @Qualifier("gambaFoodCatcher")
            final GambaFoodCatcher gambaFoodCatcher,
            @Qualifier("mackerelCatcher")
            final MackerelCatcher mackerelCatcher,
            @Qualifier("megaTunaCatcher")
            final MegaTunaCatcher megaTunaCatcher,
            @Qualifier("sardineCatcher")
            final SardineCatcher sardineCatcher,
            @Qualifier("shrimpCatcher")
            final ShrimpCatcher shrimpCatcher,
            @Qualifier("tunaCatcher")
            final TunaCatcher tunaCatcher) {
        this.bonitoCatcher = bonitoCatcher;
        this.codCatcher = codCatcher;
        this.gambaFoodCatcher = gambaFoodCatcher;
        this.mackerelCatcher = mackerelCatcher;
        this.megaTunaCatcher = megaTunaCatcher;
        this.sardineCatcher = sardineCatcher;
        this.shrimpCatcher = shrimpCatcher;
        this.tunaCatcher = tunaCatcher;
    }

    public static void main(String[] args) {
        SpringApplication.run(JeorgActionAOPLauncher.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        bonitoCatcher.catchByHand();
        bonitoCatcher.catchByHandExtra();
        bonitoCatcher.catchWithClaws();
        codCatcher.catchByHand();
        gambaFoodCatcher.catchByHand();
        mackerelCatcher.catchWithNet();
        megaTunaCatcher.catchWithNet();
        sardineCatcher.catchWithNet();
        shrimpCatcher.catchByHand();
        tunaCatcher.catchByHand();
    }
}
