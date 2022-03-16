package user;

import scm.Resource;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class MainGui extends JFrame {

    private JLabel status;
    private JPanel panel;
    private JList<Resource> productList;
    private JLabel ticket;
    private JLabel serving;
    private final List<Integer> tickets;

    public MainGui() {
        $$$setupUI$$$();
        this.productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.productList.setLayoutOrientation(JList.VERTICAL_WRAP);
        this.productList.setVisibleRowCount(3);
        this.productList.setFixedCellHeight(25);
        this.productList.setFixedCellWidth(175);
        this.status.setBorder(new EmptyBorder(0, 0, 5, 0));
        this.panel.setBorder(new EmptyBorder(15, 15, 15, 15));
        this.serving.setBorder(new EmptyBorder(5, 0, 0, 0));
        this.ticket.setBorder(new EmptyBorder(5, 0, 0, 0));
        this.tickets = new LinkedList<>();
        setSize(400, 250);
        setTitle("Smart Coffee Machine: ");
        getContentPane().add(this.panel);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void setStatusText(final String status) {
        this.status.setText("Status: " + status);
    }

    public void setProducts(final List<Resource> products) {
        DefaultListModel<Resource> model = new DefaultListModel<>();
        model.addAll(products);
        this.productList.setModel(model);
    }

    public void setName(final String name) {
        this.setTitle(this.getTitle() + name);
    }

    // SET LISTENER

    public void setSelectProductAction(final Consumer<Resource> consumer) {
        this.productList.addListSelectionListener(e -> {
            if (!this.productList.isSelectionEmpty() && !e.getValueIsAdjusting()) {
                consumer.accept(this.productList.getSelectedValue());
                this.productList.clearSelection();
            }
        });
    }

    // UPDATE UI METHODS

    public void setServingNumber(final int queueNumber) {
        SwingUtilities.invokeLater(() -> {
            this.serving.setText("Now serving " + queueNumber);
            if (!this.tickets.isEmpty()) {
                if (this.tickets.get(0) < queueNumber) {
                    this.tickets.remove(0);
                    SwingUtilities.invokeLater(() -> this.ticket.setText("Your ticket is " + (this.tickets.size() > 0 ? this.tickets.get(0) : "-")));
                }
                if (this.tickets.get(0) == queueNumber)
                    JOptionPane.showMessageDialog(this, "Your product will be ready shortly.");
            }
        });
    }

    public void showTicket(final int ticket) {
        this.tickets.add(ticket);
        SwingUtilities.invokeLater(() -> this.ticket.setText("Your ticket is " + this.tickets.get(0)));
    }

    public void showDialog(final String stringToShow) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, stringToShow));
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        productList = new JList<>();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(productList, gbc);
        status = new JLabel();
        status.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        panel.add(status, gbc);
        ticket = new JLabel();
        ticket.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(ticket, gbc);
        serving = new JLabel();
        serving.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(serving, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

}
