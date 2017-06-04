package br.eti.rodrigosiqueira.bigrock.model;

import java.util.Calendar;

/**
 * Created by luiz.massa on 03/05/17.
 */

public class BigRock {
    
    private Integer idBigRock;
    private String nmBigRock;
    private String dsBigRock;
    private String tpStatus;
    private Long dtInsert;
    private Long dtEnd;
    private String nrLat;
    private String nrLng;
    private Integer idUser;

    public Integer getIdBigRock() {
        return idBigRock;
    }

    public void setIdBigRock(Integer idBigRock) {
        this.idBigRock = idBigRock;
    }

    public String getNmBigRock() {
        return nmBigRock;
    }

    public void setNmBigRock(String nmBigRock) {
        this.nmBigRock = nmBigRock;
    }

    public String getDsBigRock() {
        return dsBigRock;
    }

    public void setDsBigRock(String dsBigRock) {
        this.dsBigRock = dsBigRock;
    }

    public String getTpStatus() {
        return tpStatus;
    }

    public void setTpStatus(String tpStatus) {
        this.tpStatus = tpStatus;
    }

    public Long getDtInsert() {
        return dtInsert;
    }

    public void setDtInsert(Long dtInsert) {
        this.dtInsert = dtInsert;
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

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return idBigRock + " " + nmBigRock;
    }
}
