package dev.daniel.compo.piece;

import dev.daniel.compo.composer.Composer;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
public class Piece{
        private int pieceId;
        private String title;
        private Integer year;
        private Integer composerId;
        public Piece(){
                this.pieceId = 0;
                this.year = null;
                this.title = "";
                this.composerId = 0;
        }
        public Piece(int pieceId, String title, Integer year, Integer composerId){
                this.pieceId = pieceId;
                this.title = title;
                this.year = year;
                this.composerId = composerId;
        }
        public int getPieceId() {
                return pieceId;
        }
        public void setPieceId(int pieceId) {
                this.pieceId = pieceId;
        }
        public String getTitle() {
                return title;
        }
        public void setTitle(String title) {
                this.title = title;
        }
        public Integer getYear() {
                return year;
        }
        public void setYear(Integer year) {
                this.year = year;
        }
        public Integer getComposerId() {
                return composerId;
        }
        public void setComposerId(Integer composerId) {
                this.composerId = composerId;
        }
        public String toString(){
                return "Piece ID: " + this.getPieceId() + "\nTitle: " + this.getTitle() + "\nYear: " + this.getYear() + "\nComposer ID: " + this.getComposerId();
        }
}