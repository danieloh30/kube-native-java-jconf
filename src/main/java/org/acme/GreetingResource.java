package org.acme;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/hello")
public class GreetingResource {

    @ConfigProperty(name = "name")
    String name;

    @ConfigProperty(name = "myconfig.property.name")
    String nameByKube;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/person")
    @RolesAllowed("admin")
    public List<Person> listAll() {
        return Person.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/person/{id}")
    @RolesAllowed("user")
    public Person findById(@PathParam("id") Long id) {
        return Person.findById(id);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greeting")
    public String greeting() {
        return "Welcome to Kube Native Java with Quarkus at jconf.dev, " + nameByKube;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from jconf.dev";
    }
}