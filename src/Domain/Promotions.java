package Domain;

public class Promotions{
    private Date expiryDate;
    private Date issueDate;
    private String promoCode;

    public final Date getExpiryDate(){
        return expiryDate;
    }

    public final Date getIssueDate(){
        return issueDate;
    }

    public final String getPromoCode(){
        return promoCode;
    }

    public void setExpiryDate(final Date expiryDate){
        this.expiryDate = expiryDate;
    }

    public void setIssueDate(final Date issueDate){
        this.issueDate = issueDate;
    }

    public void setPromoCode(final String promoCode){
        this.promoCode = promoCode;
    }

}