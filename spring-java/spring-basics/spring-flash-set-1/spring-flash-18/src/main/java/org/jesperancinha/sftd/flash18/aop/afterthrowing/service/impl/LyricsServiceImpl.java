package org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl;

import org.springframework.stereotype.Service;

/**
 * Service used as a source for the tested {@link org.aspectj.lang.annotation.Aspect} of this module.
 */
@Service
public class LyricsServiceImpl implements LyricsService {

    @Override
    public void enumerateLyric1() {
        throw new RuntimeException("Pick up the phone");
    }

    @Override
    public void resultLyric1() {
        throw new RuntimeException("Answer the phone");

    }

    @Override
    public void enumerateLyric2() {
        throw new RuntimeException("Talk friendly");

    }

    @Override
    public void resultLyric2() {
        throw new RuntimeException("Be nice");

    }

    @Override
    public void enumerateLyric3() {
        throw new RuntimeException("Ask how's it going");

    }

    @Override
    public void resultLyric3() {
        throw new RuntimeException("Maybe arrange to travel");

    }

    @Override
    public void resultLyric4() {
        throw new RuntimeException("It is overbooked!");
    }
}
