package org.jesperancinha.sftd.action.ioc.interfaces;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;

public interface RestaurantInterface {

    static void tutorialOne() {
        ConsolerizerComposer.outSpace()
                .cyan("Check the menu.")
                .reset();

    }

    static void tutorialTwo() {
        ConsolerizerComposer.outSpace()
                .green("Order a meal.")
                .reset();

    }

    class Table {
        public Table() {
            ConsolerizerComposer.outSpace()
                    .yellow("Table is created!")
                    .reset();
        }
    }

}
