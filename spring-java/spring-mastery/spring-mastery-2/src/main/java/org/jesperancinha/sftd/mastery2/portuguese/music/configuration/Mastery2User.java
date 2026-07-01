package org.jesperancinha.sftd.mastery2.portuguese.music.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor
public class Mastery2User {
    String name;
    String password;
    @EqualsAndHashCode.Exclude
    String role;

}
