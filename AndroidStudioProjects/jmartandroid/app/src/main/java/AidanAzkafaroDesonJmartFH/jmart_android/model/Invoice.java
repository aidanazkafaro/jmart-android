package AidanAzkafaroDesonJmartFH.jmart_android.model;

import android.media.Rating;

import java.util.Date;

/**
 * Class untuk membuat produk
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class Invoice extends Serializable{

    public int buyerId;
    public int productId;
    public int complaintId;
    public Rating rating;
    public final Date date = null;

    public enum Status {
        WAITING_CONFIRMATION,
        CANCELLED,
        ON_PROGRESS,
        ON_DELIVERY,
        COMPLAINT,
        FINISHED,
        FAILED
    }

    public enum Rating {
        NONE,
        BAD,
        NEUTRAL,
        GOOD
    }

    /**
     * constructor Invoice
     * @param buyerId parameter id pembeli
     * @param productId paramter id product yang dibeli
     */
    protected Invoice(int buyerId, int productId){
        this.buyerId = buyerId;
        this.productId = productId;
    }

    /**
     * mengembalikan harga produk
     * @param product object Product yang dibeli
     * @return
     */
    public double getTotalPay(Product product) {
        return product.price;
    }

}
