package dailyprogrammer;

import java.util.HashSet;

public class Challenge264I {
	
	public static void main(String[] args) {
            
        int[][] myRoutes = {
            {2,1,2},
            {5,2,8}
        };
        
        GossipCounter gc = new GossipCounter(myRoutes);
        
        System.out.println(gc.runRoutes());
	}
	
}

class GossipCounter {

    BusRoute[] busRoute;
    int[] routePositions;

    public GossipCounter(int[][] allBusRoutes) {
        
        //constructing busRoute objects
        busRoute = new BusRoute[allBusRoutes.length];

        for (int i=0; i < allBusRoutes.length;i++) {
            busRoute[i] = new BusRoute(allBusRoutes[i]);
            busRoute[i].gossips.add(i);
        }
        
        //initializing routePositions array
        routePositions = new int[allBusRoutes.length];
        for (int i=0; i < routePositions.length;i++) {
            routePositions[i] = busRoute[i].currentStop;
        }      

    }

    public void advanceRoutes() {
        //advances all busRoutes to next position
        for (int i=0; i < busRoute.length;i++) {
            routePositions[i] = busRoute[i].getNext();
        }

    }
    
    public void exchangeGossips() {
		//drivers that are at the same stop share gossips 
	    for (int i = 0; i < this.busRoute.length; i++) {
	    	for (int j = 0; j < this.busRoute.length; j++) {
	    		if (this.routePositions[i] == this.routePositions[j]) {
	    			//share gossips
	    			this.busRoute[i].gossips.addAll(this.busRoute[j].gossips);
	    			this.busRoute[j].gossips.addAll(this.busRoute[i].gossips);
	    		}
	    	}
	    }    	
    }
    
    public String runRoutes () {
        boolean allGossipsExchanged = false;
        int counter = 0;
        int maxStops = 480;
        String result = null;
        
        while(allGossipsExchanged != true && counter <=maxStops) {
	    	this.exchangeGossips(); 
	    	
	     	//statement to be validated below
	    	allGossipsExchanged = true;
	    	
	    	for (int i = 0; i < this.busRoute.length; i++) {
	    		if (this.busRoute[i].gossips.size() < this.busRoute.length) {
	    			allGossipsExchanged = false;
	    		}
	    	}
	    	
	    	this.advanceRoutes();
	    	counter = counter + 1;
        }
        
        if (counter < maxStops) {
        	result = Integer.toString(counter);
        } else if (counter >= maxStops) {
        	result = "Never";
        }
        
        return result;
    }

    class BusRoute {

        int currentStop;
        int currentPosition;
        int[] routeStops;
        HashSet<Integer> gossips;

        public BusRoute(int[] routeArray) {
            
        	gossips = new HashSet <Integer> ();
        	routeStops = new int[routeArray.length];
            currentPosition = 0;
            
            
            for (int i=0; i < routeArray.length;i++) {
                routeStops[i] = routeArray[i];
            }
            
            currentStop = this.routeStops[currentPosition];
        }

        public int getNext() {

            //changes the currentPosition variable for this class

            //if route position is not the last stop
            if (this.currentPosition < (routeStops.length - 1)) {
                    this.currentPosition = this.currentPosition + 1;
            } 
            
            //if route position is the last stop
            else if (this.currentPosition == (routeStops.length - 1)) {
                    this.currentPosition = 0;
            }
            
            currentStop = routeStops[currentPosition];
            
            return currentStop;

        }
    }

}