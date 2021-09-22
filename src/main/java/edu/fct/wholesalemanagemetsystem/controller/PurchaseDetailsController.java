package edu.fct.wholesalemanagemetsystem.controller;

import edu.fct.wholesalemanagemetsystem.db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class PurchaseDetailsController implements Initializable {

    @FXML
    private TableView<PurchaseDetailsTableModel> tablePurchaseDetails;

    @FXML
    private TableColumn<PurchaseDetailsTableModel, String> col1Date;

    @FXML
    private TableColumn<PurchaseDetailsTableModel, String> col2PurchaseID;

    @FXML
    private TableColumn<PurchaseDetailsTableModel, String> col3TotalCost;

    @FXML
    private Label tfTotalPurchases;

    ObservableList<PurchaseDetailsTableModel> purchasedetaillist = FXCollections.observableArrayList();

    public void loadPurchaseDetails(){
        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select purchase_id,date,total_cost from purchase");
            while (rs.next()){
                purchasedetaillist.add(new PurchaseDetailsTableModel(rs.getString("date"),rs.getString("purchase_id"),rs.getString("total_cost")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        col1Date.setCellValueFactory(new PropertyValueFactory<>("col1Date"));
        col2PurchaseID.setCellValueFactory(new PropertyValueFactory<>("col2PurchaseID"));
        col3TotalCost.setCellValueFactory(new PropertyValueFactory<>("col3TotalCost"));
        tablePurchaseDetails.setItems(purchasedetaillist);

        try {
            Connection con = DBConnection.getInstance().getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select sum(total_cost) from purchase");
            rs.next();
            double total = rs.getDouble(1);
            tfTotalPurchases.setText(String.valueOf(total));
        }
        catch(Exception e) {

        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPurchaseDetails();
    }

    }
