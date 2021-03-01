import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.util.Arrays;
public class CardParser 
{
	private String urlString;
	private ArrayList<HearthstoneCard> theMinions;
	
	public CardParser(String urlString)
	{
		//initial fields
		this.urlString = urlString;
		theMinions = new ArrayList<HearthstoneCard>();
		
		URLReader hearthstoneURLReader = new URLReader(this.urlString);
		Object obj = JSONValue.parse(hearthstoneURLReader.getTheURLContents());
		
	    if(obj instanceof JSONArray)
	    {
	    	//I am only in here if obj IS a JSONArray
	    	JSONArray array = (JSONArray)obj;
	    	
		    for(int i = 0; i < array.size(); i++)
		    {
		    	JSONObject cardData = (JSONObject)array.get(i);
		    	if(cardData.containsKey("cost") && cardData.containsKey("name"))
		    	{
		    		if(cardData.containsKey("type") && cardData.get("type").equals("MINION"))
		    		{
		    			//I am only here is this is a minion card!!!
		    			//System.out.println(cardData.keySet().toString());
		    			String name = (String)cardData.get("name");
		    			int cost = Integer.parseInt(cardData.get("cost").toString());
		    			int attack = Integer.parseInt(cardData.get("attack").toString());
		    		 	int health = Integer.parseInt(cardData.get("health").toString());
		    			HearthstoneCard temp = new HearthstoneCard(name, cost, attack, health);
		    			theMinions.add(temp);
		    			
		    			
		    			
		    			
		    		}
		    		
		    		
		    		
		    	}
		    	
		    }
	    }
	  
	
	}
	
	public void showMinions()
	{
		for(int i = 0; i < this.theMinions.size(); i++)
		{
			this.theMinions.get(i).display();
		}
	}
	public void insertionsortLowestToHighestCost()
	{
		for (int currStart = 1; currStart < this.theMinions.size(); currStart++)
		{
			// try ot move the value at currStart as far up the array as possible 
			//then move on to the next currStart 
			//HearthstoneCard currValue = this.theMinions.get(currStart);
			HearthstoneCard temp;
			int currIndex = currStart;
			while(currIndex > 0 && this.theMinions.get(currIndex).getCost() <
					this.theMinions.get(currIndex -1).getCost())
			{
				//swap the two places 
				temp = this.theMinions.get(currIndex);
				this.theMinions.set(currIndex, this.theMinions.get(currIndex-1));
				this.theMinions.set(currIndex - 1, temp);
				currIndex--; 
			}
		}
	}
	public void insertionsortHigestToLowestCard()
	{
		for (int currStart = 1; currStart < this.theMinions.size(); currStart++)
		{
			HearthstoneCard temp;
			int currIndex = currStart;
			while(currIndex > 0 && this.theMinions.get(currIndex).getCost() >
					this.theMinions.get(currIndex -1).getCost())
			{
				temp = this.theMinions.get(currIndex);
				this.theMinions.set(currIndex, this.theMinions.get(currIndex-1));
				this.theMinions.set(currIndex - 1, temp);
				currIndex--; 
			}
		}}
	/*public void SortFunction()
	{
		int currStart = 0;
		int swapPros, temps;
		
		while(currStart < this.theMinions.size()) 
		{
			swapPros = currStart;
			for(int i = currStart + 1; i < this.theMinions.size(); i++)
			{
				if(this.theMinions.getCost[i]  )
			}
		}
	}*/
	public void sortLowestToHighestCost()
	{
		ArrayList<HearthstoneCard> theSortedList = new ArrayList <HearthstoneCards>
		HearthstoneCard nextSmallest;
		while(this.theMinions.size() > 0 )
		{
			nextSmallest = this.findSmallest();
			theSortedList.add(nextSmallest);
			
		}
		this.theMinions = theSortedList;  // this makes the var theMinions point to the same place
		//as the sorted list in memory. We could have also kept it in the orginal place and copyed the sorted list back over
	}
	private HearthstoneCard findSmallest()
	{
		//go through current state of and remove/ return card with smallest value
		HearthstoneCard currWinner = this.theMinions.get(0);
		int indexOfWinner = 0;
		
		for (int i =1; this.theMinions.size(); i++)
		{
			if (this.theMinions.get(1).getCost() < currWinner.getCost())
			{
				currWinner = this.theMinions.get(i);
				indexOfWinner = i; 
			}//test 
		}
	//the card with the smallest cost should be in currWinner the postion of the card should be
		// in index of winner
		this.theMinions.remove(indexOfWinner);
		return currWinner;
	}
}