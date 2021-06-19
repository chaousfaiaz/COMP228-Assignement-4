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

    import javax.swing.JFrame;

public class StudentInfo {

    public static void main(String[] args) {

        // object displaying frame
        StudentInfoFrame studentInformationFrame = new StudentInfoFrame();
        studentInformationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Setting frame visibility
        studentInformationFrame.setVisible(true);
        // Setting frame size
        studentInformationFrame.setSize(900, 400);
        // Setting frame display = center
        studentInformationFrame.setLocationRelativeTo(null);

    }

}

