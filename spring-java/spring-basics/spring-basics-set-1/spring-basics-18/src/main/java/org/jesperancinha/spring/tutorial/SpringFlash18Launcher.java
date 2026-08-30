package org.jesperancinha.spring.tutorial;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.spring.tutorial.service.LyricsService;
import org.jesperancinha.spring.tutorial.service.LyricsServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringFlash18Launcher {
    static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(SpringFlash18Launcher.class, args);
        mutateLyricsServiceImpl(ac);
    }

    static void mutateLyricsServiceImpl(ApplicationContext ac) {
        LyricsService lyricsService = ac.getBean("lyricsServiceImpl", LyricsServiceImpl.class);
        try {
            try {
                lyricsService.enumerateLyric1();
            } finally {
                try {
                    lyricsService.resultLyric1();
                } finally {
                    try {
                        lyricsService.enumerateLyric2();
                    } finally {
                        try {
                            lyricsService.resultLyric2();
                        } finally {
                            try {
                                lyricsService.enumerateLyric3();
                            } finally {
                                try {
                                    lyricsService.resultLyric3();
                                } finally {
                                    lyricsService.resultLyric4();
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            ConsolerizerComposer.outSpace().red(e).reset();
        }
    }
}
