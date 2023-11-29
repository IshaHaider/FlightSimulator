package src.Controllers;

import src.Domain.*;
import src.Presentation.*;

public class MainController {
    private DBController db;

    private LoginController loginController;
    private SeatController seatController;
    private FlightController flightController;
    private PromotionController promotionController;

    public MainController() { 
        this.db = DBController.getOnlyInstance();
        this.loginController = new LoginController();
        this.seatController = new SeatController();
        this.flightController = new FlightController();
        this.promotionController = new PromotionController();

        Gui frame = new Gui(loginController,flightController, seatController, db, promotionController);

        loginController.setMainFrame(frame);

        this.loginController.setLoginPanel(frame.getLoginPanel());
        
        this.seatController.setSearchFlightPanel(frame.getSearchFlightPanel());
        this.seatController.setCreditCardPanel(frame.getCreditCardPanel());
        this.seatController.setCancelFlightPanel(frame.getCancelFlightPanel());

        this.flightController.setAdminPanel(frame.getAdminPanel());
        this.flightController.setAirlineAgentPanel(frame.getAirlineAgentPanel());

        this.promotionController.setUserPanel(frame.getuserPanel());

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainController();
    }
}