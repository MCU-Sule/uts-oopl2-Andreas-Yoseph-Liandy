/**
 * @1972015 Andreas Yoseph Liandy
 */

package com.example.squiddemo.entity;

public class player {
    private int id;
    private String name;
    private int umur;
    private String keahlian;


    public player(int id, String name, int umur, String keahlian) {
        this.id = id;
        this.name = name;
        this.umur = umur;
        this.keahlian = keahlian;
    }

    public player(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getKeahlian() {
        return keahlian;
    }

    public void setKeahlian(String keahlian) {
        this.keahlian = keahlian;
    }


}
