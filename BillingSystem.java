
package javaapplication94;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillingSystem extends JFrame {

    JTextField itemNameField, quantityField, priceField;
    JTextArea billArea;
    double totalAmount = 0;

    public BillingSystem() {
        setTitle("Billing System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        itemNameField = new JTextField(20);
        quantityField = new JTextField(10);
        priceField = new JTextField(10);
        billArea = new JTextArea(10, 30);
        billArea.setEditable(false);

        JButton addItemButton = new JButton("Add Item");
        JButton generateBillButton = new JButton("Generate Bill");

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addItem();
            }
        });

        generateBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateBill();
            }
        });
        

        JPanel panel = new JPanel();
        panel.add(new JLabel("Item Name: "));
        panel.add(itemNameField);
        panel.add(new JLabel("Quantity: "));
        panel.add(quantityField);
        panel.add(new JLabel("Price: "));
        panel.add(priceField);
        panel.add(addItemButton);
        panel.add(generateBillButton);

        JScrollPane scrollPane = new JScrollPane(billArea);

        add(panel, "North");
        add(scrollPane);

        setVisible(true);
    }

    private void addItem() {
        String itemName = itemNameField.getText();
        int quantity = Integer.parseInt(quantityField.getText());
        double price = Double.parseDouble(priceField.getText());

        double totalItemAmount = quantity * price;
        totalAmount += totalItemAmount;

        String itemDetails = String.format("%s - %d x %.2f = %.2f\n", itemName, quantity, price, totalItemAmount);
        billArea.append(itemDetails);

        itemNameField.setText("");
        quantityField.setText("");
        priceField.setText("");
    }

    private void generateBill() {
        JOptionPane.showMessageDialog(this, String.format("Total Amount: %.2f", totalAmount));
        resetBill();
    }

    private void resetBill() {
        totalAmount = 0;
        billArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BillingSystem();
            }
        });
    }
}
