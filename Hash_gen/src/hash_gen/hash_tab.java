package hash_gen;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Rz39
 */
public class hash_tab {
    public SimpleStringProperty string, MD5, SHA1, Base64;

    public hash_tab(String string, String MD5, String SHA1, String Base64) {
        this.string = new SimpleStringProperty(string);
        this.MD5 = new SimpleStringProperty(MD5);
        this.SHA1 = new SimpleStringProperty(SHA1);
        this.Base64 = new SimpleStringProperty(Base64);
    }

    public String getString() {
        return string.get();
    }

    public void setString(SimpleStringProperty string) {
        this.string = string;
    }

    public String getMD5() {
        return MD5.get();
    }

    public void setMD5(SimpleStringProperty MD5) {
        this.MD5 = MD5;
    }

    public String getSHA1() {
        return SHA1.get();
    }

    public void setSHA1(SimpleStringProperty SHA1) {
        this.SHA1 = SHA1;
    }

    public String getBase64() {
        return Base64.get();
    }

    public void setBase64(SimpleStringProperty Base64) {
        this.Base64 = Base64;
    }
    
    
}
