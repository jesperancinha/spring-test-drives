package org.jesperancinha.spring.flash57.secured.services;

import jakarta.annotation.security.RolesAllowed;
import org.jesperancinha.spring.flash57.secured.domain.Throne;
import org.jesperancinha.spring.flash57.secured.dto.ThroneDto;
import org.jesperancinha.spring.flash57.secured.repository.ThroneRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.ORANGE;

@Service
@Profile("test")
public class ThroneServiceJsr250Impl implements ThroneService {

    private final ThroneRepository throneRepository;

    public ThroneServiceJsr250Impl(ThroneRepository throneRepository) {
        this.throneRepository = throneRepository;
    }

    /**
     * (C) reate
     *
     * @param throne
     * @return
     */
    @RolesAllowed("RULER")
    public ThroneDto createThrone(Throne throne) {
        final Throne save = this.throneRepository.save(throne);
        return ThroneDto
                .builder()
                .throneType(save.getThroneType())
                .keeper(save.getKeeper())
                .build();
    }


    /**
     * (U) pdate
     *
     * @param throne
     */
    @RolesAllowed("RULER")
    public void updateThrone(Throne throne) {
        this.throneRepository.save(throne);
    }

    /**`
     * (R) ead
     *
     * @param id
     * @return {@link Throne}
     */
    @RolesAllowed("RULER")
    public Throne getThrone(final Long id) {
        final var throne = throneRepository.getOne(id);
        ORANGE.printGenericLn(throne);
        return throne;
    }

    @RolesAllowed("RULER")
    public List<Throne> getAll() {
        return throneRepository.findAll();
    }

    /**
     * (D) elete
     *
     * @param throne
     */
    @RolesAllowed("RULER")
    public void deleteThrone(Throne throne) {
        throneRepository.delete(throne);
    }

    @RolesAllowed({"RULER", "ADMIN", "DANCER"})
    public String dance() {
        return "We would Pop Champagne and Raise our tones!";
    }
}
