package com.system.info.rest;

//tag::copyright[]
/*******************************************************************************
* Copyright (c) 2017, 2022 IBM Corporation and others.
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*
* Contributors:
*     IBM Corporation - Initial implementation
*******************************************************************************/
//end::copyright[]


import jakarta.json.JsonArray;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.annotation.security.RolesAllowed;

import java.util.logging.Logger;

import org.eclipse.microprofile.jwt.Claim;

@RequestScoped
@Path("/properties")
public class SystemResource {

private static Logger logger = Logger.getLogger(SystemResource.class.getName());
	
 @Inject
 // tag::claim[]
 @Claim("groups")
 // end::claim[]
 // tag::rolesArray[]
 private JsonArray roles;
 // end::rolesArray[]

 @GET
 // tag::usernameEndpoint[]
 @Path("/username")
 // end::usernameEndpoint[]
 @Produces(MediaType.APPLICATION_JSON)
 // tag::rolesAllowedAdminUser1[]
 @RolesAllowed({ "admin", "user" })
 // end::rolesAllowedAdminUser1[]
 public String getUsername() {
	 logger.info("user info retrieved : "+System.getProperties().getProperty("user.name"));
     return System.getProperties().getProperty("user.name");
 }

 @GET
 // tag::osEndpoint[]
 @Path("/os")
 // end::osEndpoint[]
 @Produces(MediaType.APPLICATION_JSON)
 // tag::rolesAllowedAdmin[]
 @RolesAllowed({ "admin" })
 // end::rolesAllowedAdmin[]
 public String getOS() {
	 logger.info("user info retrieved : "+System.getProperties().getProperty("user.name"));
     return System.getProperties().getProperty("os.name");
 }

 @GET
 // tag::rolesEndpoint[]
 @Path("/jwtroles")
 // end::rolesEndpoint[]
 @Produces(MediaType.APPLICATION_JSON)
 // tag::rolesAllowedAdminUser2[]
 @RolesAllowed({ "admin", "user" })
 // end::rolesAllowedAdminUser2[]
 public String getRoles() {
     return roles.toString();
 }
}
