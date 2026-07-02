package org.jesperancinha.sftd.flash35.aop.security.southern.oracle;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.GREEN;
import static org.jesperancinha.console.consolerizer.console.ConsolerizerGraphs.printUnicornsLn;

public class SouthernOracleMessage {
    public void giveANewName() {
        printUnicornsLn(100);
        GREEN.printGenericLn("The Empress needs a new name");
        printUnicornsLn(100);
    }
}