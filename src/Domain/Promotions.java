package Domain;

public class Promotions{
    private Date expiryDate;
    private Date issueDate;
    private String promoCode;

    public void setExpiryDate(final Date exp){expiryDate = exp;}
    public void setIssueDate(final Date issue){issueDate = issue;}
    public void setPromoCode(final String code){promoCode = code;}
    public final Date getExpiryDate(){return expiryDate;}
    public final Date getIssueDate(){return issueDate;}
    public final String getPromoCode(){return promoCode;}

}