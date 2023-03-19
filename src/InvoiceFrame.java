import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.util.Vector;

public class InvoiceFrame extends JFrame {
    Vector<JTextField> quantity = new Vector<>();
    Vector<JTextField> prices = new Vector<>();
    Vector<JTextField> totals = new Vector<>();

    JFrame frame;
    JPanel main;

    JPanel title;
    JTextField header;
    JPanel address;
    JTextArea addressField;

    JPanel product;
    JTextField itemTitle;
    JTextField qtyTitle;
    JTextField priceTitle;
    JTextField totalTitle;
    JTextField item1;
    JTextField qty1;
    JTextField price1;
    JTextField total1;
    JTextField item2;
    JTextField qty2;
    JTextField price2;
    JTextField total2;
    JTextField item3;
    JTextField qty3;
    JTextField price3;
    JTextField total3;

    JPanel overall;
    JTextField amountDue;
    JButton calculate;

    public InvoiceFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        frame = new JFrame();
        main = new JPanel();

        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        frame.setSize(screenWidth / 2, screenHeight / 2);
        frame.setLocation(screenWidth / 4, screenHeight / 4);

        main.setLayout(new BorderLayout());

        title();
        title.setBounds((frame.getWidth() / 4) + 20, 300, 340,75);
        address.setBounds(frame.getWidth() / 5 + frame.getWidth() / 5, 85, 200, 80);
        main.add(title);
        main.add(address);

        product();
        product.setBounds(30, 185, frame.getWidth() - 40, 80);
        main.add(product);

        total();
        //No matter what I do, this panel will not budge, so I had to leave it at the top and move my title to the bottom
        overall.setBounds(20, 190, 40, 50);
        main.add(overall);

        frame.add(main);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void title() {
        title = new JPanel();

        header = new JTextField("Camp Invoice");
        header.setEditable(false);
        header.setPreferredSize(new Dimension(340, 75));
        header.setFont(new Font("Arial", Font.BOLD, 50));
        header.setBorder(null);
        title.add(header);

        address = new JPanel();
        address.setBorder(new LineBorder(Color.BLACK, 1));

        addressField = new JTextArea(4, 10);
        addressField.setBackground(frame.getBackground());
        addressField.setText("Camp Goods Inc.\n435 North Street\nRoadTown, CA 45832");
        addressField.setFont(new Font("Arial", Font.PLAIN, 15));
        addressField.setEditable(false);
        address.add(addressField);
    }

    private void product() {
        product = new JPanel();
        product.setLayout(new GridLayout(4, 4, 1, 1));

        //Row 1
        itemTitle = new JTextField("Item");
        itemTitle.setEditable(false);
        itemTitle.setBorder(null);
        product.add(itemTitle);
        qtyTitle = new JTextField("Qty");
        qtyTitle.setEditable(false);
        qtyTitle.setBorder(null);
        product.add(qtyTitle);
        priceTitle = new JTextField("Price");
        priceTitle.setEditable(false);
        priceTitle.setBorder(null);
        product.add(priceTitle);
        totalTitle = new JTextField("Total");
        totalTitle.setEditable(false);
        totalTitle.setBorder(null);
        product.add(totalTitle);

        //Row 2
        item1 = new JTextField();
        product.add(item1);
        qty1 = new JTextField();
        quantity.add(qty1);
        product.add(qty1);
        price1 = new JTextField("$");
        prices.add(price1);
        product.add(price1);
        total1 = new JTextField();
        total1.setEditable(false);
        total1.setBorder(null);
        totals.add(total1);
        product.add(total1);

        //Row 3
        item2 = new JTextField();
        product.add(item2);
        qty2 = new JTextField();
        quantity.add(qty2);
        product.add(qty2);
        price2 = new JTextField("$");
        prices.add(price2);
        product.add(price2);
        total2 = new JTextField();
        total2.setEditable(false);
        total2.setBorder(null);
        totals.add(total2);
        product.add(total2);

        //Row 4
        item3 = new JTextField();
        product.add(item3);
        qty3 = new JTextField();
        quantity.add(qty3);
        product.add(qty3);
        price3 = new JTextField("$");
        prices.add(price3);
        product.add(price3);
        total3 = new JTextField();
        total3.setEditable(false);
        total3.setBorder(null);
        totals.add(total3);
        product.add(total3);
    }

    public void total() {
        overall = new JPanel();

        amountDue = new JTextField("Amount Due: ");
        amountDue.setEditable(false);
        amountDue.setBorder(null);
        amountDue.setPreferredSize(new Dimension(110, 20));
        overall.add(amountDue);

        calculate = new JButton("Calculate");
        calculate.addActionListener((ActionEvent ae) -> calcTotal(quantity, prices, totals, amountDue));
        overall.add(calculate);
    }

    public void calcTotal(Vector<JTextField> quantity, Vector<JTextField> prices, Vector<JTextField> totals, JTextField amountDue) {
        String test;
        int i = 0;
        double calc, fullTotal = 0;
        Vector<Double> amount = new Vector<>();
        Vector<Double> cost = new Vector<>();
        Vector<Double> multi = new Vector<>();

        DecimalFormat round = new DecimalFormat("0.00");

        for (JTextField qty : quantity) {
            if (qty.getText().equals("")) {
                JOptionPane.showMessageDialog(frame, "You are missing a quantity.");
                return;
            }

            try {
                amount.add(Double.valueOf(qty.getText()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "You might not have put a number into the Quantity box.");
                return;
            }
        }

        for (JTextField price : prices) {
            if (price.getText().equals("")) {
                JOptionPane.showMessageDialog(frame, "You are missing a price.");
                return;
            }

            test = price.getText().substring(1);

            try {
                cost.add(Double.valueOf(test));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "You might not have put a number into the Price box.");
                return;
            }

        }

        for (JTextField total : totals) {
            calc = amount.get(i) * cost.get(i);

            multi.add(calc);

            total.setText("$" + round.format(calc));

            i++;
        }

        for (double num : multi) {
            fullTotal += num;
        }

        amountDue.setText("Amount Due: $" + round.format(fullTotal));
    }
}
