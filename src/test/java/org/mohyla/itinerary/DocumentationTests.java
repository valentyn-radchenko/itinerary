package org.mohyla.itinerary;

import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.junit.jupiter.api.Test;

class DocumentationTests {

    @Test
    void writeDocumentationSnippets() {
        ApplicationModules modules = ApplicationModules.of(ItineraryApplication.class);

        new Documenter(modules)
                .writeModulesAsPlantUml()               // C4-style diagram of all modules
                .writeIndividualModulesAsPlantUml()     // Diagrams per module
                .writeModuleCanvases();                 // Tabular overview of each module
    }
}
