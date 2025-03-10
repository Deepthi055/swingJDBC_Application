package SwingJDBC;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class BurgerOrderingApp extends JFrame {
    private JLabel totalLabel;
    private JButton resetButton, cartButton, orderButton;
    private double totalPrice = 0.0;
    private final ArrayList<Burger> burgers = new ArrayList<>();
    private final JPanel burgerPanel;

    public BurgerOrderingApp() {
        setTitle("🍔 TASTYBURGER");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Background Panel Setup
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);

        createNavBar();

        // Burger Data
        burgers.add(new Burger("BBQ Burger", "src/SwingJDBC/images/bbq_burger.jpg", 5.99));
        burgers.add(new Burger("Cheese Burger", "src/SwingJDBC/images/cheese_burger.jpg", 4.99));
        burgers.add(new Burger("Classic Burger", "src/SwingJDBC/images/classic_burger.jpeg", 3.99));
        burgers.add(new Burger("Veggie Burger", "src/SwingJDBC/images/veggie_burger.jpg", 4.49));
        burgers.add(new Burger("Spicy Chicken Burger", "src/SwingJDBC/images/spicy_chicken_burger.jpeg", 6.49));
        burgers.add(new Burger("Double Patty Burger", "src/SwingJDBC/images/double_patty_burger.jpeg", 7.99));
        burgers.add(new Burger("Mushroom Swiss Burger", "src/SwingJDBC/images/mush.jpeg", 6.99));
        burgers.add(new Burger("Fish Fillet Burger", "src/SwingJDBC/images/fish_fillet_burger.jpeg", 6.79));
        burgers.add(new Burger("Mexican Jalapeño Burger", "src/SwingJDBC/images/mexican.jpeg", 6.89));

        JPanel containerPanel = new JPanel(new GridBagLayout());
        burgerPanel = new JPanel(new GridLayout(0, 3, 15, 15)); // **3 columns, dynamic rows, better spacing**
        burgerPanel.setBackground(Color.LIGHT_GRAY);
        loadBurgers();

        containerPanel.add(burgerPanel);
        JScrollPane scrollPane = new JScrollPane(containerPanel);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Bottom Buttons Panel
        JPanel bottomPanel = new JPanel();
        cartButton = createStyledButton("🛍 View Cart", Color.GREEN);
        resetButton = createStyledButton("❌ Reset", Color.RED);
        orderButton = createStyledButton("📦 Order Now", Color.BLUE);

        cartButton.addActionListener(e -> showCart());
        resetButton.addActionListener(e -> resetOrder());
        orderButton.addActionListener(e -> placeOrder());

        bottomPanel.add(cartButton);
        bottomPanel.add(resetButton);
        bottomPanel.add(orderButton);

        totalLabel = new JLabel("Total: $0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        bottomPanel.add(totalLabel);

        mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
        setVisible(true);
    }

    private void createNavBar() {
        JPanel navBar = new JPanel();
        navBar.setBackground(Color.YELLOW);
        navBar.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

        String[] menuItems = {"🏠 Home", "📜 Menu", "🛒 Cart", "ℹ About", "❌ Exit"};
        for (String item : menuItems) {
            JButton button = new JButton(item);
            button.setBackground(Color.YELLOW);
            button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
            button.setFont(new Font("Arial", Font.BOLD, 14));
            navBar.add(button);
        }
        add(navBar, BorderLayout.NORTH);
    }

    private void loadBurgers() {
        for (Burger burger : burgers) {
            JPanel panel = new JPanel(new BorderLayout());
            panel.setPreferredSize(new Dimension(210, 230)); // **Smaller size**
            panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            panel.setBackground(Color.WHITE);

            JLabel imageLabel = new JLabel(new ImageIcon(burger.getImagePath()));
            JLabel nameLabel = new JLabel("<html><center>" + burger.getName() + "<br>Price: $" + burger.getPrice() + "</center></html>", JLabel.CENTER);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 12));

            JButton addButton = createStyledButton("Add 🛒", new Color(255, 204, 0));
            addButton.addActionListener(e -> addToCart(burger));

            panel.add(imageLabel, BorderLayout.CENTER);
            panel.add(nameLabel, BorderLayout.NORTH);
            panel.add(addButton, BorderLayout.SOUTH);
            burgerPanel.add(panel);
        }
    }

    private void addToCart(Burger burger) {
        totalPrice += burger.getPrice();
        totalLabel.setText("Total: $" + String.format("%.2f", totalPrice));
        addToCartDB(burger.getName(), burger.getPrice());
    }

    private void addToCartDB(String itemName, double price) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO cart (item_name, price) VALUES (?, ?)")) {
            stmt.setString(1, itemName);
            stmt.setDouble(2, price);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showCart() {
        JOptionPane.showMessageDialog(this, "Total Price: $" + String.format("%.2f", totalPrice));
    }

    private void resetOrder() {
        totalPrice = 0.0;
        totalLabel.setText("Total: $0.00");
    }

    private void placeOrder() {
        if (totalPrice == 0) {
            JOptionPane.showMessageDialog(this, "Your cart is empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "✅ Order placed successfully!\nTotal Amount: $" + String.format("%.2f", totalPrice));
            resetOrder();
        }
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.BLACK);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BurgerOrderingApp::new);
    }
}

class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/BurgerShop";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
