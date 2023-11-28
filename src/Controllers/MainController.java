package src.Controllers;
import Domain.*;
import Presentation.*;
import src.Controllers.DBController;

public class MainController {
    private DBController dbController;
    // private CommunicationSystem communicationSystemController;
    private LoginController loginController;
    // private RegistrationController registrationController;
    private SeatController seatController;
    private TicketController ticketController;

    public MainController () {
        Gui frame = new Gui();

        this.dbController = getOnlyInstance();
        // this.communicationSystemController = new CommunicationSystem();
        this.loginController = new LoginController(Gui, DBController);
        // this.registrationController = new RegistrationController();
        this.seatController = new SeatController(Gui, DBController);
        // this.ticketController = new TicketController(Gui, DBController);

        this.loginController.setLoginPanel(frame.getLoginPanel());
        this.seatController.setSearchFlightPanel(frame.getSearchFlightPanel());

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainController();
    }
}