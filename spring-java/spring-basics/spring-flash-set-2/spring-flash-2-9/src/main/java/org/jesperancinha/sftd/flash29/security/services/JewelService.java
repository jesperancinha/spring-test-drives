package org.jesperancinha.sftd.flash29.security.services;

import org.jesperancinha.sftd.flash29.security.domain.Jewel;
import org.jesperancinha.sftd.flash29.security.dto.JewelDto;
import org.jesperancinha.sftd.flash29.security.repository.JewelRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.jesperancinha.console.consolerizer.common.ConsolerizerColor.ORANGE;

@Service
public class JewelService {

    private final JewelRepository jewelRepository;

    public JewelService(JewelRepository jewelRepository) {
        this.jewelRepository = jewelRepository;
    }

    /**
     * (C) reate
     *
     * @param jewel
     * @return
     */
    @PreAuthorize("hasRole('WRITE') && hasRole('ADMIN') && #jewel.guardian != null &&  #jewel.guardian == authentication.name")
    public JewelDto createJewel(JewelDto jewel) {
        final var savedJewel = this.jewelRepository.save(Jewel.builder().jewelType(jewel.getJewelType()).guardian(jewel.getGuardian()).build());
        return JewelDto.builder()
                .jewelType(savedJewel.getJewelType())
                .guardian(savedJewel.getGuardian())
                .build();
    }


    /**
     * (U) pdate
     *
     * @param jewel {@link Jewel}
     */
    @PreAuthorize("hasRole('ROLE_ADMIN') && #jewel.guardian != null &&  #jewel.guardian == authentication.name")
    public JewelDto updateJewel(JewelDto jewel) {
        final Jewel save = this.jewelRepository.save(Jewel
                .builder()
                .jewelType(jewel.getJewelType())
                .guardian(jewel.getGuardian())
                .build());
        return JewelDto.builder()
                .jewelType(save.getJewelType())
                .guardian(save.getGuardian())
                .build();
    }

    /**
     * (R) ead
     *
     * @param id
     * @return {@link Jewel}
     */
    @PostAuthorize("returnObject.guardian == authentication.name")
    public JewelDto getJewelById(final Long id) {
        final Jewel jewel = jewelRepository.findById(id).orElse(null);
        if (Objects.isNull(jewel)) {
            return null;
        }
        ORANGE.printGenericLn(jewel);
        return JewelDto.builder().jewelType(jewel.getJewelType()).guardian(jewel.getGuardian()).build();
    }

    public List<JewelDto> getAll() {
        return jewelRepository.findAll().stream().map(jewel ->
                JewelDto.builder()
                        .jewelType(jewel.getJewelType())
                        .guardian(jewel.getGuardian())
                        .build())
                .collect(Collectors.toList());
    }

    /**
     * (D) elete
     *
     * @param jewel
     */
    @PreAuthorize("#jewel.guardian == authentication.name && hasRole('ROLE_ADMIN')")
    public void deleteJewel(JewelDto jewel) {
        jewelRepository.delete(Jewel.builder().jewelType(jewel.getJewelType()).guardian(jewel.getGuardian()).build());
    }
}
