package main.java.utils.tagsFX;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class CustomTextField extends TextField {
    public CustomTextField(String text, boolean isNumeric) {
        super(text);
        if (isNumeric) {
            isNumeric(this);
        }
    }

    public CustomTextField(String text, boolean isNumeric, int numMaxim) {
        super(text);
        numMaxToChars(numMaxim);
        if (isNumeric) {
            isNumericNumMax(this, numMaxim);
        }
    }

    //
    public void setBindSlider(CustomSlider slider) {
        this.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                slider.setValue(Double.parseDouble(newValue));
            }
        });
    }

    //
    private static void isNumeric(CustomTextField customTextField) {
        customTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    customTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    private static void isNumericNumMax(CustomTextField customTextField, int numMaxim) {
        customTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    customTextField.setText(newValue.replaceAll("[^\\d]", ""));

                }
                if (Integer.parseInt(customTextField.getText()) > numMaxim) {
                    customTextField.setText(String.valueOf(numMaxim));
                }
            }
        });
    }

    private void numMaxToChars(int numMax) {
        if (numMax < 11) {
            this.setMaxWidth(30);
        } else {
            this.setMaxWidth(40);
        }
    }
}
