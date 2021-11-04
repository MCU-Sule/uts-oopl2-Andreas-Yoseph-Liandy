/**
 * @1972015 Andreas Yoseph Liandy
 */

package com.example.squiddemo.controller;

import com.example.squiddemo.dao.hutangDaoImpl;
import com.example.squiddemo.dao.playerDaoImpl;
import com.example.squiddemo.entity.hutang;
import com.example.squiddemo.entity.player;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StageModalController implements Initializable {
    @FXML
    public TextField txtId;
    @FXML
    public TextField txtNama;
    @FXML
    public TextField txtUmur;
    @FXML
    public TextField txtKeahlian;
    @FXML
    private BorderPane apAndre;
    private ObservableList<player> players;
    private ObservableList<hutang> hutangs;

    private boolean hasil;

    public void btnOk(ActionEvent actionEvent) {
        if (txtId.getText().trim().isEmpty() ||
                txtNama.getText().trim().isEmpty() || txtUmur.getText().trim().isEmpty()||
                txtKeahlian.getText().trim().isEmpty()){
            Alert alertAndre = new Alert(Alert.AlertType.INFORMATION);
            alertAndre.setContentText("Please fill !");
            alertAndre.show();
        }else {
            try {
                for (int i=0; i<players.size(); i++){
                    if (players.get(i).getId()==Integer.parseInt(txtId.getText())){
                        Alert alertAndre = new Alert(Alert.AlertType.ERROR);
                        alertAndre.setContentText("Id Sudah Ada");
                        alertAndre.showAndWait();
                        hasil = true;
                    } else {
                        hasil = false;
                    }
                }
                player player = new player();
                player.setId(Integer.valueOf(txtId.getText()));
                player.setName(String.valueOf(txtNama.getText()));
                player.setUmur(Integer.valueOf(txtUmur.getText()));
                player.setKeahlian(String.valueOf(txtKeahlian.getText()));

                if (hasil != true){
                    playerDaoImpl mainDao = new playerDaoImpl();
                    mainDao.addData(player);
                    hutangs.clear();
                    hutangs.addAll((hutang) mainDao.showData());
                } else {
                    Alert alertAndre = new Alert(Alert.AlertType.ERROR);
                    alertAndre.setContentText("Duplicate Detected!");
                    alertAndre.show();
                }

            } catch (NumberFormatException ex) {
                Alert alertAndre = new Alert(Alert.AlertType.ERROR);
                alertAndre.setContentText("The Fill Must Be Number!");
                alertAndre.show();
            }
        }
    }

    public void btnCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) apAndre.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setMaincontroller(SquidController squidController) {
    }
}
