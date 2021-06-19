/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comp228lab4faiazexercise1;

/**
 *
 * @author faiaz
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class StudentInfoFrame extends JFrame implements ActionListener, ItemListener, MouseListener {

    // label array declaration
    private JLabel[] myLabel;

    // label names array declaration
    private final String[] names;

    // text field array declaration
    private JTextField[] myText;

    // Declaring combo box to hold string values
    private final JComboBox<String> course;

    // radio buttons declaration
    private final JRadioButton radioComputerScience, radioBusiness;

    // radio button group declaration
    private final ButtonGroup groupStudentMajor;

    // check boxes declaration
    private final JCheckBox checkStudentCouncil, checkVolunteerWork, checkSports;

    // Declaring a button to display the information
    private final JButton display;

    // JList to display courses selected
    private JList<String> courseList;

    // list model to manipulate the JList elements
    private DefaultListModel<String> listModel = new DefaultListModel<String>();

    // scroll pane
    private final JScrollPane scroll;

    // text area that will containing student information
    private final JTextArea output;

    // Declaring panels to organize the content
    private final JPanel west, east, eastNorth, eastCenter, south, southBox, center;

    // Constructor

    public StudentInfoFrame() {

        // title
        super("Student Info Form");

        // Creating radio buttons
        radioComputerScience = new JRadioButton("Computer Science");
        radioBusiness = new JRadioButton("Business");

        // Creating check boxes
        checkStudentCouncil = new JCheckBox("Student Council");
        checkVolunteerWork = new JCheckBox("Volunteer Work");
        checkSports = new JCheckBox("Sports");

        // radio button group and adding radio buttons
        groupStudentMajor = new ButtonGroup();
        groupStudentMajor.add(radioComputerScience);
        groupStudentMajor.add(radioBusiness);

        // Creating JList element
        courseList = new JList<String>(listModel);

        // combo box
        course = new JComboBox<String>();
        course.setMaximumRowCount(3);
        course.setPreferredSize(new Dimension(250, 60));
        course.setSize(new Dimension(100, 500));

        // Creating text area that will hold information
        output = new JTextArea(4, 75);
        output.setEditable(false);

        // Creating scroll bar and attaching it to the text area
        scroll = new JScrollPane(output);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        // button to display information
        display = new JButton("Display");
        display.setAlignmentX(JComponent.CENTER_ALIGNMENT);


        // Creating panels
        west = new JPanel();
        east = new JPanel();
        eastNorth = new JPanel();
        eastCenter = new JPanel();
        south = new JPanel();
        southBox = new JPanel();
        center = new JPanel();

        // Setting frame layout
        setLayout(new BorderLayout(10, 10));

        // Setting west panel layout
        west.setLayout(new GridLayout(7, 1, 3, 4));

        // Adding panels to the frame
        add(west, BorderLayout.WEST);
        add(east, BorderLayout.EAST);
        add(south, BorderLayout.SOUTH);
        add(center, BorderLayout.CENTER);

        // Adding panel to south panel
        south.add(southBox);

        // Setting layout to panel
        southBox.setLayout(new BoxLayout(southBox, BoxLayout.Y_AXIS));
        // Adding button and text area to panel
        southBox.add(display);
        southBox.add(scroll);

        // Initializing label and text field array
        names = new String[]{"Name:", "Address:", "Province:", "City:", "Postal Code:", "Phone number:",
                "Email:"};
        myLabel = new JLabel[names.length];
        myText = new JTextField[names.length];

        // Loop to create labels and text fields
        // Adding labels and text fields inside the loop

        for (int i = 0; i < names.length; i++) {

            myLabel[i] = new JLabel(names[i]);
            myText[i] = new JTextField(20);
            west.add(myLabel[i]);
            west.add(myText[i]);

        }
        // Setting layout to panel
        center.setLayout(new BorderLayout(2, 2));

        // Adding check boxes to panel
        center.add(checkStudentCouncil, BorderLayout.NORTH);
        center.add(checkVolunteerWork, BorderLayout.CENTER);
        center.add(checkSports, BorderLayout.SOUTH);

        // Setting layout to panel
        east.setLayout(new BorderLayout(2, 40));

        // Setting layout to panel
        eastNorth.setLayout(new FlowLayout());

        // Adding panel inside another panel
        east.add(eastNorth, BorderLayout.NORTH);
        east.add(eastCenter, BorderLayout.CENTER);

        // Adding radio buttons to panel
        eastNorth.add(radioComputerScience);
        eastNorth.add(radioBusiness);

        // Setting layout to panel
        eastCenter.setLayout(new GridLayout(2, 1, 0, 0));

        // Adding listener to combo box, button, and radio buttons
        course.addItemListener(this);
        display.addMouseListener(this);
        radioBusiness.addActionListener(this);
        radioComputerScience.addActionListener(this);

        // Adding combo box and JList to panel
        eastCenter.add(course);
        eastCenter.add(courseList);


    }

    // Listener for combo box
    @Override
    public void itemStateChanged(ItemEvent e) {

        // When an item is selected
        if (e.getStateChange() == ItemEvent.SELECTED) {

            // If the selected item is not in the list, it is added
            if (!listModel.contains(e.getItem().toString()))
                listModel.addElement(e.getItem().toString());

        }

    }

    // Listener for radio buttons
    @Override
    public void actionPerformed(ActionEvent e) {


        JRadioButton auxRadio = (JRadioButton) e.getSource();

        // If computer science radio button is selected
        if (auxRadio.getText() == "Computer Science") {

            // Clearing combo box and JList
            course.removeAllItems();
            listModel.removeAllElements();
            // Adding elements to combo box
            course.addItem("");
            course.addItem("ASP.Net");
            course.addItem("Structure of HCIS");
            course.addItem("Java");
            course.addItem("C#");


        }

        // If business radio button is selected

        else {

            // Clearing combo box and JList
            course.removeAllItems();
            listModel.removeAllElements();
            // Adding elements to combo box
            course.addItem("");
            course.addItem("Accounting");
            course.addItem("Operation Research");
            course.addItem("Business Management");
            course.addItem("Marketing");

        }

    }


    // Listener for display button
    @Override
    public void mouseClicked(MouseEvent e) {


        // Creating courses, activities and program variables
        String myCourses = "";
        String otherActivities = "";
        String program = "";

        // Checking which check boxes were selected and assigning the value to
        // otherActivities variable
        if (checkStudentCouncil.isSelected() && checkVolunteerWork.isSelected() && checkSports.isSelected())

            otherActivities = checkStudentCouncil.getText() + "-" + checkVolunteerWork.getText() + "-" + checkSports.getText();

        else if (checkStudentCouncil.isSelected() && !checkVolunteerWork.isSelected() && !checkSports.isSelected())

            otherActivities = checkStudentCouncil.getText();

        else if (checkVolunteerWork.isSelected() && !checkStudentCouncil.isSelected() && !checkSports.isSelected())

            otherActivities = checkVolunteerWork.getText();

        else if (checkSports.isSelected() && !checkStudentCouncil.isSelected() && !checkVolunteerWork.isSelected())

            otherActivities = checkSports.getText();


        else if (checkStudentCouncil.isSelected() && checkVolunteerWork.isSelected() && !checkSports.isSelected())

            otherActivities = checkStudentCouncil.getText() + "-" + checkVolunteerWork.getText();

        else if (checkStudentCouncil.isSelected() && !checkVolunteerWork.isSelected() && checkSports.isSelected())

            otherActivities = checkStudentCouncil.getText() + "-" + checkSports.getText();

        else if (!checkStudentCouncil.isSelected() && checkVolunteerWork.isSelected() && checkSports.isSelected())

            otherActivities = checkVolunteerWork.getText() + "-" + checkSports.getText();

        else if (!checkStudentCouncil.isSelected() && !checkVolunteerWork.isSelected())

            otherActivities = "No activities";

        // Checking which radio button was selected and assigning the value to

        // program variable

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

            myCourses += listModel.getElementAt(i) + "\n";

        }

        // Printing student information in text area
        output.setText(String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s%n%s%s", myText[0].getText(), myText[1].getText(),
                myText[2].getText(), myText[3].getText(), myText[4].getText(), myText[5].getText(), myText[6].getText(),
                otherActivities, program, "Courses:", myCourses));

        // Setting text area cursor at the beginning
        output.setCaretPosition(0);
    }

    // Not implemented methods

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
