import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdersController implements ActionListener {

    OrdersView myView;
    DataAccess myDAO;

    public OrdersController(OrdersView view, DataAccess dao) {
        myView = view;
        myDAO = dao;
        myView.btnLoad.addActionListener(this);
        myView.btnSave.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == myView.btnLoad) {      // button Load is clicked
            loadOrderAndDisplay();
        }

        if (e.getSource() == myView.btnSave) {      // button Save is clicked
            saveOrder();
        }

    }

    private void saveOrder() {
        OrdersModel ordersModel = new OrdersModel();

        try {
            int orderID = Integer.parseInt(myView.txtOrderID.getText());
            ordersModel.orderID = orderID;
            ordersModel.orderDate = myView.txtOrderDate.getText();
            ordersModel.customer = myView.txtCustomer.getText();
            ordersModel.totalCost = Double.parseDouble(myView.txtTotalCost.getText());
            ordersModel.totalTax = Double.parseDouble(myView.txtTotalTax.getText());

            myDAO.saveOrder(ordersModel);
            JOptionPane.showMessageDialog(null, "Order saved successfully!");


        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for OrderID");
            ex.printStackTrace();
        }    }

    private void loadOrderAndDisplay() {
        try {
            int orderID = Integer.parseInt(myView.txtOrderID.getText());
            OrdersModel ordersModel = myDAO.loadOrder(orderID);
            myView.txtOrderDate.setText(ordersModel.orderDate);
            myView.txtCustomer.setText(ordersModel.customer);
            myView.txtTotalCost.setText(String.valueOf(ordersModel.totalCost));
            myView.txtTotalTax.setText(String.valueOf(ordersModel.totalTax));

        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for OrderID");
            ex.printStackTrace();
        }
    }

}
