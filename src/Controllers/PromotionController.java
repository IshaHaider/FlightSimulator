package src.Controllers;

import src.Domain.*;
import src.Presentation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;

public class PromotionController implements Observer {
    private UserPanel userPanel;
    private DBController db = DBController.getOnlyInstance();
    private ArrayList<Promotions> currentPromotions = new ArrayList<>();
    private Subject subject;

    public PromotionController(Subject s) { 
        subject = s;
        subject.register(this);
        loadPromotions(); 
    }

    @Override
    public void update(){
        loadPromotions();
    }

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

    public Promotions getPromotion(int promotionID) {
        try {
            ResultSet promos = db.selectTableFromAttribute("PROMOTIONS", "promotionID", promotionID);
            if (promos.next()){
                return new Promotions(promotionID, promos.getString("name"), promos.getString("discount"), promos.getDate("startDate").toLocalDate(), promos.getDate("endDate").toLocalDate());
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return null;
    }

    public void updatePromotion(Promotions promotion) { db.updatePromotion(promotion); }
    public void deletePromotion(int promotionID) { db.removePromotion(promotionID); }
    public ArrayList<Promotions> getCurrentPromotions() {return this.currentPromotions; }
    public void setCurrentPromotions(ArrayList<Promotions> cp) { this.currentPromotions = cp; }
    public void setUserPanel( UserPanel panel ) { this.userPanel = panel; }

}

