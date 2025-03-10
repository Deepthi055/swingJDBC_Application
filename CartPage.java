package SwingJDBC; 
// Add this at the top

import javax.swing.*;
import java.util.ArrayList;
import SwingJDBC.Burger;
import SwingJDBC.CartItem;


public class CartPage extends JFrame {
    public CartPage(ArrayList<CartItem> cartItems, double totalPrice) {
        setTitle("🛒 Your Cart");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTextArea cartDetails = new JTextArea();
        for (CartItem item : cartItems) {
            cartDetails.append(item.burger.name + " - $" + item.burger.price + " x " + item.quantity + "\n");
        }
        cartDetails.append("\nTotal Price: $" + totalPrice);
        add(new JScrollPane(cartDetails));
        setVisible(true);
    }
}
