public class Lab4
{
    public static void turnRight()
    {
        Robot.turnLeft();
        Robot.turnLeft();
        Robot.turnLeft();
    }

    public static void turnAround()
    {
        Robot.turnLeft();
        Robot.turnLeft();
    }

    public static void backUp()
    {
        turnAround();
        Robot.move();
        turnAround();
    }

    public static void completeBars()
    {
        //insert instructions below
        //before: Robot stands at the west end of the bars, facing east
        //after: bars completed
        while(Robot.frontIsClear())
        {completeOneBar();
            Robot.move();
        }
        completeOneBar();
    }

    public static void completeOneBar()
    //before: Robot faces east at the bottom of a bar
    //after: Robot faces east, having completed the bar
    {
        Robot.turnLeft();
        while(!Robot.onDark())
        {
            Robot.move();
        }
        if(Robot.onDark())
        { 
            turnAround();
            while(Robot.frontIsClear())
            {
                Robot.move();
                Robot.makeDark();
            }
        }
        Robot.turnLeft();
    }

    public static void testCompleteBars1()
    {
        Robot.load("bars1.txt");
        Robot.setDelay(0.025);
        completeBars();
    }

    public static void testCompleteBars2()
    {
        Robot.load("bars2.txt");
        Robot.setDelay(0.025);
        completeBars();
    }

    public static void combinePiles()
    {
        //insert instructions below
        //before: robot facing north, at the bottom of the left pile
        //after: piles are combined
        while(isDarkAhead())
        {
            moveSquare();
            if(!Robot.onDark())
            {
                Robot.move();
            }
        }
    }

    public static void moveSquare()
    //before: Robot on left pile, facing north
    //after: Robot at the bottom of the left pile, facing north. The square the robot has been "moved" to the right pile
    {
        if(Robot.onDark())
        {
            Robot.makeLight();
        }
        turnRight();
        Robot.move();
        Robot.turnLeft();
        while(Robot.onDark())
        {
            Robot.move();
        }
        Robot.makeDark();
        turnAround();
        while(Robot.frontIsClear())
        {
            Robot.move();
        }
        turnRight();
        Robot.move();
        turnRight();

    }

    public static boolean isDarkAhead()
    //before: robot stands at the bottom of the left pile but doesn't know if there are any dark tiles ahead
    //after: robot knows if there are dark tiles ahead
    {
        if(Robot.onDark())
        {
            return true;
        }
        while(Robot.frontIsClear())
        {
            Robot.move();
            if(Robot.onDark())
            {
                return true;
            }
        }
        turnAround();
        while(Robot.frontIsClear())
        {
            Robot.move();
        }
        turnAround();
        return false;
    }

    public static void testCombinePiles1()
    {
        Robot.load("piles1.txt");
        Robot.setDelay(0.025);
        combinePiles();
    }

    public static void testCombinePiles2()
    {
        Robot.load("piles2.txt");
        Robot.setDelay(0.025);
        combinePiles();
        
    }

    public static void connectDots()
    {
        //insert instructions below
        //before: Robot stands on first dot, facing any direction
        //after: Dots have been connected

        while(areDotsLeft())
        {
            if(isDotAhead())
            {
                connectDotAhead();
            }
            if(isDotBehind())
            {
                connectDotBehind();
            }
            if(isDotToLeft())
            {
                connectDotToLeft();
            }
            if(isDotToRight())
            {
                connectDotToRight();
            }
        }
    }
    
    public static boolean areDotsLeft()
    {
        //before: robot stands on any square
        //after: robot knows if there are dots left
        if(isDotAhead())
        {return true;}
        if(isDotBehind())
        {return true;}
        if(isDotToLeft())
        {return true;}
        if(isDotToRight())
        {return true;}
        return false;
    }
    public static boolean isDotAhead()
    {
        //before: Robot stands on square without knowing if there is a dot ahaed
        //after: Robot stands on same square, knowing whether there is a dot straight ahead
        Robot.move();
        if(!Robot.onDark())
        {
            Robot.move();
            if(Robot.onDark())
            {
                turnAround();
                Robot.move();
                Robot.move();
                turnAround();
                return true;
            }
            turnAround();
            Robot.move();
            Robot.move();
            turnAround();
            return false;
        }
        turnAround();
        Robot.move();
        turnAround();
        return false;
    }

    public static void connectDotAhead()
    {
        //before: Robot stands on a tile with a dot straight ahead
        //after: Robot stands on the dot ahead, facing the same direction, having connected the two dots
        Robot.move();
        Robot.makeDark();
        Robot.move();
    }

    public static boolean isDotBehind()
    {
        //before: Robot stands on square without knowing if there is a dot behind
        //after: Robot stands on same square, knowing whether there is a dot directly behind
        turnAround();
        Robot.move();
        if(!Robot.onDark())
        {
            Robot.move();
            if(Robot.onDark())
            {
                turnAround();
                Robot.move();
                Robot.move();
                return true;
            }
            turnAround();
            Robot.move();
            Robot.move();
            return false;
        }
        turnAround();
        Robot.move();
        return false;
    }

    public static void connectDotBehind()
    {
        //before: Robot stands on a tile with a dot behind
        //after: Robot stands on the dot behind, turned 180 degrees around, having connected the two dots
        turnAround();
        Robot.move();
        Robot.makeDark();
        Robot.move();
    }

    public static boolean isDotToLeft()
    {
        //before: Robot stands on square without knowing if there is a dot to the left
        //after: Robot stands on same square, knowing whether there is a dot to the left
        Robot.turnLeft();
        Robot.move();
        if(!Robot.onDark())
        {
            Robot.move();
            if(Robot.onDark())
            {
                turnAround();
                Robot.move();
                Robot.move();
                Robot.turnLeft();
                return true;
            }
            turnAround();
            Robot.move();
            Robot.move();
            Robot.turnLeft();
            return false;
        }
        turnAround();
        Robot.move();
        Robot.turnLeft();
        return false;
    }

    public static void connectDotToLeft()
    {  //before: Robot stands on a tile with a dot to the left
        //after: Robot stands on the dot to the left, turned 90 degrees counterclockwise, having connected the two dots
        Robot.turnLeft();
        Robot.move();
        Robot.makeDark();
        Robot.move();
    }

    public static boolean isDotToRight()
    {
        //before: Robot stands on square without knowing if there is a dot to the right
        //after: Robot stands on same square, knowing whether there is a dot to the right
        turnRight();
        Robot.move();
        if(!Robot.onDark())
        {
            Robot.move();
            if(Robot.onDark())
            {
                turnAround();
                Robot.move();
                Robot.move();
                turnRight();
                return true;
            }
            turnAround();
            Robot.move();
            Robot.move();
            turnRight();
            return false;
        }
        turnAround();
        Robot.move();
        turnRight();
        return false;
    }

    public static void connectDotToRight()
    {  //before: Robot stands on a tile with a dot to the right
        //after: Robot stands on the dot to the right, turned 90 degrees clockwise, having connected the two dots
        turnRight();
        Robot.move();
        Robot.makeDark();
        Robot.move();
    }

    public static void testConnectDots1()
    {
        Robot.load("connect1.txt");
        Robot.setDelay(0.025);
        connectDots();
    }

    public static void testConnectDots2()
    {
        Robot.load("connect2.txt");
        Robot.setDelay(0.025);
        connectDots();
    }
}
