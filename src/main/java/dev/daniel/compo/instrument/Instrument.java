package dev.daniel.compo.instrument;

public class Instrument {
    private String instrumentName;
    private Integer instrumentId;

    public Instrument(){
        this.instrumentId = 0;
        this.instrumentName = null;
    }
    public Instrument(Integer instrumentId, String instrumentName){
        this.instrumentId = instrumentId;
        this.instrumentName = instrumentName;
    }
    public Integer getInstrumentId(){
        return instrumentId;
    }
    public void setInstrumentId(Integer instrumentId){
        this.instrumentId = instrumentId;
    }
    public String getInstrumentName(){
        return instrumentName;
    }
    public void setInstrumentName(String instrumentName){
        this.instrumentName = instrumentName;
    }
    public String toString(){
        return "Instrument ID: " + this.instrumentId + "\nInstrument Name: " + getInstrumentName();
    }
}
