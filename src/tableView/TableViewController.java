/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableView;

import helpers.DbConnect;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.itemsForStorage;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


/**
 * FXML Controller class
 *
 * @author user
 */
public class TableViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
  
    @FXML
    private TableView<itemsForStorage> columnAction;
    
      @FXML
    private TableView<itemsForStorage> tableItems;

    @FXML
    private TableColumn<itemsForStorage,String> columnId;

    @FXML
    private TableColumn<itemsForStorage, String> columnName;

    @FXML
    private TableColumn<itemsForStorage, String> columnPriceUnit;

    @FXML
    private TableColumn<itemsForStorage, String> columnQuantity;

    @FXML
    private TableColumn<itemsForStorage, String> columnUnit;

    @FXML
    private FontAwesomeIconView iconExit;

    @FXML
    private TextField textId;

    @FXML
    private TextField textMeasUnit;

    @FXML
    private TextField textName;

    @FXML
    private TextField textPriceUnit;

    @FXML
    private TextField textQuantity;

    @FXML
    private TextField textSearch;

    String query=null;
    Connection connection=null;
    PreparedStatement pst =null;
    ResultSet rs =null;
    itemsForStorage item=null;
    
    ObservableList<itemsForStorage> itemsForStorageList=FXCollections.observableArrayList();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadDataFromTable();
    }  

    private void loadDataFromTable() {
        
        connection=DbConnect.getConnect();
        refreshTable();
        
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnUnit.setCellValueFactory(new PropertyValueFactory<>("Unit"));
        columnPriceUnit.setCellValueFactory(new PropertyValueFactory<>("Price/Unit"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        
    }
    
    @FXML
    private void refreshTable(){
    try {     
            itemsForStorageList.clear();
            query="select * from storage";
            pst=connection.prepareStatement(query);
            rs=pst.executeQuery();
            
            while(rs.next())
            {
            itemsForStorageList.add(new itemsForStorage(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("unit"),
            rs.getString("price_unit"),
            rs.getString("quantity")
            ));
            tableItems.setItems(itemsForStorageList);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    void addButton(MouseEvent event) {

    }

    @FXML
    void closeButton(MouseEvent event) {
      Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
      stage.close();
    }

    @FXML
    void deleteButton(MouseEvent event) {
      
    }

    @FXML
    void refreshButton(MouseEvent event) {
     
        refreshTable();
        
    }

    @FXML
    void updateButton(MouseEvent event) {

    }
    
}
