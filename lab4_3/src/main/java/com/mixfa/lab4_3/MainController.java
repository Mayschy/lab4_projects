package com.mixfa.lab4_3;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public TextField firstField;
    public TextField secondField;
    public Label answerLabel;
    public RadioButton multBtn;
    public RadioButton divideBtn;
    public RadioButton minusBtn;
    public RadioButton plusBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Utils.setTextFieldNumberOnly(firstField);
        Utils.setTextFieldNumberOnly(secondField);
    }

    public void calculate(ActionEvent actionEvent) {
        var firstNumber = Double.parseDouble(firstField.getText());
        var secondNumber = Double.parseDouble(secondField.getText());

        double answer;

        var selectedToggle = multBtn.getToggleGroup().getSelectedToggle();
        if (selectedToggle == multBtn)
            answer = firstNumber * secondNumber;
        else if (selectedToggle == divideBtn)
            answer = firstNumber / secondNumber;
        else if (selectedToggle == minusBtn)
            answer = firstNumber - secondNumber;
        else
            answer = firstNumber + secondNumber;

        answerLabel.setText(STR."Answer: \{answer}");
    }
}