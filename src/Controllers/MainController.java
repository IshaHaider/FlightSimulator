package src.Controllers;

import src.Domain.*;
import src.Presentation.*;

public class MainController {
    private DBController db = DBController.getOnlyInstance();;

    private LoginController loginController;
    private SeatController seatController;
    private FlightController flightController;

    public MainController() { 
        this.loginController = new LoginController();
        this.seatController = new SeatController();
        this.flightController = new FlightController();

        Gui frame = new Gui(loginController,flightController, seatController, db);

        loginController.setMainFrame(frame);

        this.loginController.setLoginPanel(frame.getLoginPanel());
        
        this.seatController.setSearchFlightPanel(frame.getSearchFlightPanel());
        this.seatController.setCreditCardPanel(frame.getCreditCardPanel());
        this.seatController.setCancelFlightPanel(frame.getCancelFlightPanel());

        this.flightController.setAdminPanel(frame.getAdminPanel());
        this.flightController.setAirlineAgentPanel(frame.getAirlineAgentPanel());

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainController();
    }
}