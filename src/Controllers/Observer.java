package src.Controllers;

import src.Controllers.DBController;
import src.Domain.*;
import src.Presentation.*;

// public interface Observer<T> {
//     void update(T data);
// }

public interface Observer {
    void update(DBController db);
}

