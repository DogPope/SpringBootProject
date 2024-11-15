package dev.daniel.compo.composition;

public class Composition{
    private Integer compositionId;
    private String title;
    private Integer year;
    private Integer musicianId;
    public Composition(){
        this.compositionId = 0;
        this.year = null;
        this.title = "";
        this.musicianId = 0;
    }
    public Composition(int compositionId, String title, Integer year, Integer musicianId){
        this.compositionId = compositionId;
        this.title = title;
        this.year = year;
        this.musicianId = musicianId;
    }
    public int getCompositionId() {
        return compositionId;
    }
    public void setCompositionId(Integer compositionId) {
        this.compositionId = compositionId;
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
        return "Composition ID: " + this.getCompositionId() + "\nTitle: " + this.getTitle() + "\nYear: " + this.getYear() + "\nMusician ID: " + this.getMusicianId();
    }
}