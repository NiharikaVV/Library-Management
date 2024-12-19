import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StaffManagement extends JFrame {
    private JTextField nameField, roleField;
    private JButton addButton, removeButton;

    public StaffManagement() {
        setTitle("Staff Management");
        setLayout(new FlowLayout());
        
        JLabel nameLabel = new JLabel("Staff Name:");
        nameField = new JTextField(20);
        
        JLabel roleLabel = new JLabel("Role:");
        roleField = new JTextField(20);
        
        addButton = new JButton("Add Staff");
        removeButton = new JButton("Remove Staff");
        
        add(nameLabel);
        add(nameField);
        add(roleLabel);
        add(roleField);
        add(addButton);
        add(removeButton);
        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStaff();
            }
        });
        
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStaff();
            }
        });
        
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addStaff() {
        String name = nameField.getText();
        String role = roleField.getText();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO staff (name, role) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.setString(2, role);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Staff Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void removeStaff() {
        String name = nameField.getText();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM staff WHERE name = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, name);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Staff Removed Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new StaffManagement();
    }
}
