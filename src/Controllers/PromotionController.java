package src.Controllers;

import src.Domain.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class PromotionController {

    private DBController db;
    private ArrayList<Promotions> currentPromotions = new ArrayList<>();

    public PromotionController(DBController db) {
        this.db = db;
        loadPromotions();
    }

    private void loadPromotions() {
        try {
            ResultSet promotions = db.selectTable("PROMOTIONS");

            while (promotions.next()) {
                int promotionID = promotions.getInt("promotionID");
                String name = promotions.getString("name");
                String discount = promotions.getString("discount");
                Date startDate = promotions.getDate("startDate");
                Date endDate = promotions.getDate("endDate");

                Promotions promo = new Promotions(name, discount, startDate, endDate);
                promo.setPromotionID(promotionID);
                currentPromotions.add(promo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPromotion(Promotions promotion) {
        db.insertPromotion(promotion);
        currentPromotions.add(promotion);
    }

    public void updatePromotion(Promotions promotion) {
        db.updatePromotion(promotion);
        for (int i = 0; i < currentPromotions.size(); i++) {
            if (currentPromotions.get(i).getPromotionID() == promotion.getPromotionID()) {
                currentPromotions.set(i, promotion);
                break;
            }
        }
    }

    public Promotions getPromotion(int promotionID) {
        for (Promotions promo : currentPromotions) {
            if (promo.getPromotionID() == promotionID) {
                return promo;
            }
        }
        return null;
    }

    public void deletePromotion(int promotionID) {
        db.removePromotion(promotionID);
        currentPromotions.removeIf(p -> p.getPromotionID() == promotionID);
    }

    public ArrayList<Promotions> getAllPromotions() {
        return new ArrayList<>(currentPromotions);
    }

}
