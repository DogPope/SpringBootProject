package dev.daniel.compo.piece;

import dev.daniel.compo.composer.Composer;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
public class Piece extends Composer {
        @Positive
        private Integer pieceId;
        @NotNull
        private String title;
        @Nullable
        private Integer year;
        @NotNull
        private Integer composerId;
}