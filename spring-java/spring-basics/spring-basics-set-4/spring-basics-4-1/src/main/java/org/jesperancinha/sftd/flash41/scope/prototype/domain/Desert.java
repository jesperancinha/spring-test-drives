package org.jesperancinha.sftd.flash41.scope.prototype.domain;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.jesperancinha.console.consolerizer.common.ConsolerizerColor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.MAGENTA;

@Component
@Scope("prototype")
public class Desert {

    private static final String[] DESERT_MISS_THE_RAIN = new String[]{
            "I step off the train",
            "I'm walking down your street again",
            "And past your door",
            "I guess you don't live there anymore",
    };

    private String statement;

    public Desert() {
        this.statement = DESERT_MISS_THE_RAIN[(int) (DESERT_MISS_THE_RAIN.length * Math.random())];
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    @PostConstruct
    public void init() {
        GREEN.printGenericLn("This method gets called on the initialization of the bean");
    }

    @PreDestroy
    public void destroy() {
        GREEN.printGenericLn("This method won't be called!");
        ConsolerizerColor.RED.printThrowableAndExit(new RuntimeException("If this get's called I'll shut down the process. This just doesn't happen, because the Spring Container is unaware of my existence. I'm prototype"));
    }

    public void reachableDestroy() {
        MAGENTA.printGenericLn("Since you miss me like the desert miss the rain, you need to keep a reference to me and make sure to call your own destroy mechanism");
    }
}
