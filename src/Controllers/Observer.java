package src.Controllers;

import src.Domain.*;
import src.Presentation.*;

public interface Observer<T> {
    void update(T data);
}
