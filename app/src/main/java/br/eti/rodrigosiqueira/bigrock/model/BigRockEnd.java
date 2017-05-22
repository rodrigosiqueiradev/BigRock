package br.eti.rodrigosiqueira.bigrock.model;

/**
 * Created by luiz.massa on 03/05/17.
 */

public class BigRockEnd {


    private Integer idBigRockEnd;
    private Integer idBigRock;
    private Long dtEnd;
    private String nrLat;
    private String nrLng;


    public Integer getIdBigRockEnd() {
        return idBigRockEnd;
    }

    public void setIdBigRockEnd(Integer idBigRockEnd) {
        this.idBigRockEnd = idBigRockEnd;
    }

    public Integer getIdBigRock() {
        return idBigRock;
    }

    public void setIdBigRock(Integer idBigRock) {
        this.idBigRock = idBigRock;
    }

    public Long getDtEnd() {
        return dtEnd;
    }

    public void setDtEnd(Long dtEnd) {
        this.dtEnd = dtEnd;
    }

    public String getNrLat() {
        return nrLat;
    }

    public void setNrLat(String nrLat) {
        this.nrLat = nrLat;
    }

    public String getNrLng() {
        return nrLng;
    }

    public void setNrLng(String nrLng) {
        this.nrLng = nrLng;
    }
}
