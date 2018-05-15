package hash_gen;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Rz39
 */
public class FXMLDocumentController implements Initializable {
    
    private PreparedStatement ps = null;
    private Connection connect = null;
    private ResultSet rs;
    private ObservableList<hash_tab> hash;
    String str;
    hasher hashing = new hasher();
   
    @FXML private TableView <hash_tab> table_hash;
    @FXML private TableColumn <hash_tab, String> string_col;
    @FXML private TableColumn <hash_tab, String> MD5_col;
    @FXML private TableColumn <hash_tab, String> SHA1_col;
    @FXML private TableColumn <hash_tab, String> Base64_col;
    
    @FXML 
    private TextField str_in;
    
    @FXML
    private void gen_btn(ActionEvent event) {
        str = str_in.getText();
        
        try {    
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/generator_hash","root","");
            connect.createStatement().executeUpdate("INSERT INTO HASIL_HASHING VALUES"+"('"+str+"','"+hashing.md5encrypt(str)+"','"+hashing.sha1(str)+"','"+hashing.base64(str)+"')");
            LoadDataFromDB();
        } catch (SQLException | NoSuchAlgorithmException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        str_in.setText("");
    }
    
    @FXML
    private void del_btn(ActionEvent event){
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/generator_hash","root","");
            connect.createStatement().executeUpdate("DELETE FROM HASIL_HASHING");
            LoadDataFromDB();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/generator_hash","root","");
            hash = FXCollections.observableArrayList();
            SetCellTable();
            LoadDataFromDB();
        }
        catch (SQLException ex)
        {
            System.out.println("SQLExeption: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    private void SetCellTable()
    {
        string_col.setCellValueFactory(new PropertyValueFactory<>("String"));
        MD5_col.setCellValueFactory(new PropertyValueFactory<>("MD5"));
        SHA1_col.setCellValueFactory(new PropertyValueFactory<>("SHA1"));
        Base64_col.setCellValueFactory(new PropertyValueFactory<>("Base64"));
    }
    
    private void LoadDataFromDB()
    {
        int row = table_hash.getItems().size();
        for(int i = 0; i< row; i++){
            hash.remove(0);
        }
        try{
            ps = connect.prepareStatement("SELECT * FROM HASIL_HASHING");
            rs = ps.executeQuery();
            while(rs.next())
            {
                hash.add(new hash_tab(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            table_hash.setItems(hash);
        } catch(SQLException ex){
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
