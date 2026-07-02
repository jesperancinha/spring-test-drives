package org.jesperancinha.sftd.flash5.persistence.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PotatoService.class)
class PotatoServiceTest {

    @Autowired
    private PotatoService potatoService;

    @MockitoBean
    private PotatoRepository potatoRepository;

    @Captor
    private ArgumentCaptor<Potato> potatoArgumentCaptor;

    @BeforeEach
    public void setUp() {
        final var newPotato1 = new Potato();
        newPotato1.setForm("Sweet");
        final var newPotato2 = new Potato();
        newPotato2.setForm("Kind");
        when(potatoRepository.findById(1L)).thenReturn(Optional.of(newPotato1));
        when(potatoRepository.findById(2L)).thenReturn(Optional.of(newPotato2));
        assertThat(potatoRepository).isNotNull();
    }

    @Test
    void testCreatePotatoWhenCreatedThenCanCallFromDatabase() {
        final var newPotato = new Potato();
        newPotato.setForm("Kindest of them all");
        final var savedPotato = new Potato();
        savedPotato.setForm("Kindest of the universe");
        savedPotato.setId(3L);
        when(potatoRepository.save(newPotato)).thenReturn(savedPotato);

        final Potato potato = potatoService.createPotato(newPotato);

        assertThat(potato).isNotNull();
        assertThat(potato.getForm()).isEqualTo("Kindest of the universe");
        assertThat(potato.getId()).isNotNull();
        assertThat(potato.getId()).isEqualTo(3L);
        verify(potatoRepository, times(1)).save(potatoArgumentCaptor.capture());
        final Potato potatoToSave = potatoArgumentCaptor.getValue();
        assertThat(potatoToSave).isNotNull();
        assertThat(potatoToSave.getId()).isNull();
        assertThat(potatoToSave.getForm()).isEqualTo("Kindest of them all");
    }

    @Test
    void testGetAllPotatoesWhenListingThenGetAllFromDatabase() {
        final var newPotato1 = new Potato();
        newPotato1.setForm("Sweet");
        final var newPotato2 = new Potato();
        newPotato2.setForm("Kind");
        when(potatoRepository.findAll()).thenReturn(Arrays.asList(newPotato1, newPotato2));

        final List<Potato> all = potatoService.getAllPotatoes();

        assertThat(all).hasSize(2);
        assertThat(all).contains(newPotato1, newPotato2);
        verify(potatoRepository, only()).findAll();
    }
}