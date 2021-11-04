/**
 * @1972015 Andreas Yoseph Liandy
 */

package com.example.squiddemo.dao;

import com.example.squiddemo.entity.hutang;
import com.example.squiddemo.entity.player;
import com.example.squiddemo.util.MySQLConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class hutangDaoImpl implements daoInterface<hutang>{
    @Override
    public int addData(hutang data) {
        int result = 0;
        try {
            String query = "INSERT INTO hutang (id,PemberiUtang,Jumlah,Player_id) VALUES (?,?,?,?)";
            PreparedStatement ps;
            ps = MySQLConnection.getConnection().prepareStatement(query);
            ps.setInt(1,data.getId());
            ps.setString(2,data.getPemberiUtang());
            ps.setDouble(3,data.getJumlah());
            ps.setInt(4,data.getPlayer_id());
            result = ps.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public int delData(hutang data) {
        int result = 0;
        try {
            String query = "DELETE FROM hutang WHERE id=?";
            Connection conn = MySQLConnection.getConnection();
            conn.setAutoCommit(false);
            PreparedStatement ps;
            ps= conn.prepareStatement(query);
            ps.setInt(1,data.getId());
            result= ps.executeUpdate();
            if (result != 0){
                conn.commit();
            }
            else{
                conn.rollback();
            }

        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int updateData(hutang data) {
        int result = 0;
        try {
            String query = "UPDATE hutang set PemberiUtang=?, Jumlah=?, Player_id=? WHERE id=?";
            PreparedStatement ps;
            ps = MySQLConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getPemberiUtang());
            ps.setDouble(2, data.getJumlah());
            ps.setInt(3, data.getPlayer_id());
            ps.setInt(4, data.getId());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public List<hutang> showData() {
        ObservableList<hutang> hutanggs= FXCollections.observableArrayList();
        try {
            String query="SELECT h.id, h.PemberiUtang, h.Jumlah, h.Player_id, p.id FROM Hutang h JOIN Player p ON p.id = h.Player_id";
            PreparedStatement ps;
            ps= MySQLConnection.getConnection().prepareStatement(query);
            ResultSet res=ps.executeQuery();
            while (res.next()){
                int hutangId= res.getInt("id");
                String hutangNama = res.getString("Nama");
                player player = new player();
                hutang hutang = new hutang();
                hutang.setId(res.getInt("id"));
                hutang.setPemberiUtang(res.getString("PemberiUtang"));
                hutang.setJumlah(res.getInt("Jumlah"));
                hutang.setPlayer_id(res.getInt("Player_id"));
                hutang.setPlayer(player);
                hutanggs.add(hutang);
            }

        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return hutanggs;
    }
}
