package org.jesperancinha.sftd.action.aop.aspects;

import org.aspectj.lang.JoinPoint;
import org.jesperancinha.sftd.action.aop.beans.HarvestingService;
import org.jesperancinha.sftd.action.aop.fishing.Fisher;
import org.jesperancinha.sftd.action.aop.fishing.Shrimper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith({SpringExtension.class})
@ContextConfiguration(classes = {
        HarvestingAspect.class,
        HarvestingService.class,
        Fisher.class,
        Shrimper.class
})
@EnableAspectJAutoProxy(proxyTargetClass = true)
class HarvestingAspectTest {

    @MockitoBean
    private HarvestingService harvestingService;

    @Autowired
    private Fisher fisher;

    @Autowired
    private Shrimper shrimper;

    @Captor
    private ArgumentCaptor<JoinPoint> joinPointArgumentCaptor;

    @Captor
    private ArgumentCaptor<Shrimper> shrimperArgumentCaptor;

    @Test
    void testAnyHarvesterWhenCall3HarversterTypesNSubsThenTriggerAdvices() {
        fisher.harvest();
        shrimper.harvest();

        verify(harvestingService, times(2)).thisHarvester(joinPointArgumentCaptor.capture());
        final List<String> pointCutList = joinPointArgumentCaptor.getAllValues()
                .stream().map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(pointCutList).hasSize(2);
        assertThat(pointCutList)
                .containsAll(List.of("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()", "SeaFood org.jesperancinha.sftd.action.aop.fishing.Fisher.harvest()"));
        verify(harvestingService, times(1)).targetShrimper(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue2 = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue2.getSignature().toString())
                .isEqualTo("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()");
        verify(harvestingService, times(2)).targetFisher(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue3 = joinPointArgumentCaptor.getAllValues()
                .stream().skip(3L).map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(joinPointArgumentCaptorValue3).hasSize(2);
        assertThat(joinPointArgumentCaptorValue3)
                .containsAll(List.of("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()", "SeaFood org.jesperancinha.sftd.action.aop.fishing.Fisher.harvest()"));
        verify(harvestingService, times(2)).targetHarvester(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue4 = joinPointArgumentCaptor.getAllValues()
                .stream().skip(5L).map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(joinPointArgumentCaptorValue4).hasSize(2);
        assertThat(joinPointArgumentCaptorValue4)
                .containsAll(List.of("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()", "SeaFood org.jesperancinha.sftd.action.aop.fishing.Fisher.harvest()"));
        verify(harvestingService, times(2)).thisFisher(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue5 = joinPointArgumentCaptor.getAllValues()
                .stream().skip(7L).map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(joinPointArgumentCaptorValue5).hasSize(2);
        assertThat(joinPointArgumentCaptorValue5)
                .containsAll(List.of("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()", "SeaFood org.jesperancinha.sftd.action.aop.fishing.Fisher.harvest()"));
        verify(harvestingService, times(1)).thisShrimper(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue6 = joinPointArgumentCaptor.getAllValues()
                .stream().skip(9L).map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(joinPointArgumentCaptorValue6).hasSize(1);
        assertThat(joinPointArgumentCaptorValue6)
                .containsAll(List.of("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()"));
        verifyNoMoreInteractions(harvestingService);
    }


    @Test
    void testAnyHarvesterWhenCallReferenceToFisherThenTriggerOnlyFisherRefAdvicesAndAllInstanceShrimperAdvices() {
        final Fisher fisherShrimper = shrimper;
        fisherShrimper.harvest();

        verify(harvestingService, times(1)).thisHarvester(joinPointArgumentCaptor.capture());
        final List<String> pointCutList = joinPointArgumentCaptor.getAllValues()
                .stream().map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(pointCutList).hasSize(1);
        assertThat(pointCutList)
                .containsAll(List.of("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()"));
        verify(harvestingService, times(1)).targetShrimper(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue2 = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue2.getSignature().toString())
                .isEqualTo("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()");
        verify(harvestingService, times(1)).targetFisher(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue3 = joinPointArgumentCaptor.getAllValues()
                .stream().skip(2L).map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(joinPointArgumentCaptorValue3).hasSize(1);
        assertThat(joinPointArgumentCaptorValue3)
                .containsAll(List.of("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()"));
        verify(harvestingService, times(1)).targetHarvester(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue4 = joinPointArgumentCaptor.getAllValues()
                .stream().skip(3L).map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(joinPointArgumentCaptorValue4).hasSize(1);
        assertThat(joinPointArgumentCaptorValue4)
                .containsAll(List.of("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()"));
        verify(harvestingService, times(1)).thisFisher(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue5 = joinPointArgumentCaptor.getAllValues()
                .stream().skip(4L).map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(joinPointArgumentCaptorValue5).hasSize(1);
        assertThat(joinPointArgumentCaptorValue5)
                .containsAll(List.of("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()"));
        verify(harvestingService, times(1)).thisShrimper(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue6 = joinPointArgumentCaptor.getAllValues()
                .stream().skip(5L).map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(joinPointArgumentCaptorValue6).hasSize(1);
        assertThat(joinPointArgumentCaptorValue6)
                .containsAll(List.of("Shrimp org.jesperancinha.sftd.action.aop.fishing.Shrimper.harvest()"));
        verifyNoMoreInteractions(harvestingService);
    }

    @Test
    void testFishAnythingWhenCallReferenceToFisherThenTriggerOnlyFisherRefAdvicesAndAllInstanceShrimperAdvices() {
        final Fisher fisherShrimper = shrimper;
        fisherShrimper.justFishAnything();

        verify(harvestingService, times(1)).thisHarvester(joinPointArgumentCaptor.capture());
        final List<String> pointCutList = joinPointArgumentCaptor.getAllValues()
                .stream().map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(pointCutList).hasSize(1);
        assertThat(pointCutList)
                .containsAll(List.of("void org.jesperancinha.sftd.action.aop.fishing.Fisher.justFishAnything()"));
        verify(harvestingService, times(1)).targetShrimper(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue2 = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue2.getSignature().toString())
                .isEqualTo("void org.jesperancinha.sftd.action.aop.fishing.Fisher.justFishAnything()");
        verify(harvestingService, times(1)).targetFisher(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue3 = joinPointArgumentCaptor.getAllValues()
                .stream().skip(2L).map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(joinPointArgumentCaptorValue3).hasSize(1);
        assertThat(joinPointArgumentCaptorValue3)
                .containsAll(List.of("void org.jesperancinha.sftd.action.aop.fishing.Fisher.justFishAnything()"));
        verify(harvestingService, times(1)).targetHarvester(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue4 = joinPointArgumentCaptor.getAllValues()
                .stream().skip(3L).map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(joinPointArgumentCaptorValue4).hasSize(1);
        assertThat(joinPointArgumentCaptorValue4)
                .containsAll(List.of("void org.jesperancinha.sftd.action.aop.fishing.Fisher.justFishAnything()"));
        verify(harvestingService, times(1)).thisFisher(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue5 = joinPointArgumentCaptor.getAllValues()
                .stream().skip(4L).map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(joinPointArgumentCaptorValue5).hasSize(1);
        assertThat(joinPointArgumentCaptorValue5)
                .containsAll(List.of("void org.jesperancinha.sftd.action.aop.fishing.Fisher.justFishAnything()"));
        verify(harvestingService, never()).thisShrimper(joinPointArgumentCaptor.capture());
        verifyNoMoreInteractions(harvestingService);
    }


    @Test
    void testFishShrimpWhenCallParameterThenTriggerOnlyFisherRefAdvicesAndAllInstanceShrimperAdvices() {
        shrimper.secretHarvest();

        verify(harvestingService, times(1))
                .passingTargetArgument(joinPointArgumentCaptor.capture(), shrimperArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue).isNotNull();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString())
                .isEqualTo("void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()");
        final var value = shrimperArgumentCaptor.getValue();
        assertThat(value).isNotNull();

        verify(harvestingService, times(1)).thisHarvester(joinPointArgumentCaptor.capture());
        final List<String> pointCutList = joinPointArgumentCaptor.getAllValues()
                .stream().map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(pointCutList).hasSize(2);
        assertThat(pointCutList)
                .containsAll(List.of("void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()",
                        "void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()"));
        verify(harvestingService, times(1)).targetShrimper(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue2 = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue2.getSignature().toString())
                .isEqualTo("void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()");
        verify(harvestingService, times(1)).targetFisher(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue3 = joinPointArgumentCaptor.getAllValues()
                .stream().skip(3L).map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(joinPointArgumentCaptorValue3).hasSize(1);
        assertThat(joinPointArgumentCaptorValue3)
                .containsAll(List.of("void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()"));
        verify(harvestingService, times(1)).targetHarvester(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue4 = joinPointArgumentCaptor.getAllValues()
                .stream().skip(4L).map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(joinPointArgumentCaptorValue4).hasSize(1);
        assertThat(joinPointArgumentCaptorValue4)
                .containsAll(List.of("void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()"));
        verify(harvestingService, times(1)).thisFisher(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue5 = joinPointArgumentCaptor.getAllValues()
                .stream().skip(5L).map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(joinPointArgumentCaptorValue5).hasSize(1);
        assertThat(joinPointArgumentCaptorValue5)
                .containsAll(List.of("void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()"));
        verify(harvestingService, times(1)).thisShrimper(joinPointArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue6 = joinPointArgumentCaptor.getAllValues()
                .stream().skip(6L).map(joinPoint -> joinPoint.getSignature().toString()).collect(Collectors.toList());
        assertThat(joinPointArgumentCaptorValue6).hasSize(1);
        assertThat(joinPointArgumentCaptorValue6)
                .containsAll(List.of("void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()"));
        verifyNoMoreInteractions(harvestingService);
    }


    @Test
    void testFishShrimpWhenCallParameterThenProxyServiceGetsIntoAdvice() {
        shrimper.secretHarvest();

        verify(harvestingService, times(1))
                .passingTargetArgument(joinPointArgumentCaptor.capture(), shrimperArgumentCaptor.capture());
        final var joinPointArgumentCaptorValue = joinPointArgumentCaptor.getValue();
        assertThat(joinPointArgumentCaptorValue).isNotNull();
        assertThat(joinPointArgumentCaptorValue.getSignature().toString())
                .isEqualTo("void org.jesperancinha.sftd.action.aop.fishing.Shrimper.secretHarvest()");
        final var value = shrimperArgumentCaptor.getValue();
        assertThat(value).isNotNull();
        assertThat(value.getId()).isEqualTo(shrimper.getId());
        assertThat(value).isNotSameAs(shrimper);
        assertThat(value).isNotEqualTo(shrimper);
    }
}