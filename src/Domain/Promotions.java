package src.Domain;

import java.util.Date;

public class Promotions {
    String name;
    Date startDate;
    Date endDate;
    boolean isValid() { return true; }
    float applyPromotion() { return 0.0f; }
}