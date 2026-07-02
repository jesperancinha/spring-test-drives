package org.jesperancinha.sftd.flash18.aop.afterthrowing;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsService;
import org.jesperancinha.sftd.flash18.aop.afterthrowing.service.impl.LyricsServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringFlash18Launcher {
    public static void main(String[] args) {
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
