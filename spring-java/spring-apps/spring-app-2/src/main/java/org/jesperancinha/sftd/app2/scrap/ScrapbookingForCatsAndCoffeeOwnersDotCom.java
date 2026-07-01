package org.jesperancinha.sftd.app2.scrap;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.app2.scrap.dto.ScrapbookDto;
import org.jesperancinha.sftd.app2.scrap.model.ScrapbookType;
import org.jesperancinha.sftd.app2.scrap.service.ScrapbookService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static org.jesperancinha.console.consolerizer.console.ConsolerizerComposer.title;

@SpringBootApplication
public class ScrapbookingForCatsAndCoffeeOwnersDotCom implements ApplicationRunner {

    private final ScrapbookService scrapbookService;

    public ScrapbookingForCatsAndCoffeeOwnersDotCom(
            @Qualifier("scrapbookClosedService")
                    ScrapbookService scrapbookService) {
        this.scrapbookService = scrapbookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ScrapbookingForCatsAndCoffeeOwnersDotCom.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final ScrapbookDto scrapbook = new ScrapbookDto();
        scrapbook.setName("Kittens are the best!");
        scrapbook.setScrapbookType(ScrapbookType.CATS);
        scrapbook.setPages(222);
        scrapbookService.createScrapbook(scrapbook);
        ConsolerizerComposer.outSpace()
                .green(title("I. Codebase"))
                .blue("If you look at this whole repo, you will see many apps")
                .blue("This does not follow principle one of the 12 factor app")
                .blue("However, if this module was in a single repo with nothing but library dependencies,")
                .blue("it would still follow the 12 factor app principle.")
                .blue("This means that all of the applications in this module form distributed system.")
                .blue("At the same time, each individual app only has a codebase and that does follow the principle")
                .blue("Individually, they all follow principle I")
                .green(title("II. Dependencies"))
                .blue("This is essentially what the pom file is all about")
                .blue("In this repo, we use maven as a package dependency manager.")
                .green(title("III. Config"))
                .blue("In Spring applications such as the ones provided in this repo, configuration is provided by the use of application.properties files.")
                .blue("In our case a dash between application and a name will define properties exclusively applied to that profile.")
                .blue("This principle means that an application should have profile specific configurations, namely designated for develop, acceptance and productoion")
                .green(title("IV. Backing services"))
                .blue("In this application, all our data will be stored in memory in database app2.")
                .blue("The database we have configured in an H2 database.")
                .blue("However, if you notice closely in the code, there is no reference to third parties")
                .blue("Using Spring in this case allows us to create backing services which can be used regardless of what the third party providing the database is.")
                .green(title("V. Build, release, run"))
                .blue("If you run this application via your IDE, you will realize that you just pressed a button in order to do it.")
                .blue("The reason for this is that your IDE has built the code, combined the built code with the compiled code into a package and finally it ran it as a JAR file.")
                .blue("It is this combination between compiled code and configs that make our release.")
                .green(title("VI. Processes"))
                .blue("An application must run in a stateless mode and no data should be shared in session.")
                .blue("Data that needs to be kept, must be stored in a stateful backing service.")
                .green(title("VII. Port binding"))
                .blue("This application binds to port 8081.")
                .blue("It is completely self-contained. These these are two characteristics that define port binding.")
                .blue("Because this application exposes its services through port 8081, it can be used as a backing service to another application")
                .green(title("VIII. Concurrency"))
                .blue("Multithreading")
                .green(title("IX. Disposability"))
                .blue("This application makes a graceful shutdown.")
                .green(title("X. Dev/prod parity"))
                .blue("The dev, acc and prod use roughly the same configuration. It differs only, for example, in which database we use")
                .green(title("XI. Logs"))
                .blue("When logs are treated in streams")
                .green(title("XII. Admin processes"))
                .blue("ETL processes where databases get migrated. REPL one off processes.")
                .reset();
    }
}
