package com.symantec.dlpx.flexresponse.examples.suppressmanageralerts;

import java.util.*;
import static com.symantec.dlpx.flexresponse.incident.PreventOrProtectStatus.FLEX_RESPONSE_EXECUTED; 
import static com.symantec.dlpx.flexresponse.incident.PreventOrProtectStatus.FLEX_RESPONSE_ERROR; 

import com.symantec.dlpx.flexresponse.action.ActionResult;
import com.symantec.dlpx.flexresponse.action.ActionResultBuilder;
import com.symantec.dlpx.flexresponse.action.IncidentResponseAction;
import com.symantec.dlpx.flexresponse.configuration.ConfigurationParameters;
import com.symantec.dlpx.flexresponse.incident.Incident;

import com.symantec.dlpx.flexresponse.incident.*;
import com.symantec.dlpx.flexresponse.action.ActionResult.*;
import com.symantec.dlpx.flexresponse.action.ActionResultBuilder.*;
import com.symantec.dlpx.flexresponse.action.IncidentResponseAction.*;
import com.symantec.dlpx.flexresponse.configuration.ConfigurationParameters.*;
import com.symantec.dlpx.flexresponse.discover.*;
import com.symantec.dlpx.flexresponse.discover.ExchangeItem.*;
import com.symantec.dlpx.flexresponse.incident.ContentItem.*;
import com.symantec.dlpx.flexresponse.action.UnsupportedIncidentTypeException;

public class SuppressManagerAlertsAction implements IncidentResponseAction{

	private static final String RESOLUTION_VALUE = "Manager is a recipient";
	private static final String MESSAGE_SUCCESS = "Manager notification email suppressed";
	private static final String MESSAGE_FAIL = "Manager not found. Plugin took no action";

	private Collection<String> recipients;
	private String recipient;
	private CustomAttribute managerAttribute;
	private ContentItem email;
	private String manager;
	
	public ActionResult execute(Incident incident, ConfigurationParameters parameters) 
	throws UnsupportedIncidentTypeException{
		try{
			
			//get employee manager from the CustomAttribute object
			managerAttribute = incident.getCustomAttribute("Manager Email");
			manager = managerAttribute.getValue();
	
			//get email recipients
			
			email = incident.getContentItem();

			if (! (email instanceof ExchangeItem)){
				throw new UnsupportedIncidentTypeException("This plugin only supports incidents on emails.");
			}
			else{

				recipients = ((ExchangeItem) email).getRecipients();

				//if manager in recipients:
				Iterator<String> iterator = recipients.iterator();
				while( iterator.hasNext()){
					recipient = iterator.next();
					if(recipient.equals(manager)){
						
						//set status for successful resolution
						return new ActionResultBuilder()
						.setMessage(MESSAGE_SUCCESS)
						.addCustomAttribute(incident.getCustomAttribute("Resolution"),RESOLUTION_VALUE)
						.setPreventOrProtectStatus(FLEX_RESPONSE_EXECUTED)
						.getActionResult();
					}
				}
			}
			//manager was not a recipient; no action should be taken
			return new ActionResultBuilder()
			.setMessage(MESSAGE_FAIL)
			.getActionResult();
		}
		catch (RuntimeException e){return createFailedActionResult(e);}
	}
	
	private ActionResult createFailedActionResult(RuntimeException e){

		return new ActionResultBuilder()
			.setMessage(e.getMessage())
			.setPreventOrProtectStatus(FLEX_RESPONSE_ERROR).getActionResult();
	}
}