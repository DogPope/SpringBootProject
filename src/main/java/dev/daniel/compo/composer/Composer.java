package dev.daniel.compo.composer;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.util.GregorianCalendar;

public record Composer(
        @Positive
        Integer composerId,
        @NotEmpty
        String firstName,
        String lastName,
        Country country,
        Gender gender,
        GregorianCalendar dateOfBirth,
        @Nullable
        GregorianCalendar dateOfDeath

) {
    // The after() method breaks the whole thing. Find some fix for this.
    /*public Composer{
        if(!dateOfBirth().after(dateOfDeath())){
            throw new IllegalArgumentException("The Composer must have been born before dying, not the other way around!");
        }
    }*/
}
