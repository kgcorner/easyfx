/***
* This class is responsible for loading all the controllers of the project. Method populateControllerList is supposed to be called in the begening 
* of the program and later on load method of BaseController should be used for loading fxml files. 
***/


package com.easyfx.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.easyfx.exception.NoControllorFoundException;
import com.easyfx.exception.NoEventFound;
import com.easyfx.exception.NoResultFound;
import com.easyfx.model.Controller;

public class ControllerFactory {
	private static List<Controller> controllerList;	
	public static void populateControllerList(String config)
	{
		controllerList= new ArrayList<>();
		File file=new File(config);
		DocumentBuilderFactory docBuilderFactory= DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder docBuilder= docBuilderFactory.newDocumentBuilder();
			Document doc=docBuilder.parse(ControllerFactory.class.getResourceAsStream("/"+config));
			doc.getDocumentElement().normalize();
			//System.out.println(doc.getDocumentElement().getNodeName());
			NodeList nodes=doc.getElementsByTagName("controller");
			for (int i = 0; i < nodes.getLength() ; i++) {		
				Controller controller=new Controller();
				Node node= nodes.item(i);
				if(node.getNodeType()==Node.ELEMENT_NODE)
				{
					Element nodeElement=(Element)node;
					//controller.setClassName(node.getUserData("class").toString());
					Map<String, String> resultMap= new HashMap<>();
					Map<String, String> eventMap= new HashMap<>();
					List<String> interceptorList= new ArrayList();
					if(node.hasAttributes())
					{
						controller.setClassName(nodeElement.getAttribute("class"));
						controller.setName(nodeElement.getAttribute("name"));
						NodeList nodeList=nodeElement.getElementsByTagName("result");
						for (int j = 0; j < nodeList.getLength(); j++) {
							Element innerNodeElement=(Element)nodeList.item(j);
							resultMap.put(innerNodeElement.getAttribute("name"), innerNodeElement.getAttribute("fxmlfile"));
						}
						nodeList=nodeElement.getElementsByTagName("event");
						for (int j = 0; j < nodeList.getLength(); j++) {
							Element innerNodeElement=(Element)nodeList.item(j);
							eventMap.put(innerNodeElement.getAttribute("id"), innerNodeElement.getAttribute("method"));
						}
						nodeList=nodeElement.getElementsByTagName("interceptor");
						for (int j = 0; j < nodeList.getLength(); j++) {
							Element innerNodeElement=(Element)nodeList.item(j);
							interceptorList.add(innerNodeElement.getAttribute("classname"));
							//eventMap.put(innerNodeElement.getAttribute("name"), innerNodeElement.getAttribute("classname"));
						}
						controller.setResultSet(resultMap);
						controller.setEventSet(eventMap);
						controller.setInterceptorSet(interceptorList);
						controllerList.add(controller);
						System.out.println(controller.getName()+" Added");
					}
				}
			}
		} 
		catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(IOException x)
		{
			x.printStackTrace();
		}
		catch(SAXException x)
		{
			x.printStackTrace();
		}
	}
	public static List<Controller> getControllerList()
	{
		return controllerList;
	}
	public static String getFxmlFile(String controllerName,String controllerClass,String result) throws NoControllorFoundException, NoResultFound
	{
		if(controllerList==null || controllerList.size()<1)
		{
			throw new NoControllorFoundException();
		}
		else
		{
			for(Controller controller:controllerList)
			{
				if(controller.getClassName().equalsIgnoreCase(controllerClass) && controller.getName().equalsIgnoreCase(controllerName))
				{
					return controller.getResultSet().get(result);
				}
			}
			throw new NoResultFound();
		}
	}
	public static String getEvent(String eventID,String controllerName) throws NoControllorFoundException, NoEventFound
	{
		if(controllerList==null || controllerList.size()<1)
		{
			throw new NoControllorFoundException();
		}
		else
		{
			for(Controller controller:controllerList)
			{
				if(controller.getClassName().equalsIgnoreCase(controllerName) && controller.getName().equalsIgnoreCase(controllerName))
				{
					return controller.getEventSet().get(eventID);
				}
			}
			throw new NoEventFound();
		}
	}
	public static Controller getController(String name)
	{
		for(Controller c:getControllerList())
		{
			if(c.getName().equals(name))
			{
				return c;
			}
		}
		return null;
	}
}
