package src.Controllers;

import src.Domain.*;
import src.Presentation.*;

public class MainController {
    private DBController dbController;

    private LoginController loginController;
    private SeatController seatController;
    private FlightController flightController;

    public MainController () {

        this.dbController = getOnlyInstance();
        this.loginController = new LoginController(frame, dbController);
        this.seatController = new SeatController(frame, dbController);
        this.flightController = new FlightController(frame, dbController);

        Gui frame = new Gui(loginController,flightController, seatController, dbController);

        this.loginController.setLoginPanel(frame.getLoginPanel());
        
        this.seatController.setSearchFlightPanel(frame.getSearchFlightPanel());
        this.seatController.setCreditCardPanel(frame.getCreditCardPanel());
        this.seatController.setCreditCardPanel(frame.getCancelFlightPanel());

        this.flightController.setAdminPanel(frame.getAdminPanel());
        this.flightController.setAirlineAgentPanel(frame.getAirlineAgentPanel());

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainController();
    }
}