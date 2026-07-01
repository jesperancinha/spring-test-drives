package org.jesperancinha.sftd.flash46.request.param;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Map;
import java.util.stream.Stream;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SpringFlash46Launcher.class)
class SpringFlash46LauncherTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void main() {
    }

    @Test
    void index() {
    }

    @TestFactory
    Stream<DynamicTest> testCallDogsWhenDogTypeThenRightYouTubeVideo() {
        final var testMap = Map.of(
                DogType.DOBERMANN, "0LgCGpbMhAo",
                DogType.SIBERIAN_HUSKY, "tbn8IF0D9Yg",
                DogType.ROTTWEILLER, "GBZnnOe_n5g",
                DogType.COCKERS_SPANIEL, "k4N_DkpMZAY",
                DogType.GERMAN_SHEPPARD, "MxaJb-fYR4M",
                DogType.BELGIAN_SHEPPARD, "VcsyScrd0UQ"
        );

        return testMap.entrySet().stream().map(test ->
                DynamicTest.dynamicTest("Testing Dog " + test.getKey(),
                        () -> mockMvc.perform(MockMvcRequestBuilders.get(
                                "/dogs?dogType={dogType}", test.getKey()))
                                .andExpect(status().isOk())
                                .andExpect(content().string(containsString(test.getValue())))));
    }

    @TestFactory
    Stream<DynamicTest> testCallCatsWhenCatTypeThenRightYouTubeVideo() {
        final var testMap = Map.of(
                CatType.MAINE_COON, "weebcQPRGUE",
                CatType.BENGAL, "FVGhzxzx88M",
                CatType.PERSIAN, "RYe0pHIMsaU",
                CatType.SIAMESE, "Q4Vjadpyehw",
                CatType.SAVANNAH, "abuAUjFv88s",
                CatType.SCOTTISH_FOLD, "lnlg55QR3hw"
        );

        return testMap.entrySet().stream().map(test ->
                DynamicTest.dynamicTest("Testing Cat " + test.getKey(),
                        () -> mockMvc.perform(MockMvcRequestBuilders.get(
                                "/cats?catType={catType}", test.getKey()))
                                .andExpect(status().isOk())
                                .andExpect(content().string(containsString(test.getValue())))));
    }
}