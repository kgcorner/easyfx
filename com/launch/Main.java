package com.launch;

import java.util.Set;

import com.easyfx.model.Controller;
import com.easyfx.util.ControllerFactory;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String root=Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		ControllerFactory.populateControllerList(root+"javafxmap.xml");		
		for(Controller c:ControllerFactory.getControllerList())
		{
			Set<String> keys=c.getResultSet().keySet();
			System.out.println("For Controller:"+c.getName());
			for(String k:keys)
			{
				System.out.println("Result:"+k+" Fxml:"+c.getResultSet().get(k).toString());
			}
			keys=c.getEventSet().keySet();			
			for(String k:keys)
			{
				System.out.println("Event:"+k+" Method:"+c.getEventSet().get(k).toString());
			}
		}
	}

}
