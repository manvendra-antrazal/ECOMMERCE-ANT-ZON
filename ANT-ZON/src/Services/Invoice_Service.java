package Services;

import Constants.Message;
import Modal.Product;
import View.BuyerView;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Invoice_Service {

    public static void generateInvoice(int buyerId, List<Product> products, List<Integer> quantities, double totalPrice, List<String> txnIds) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = dtf.format(LocalDateTime.now());

        File invoiceDir = new File(Message.INVOICE_FOLDER);
        if (!invoiceDir.exists()) {
            invoiceDir.mkdir();
        }

        String fileName = Message.INVOICE_FOLDER + Message.INVOICE_FILENAME_PREFIX + buyerId + "_" + timestamp + ".txt";

        StringBuilder invoice = new StringBuilder();
        invoice.append(Message.INVOICE_HEADER).append("\n")
               .append("Buyer ID: ").append(buyerId).append("\n")
               .append("Date: ").append(LocalDateTime.now()).append("\n\n")
               .append(String.format("%-5s %-25s %-12s %-10s %-12s %-15s\n", "No.", "Product", "Unit Price", "Qty", "Total", "Transaction ID"))
               .append("-----------------------------------------------------------------------------\n");

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            int qty = quantities.get(i);
            String txnId = txnIds.get(i);
            double originalUnitPrice = p.getProduct_Price();

            double lineTotal = originalUnitPrice * qty;

            invoice.append(String.format("%-5d %-25s $%-11.2f %-10d $%-11.2f %-15s\n",
                    i + 1, p.getProduct_Name(), originalUnitPrice, qty, lineTotal, txnId));
        }

        invoice.append("\n")
               .append(String.format("GRAND TOTAL: $%.2f\n", totalPrice))
               .append(Message.NO_COD_NOTE).append("\n");

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(invoice.toString());
        } catch (IOException e) {
            throw new IOException(e);
        }
        BuyerView.showMessage(invoice.toString());
    }
}
