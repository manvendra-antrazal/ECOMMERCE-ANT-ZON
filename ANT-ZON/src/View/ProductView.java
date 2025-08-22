package View;

import Constants.Message;
import Modal.Category;
import Modal.Product;
import Modal.Sub_Category;
import Util.PrintUtil;
import java.util.List;


public class ProductView {


    public static void displayProducts(List<Product> products) {
        if (products == null || products.isEmpty()) {
            PrintUtil.printMessage(Message.EMPTY_SELLER_PRODUCTS_LIST);
            return;
        }

        PrintUtil.printMessages(
                Message.AVAILABLE_PRODUCTS,
                Message.PRODUCT_UPPER
        );

        System.out.printf("║ %-3s ║ %-20s ║ %-8s ║ %-8s ║ %-50s ║\n",
                "No", "Product Name", "Price", "Qty", "Description");
        PrintUtil.printMessage(Message.PRODUCTS_MIDDLE);

        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            String desc = p.getProduct_Description();
            if (desc != null && desc.length() > 50) {
                desc = desc.substring(0, 47) + "...";
            }
            System.out.printf("║ %-3d ║ %-20s ║ %-8.2f ║ %-8d ║ %-50s ║\n",
                    i + 1,
                    p.getProduct_Name(),
                    p.getProduct_Price(),
                    p.getProduct_Quantity(),
                    desc != null ? desc : "N/A");
        }

        PrintUtil.printMessage(Message.PRODUCTS_LAST);
    }

    public static void displayCategories(List<Category> categories) {
        if (categories == null || categories.isEmpty()) {
            PrintUtil.printMessage(Message.CATEGORY_NOT_AVAILABLE);
            return;
        }

        PrintUtil.printMessage(Message.SELLER_CATEGORY_FRAME);
        for (int i = 0; i < categories.size(); i++) {
            System.out.printf("║   %-2d. %-31s  ║\n", (i + 1), categories.get(i).getCategory_Name());
        }
        PrintUtil.printMessage(Message.SELLER_CATEGORY_LOWER_FRAME);
    }

    public static void displaySubCategories(List<Sub_Category> subCategories) {
        if (subCategories == null || subCategories.isEmpty()) {
            PrintUtil.printMessage(Message.NO_SUB_CATEGORY_FOUND);
            return;
        }

        PrintUtil.printMessage(Message.SELLER_SUB_CATEGORY_UPPER_FRAME);
        for (int i = 0; i < subCategories.size(); i++) {
            System.out.printf("║   %-2d. %-31s  ║\n", i + 1, subCategories.get(i).getSub_cat_Name());
        }
        PrintUtil.printMessage(Message.SELLER_SUB_CATEGORY_LOWER_FRAME);
    }

    public static void displayProductListSimple(List<Product> products) {
        if (products == null || products.isEmpty()) {
            PrintUtil.printMessage(Message.NO_PRODUCT_FOUND);
            return;
        }
        PrintUtil.printMessage(Message.PRODUCT_LIST);
        for (Product p : products) {
            System.out.printf("║ %-4d ║ %-25s ║\n", p.getProduct_Id(), p.getProduct_Name());
        }
    }
}
