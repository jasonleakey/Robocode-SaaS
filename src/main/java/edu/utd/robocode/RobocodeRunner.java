package edu.utd.robocode;

/*******************************************************************************
 * Copyright (c) 2001-2013 Mathew A. Nelson and Robocode contributors
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://robocode.sourceforge.net/license/epl-v10.html
 *******************************************************************************/

import org.apache.commons.lang3.StringUtils;

import net.sf.robocode.io.Logger;
import net.sf.robocode.util.StringUtil;
import robocode.BattleResults;
import robocode.control.BattleSpecification;
import robocode.control.BattlefieldSpecification;
import robocode.control.RobocodeEngine;
import robocode.control.RobotSpecification;
import robocode.control.events.BattleAdaptor;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.BattleErrorEvent;
import robocode.control.events.BattleFinishedEvent;
import robocode.control.events.BattleMessageEvent;
import robocode.control.events.BattleStartedEvent;

/**
 * Sample application that runs two sample robots in Robocode.
 * 
 * @author Flemming N. Larsen (original)
 */
public class RobocodeRunner
{
    public RobocodeRunner(String[] robots)
    {
        Logger.logMessage("robocode.class.path: "
                + System.getProperty("java.class.path"));

        // Battle listener used for receiving battle events
        BattleObserver battleListener = new BattleObserver();

        // Create the RobocodeEngine
        // IRobocodeEngine engine = new RobocodeEngine(new
        // java.io.File("C:/Robocode")); // Run from C:/Robocode
        RobocodeEngine engine = new RobocodeEngine(); // Run from current
                                                      // working directory
        

        // Add battle listener to our RobocodeEngine
        engine.addBattleListener(battleListener);

        // Show the battles
        engine.setVisible(true);

        // Setup the battle specification

        int numberOfRounds = 5;
        BattlefieldSpecification battlefield = new BattlefieldSpecification(
                800, 600); // 800x600
        
        System.out.println(RobocodeEngine.getRobotsDir());
        
        RobotSpecification[] selectedRobots = engine.getLocalRepository(StringUtils.join(robots, ','));
//        RobotSpecification[] selectedRobots = engine.getLocalRepository();
        for (RobotSpecification robot : selectedRobots)
        {
            System.out.print(robot.getName() + ", ");
        }
        // RobotSpecification[] selectedRobots =
        // engine.getLocalRepository("tested.robots.BattleLost,tested.robots.EnvAttacks");

        BattleSpecification battleSpec = new BattleSpecification(
                numberOfRounds, battlefield, selectedRobots);

        // Run our specified battle and let it run till it's over
        engine.runBattle(battleSpec, true/* wait till the battle is over */);
    }

    /**
     * BattleListener for handling the battle event we are interested in.
     */
    static class BattleObserver extends BattleAdaptor
    {

        public void onBattleStarted(BattleStartedEvent e)
        {
            System.out.println("-- Battle was started --");
        }

        public void onBattleFinished(BattleFinishedEvent e)
        {
            if (e.isAborted())
            {
                System.out.println("-- Battle was aborted --");
            }
            else
            {
                System.out.println("-- Battle was finished succesfully --");
            }
        }

        public void onBattleCompleted(BattleCompletedEvent e)
        {
            System.out.println("-- Battle has completed --");

            // Print out the sorted results with the robot names
            System.out.println("\n-- Battle results --");
            for (BattleResults result : e.getSortedResults())
            {
                System.out.println("  " + result.getTeamLeaderName() + ": "
                        + result.getScore());
            }
        }

        public void onBattleMessage(BattleMessageEvent e)
        {
            System.out.println("Msg> " + e.getMessage());
        }

        public void onBattleError(BattleErrorEvent e)
        {
            System.out.println("Err> " + e.getError());
        }
    }

}