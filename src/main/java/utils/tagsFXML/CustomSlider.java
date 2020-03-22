package main.java.utils.tagsFXML;

import javafx.geometry.Insets;
import javafx.scene.control.Slider;

import javax.swing.plaf.PanelUI;

public class CustomSlider extends Slider {

    public CustomSlider(int min, int max, int value) {
        super(min, max, value);
        int counterX;
        if (max < 1) {
            counterX = 1;
        } else if (max < 11) {
            counterX = (int) max;
            //this.
        } else if (max < 26) {
            counterX = 10;
        } else if (max < 151) {
            counterX = 25;
        } else if (max < 501) {
            counterX = 100;
        } else {
            counterX = 250;
        }
        // enable las marcas con numeros
        this.setShowTickMarks(true);
        //enable numeros
        this.setShowTickLabels(true);
        //disable las marcas entre medio de las TickMarcs
        this.setSnapToTicks(false);
        //Calcular cada cuanto poner las marcas
        this.setMajorTickUnit(counterX);
        this.setMinorTickCount(counterX - 1);
        //se pueden usar decimales? 0.00001 or 0.1
        this.setBlockIncrement(1);
    }

    public void setmargin(int top, int left, int right, int bottom) {
        this.setPadding(new Insets(top, right, bottom, left));
    }
}
