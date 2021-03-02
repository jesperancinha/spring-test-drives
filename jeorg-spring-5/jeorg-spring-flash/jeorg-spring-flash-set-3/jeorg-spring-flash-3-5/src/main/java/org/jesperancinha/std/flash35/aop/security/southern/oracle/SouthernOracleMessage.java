package org.jesperancinha.std.flash35.aop.security.southern.oracle;

import static org.jesperancinha.console.consolerizer.ConsolerizerColor.GREEN;
import static org.jesperancinha.console.consolerizer.ConsolerizerGraphs.printUnicornsLn;

public class SouthernOracleMessage {
    public void giveANewName() {
        printUnicornsLn(100);
        GREEN.printGenericLn("The Empress needs a new name");
        printUnicornsLn(100);
    }
}