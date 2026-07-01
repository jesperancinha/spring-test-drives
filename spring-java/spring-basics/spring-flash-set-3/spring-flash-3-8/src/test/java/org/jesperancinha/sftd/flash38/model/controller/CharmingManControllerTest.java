package org.jesperancinha.sftd.flash38.model.controller;

import org.jesperancinha.console.consolerizer.console.ConsolerizerComposer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest
public class CharmingManControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static List<String> modelOfTheCharmlessMan = new ArrayList<String>();

    static {
        modelOfTheCharmlessMan.add("He moves in circles of friends");
        modelOfTheCharmlessMan.add("Who just pretend that they like him");
        modelOfTheCharmlessMan.add("He does the same to them");
        modelOfTheCharmlessMan.add("And when you put it all together");
        modelOfTheCharmlessMan.add("There's the model of a charmless man");
    }

    @Test
    public void testModelOfTheCharmlessMan() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("lyrics", modelOfTheCharmlessMan))
                .andExpect(view().name("index"));

        ConsolerizerComposer.outSpace()
                .yellow("When using  @ModelAttribute, we are defining automatically an attribute to put into the Model")
                .yellow("At the same time we can use this same annotation to inject the attribute we need in our injectable methods")
                .reset();
    }
}