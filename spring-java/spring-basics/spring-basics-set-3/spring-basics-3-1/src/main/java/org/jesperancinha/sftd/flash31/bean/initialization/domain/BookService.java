package org.jesperancinha.sftd.flash31.bean.initialization.domain;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.ORANGE;
import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.RED;
import static org.jesperancinha.console.consolerizer.console.ConsolerizerGraphs.printRainbowFlag;

public class BookService implements DisposableBean, InitializingBean {

    private Book book;

    @Override
    public void destroy() {
        RED.printGenericLn("This instance %s is going to be removed", this);
    }

    @Override
    public void afterPropertiesSet() {
        ORANGE.printGenericLn("Properties have been set! We only have one, and it is this book -> %s", book);
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "BookService{" +
                "book=" + book +
                '}';
    }

    private void initializationExtra() {
        printRainbowFlag("You've reached something special!");
        ORANGE.printGenericLn("Properties have been set! We only have one, and it is this book -> %s", book);
    }

    private void destructionExtra() {
        printRainbowFlag("You've reached something special!");
        ORANGE.printGenericLn("The book will now be archived-> %s", book);
    }
}
