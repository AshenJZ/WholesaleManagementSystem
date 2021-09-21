package edu.fct.wholesalemanagemetsystem.controller;

import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class ViewItemController implements Initializable {

    @FXML
    private TableView<ItemTableModel> tableItemDetails;

    @FXML
    private TableColumn<ItemTableModel, String> col1ItemID;

    @FXML
    private TableColumn<ItemTableModel, String> col2ItemName;

    @FXML
    private TableColumn<ItemTableModel, String> col3Brand;

    @FXML
    private TableColumn<ItemTableModel, String> col4Qty;

    @FXML
    private TableColumn<ItemTableModel, String> col5UnitPrize;

    ObservableList<ItemTableModel> itemdatalist = FXCollections.observableArrayList();

    public void loadItem(){
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from item");
            while (rs.next()){
                itemdatalist.add(new ItemTableModel(
                        rs.getString("item_id"),
                        rs.getString("item_name"),
                        rs.getString("brand"),
                        rs.getString("available_quantity"),
                        rs.getString("unit_prize")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        col1ItemID.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        col2ItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        col3Brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        col4Qty.setCellValueFactory(new PropertyValueFactory<>("availableQty"));
        col5UnitPrize.setCellValueFactory(new PropertyValueFactory<>("unitPrize"));
        tableItemDetails.setItems(itemdatalist);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadItem();
    }
}

