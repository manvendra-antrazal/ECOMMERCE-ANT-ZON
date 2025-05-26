package Services;

import Constants.Message;
import Modal.Product;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice_Service {

    public static void generateInvoice(int buyerId, Product product, int qty, double discountedTotal, String txnId) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        String timestamp = dtf.format(LocalDateTime.now());

        // Ensure invoice folder exists
        File invoiceDir = new File(Message.INVOICE_FOLDER);
        if (!invoiceDir.exists()) {
            invoiceDir.mkdir();
        }

        String fileName = Message.INVOICE_FOLDER + Message.INVOICE_FILENAME_PREFIX + buyerId + "_" + timestamp + ".txt";

        double originalUnitPrice = product.getProduct_Price();
        double originalTotal = originalUnitPrice * qty;
        double discountAmount = originalTotal - discountedTotal;
        double discountPercent = discountAmount > 0 ? (discountAmount / originalTotal) * 100 : 0;

        StringBuilder invoice = new StringBuilder();
        invoice.append(Message.INVOICE_HEADER).append("\n")
                .append("Buyer ID: ").append(buyerId).append("\n")
                .append("Transaction ID: ").append(txnId).append("\n")
                .append("Date: ").append(LocalDateTime.now()).append("\n")
                .append("Product Name: ").append(product.getProduct_Name()).append("\n\n")
                .append(String.format("%-25s %-12s %-10s %-12s\n", "Product", "Unit Price", "Qty", "Total"))
                .append("----------------------------------------------------------------\n")
                .append(String.format("%-25s $%-11.2f %-10d  %-11.2f\n",
                        product.getProduct_Name(),
                        originalUnitPrice,
                        qty,
                        originalTotal))
                .append("\n");

        if (discountAmount > 0) {
            invoice.append(String.format("Discount Applied: %.2f%% (- %.2f)\n", discountPercent, discountAmount));
            invoice.append(String.format("Total after Discount:  %.2f\n\n", discountedTotal));
        }

        invoice.append("FINAL TOTAL:  ").append(String.format("%.2f", discountedTotal)).append("\n")
               .append(Message.NO_COD_NOTE).append("\n");

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(invoice.toString());
        }

        System.out.println(invoice);
        System.out.printf(Message.INVOICE_GENERATED + "%n", fileName);
    }

}
