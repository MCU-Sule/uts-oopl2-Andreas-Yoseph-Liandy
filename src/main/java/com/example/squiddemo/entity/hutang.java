/**
 * @1972015 Andreas Yoseph Liandy
 */

package com.example.squiddemo.entity;

public class hutang {
    private int id;
    private String pemberiUtang;
    private double jumlah;
    private int player_id;

    public hutang(int id, String pemberiUtang, double jumlah, int player_id) {
        this.id = id;
        this.pemberiUtang = pemberiUtang;
        this.jumlah = jumlah;
        this.player_id = player_id;
    }

    public hutang(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPemberiUtang() {
        return pemberiUtang;
    }

    public void setPemberiUtang(String pemberiUtang) {
        this.pemberiUtang = pemberiUtang;
    }

    public double getJumlah() {
        return jumlah;
    }

    public void setJumlah(double jumlah) {
        this.jumlah = jumlah;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public void setPlayer(player player) {
    }

    public int updateData(hutang selected) {
    }

    public hutang showData() {
    }
}
