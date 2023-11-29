package src.Controllers;

import src.Domain.*;
import src.Presentation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlightController {
    private Gui mainFrame; 
    private DBController db;
    private UserSession userInstance;
    private AirlineAgentPanel airlineAgentPanel;
    private AdminPanel adminPanel;

    public FlightController (Gui mainFrame, DBController db) {
        this.mainFrame = mainFrame;
        this.db = db;
        this.userInstance = UserSession.getInstance();
    }

    public void setAirlineAgentPanel(AirlineAgentPanel panel) {
        this.airlineAgentPanel = panel;
    }
    
    public void setAdminPanel(AdminPanel panel) {
        this.adminPanel = panel;
    }
    
    public ArrayList<Flight> 
    
}