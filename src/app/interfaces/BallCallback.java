package app.interfaces;

import app.utils.enums.Directions;

public interface BallCallback {

    interface AutoMoveBall {
        void moveTo(Directions direction);
    }

}
