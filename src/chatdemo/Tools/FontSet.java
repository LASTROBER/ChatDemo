package chatdemo.Tools;

import javax.swing.*;
import java.awt.*;

public class FontSet {
    String fontType;
    String message;
    JTextArea jTextArea;

    public FontSet(){

    }
    public FontSet(String fontType, String message, JTextArea jtextArea) {
        this.fontType = fontType;
        this.message = message;
        Font font = new Font(fontType,Font.PLAIN,12);
        jtextArea.setFont(font);
    }

    public String getFontType() {
        return fontType;
    }

    public void setFontType(String fontType) {
        this.fontType = fontType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JTextArea getjTextArea() {
        return jTextArea;
    }

    public void setjTextArea(JTextArea jTextArea) {
        this.jTextArea = jTextArea;
    }
}
