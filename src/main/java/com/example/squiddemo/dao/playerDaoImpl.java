/**
 * @1972015 Andreas Yoseph Liandy
 */

package com.example.squiddemo.dao;

import com.example.squiddemo.entity.player;
import com.example.squiddemo.util.MySQLConnection;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;
import static javafx.collections.FXCollections.*;
public class playerDaoImpl implements daoInterface<player>{

    @Override
    public int addData(player data) {
        int result = 0;
        try {
            String query = "INSERT INTO player (id, Nama, Umur, Keahlian) VALUES (?,?,?,?)";
            PreparedStatement ps;
            ps = MySQLConnection.getConnection().prepareStatement(query);
            ps.setInt(1,data.getId());
            ps.setString(2,data.getName());
            ps.setInt(3,data.getUmur());
            ps.setString(4,data.getKeahlian());
            result = ps.executeUpdate();
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public int delData(player data) {
        int result = 0;
        try {
            String query = "DELETE  FROM player WHERE id=?";
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
    public int updateData(player data) {
        int result = 0;
        try {
            String query = "Update player set Nama=?,Umur=?, Keahlian=? WHERE id=?";
            PreparedStatement ps;
            ps = MySQLConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getName());
            ps.setInt(2, data.getUmur());
            ps.setString(3, data.getKeahlian());
            ps.setInt(4, data.getId());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return result;
    }

    @Override
    public List<player> showData() {
        ObservableList<player> playerList = observableArrayList();
        try {
            String query = "SELECT * FROM player;";
            PreparedStatement ps;
            ps = MySQLConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                int id= res.getInt("id");
                String play = res.getString("Nama");
                player pl = new player();
                playerList.add(pl);
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return playerList;
    }
}
