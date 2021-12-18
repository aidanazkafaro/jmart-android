package AidanAzkafaroDesonJmartFH.jmart_android.model;

/**
 * @author Aidan Azkafaro Deson
 * @version 1.0
 * @since 18 Desember 2021
 */
public class Product extends Serializable{

    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;

    public String toString(){
         return name;
    }


}
