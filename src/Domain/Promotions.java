package src.Domain;

import java.time.LocalDate;

public class Promotions {
    private int promotionID;
    private String name;
    private String discount; 
    private LocalDate startDate;
    private LocalDate endDate;
    
    public Promotions() {
        this.promotionID = 0;
        this.name = "";
        this.discount = "";
    }

    public Promotions(String name, String discount, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public final int getPromotionID() {return promotionID;}
    public final String getName() {return name;}
    public final String getDiscount() {return discount;}
    public final LocalDate getStartDate() {return startDate;}
    public final LocalDate getEndDate() {return endDate;}

    public void setPromotionID(final int promotionID) {this.promotionID = promotionID;}
    public void setName(final String name) {this.name = name;}
    public void setDiscount(final String discount) {this.discount = discount;}
    public void setStartDate(final LocalDate startDate) {this.startDate = startDate;}
    public void setEndDate(final LocalDate endDate) {this.endDate = endDate;}

    public boolean isValid() { 
        LocalDate currentDate = LocalDate.now();
        if (this.startDate.isBefore(currentDate) && this.endDate.isAfter(currentDate)) {
            return true;
        }
        return false;
    }
    
}