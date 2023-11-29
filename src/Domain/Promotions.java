package src.Domain;



public class Promotions {
    private int promotionID;
    private String name;
    private String discount; 
    private DDate startDate;
    private DDate endDate;
    
    public Promotions() {
        this.promotionID = 0;
        this.name = "";
        this.discount = "";
        this.startDate = new DDate();
        this.endDate = new DDate();
    }

    public Promotions(String name, String discount, DDate startDate, DDate endDate) {
        this.name = name;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public final int getPromotionID() {return promotionID;}
    public final String getName() {return name;}
    public final String getDiscount() {return discount;}
    public final DDate getStartDate() {return startDate;}
    public final DDate getEndDate() {return endDate;}

    public void setPromotionID(final int promotionID) {this.promotionID = promotionID;}
    public void setName(final String name) {this.name = name;}
    public void setDiscount(final String discount) {this.discount = discount;}
    public void setStartDate(final DDate startDate) {this.startDate = startDate;}
    public void setEndDate(final DDate endDate) {this.endDate = endDate;}

    public boolean isValid() { 
        DDate currentDate = new DDate();
        if (this.startDate.getDate().before(currentDate.getDate()) && this.endDate.getDate().after(currentDate.getDate())) {
            return true;
        }
        return false;
    }
    
}