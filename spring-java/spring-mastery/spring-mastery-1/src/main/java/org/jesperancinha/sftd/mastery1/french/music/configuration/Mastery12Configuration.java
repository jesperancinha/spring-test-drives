package org.jesperancinha.sftd.mastery1.french.music.configuration;

import jakarta.annotation.PostConstruct;
import org.jesperancinha.sftd.mastery1.french.music.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@PropertySource("classpath:extras.properties")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "mastery1")
@Profile("prod")
@Qualifier("mastery1Configuration")
public class Mastery12Configuration implements Mastery1Configuration {

    private String group;

    private List<String> members;

    private final MemberRepository memberRepository;

    public Mastery12Configuration(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @PostConstruct
    public void afterConfig() {
    }

    private void resetAll() {
        memberRepository.deleteAll();
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    @Override
    public void makeAllTest() {

    }
}
