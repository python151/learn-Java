import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Day implements Serializable{
    private String[] dayNames = Main.dayNames;
    public int dayOfWeek;

    public String name;
    
    public ArrayList<Exercise> exercises = new ArrayList<Exercise>();
    public ArrayList<Equipment> equipment = new ArrayList<Equipment>();

    public Day(String aName, int aDayOfWeek) {
        name = aName;
        dayOfWeek = aDayOfWeek;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);

        for (Equipment e : exercise.equipment) {
            if (!equipment.contains(e)) equipment.add(e);            
        }
    }

    public void printInfo() {
        System.out.println((String) name);
        System.out.println((int) dayOfWeek);

        System.out.println((String) "Exercises:");
        for (Exercise e : exercises) {
            System.out.println((String) "    "+e.name);
        }
    }

    public void showInfo() {
        JFrame frame = new JFrame("Workout");
        frame.setSize(500, 150);
        frame.setLayout(new GridLayout(2, 2));
        frame.setLocation(100, (dayOfWeek)*150);

        JPanel title = new JPanel();

        JLabel day = new JLabel(dayNames[dayOfWeek]);
        day.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.add(day);

        JLabel divider = new JLabel("-");
        divider.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.add(divider);

        JLabel nameLable = new JLabel(name);
        nameLable.setAlignmentX(Component.RIGHT_ALIGNMENT);
        title.add(nameLable);
        
        frame.add(title);

        JPanel exercisePanel = new JPanel();
        exercisePanel.add(new JLabel("Exercises: "));

        int i=0;

        for (Exercise e : exercises) {
            String nameOfE = e.name;

            if (i != exercises.size()-1) {
                nameOfE = e.name + ",";
            }

            exercisePanel.add(new JLabel(nameOfE));
            i++;
        }
        frame.add(exercisePanel);

        JPanel buttonPanel = new JPanel();

        JButton newDayButton = new JButton("New day/exercise");
        newDayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Main.newDayGui();
            }
        });

        buttonPanel.add(newDayButton);
        frame.add(buttonPanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}