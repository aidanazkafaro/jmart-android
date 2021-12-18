package AidanAzkafaroDesonJmartFH.jmart_android.model;

import java.util.ArrayList;
import java.util.Date;

import AidanAzkafaroDesonJmartFH.jmart_android.ProductFragment;

public class Payment extends Invoice{
    /**
     * The Product count.
     */
    public int productCount;
    /**
     * The Shipment object.
     */
    public Shipment shipment;
    /**
     * arraylist history untuk tracking pembelian
     */
    public ArrayList<Record> history = new ArrayList<Record>();
    /**
     * The Product name.
     */
    public String productName;

    /**
     * Instantiates a new Payment.
     *
     * @param buyerId      the buyer id
     * @param productId    the product id
     * @param productCount the product count
     * @param shipment     the shipment object
     */
    public Payment(int buyerId, int productId, int productCount, Shipment shipment){
        super(buyerId,productId);
        this.productCount = productCount;
        this.shipment = shipment;
    }

    @Override
    public double getTotalPay(Product product) {
        return product.price*((100.0-product.discount)/100)*productCount + shipment.cost;
    }


    public static class Record{
        /**
         * The Status.
         */
        public Status status;
        /**
         * The Date.
         */
        public final Date date = null;
        /**
         * The Message.
         */
        public String message;

        /**
         * Instantiates a new Record.
         *
         * @param status  the status
         * @param message the message
         */
        public Record(Status status, String message){
            this.status = status;
            this.message = message;
        }
    }

    @Override
    public String toString(){
        return "Product ID: " + String.valueOf(productId) + "\nProduct Count: " + String.valueOf(productCount);
    }


}
