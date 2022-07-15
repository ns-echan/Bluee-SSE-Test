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
package com.symantec.dlpx.flexresponse.examples.quarantine;

import static com.symantec.dlpx.flexresponse.incident.PreventOrProtectStatus.FLEX_RESPONSE_EXECUTED;
import static com.symantec.dlpx.flexresponse.incident.PreventOrProtectStatus.FLEX_RESPONSE_ERROR;

import com.symantec.dlpx.flexresponse.action.ActionResult; 
import com.symantec.dlpx.flexresponse.action.ActionResultBuilder;
import com.symantec.dlpx.flexresponse.action.IncidentResponseAction;
import com.symantec.dlpx.flexresponse.action.UnsupportedIncidentTypeException;
import com.symantec.dlpx.flexresponse.configuration.ConfigurationParameters;
import com.symantec.dlpx.flexresponse.discover.ContentRoot;
import com.symantec.dlpx.flexresponse.discover.FileServerItem;
import com.symantec.dlpx.flexresponse.discover.RemediationLocation;
import com.symantec.dlpx.flexresponse.discover.TargetType;
import com.symantec.dlpx.flexresponse.incident.ContentItem;
import com.symantec.dlpx.flexresponse.incident.CustomAttribute;
import com.symantec.dlpx.flexresponse.incident.Incident;

import java.util.Date;

/**
 * An example of a simple IncidentResponseAction for quarantining files.
 *
 * This action gets some basic information about the incident and then updates 
 * its custom attributes and remediation location.  The code for doing the 
 * actual quarantining work is not provided to keep this example simple.
 */
public class QuarantineAction implements IncidentResponseAction
{
	private final Quarantiner quarantiner;
	private final LocalizedMessageBuilder localizedMessageBuilder 
		= new LocalizedMessageBuilder();

	public QuarantineAction(Quarantiner quarantiner)
	{
		this.quarantiner = quarantiner;
	}
	
	@Override
	public ActionResult execute(Incident incident, ConfigurationParameters parameters)
		throws UnsupportedIncidentTypeException
	{
		try
		{
			CustomAttribute quarantineDateAttribute 
				= incident.getCustomAttribute("Quarantine Date");

			QuarantineResult result 
				= quarantiner.quarantine(getQuarantineData(incident));

			return createSuccessfulActionResult(quarantineDateAttribute, 
												result.quarantinedFileLocation);
		}
		catch (RuntimeException e)
		{
			return createFailedActionResult(e);
		}
	}

	private QuarantineData getQuarantineData(Incident incident) 
		throws UnsupportedIncidentTypeException
	{
		FileServerItem fileServerItem = getFileServerItem(incident);
		ContentRoot contentRoot = fileServerItem.getContentRoot();
		RemediationLocation location = getRemediationLocation(fileServerItem);
		
		return createQuarantineData(fileServerItem, contentRoot, location);
	}

	private QuarantineData createQuarantineData(FileServerItem fileServerItem, 
												ContentRoot contentRoot,
												RemediationLocation location)
	{
		QuarantineData data = new QuarantineData();		
		data.scanFilePath = fileServerItem.getLocation();
		data.quarantineFolderPath = location.getLocation();
		data.quarantineFolderWriteCredential = location.getCredential();
		data.scanFolderWriteCredential = contentRoot.getRemediationCredential();
		data.scanFilePath = fileServerItem.getLocation();
		
		return data;
	}

	private RemediationLocation getRemediationLocation(FileServerItem fileServerItem)
	{
		RemediationLocation remediationLocation 
			= fileServerItem.getScanConfiguration().getRemediationLocation();
		
		if (remediationLocation == null)
		{
			String message 
				= getLocalizedString("NO_REMEDATION_LOCATION_MESSAGE");
			
			throw new RuntimeException(message);
		}

		return remediationLocation;
	}

	private String getLocalizedString(String key, String ... args)
	{
		return localizedMessageBuilder.getLocalizedString(key, args);
	}

	private FileServerItem getFileServerItem(Incident incident)
		throws UnsupportedIncidentTypeException
	{
		ContentItem contentItem = incident.getContentItem();
		if (!(contentItem instanceof FileServerItem))
		{
			String message = getLocalizedString("UNSUPPORTED_INCIDENT_MESSAGE");
			throw new UnsupportedIncidentTypeException(message);
		}

		FileServerItem fileServerItem = (FileServerItem)contentItem;
		checkTargetType(fileServerItem);
		
		return fileServerItem;
	}

	private void checkTargetType(FileServerItem item) 
		throws UnsupportedIncidentTypeException
	{
		TargetType targetType = item.getScanConfiguration().getTargetType();
		if (targetType != TargetType.FILESYSTEM_SERVER 
			&& targetType != TargetType.FILESYSTEM_SCANNER)
		{
			String message = getLocalizedString("UNSUPPORTED_INCIDENT_MESSAGE");
			throw new UnsupportedIncidentTypeException(message);
		}
	}

	private ActionResult createSuccessfulActionResult(CustomAttribute quarantineDateAttribute,
													  String remediatedFileLocation)
	{
		String message 
			= getLocalizedString("ACTION_RESULT_MESSAGE", remediatedFileLocation);

		return new ActionResultBuilder()
			.setMessage(message).setRemediationLocation(remediatedFileLocation)
			.setPreventOrProtectStatus(FLEX_RESPONSE_EXECUTED)
			.addCustomAttribute(quarantineDateAttribute, new Date().toString())
			.getActionResult();
	}

	private ActionResult createFailedActionResult(RuntimeException e)
	{
		return new ActionResultBuilder()
			.setMessage(e.getMessage())
			.setPreventOrProtectStatus(FLEX_RESPONSE_ERROR).getActionResult();
	}
}
