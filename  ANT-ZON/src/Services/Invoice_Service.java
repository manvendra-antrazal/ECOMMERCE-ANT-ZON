package Services;

import Constants.Message;
import Modal.Product;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice_Service {

    public static void generateInvoice(int buyerId, Product product, int qty, double total, String txnId) throws IOException {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    String timestamp = dtf.format(LocalDateTime.now());

    // Ensure invoice folder exists
    File invoiceDir = new File(Message.INVOICE_FOLDER);
    if (!invoiceDir.exists()) {
        invoiceDir.mkdir();
    }

    String fileName = Message.INVOICE_FOLDER + Message.INVOICE_FILENAME_PREFIX + buyerId + "_" + timestamp + ".txt";

    StringBuilder invoice = new StringBuilder();
    invoice.append(Message.INVOICE_HEADER).append("\n")
            .append("Buyer ID: ").append(buyerId).append("\n")
            .append("Transaction ID: ").append(txnId).append("\n")
            .append("Date: ").append(LocalDateTime.now()).append("\n")
            .append("Product Name: ").append(product.getProduct_Name()).append("\n\n") 
            .append(String.format("%-25s %-10s %-10s %-10s\n", "Product", "Price", "Qty", "Total"))
            .append("------------------------------------------------------------\n")
            .append(String.format("%-25s $%-9.2f %-10d $%-9.2f\n",
                    product.getProduct_Name(),
                    product.getProduct_Price(),
                    qty,
                    total))
            .append("\nTOTAL: $").append(total).append("\n")
            .append(Message.NO_COD_NOTE).append("\n");
            // .append(Message.INVOICE_GENERATED).append("\n");

    try (FileWriter writer = new FileWriter(fileName)) {
        writer.write(invoice.toString());
    }

    System.out.println(invoice);
    System.out.printf((Message.INVOICE_GENERATED) + "%n", fileName);
}

    
}
