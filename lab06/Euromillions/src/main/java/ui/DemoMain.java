package ui;

import euromillions.CuponEuromillions;
import euromillions.Dip;
import euromillions.EuromillionsDraw;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DemoMain {

    /**
     * demonstrates a client for ramdom euromillions bets
     */
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("DemoMain");

        // played sheet
        CuponEuromillions thisWeek = new CuponEuromillions();
        logger.log(Level.INFO, "Betting with three random bets...");
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());
        thisWeek.addDipToCuppon(Dip.generateRandomDip());

        // simulate a random draw
        EuromillionsDraw draw = EuromillionsDraw.generateRandomDraw();

        //report results
        logger.log(Level.INFO, "You played:");
        logger.log(Level.INFO, "{0}", thisWeek.format());

        logger.log(Level.INFO, "Draw results:");
        logger.log(Level.INFO, "{0}", draw.getDrawResults().format());

        logger.log(Level.INFO, "Your score:");
        for (Dip dip : draw.findMatches(thisWeek)) {
            logger.log(Level.INFO, "{0}", dip.format());

        }
    }
}
