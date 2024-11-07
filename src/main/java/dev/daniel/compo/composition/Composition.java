package dev.daniel.compo.composition;

public class Composition{
    private int pieceId;
    private String title;
    private Integer year;
    private Integer musicianId;
    public Composition(){
        this.pieceId = 0;
        this.year = null;
        this.title = "";
        this.musicianId = 0;
    }
    public Composition(int pieceId, String title, Integer year, Integer musicianId){
        this.pieceId = pieceId;
        this.title = title;
        this.year = year;
        this.musicianId = musicianId;
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
    public Integer getMusicianId() {
        return musicianId;
    }
    public void setMusicianId(Integer musicianId) {
        this.musicianId = musicianId;
    }
    public String toString(){
        return "Piece ID: " + this.getPieceId() + "\nTitle: " + this.getTitle() + "\nYear: " + this.getYear() + "\nComposer ID: " + this.getMusicianId();
    }
}