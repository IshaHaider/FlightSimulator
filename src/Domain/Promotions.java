package src.Domain;

import java.util.Date;


public class Promotions {
    private int promotionID;
    private String name;
    private String discount; 
    private Date startDate;
    private Date endDate;
    
    public Promotions() {
        this.promotionID = 0;
        this.name = "";
        this.discount = "";
        this.startDate = new Date();
        this.endDate = new Date();
    }

    public Promotions(String name, String discount, Date startDate, Date endDate) {
        this.name = name;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public final int getPromotionID() {return promotionID;}
    public final String getName() {return name;}
    public final String getDiscount() {return discount;}
    public final Date getStartDate() {return startDate;}
    public final Date getEndDate() {return endDate;}

    public void setPromotionID(final int promotionID) {this.promotionID = promotionID;}
    public void setName(final String name) {this.name = name;}
    public void setDiscount(final String discount) {this.discount = discount;}
    public void setStartDate(final Date startDate) {this.startDate = startDate;}
    public void setEndDate(final Date endDate) {this.endDate = endDate;}

    public boolean isValid() { 
        Date currentDate = new Date();
        if (this.startDate.before(currentDate) && this.endDate.after(currentDate)) {
            return true;
        }
        return false;
    }
    
}