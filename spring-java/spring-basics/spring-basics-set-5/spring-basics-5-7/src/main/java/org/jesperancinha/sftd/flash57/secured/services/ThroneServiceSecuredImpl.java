package org.jesperancinha.sftd.flash57.secured.services;

import org.jesperancinha.sftd.flash57.secured.domain.Throne;
import org.jesperancinha.sftd.flash57.secured.dto.ThroneDto;
import org.jesperancinha.sftd.flash57.secured.repository.ThroneRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.ORANGE;

@Service
@Profile("acc")
public class ThroneServiceSecuredImpl implements ThroneService {

    private final ThroneRepository throneRepository;

    public ThroneServiceSecuredImpl(ThroneRepository throneRepository) {
        this.throneRepository = throneRepository;
    }

    /**
     * (C) reate
     *
     * @param throne
     * @return
     */
    @Secured({"ROLE_RULER"})
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
    @Secured({"ROLE_RULER"})
    public void updateThrone(Throne throne) {
        this.throneRepository.save(throne);
    }

    /**
     * (R) ead
     *
     * @param id
     * @return {@link Throne}
     */
    @Secured({"IS_AUTHENTICATED_FULLY", "ROLE_RULER"})
    public Throne getThrone(final Long id) {
        final var throne = throneRepository.getOne(id);
        ORANGE.printGenericLn(throne);
        return throne;
    }

    @Secured({"ROLE_RULER"})
    public List<Throne> getAll() {
        return throneRepository.findAll();
    }

    /**
     * (D) elete
     *
     * @param throne
     */
    @Secured({"ROLE_RULER"})
    public void deleteThrone(Throne throne) {
        throneRepository.delete(throne);
    }

    @Secured({"ROLE_RULER", "ROLE_DANCER", "ROLE_ADMIN"})
    public String dance() {
        return "We would Pop Champagne and Raise our tones!";
    }
}
