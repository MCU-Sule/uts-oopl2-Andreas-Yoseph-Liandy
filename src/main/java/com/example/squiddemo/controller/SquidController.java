/**
 * @1972015 Andreas Yoseph Liandy
 */

package com.example.squiddemo.controller;

import com.example.squiddemo.SquidApplication;
import com.example.squiddemo.dao.hutangDaoImpl;
import com.example.squiddemo.dao.playerDaoImpl;
import com.example.squiddemo.entity.hutang;
import com.example.squiddemo.entity.player;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SquidController implements Initializable {
    @FXML
    public TableView<player> tablePemain;
    @FXML
    public TableColumn <player,Integer>kolomId;
    @FXML
    public TableColumn <player,String>kolomNama;
    @FXML
    public TableColumn <player,Integer>kolomUmur;
    @FXML
    public TableColumn <player,String>kolomKemampuan;
    @FXML
    public Button btnDataHutang;
    @FXML
    public ComboBox<com.example.squiddemo.entity.hutang> comboPeserta;
    @FXML
    public TextField txtPemberiUtang;
    @FXML
    public TextField txtJumlah;
    @FXML
    public TableView <hutang> tableHutang;
    @FXML
    public TableColumn <hutang,Integer>kolomIdPemain;
    @FXML
    public TableColumn <hutang,String>kolomHutangPemain;
    @FXML
    public TableColumn <hutang,String>kolomSejumlahPemain;


    private ObservableList<player> players;
    private ObservableList<hutang> hutangs;
    private boolean hasil;

    @FXML
    private BorderPane apAndre;
    private Object hutang;

    public ObservableList<player> getPlayer(){
        return players;
    }

    public void btnAddPemain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SquidApplication.class.getResource("ModalPage.fxml"));
        Parent root = fxmlLoader.load();
        StageModalController modalStage= fxmlLoader.getController();
        modalStage.setMaincontroller(this);
        Scene sceneroot = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(sceneroot);
        stage.initOwner(apAndre.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();

    }

    public void btnEditPemain(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SquidApplication.class.getResource("ModalPage.fxml"));
        Parent root = fxmlLoader.load();
        StageModalController modalStage= fxmlLoader.getController();
        modalStage.setMaincontroller(this);
        Scene sceneroot = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(sceneroot);
        stage.initOwner(apAndre.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
        hutang selected = null;
        hutang = (hutang) tableHutang.getSelectionModel().getSelectedItem();
        System.out.println(selected);
        selected.setJumlah(Integer.valueOf(txtJumlah.getText()));
        selected.setPemberiUtang(String.valueOf(txtPemberiUtang.getText()));
        hutang htDao = new hutang();
        int result = htDao.updateData(selected);
        if (result != 0){
            System.out.println("Update Berhasil");
        }
        hutangs.clear();
        hutangs.addAll(htDao.showData());
        tablePemain.refresh();
    }

    public void btnHapusPemain(ActionEvent actionEvent) {
        player selected;
        selected=(player) tablePemain.getSelectionModel().getSelectedItem();
        System.out.println(selected);

        playerDaoImpl playerDao = new playerDaoImpl();
        int result = playerDao.delData(selected);
        if(result!=0){
            System.out.println("Delete Berhasil!");
        }
        ObservableList<player> pl = (ObservableList<player>) playerDao.showData();
        tablePemain.setItems(pl);
    }

    public void btnHapusHutangTerpilih(ActionEvent actionEvent) {
        hutang selected;
        selected=(hutang) tableHutang.getSelectionModel().getSelectedItem();
        System.out.println(selected);

        hutangDaoImpl hutangDao = new hutangDaoImpl();
        int result = hutangDao.delData(selected);
        if(result!=0){
            System.out.println("Delete Berhasil!");
        }
        ObservableList<hutang> ht = (ObservableList<hutang>) hutangDao.showData();
        tableHutang.setItems(ht);
    }


    public void clickTable(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnDataHutang.setDisable(false);
        playerDaoImpl playerDao =  new playerDaoImpl();
        hutangDaoImpl hutangDao= new hutangDaoImpl();
        players = (ObservableList<player>) playerDao.showData();
        hutangs = (ObservableList<hutang>) hutangDao.showData();
        comboPeserta.setItems(hutangs);
        tableHutang.setItems(hutangs);
        kolomIdPemain.setCellValueFactory(data->new SimpleStringProperty(Integer.valueOf(data.getValue().getPlayer_id())));
        kolomHutangPemain.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getPemberiUtang())));
        kolomSejumlahPemain.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getJumlah())));
    }


}
