/***
* each controller class is supposed to extend BaseController class. It provides methods for loading fxml file easily by passing result name as defined in 
* javafxmap.xml file.
***/
package com.easyfx.util;


import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

import com.easyfx.exception.NoControllorFoundException;
import com.easyfx.exception.NoEventFound;
import com.easyfx.exception.NoResultFound;
import com.easyfx.exception.UnAuthorizedAccessException;
import com.easyfx.interceptors.SecurityInterceptor;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BaseController {
	/**
	 * @param controllerName
	 * @param result
	 * @param parent
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws NoResultFound 
	 * @throws NoControllorFoundException 
	 * @throws UnAuthorizedAccessException 
	 */
	protected void loadSecure(String controllerName, String result,Pane parent) throws MalformedURLException, IOException, NoControllorFoundException, NoResultFound, UnAuthorizedAccessException
	{	

		for(String interceptor:ControllerFactory.getController(controllerName).getInterceptorSet())
		{
			try {
			 SecurityInterceptor secuInterceptor=(SecurityInterceptor)	Thread.currentThread().getContextClassLoader().loadClass(interceptor).newInstance();
			 if(secuInterceptor.check()!=null)
			 {
				 throw new UnAuthorizedAccessException();
			 }
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		String root=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();	
		result=ControllerFactory.getFxmlFile(controllerName, this.getClass().getName(), result);
		Node node=(Node) new FXMLLoader().load(this.getClass().getResourceAsStream("/"+result));
		parent.getChildren().add(node);
	}
	public BaseController() throws NoControllorFoundException {
		System.out.println("Invoked Through:"+this.getClass().getName());
		//throw new NoControllorFoundException();
	}
	
	/**
	 * @param controllerName
	 * @param result
	 * @param parent
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws NoResultFound 
	 * @throws NoControllorFoundException 
	 * @throws UnAuthorizedAccessException 
	 */
	protected void loadSecure(String controllerName, String result,BorderPane parent) throws MalformedURLException, IOException, NoControllorFoundException, NoResultFound, UnAuthorizedAccessException
	{		
		for(String interceptor:ControllerFactory.getController(controllerName).getInterceptorSet())
		{
			try {
			 SecurityInterceptor secuInterceptor=(SecurityInterceptor)	Thread.currentThread().getContextClassLoader().loadClass(interceptor).newInstance();
			 if(secuInterceptor.check()!=null)
			 {
				 throw new UnAuthorizedAccessException();
			 }
			} catch (InstantiationException | IllegalAccessException
					| ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String root=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();	
		result=ControllerFactory.getFxmlFile(controllerName, this.getClass().getName(), result);
		Node node=(Node) new FXMLLoader().load(this.getClass().getResourceAsStream("/"+result));
		parent.setCenter(node);
	}
	
	/**
	 * @param controllerName
	 * @param result
	 * @param parent
	 * @param position 0=left 1=top 2=right 3=bottom
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws NoResultFound 
	 * @throws NoControllorFoundException 
	 * 
	 */
	protected void load(String controllerName, String result,BorderPane parent,int position) throws MalformedURLException, IOException, NoControllorFoundException, NoResultFound
	{
		String root=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		result=ControllerFactory.getFxmlFile(controllerName, this.getClass().getName(), result);
		Node node=FXMLLoader.load(new File(root+result).toURL());
		switch(position)
		{
		case 1:
			parent.setLeft(node);
			break;
		case 2:
			parent.setTop(node);
			break;
		case 3:
			parent.setCenter(node);
			break;
		case 4:
			parent.setBottom(node);
			break;
		default:
			System.out.println("Error");			
		}
	}
	
	protected void onDragDetected(MouseEvent event) throws NoControllorFoundException, NoEventFound, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		String dragMethod=ControllerFactory.getEvent("drag", this.getClass().getName());
		Method[] methods=this.getClass().getMethods();
		for(Method m:methods)
		{
			if(m.getName().equals(dragMethod))
			{
				m.invoke(this, event);
				break;
			}
		}
	}
	protected void load(String controllerName, String result,BorderPane parent) throws MalformedURLException, IOException, NoControllorFoundException, NoResultFound
	{	
		String root=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();	
		result=ControllerFactory.getFxmlFile(controllerName, this.getClass().getName(), result);
		Node node=(Node) new FXMLLoader().load(this.getClass().getResourceAsStream("/"+result));
		parent.setCenter(node);
	}
	protected void clearAndLoad(String controllerName, String result,BorderPane parent) throws MalformedURLException, IOException, NoControllorFoundException, NoResultFound
	{	
		parent.setCenter(null);
		parent.setTop(null);
		parent.setRight(null);
		parent.setLeft(null);
		parent.setBottom(null);
		//String root=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();	
		result=ControllerFactory.getFxmlFile(controllerName, this.getClass().getName(), result);
		Node node=(Node) new FXMLLoader().load(this.getClass().getResourceAsStream("/"+result));
		parent.setCenter(node);
	}
	protected void loadInStage(String controllerName, String result,Stage stage) throws NoControllorFoundException, NoResultFound, IOException
	{
		//String root=getClass().getProtectionDomain().getCodeSource().getLocation().getPath();	
		result=ControllerFactory.getFxmlFile(controllerName, this.getClass().getName(), result);
		Parent node=(Parent) new FXMLLoader().load(this.getClass().getResourceAsStream("/"+result));
		Scene scene= new Scene(node);
		stage.setScene(scene);
		stage.show();
	}
}
