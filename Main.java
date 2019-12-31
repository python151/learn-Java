import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Main {
    public static String[] dayNames = {
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday"
    };
    public static ArrayList<Day> days = new ArrayList<Day>();
    

    public static void success() {
        final JFrame frame = new JFrame("Success");
        frame.setSize(300, 300);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Day Added!");
        nameLabel.setBounds(150, 150, 150, 150);

        frame.add(nameLabel);

        frame.setVisible(true);
    }

    public static JTextField text(String msg, JFrame frame, int[] bounds1, int[] bounds2) {
        JLabel nameLabel = new JLabel(msg);
        nameLabel.setBounds(bounds1[0], bounds1[1], bounds1[2], bounds1[3]);
        JTextField name = new JTextField();
        name.setBounds(bounds2[0], bounds2[1], bounds2[2], bounds2[3]);
        frame.add(nameLabel);
        frame.add(name);
        return name;
    }

    public static void newDayGuiFill(final Day day) {
        final JFrame frame = new JFrame("Add A New Exercise");
        frame.setSize(500, 300);
        frame.setLayout(null);

        int[] b1 = {100, 50, 190, 30};
        int[] b2 = {150, 50, 190, 30};
        final JTextField name = text("Name: ", frame, b1, b2);

        JButton submitButton1 = new JButton("Submit");
        submitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Exercise e = new Exercise(name.getText());
                day.addExercise(e);
                Main.days.add(day);
                Store(day);
                frame.dispose();
            }
        });
        submitButton1.setBounds(150, 170, 190, 40);
        frame.add(submitButton1);

        frame.setVisible(true);
    }

    public static void newDayGui() {
        final JFrame frame = new JFrame("Add A New Day");
        frame.setSize(500, 300);
        frame.setLayout(null);

        int[] b1 = {100, 50, 190, 30};
        int[] b2 = {150, 50, 190, 30};
        final JTextField name = text("Name: ", frame, b1, b2);

        int[] b11 = {70, 90, 190, 30};
        int[] b22 = {150, 90, 190, 30};
        final JTextField dayOfWeek = text("Day Of Week: ", frame, b11, b22);

        int[] b111 = {20, 130, 190, 30};
        int[] b222 = {150, 130, 190, 30};
        final JTextField numOfExercises = text("Number Of Exercises ", frame, b111, b222);

        JButton submitButton1 = new JButton("Submit");
        submitButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String nameInString = name.getText();
                int dayOfWeekInInt = Integer.parseInt(dayOfWeek.getText());
                Day obj = new Day(nameInString, dayOfWeekInInt);
                frame.dispose();
                for (int i=0; i<Integer.parseInt(numOfExercises.getText()); i++) {
                    newDayGuiFill(obj);
                } 
                Store(obj);                
            }
        });
        submitButton1.setBounds(150, 170, 190, 40);
        frame.add(submitButton1);

        frame.setVisible(true);
    }

    public static void newDayConsole() {
        Scanner in = new Scanner(System.in);
        System.out.println("Index Of Day: ");
        int dayOfWeek = in.nextInt();
        System.out.println("Name: ");
        String name = in.next();

        Day obj = new Day(name, dayOfWeek);

        System.out.println("how many exercises: ");
        int numOfExercises = in.nextInt();
        for (int i=0; i<numOfExercises; i++) {
            System.out.println("Name Of Exercise Number "+i+":");
            Exercise exercise = new Exercise(in.next());
            System.out.println("Amount Of Equipment Items for this exercise: ");
            int numOfEquipment = in.nextInt();
            for(int j=0; j<numOfEquipment; j++) {
                System.out.println("Name Of Equipment: ");
                String eName = in.next();
                System.out.println("Is it required y for yes n for no: ");
                boolean yes = ("y" == in.next());
                exercise.addEquipment(new Equipment(eName, yes));
            }
            obj.addExercise(exercise);
        }
        days.add(obj);
        Store(obj);
    }

    private static void showDay(int dayOfWeek) {
        days.get(dayOfWeek).showInfo();
    }

    private static void Store(Day day) {
        try {
            FileOutputStream f = new FileOutputStream(new File(dayNames[day.dayOfWeek]+".txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(day);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}
    }

    private static void Restore() {
        try {
            for(Day d : days) {
                FileInputStream fi = new FileInputStream(new File(dayNames[d.dayOfWeek]+".txt"));
                ObjectInputStream oi = new ObjectInputStream(fi);
                Day dObj = (Day) oi.readObject();
                d.equipment = dObj.equipment;
                d.exercises = dObj.exercises;
                d.name = dObj.name;
                oi.close();
                fi.close();
            }
            
        } catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
        //newDayConsole();
        for (Day day : days) Store(day);
        days.add(new Day("", 0));
        days.add(new Day("", 1));
        days.add(new Day("", 2));
        days.add(new Day("", 3));
        days.add(new Day("", 4));
        days.add(new Day("", 5));
        days.add(new Day("", 6));
        Restore();

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int currentDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        showDay(currentDayOfWeek-1);
    }
}