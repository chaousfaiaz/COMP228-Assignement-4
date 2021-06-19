/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp228lab4faiazexercise2;

/**
 *
 * @author faiaz
 */
import javafx.application.Application;
import javafx.beans.value.*;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.ScrollPane.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StudentInfoFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {


        // Declaring grid panes
        GridPane root = new GridPane();
        root.setAlignment(Pos.TOP_LEFT);
        root.setHgap(5);
        root.setVgap(5);

        GridPane rightPane = new GridPane();
        rightPane.setAlignment(Pos.TOP_LEFT);
        rightPane.setHgap(2);
        rightPane.setVgap(5);

        GridPane bottomPane = new GridPane();
        bottomPane.setAlignment(Pos.BOTTOM_CENTER);
        bottomPane.setHgap(5);
        bottomPane.setVgap(5);

        GridPane leftPane = new GridPane();
        leftPane.setAlignment(Pos.TOP_LEFT);
        leftPane.setHgap(5);
        leftPane.setVgap(5);

        GridPane rightLeftContainer = new GridPane();
        rightLeftContainer.setAlignment(Pos.TOP_LEFT);
        rightLeftContainer.setHgap(2);
        rightLeftContainer.setVgap(5);

        GridPane radioPane = new GridPane();
        radioPane.setAlignment(Pos.BOTTOM_CENTER);
        radioPane.setHgap(5);
        radioPane.setVgap(5);

        // label array declaration
        Label[] myLabel;

        // label names array declaration
        String[] names = { "Name:", "Address:", "Province:", "City:", "Postal Code:", "Phone number:", "Email:" };

        // text field array declaration
        TextField[] myText;

        // Declaring combo box to hold string values
        ComboBox<String> course;

        // radio buttons declaration
        RadioButton radioComputerScience, radioBusiness;

        // radio toggle group declaration
        ToggleGroup groupStudentMajor;

        // check boxes declaration
        CheckBox checkStudentCouncil, checkVolunteerWork,checkSports;

        // Declaring a button to display the information
        Button display;

        // list to display courses selected
        ListView<String> courseList;

        // list view model to manipulate the List element
        ObservableList<String> listModel = FXCollections.observableArrayList();

        // Declaring scroll pane that will be attached to the text area
        ScrollPane scroll;

        // Declaring a text area that will contain information about the student
        TextArea output;

        // Values for list view
        ObservableList<String> computerScienceCourses = FXCollections.observableArrayList("ASP.Net", "Structure of HCIS", "Java", "C#");

        ObservableList<String> businessCourses = FXCollections.observableArrayList("Accounting","Operation Research","Business Management", "Marketing");

        // Creating radio buttons
        radioComputerScience = new RadioButton("Computer Science");
        radioBusiness = new RadioButton("Business");

        // Creating check boxes
        checkStudentCouncil = new CheckBox(String.format("%-30s", "Student Council"));
        checkVolunteerWork = new CheckBox("Volunteer Work");
        checkSports=new CheckBox("Sports");

        // radio button group and adding radio buttons to it
        groupStudentMajor = new ToggleGroup();
        radioComputerScience.setToggleGroup(groupStudentMajor);
        radioBusiness.setToggleGroup(groupStudentMajor);

        // Creating ListView element
        courseList = new ListView<String>(listModel);
        courseList.setPrefSize(1, 100);

        // combo box
        course = new ComboBox<String>();
        course.setVisibleRowCount(3);
        course.setPrefSize(300, 60);

        // Creating text area that will hold information
        output = new TextArea();
        output.setPrefColumnCount(75);
        output.setPrefRowCount(6);
        output.setEditable(false);

        // Creating scroll bar and attaching it to the text area
        scroll = new ScrollPane(output);
        scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scroll.setHbarPolicy(ScrollBarPolicy.ALWAYS);
        GridPane.setHalignment(scroll, HPos.CENTER);


        // button to display information
        display = new Button("Display");
        GridPane.setHalignment(display, HPos.CENTER);

        // Adding elements to right pane
        rightPane.add(radioPane, 7, 0);
        rightPane.add(course, 7, 2);
        rightPane.add(courseList, 7, 3);

        // Adding radio buttons to radio pane
        radioPane.add(radioComputerScience, 0, 0);
        radioPane.add(radioBusiness, 1, 0);

        // Adding elements to left pane
        leftPane.add(checkVolunteerWork, 6, 0);
        leftPane.add(checkStudentCouncil, 6, 3);
        leftPane.add(checkSports, 6, 6);

        // Adding elements to bottom pane
        bottomPane.add(display, 0, 6);
        bottomPane.add(scroll, 0, 7);

        rightLeftContainer.add(leftPane, 0, 0);
        rightLeftContainer.add(rightPane, 1, 0);

        // Adding elements to root pane
        root.add(rightLeftContainer, 0, 0);
        root.add(bottomPane, 0, 1);

        // Initializing label and text field array
        myLabel = new Label[names.length];
        myText = new TextField[names.length];

        for (int i = 0; i < names.length; i++) {

            myLabel[i] = new Label(String.format("%-30s", names[i]));
            myText[i] = new TextField();
            leftPane.add(myLabel[i], 0, i);
            leftPane.add(myText[i], 5, i);

        }


        // Adding listener to combo box, button, and radio buttons
        groupStudentMajor.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

                RadioButton checked = (RadioButton) groupStudentMajor.getSelectedToggle();

                if (checked.getText().equals("Computer Science")) {
                    course.getSelectionModel().clearSelection();
                    listModel.clear();
                    course.setItems(computerScienceCourses);
                }

                else {
                    if (checked.getText().equals("Business")) {
                        course.getSelectionModel().clearSelection();
                        listModel.clear();
                        course.setItems(businessCourses);
                    }
                }

            }
        });

        course.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> arg0, String t1, String t2) {

                if (!listModel.contains(t2) && t2 != null) {
                    listModel.add(t2);
                }
            }
        });

        display.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                // Creating courses, activities and program variables
                String myCourses = "";
                String otherActivities = "";
                String program = "";

                // Checking which check boxes were selected and assigning the
                // value to otherActivities variable
                if (checkStudentCouncil.isSelected() && checkVolunteerWork.isSelected() && checkSports.isSelected())

                    otherActivities = checkStudentCouncil.getText().trim() + "-" + checkVolunteerWork.getText().trim() +"-" + checkSports.getText();

                else if (checkStudentCouncil.isSelected() && !checkVolunteerWork.isSelected() && !checkSports.isSelected())

                    otherActivities = checkStudentCouncil.getText().trim();

                else if (checkVolunteerWork.isSelected() && !checkStudentCouncil.isSelected()&& !checkSports.isSelected())

                    otherActivities = checkVolunteerWork.getText().trim();

                else if (checkSports.isSelected() && !checkStudentCouncil.isSelected() && !checkVolunteerWork.isSelected())

                    otherActivities= checkSports.getText().trim();

                else if (checkStudentCouncil.isSelected() && checkVolunteerWork.isSelected() && !checkSports.isSelected())

                    otherActivities = checkStudentCouncil.getText().trim() + "-" +checkVolunteerWork.getText().trim();

                else if (checkStudentCouncil.isSelected() && !checkVolunteerWork.isSelected() && checkSports.isSelected())

                    otherActivities = checkStudentCouncil.getText().trim() + "-" +checkSports.getText().trim();

                else if (!checkStudentCouncil.isSelected() && checkVolunteerWork.isSelected() && checkSports.isSelected())

                    otherActivities = checkVolunteerWork.getText().trim() + "-" +checkSports.getText().trim();


                else if (!checkStudentCouncil.isSelected() && !checkVolunteerWork.isSelected())

                    otherActivities = "No activities";

                // Checking which radio button was selected and assigning the
                // value to program variable
                if (radioBusiness.isSelected())

                    program = radioBusiness.getText();

                else if (radioComputerScience.isSelected())

                    program = radioComputerScience.getText();

                else
                    program = "No program selected";

                // Clearing text area
                output.setText("");

                // Getting courses from JList and adding the values to myCourses String
                for (int i = 0; i < listModel.size(); i++) {

                    myCourses += listModel.get(i) + "\n";

                }

                // Printing student information in text area
                output.setText(String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s%n%s%n%s", myText[0].getText(),
                        myText[1].getText(), myText[2].getText(), myText[3].getText(), myText[4].getText(),
                        myText[5].getText(), myText[6].getText(), otherActivities, program, "Courses:", myCourses));

                // Setting text area cursor at the beginning
                output.positionCaret(0);

            }

        });

        // Creating scene
        Scene scene = new Scene(root, 850, 400);

        // Configuring stage
        primaryStage.setTitle("Student Information Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
