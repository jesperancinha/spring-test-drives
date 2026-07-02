package org.jesperancinha.sftd.flash57.secured.services;

import org.jesperancinha.sftd.flash57.secured.domain.Throne;
import org.jesperancinha.sftd.flash57.secured.dto.ThroneDto;

import java.util.List;

public interface ThroneService {

    ThroneDto createThrone(final Throne throne);

    void updateThrone(Throne throne);

    Throne getThrone(final Long id);

    List<Throne> getAll();

    void deleteThrone(final Throne throne);

    String dance();
}
