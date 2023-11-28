package src.Controllers;
import Domain.*;
import Presentation.*;

public class MainController {
    private DBController dbController;
    // private CommunicationSystem communicationSystemController;
    private LoginController loginController;
    // private RegistrationController registrationController;
    private SeatController seatController;
    // private TicketController ticketController;

    public MainController () {

        Gui frame = new Gui();

        this.dbController = getOnlyInstance();
        // this.communicationSystemController = new CommunicationSystem();
        this.loginController = new LoginController();
        // this.registrationController = new RegistrationController();
        this.seatController = new SeatController();
        // this.ticketController = new TicketController();

        this.loginController.setLoginPanel(frame.getLoginPanel());
        this.seatController.setSearchFlightPanel(frame.getSearchFlightPanel());

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MainController();
    }
}