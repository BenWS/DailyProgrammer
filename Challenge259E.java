//for context of this program, please see: https://www.reddit.com/r/dailyprogrammer/comments/4bc3el/20160321_challenge_259_easy_clarence_the_slow/


package dailyprogrammer;

import java.util.Arrays;
import java.util.HashMap;

public class Challenge259E {
    public static void main (String[] args) {
        
    	KeyTraverser keyTraverser = new KeyTraverser();
    	
    	//219.45.143.143
    	
		//create hashMap for IPAddressArray
		//position array
	    Integer[][] posArray = {{1,1},{2,1},{3,1},
	                        {2,1},{2,2},{3,2},
	                        {3,1},{3,2},{3,3},
	                        {4,1},{4,2}};
	    
	    //keyboard array
	    Character[] keyboard = {'1','2','3',
	                       '4','5','6',
	                       '7','8','9',
	                       '.','0'};
	    
	    //initialize position mapping
	    HashMap<Character,Integer[]> map = new HashMap();
	    
	    for (Integer i=0; i<keyboard.length;i++) {
	        map.put(keyboard[i], posArray[i]);
	    }
		
		System.out.println(keyTraverser.traverseKeys(map.get('2'), map.get('1')));
		System.out.println(keyTraverser.traverseKeys(map.get('.'), map.get('2')));
		
	
    }
}

class KeyTraverser {
	
	public double traverseKeys(Integer[] initial_key, Integer[] final_key) {
			
		double distance_traversed = 0;
		
		Integer[] current_key = {initial_key[0], initial_key[1]};
		
		while(current_key[0]!=final_key[0]
				|| current_key[1]!=final_key[1]) {
			//method returns the distance traversed to go from initial key to final key
			
			
			Integer[] nextKey = this.findKey(current_key, final_key);
			
			
			distance_traversed = distance_traversed + this.computeDistance(current_key, nextKey);
			
			current_key[0] = nextKey[0];
			current_key[1] = nextKey[1];		
			
		}
		return distance_traversed;
	}
	
	public double computeTotalDistance(String ipAddress) {
		
		double totalDistance = 0;
		
		//for creating IPAddressArray character Array
		Character[] ipAddressArray = new Character[ipAddress.length()];
		
		for (Integer i=0;i<ipAddress.length(); i++) {
			ipAddressArray[i] = Character.valueOf(ipAddress.charAt(i));
		}
		
		//create hashMap for IPAddressArray
		//position array
	    Integer[][] posArray = {{1,1},{2,1},{3,1},
	                        {2,1},{2,2},{3,2},
	                        {3,1},{3,2},{3,3},
	                        {4,1},{4,2}};
	    
	    //keyboard array
	    Character[] keyboard = {'1','2','3',
	                       '4','5','6',
	                       '7','8','9',
	                       '.','0'};
	    
	    //initialize position mapping
	    HashMap<Character,Integer[]> map = new HashMap();
	    
	    for (Integer i=0; i<keyboard.length;i++) {
	        map.put(keyboard[i], posArray[i]);
	    }
	    
	    
		for(Integer i=1;i<ipAddressArray.length;i++) {
			totalDistance = totalDistance + this.traverseKeys(map.get(ipAddressArray[i]), map.get(ipAddressArray[i-1]));			
		}
		
		return totalDistance;
	}
	
	public Integer[] findKey (Integer[] current_key, Integer[] final_key) {
		
		Integer[] candidate_key = {0,0};
		Integer[] closest_key = {0,0};
		double minimum_distance = this.computeDistance(current_key, final_key); 
		
		int[] possible_moves= {-1,0,1};
		
		for(int i:possible_moves) {
			for(int j:possible_moves) {
				candidate_key[0] = current_key[0] + i;
				candidate_key[1] = current_key[0] + j;
				
				double distance = this.computeDistance(candidate_key,final_key);
				
				if (distance<minimum_distance) {
					minimum_distance = distance;
					closest_key[0] = candidate_key[0];
					closest_key[1] = candidate_key[1];
				}
			}
		}
		return closest_key;
	}
	
	
	public double computeDistance(Integer[] initial_key, Integer[] final_key) {
		
		//for computing distance from initial to final key
        double distance =
        		Math.sqrt(Math.pow(initial_key[0]-final_key[0], 2) + Math.pow(initial_key[1] - final_key[1], 2));
						
		return distance; 
	}
}