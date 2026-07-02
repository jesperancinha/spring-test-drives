package org.jesperancinha.sftd.flash57.secured.services;

import org.jesperancinha.sftd.flash57.secured.domain.Throne;
import org.jesperancinha.sftd.flash57.secured.dto.ThroneDto;
import org.jesperancinha.sftd.flash57.secured.repository.ThroneRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.ORANGE;

@Service
@Profile({"prod", "!test && !acc"})
public class ThroneServiceImpl implements ThroneService {

    private final ThroneRepository throneRepository;

    public ThroneServiceImpl(ThroneRepository throneRepository) {
        this.throneRepository = throneRepository;
    }

    /**
     * (C) reate
     *
     * @param throne
     * @return
     */
    @PreAuthorize("isAuthenticated() && hasRole('ROLE_RULER') && #throne.keeper != null &&  #throne.keeper == authentication.name")
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
    @PreAuthorize("isAuthenticated() && hasRole('ROLE_RULER') && #throne.keeper != null &&  #throne.keeper == authentication.name")
    public void updateThrone(Throne throne) {
        this.throneRepository.save(throne);
    }

    /**
     * (R) ead
     *
     * @param id
     * @return {@link Throne}
     */
    @PostAuthorize("isAuthenticated() && returnObject.keeper == authentication.name")
    public Throne getThrone(final Long id) {
        final var throne = throneRepository.getOne(1L);
        ORANGE.printGenericLn(throne);
        return throne;
    }

    @PreAuthorize("isAuthenticated() && hasRole('ROLE_RULER')")
    public List<Throne> getAll() {
        return throneRepository.findAll();
    }

    /**
     * (D) elete
     *
     * @param throne
     */
    @PreAuthorize("isAuthenticated() && #throne.keeper == authentication.name && hasRole('ROLE_ADMIN')")
    public void deleteThrone(Throne throne) {
        throneRepository.delete(throne);
    }

    @PreAuthorize("isAuthenticated() and (hasRole('ROLE_ADMIN') or hasRole('ROLE_RULER') or hasRole('ROLE_DANCER'))")
    public String dance() {
        return "We would Pop Champagne and Raise our tones!";
    }
}
