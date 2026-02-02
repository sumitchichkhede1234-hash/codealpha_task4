import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;

public class HotelReservationSystemGUI extends JFrame implements ActionListener {

    int standardRooms = 5;
    int deluxeRooms = 3;
    int suiteRooms = 2;

    JButton viewBtn, bookBtn, cancelBtn, exitBtn;

    public HotelReservationSystemGUI() {

        setTitle("Hotel Reservation System");
        setSize(400, 300);
        setLayout(new GridLayout(5, 1));

        JLabel heading = new JLabel("Hotel Reservation System", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 18));

        viewBtn = new JButton("View Available Rooms");
        bookBtn = new JButton("Book a Room");
        cancelBtn = new JButton("Cancel Reservation");
        exitBtn = new JButton("Exit");

        add(heading);
        add(viewBtn);
        add(bookBtn);
        add(cancelBtn);
        add(exitBtn);

        viewBtn.addActionListener(this);
        bookBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == viewBtn) {
            showRooms();
        }

        if (e.getSource() == bookBtn) {
            bookRoom();
        }

        if (e.getSource() == cancelBtn) {
            JOptionPane.showMessageDialog(this,
                    "Reservation cancelled successfully.\n(Demo cancellation)");
        }

        if (e.getSource() == exitBtn) {
            System.exit(0);
        }
    }

    void showRooms() {
        String message = "Available Rooms:\n"
                + "Standard: " + standardRooms + "\n"
                + "Deluxe: " + deluxeRooms + "\n"
                + "Suite: " + suiteRooms;

        JOptionPane.showMessageDialog(this, message);
    }

    void bookRoom() {

        String[] options = {"Standard", "Deluxe", "Suite"};
        int choice = JOptionPane.showOptionDialog(
                this,
                "Select Room Type",
                "Room Booking",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        String name = JOptionPane.showInputDialog(this, "Enter Customer Name:");

        if (name == null || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty.");
            return;
        }

        boolean booked = false;
        String roomType = "";

        if (choice == 0 && standardRooms > 0) {
            standardRooms--;
            roomType = "Standard";
            booked = true;
        } else if (choice == 1 && deluxeRooms > 0) {
            deluxeRooms--;
            roomType = "Deluxe";
            booked = true;
        } else if (choice == 2 && suiteRooms > 0) {
            suiteRooms--;
            roomType = "Suite";
            booked = true;
        }

        if (booked) {
            saveBooking(name, roomType);
            JOptionPane.showMessageDialog(this,
                    "Payment Successful!\nRoom Booked: " + roomType);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Selected room not available.");
        }
    }

    void saveBooking(String name, String roomType) {
        try {
            FileWriter fw = new FileWriter("bookings.txt", true);
            fw.write("Customer: " + name + " | Room: " + roomType + "\n");
            fw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving booking.");
        }
    }

    public static void main(String[] args) {
        new HotelReservationSystemGUI();
    }
}
