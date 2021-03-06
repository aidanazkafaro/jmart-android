package AidanAzkafaroDesonJmartFH.jmart_android.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class Shipment {

    public static final SimpleDateFormat ESTIMATION_FORMAT = new SimpleDateFormat("E MMMM dd yyyy");
    public static final Plan INSTANT = new Plan((byte)(1 << 0));    //1
    public static final Plan SAME_DAY = new Plan((byte)(1 << 1));   //2
    public static final Plan NEXT_DAY = new Plan((byte)(1 << 2));   //4
    public static final Plan REGULER = new Plan((byte)(1 << 3));    //8
    public static final Plan KARGO = new Plan((byte)(1 << 4));      //16
    public int cost;
    public String address;
    public String receipt;
    private byte plan;

    public Shipment(String address, int cost, byte plan, String receipt){
        this.address = address;
        this.cost = cost;
        this.plan = plan;
        this.receipt = receipt;
    }


    public static class Plan{
        public final byte bit;
        private Plan(byte bit){
            this.bit = bit;
        }
    }

}

