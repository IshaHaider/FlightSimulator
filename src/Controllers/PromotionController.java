package src.Controllers;

import src.Domain.*;
import src.Presentation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
 
import java.time.LocalDate;

public class PromotionController {
    private UserPanel userPanel;
    private DBController db = DBController.getOnlyInstance();
    private ArrayList<Promotions> currentPromotions = new ArrayList<>();

    public PromotionController() { loadPromotions(); }

    private void loadPromotions() {
        try {
            ResultSet promotions = db.selectTable("PROMOTIONS");

            while (promotions.next()) {
                int promotionID = promotions.getInt("promotionID");
                String name = promotions.getString("name");
                String discount = promotions.getString("discount");
                LocalDate startDate = promotions.getDate("startDate").toLocalDate();
                LocalDate endDate = promotions.getDate("endDate").toLocalDate();

                Promotions promo = new Promotions(promotionID, name, discount, startDate, endDate);
                currentPromotions.add(promo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPromotion(final Promotions promotion) {
        db.insertPromotion(promotion);
        // currentPromotions.add(promotion);
    }

    public void updatePromotion(Promotions promotion) {

        db.updatePromotion(promotion.getPromotionID());
        // for (int i = 0; i < currentPromotions.size(); i++) {
        //     if (currentPromotions.get(i).getPromotionID() == promotion.getPromotionID()) {
        //         currentPromotions.set(i, promotion);
        //         break;
        //     }
        // }
    }

    public Promotions getPromotion(int promotionID) {
        try {
            ResultSet promos = db.selectTableFromAttribute("PROMOTIONS", "promotionID", promotionID);
            if (promos.next()){
                return new Promotions(promotionID, promos.getString("name"), 
                promos.getString("discount"), promos.getDate("startDate").toLocalDate(), promos.getDate("endDate").toLocalDate());
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        // for (Promotions promo : currentPromotions) {
        //     if (promo.getPromotionID() == promotionID) {
        //         return promo;
        //     }
        // }
        return null;
    }

    public void deletePromotion(int promotionID) { db.removePromotion(promotionID);
        // currentPromotions.removeIf(p -> p.getPromotionID() == promotionID);
    }

    public ArrayList<Promotions> getCurrentPromotions() {return this.currentPromotions; }
    public void setCurrentPromotions(ArrayList<Promotions> cp) { this.currentPromotions = cp; }

    public void setUserPanel( UserPanel panel ) { this.userPanel = panel; }

}

