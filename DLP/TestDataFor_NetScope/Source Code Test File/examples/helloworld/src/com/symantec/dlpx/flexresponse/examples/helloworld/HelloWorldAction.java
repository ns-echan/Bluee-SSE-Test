//////////////////////////////////////////////////////////////////////////////////
// Symantec copyright header start
//////////////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2015 Symantec Corporation. All rights reserved.
//
// THIS SOFTWARE CONTAINS CONFIDENTIAL INFORMATION AND TRADE SECRETS OF SYMANTEC
// CORPORATION.  USE, DISCLOSURE OR REPRODUCTION IS PROHIBITED WITHOUT THE PRIOR
// EXPRESS WRITTEN PERMISSION OF SYMANTEC CORPORATION.
//
// The Licensed Software and Documentation are deemed to be commercial computer
// software as defined in FAR 12.212 and subject to restricted rights as defined
// in FAR Section 52.227-19 "Commercial Computer Software - Restricted Rights"
// and DFARS 227.7202, "Rights in Commercial Computer Software or Commercial
// Computer Software Documentation", as applicable, and any successor
// regulations.  Any use, modification, reproduction release, performance,
// display or disclosure of the Licensed Software and Documentation by the U.S.
// Government shall be solely in accordance with the terms of this Agreement.
//
//////////////////////////////////////////////////////////////////////////////////
// Symantec copyright header stop
//////////////////////////////////////////////////////////////////////////////////
package com.symantec.dlpx.flexresponse.examples.helloworld;

import static com.symantec.dlpx.flexresponse.incident.PreventOrProtectStatus.FLEX_RESPONSE_EXECUTED; 

import com.symantec.dlpx.flexresponse.action.ActionResult;
import com.symantec.dlpx.flexresponse.action.ActionResultBuilder;
import com.symantec.dlpx.flexresponse.action.IncidentResponseAction;
import com.symantec.dlpx.flexresponse.configuration.ConfigurationParameters;
import com.symantec.dlpx.flexresponse.incident.Incident;
 
/**
 * An extremely simple example of an IncidentResponseAction.
 *
 * This action simply returns the action result message
 * "Hello World! This item has been remediated." for every incident.
 */
public class HelloWorldAction implements IncidentResponseAction
{
	private static final String ACTION_RESULT_MESSAGE 
		= "Hello World! This item has been remediated.";

	public ActionResult execute(Incident incident, ConfigurationParameters parameters)
	{
		return new ActionResultBuilder()
			.setMessage(ACTION_RESULT_MESSAGE)
			.setPreventOrProtectStatus(FLEX_RESPONSE_EXECUTED)
			.getActionResult();
	}
}