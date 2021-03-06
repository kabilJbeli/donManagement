package service;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import daoDon.serviceDon;
import daoDon.serviceDonImpl;
import entities.Don;
import entities.Type;

@Path("/dons")
public class donEntityManagement{
	public serviceDonImpl servicedon = new serviceDonImpl();
	
	@PUT
	@Path("/modifyDon")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Boolean addType(Don don, @QueryParam("typeid") int idType) {
		try {
			Type type = servicedon.findType(idType);
			don.setType(type);
			return servicedon.update(don);
		}catch(NullPointerException e) {
			throw e;
			
		}
	}
	
	
	
	
	@POST
	@Path("/addType")
	@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON})
	public Type addType(Type type) {
		try {
			return servicedon.addType(type);
		}catch(NullPointerException e) {
			throw e;
			
		}
	}
	
	@GET
	@Path("/findType")
	@Produces({MediaType.APPLICATION_JSON})
	public Type findTypeById(@QueryParam("code") int code) {
	try {
					
		return servicedon.findType(code);
		
		}
	catch(NullPointerException e) {
		throw e;		
	}
	}
			
	@POST
	@Path("/add/{type}/{affectation}/{quantity}/{description}")
	@Produces({MediaType.APPLICATION_JSON})
	public Don addDon(@PathParam("type") int idType,@PathParam("affectation") String affectation,@PathParam("quantity") int quantity,@PathParam("description") String description) {
		Don don = new Don();
		don.setAffectation(affectation);
		don.setDescription(description);
		don.setQuantity(quantity);
		try {
			
			Type type = servicedon.findType(idType);
			don.setType(type);
			
			if (don.getQuantity() < (type.getQuantite() - servicedon.quantiteAjouté(type.getId())))
			{
			return servicedon.add(don);
			}
			{
				return null;
				}
		}catch(NullPointerException e) {
			throw e;
			
		}
	}
	
	@DELETE
	@Path("/removeType")
	@Produces({MediaType.TEXT_HTML})
	public String removeTypeEntity(@QueryParam("code") int code) {
		try {			
			 return "<h3>"+servicedon.removeType(code)+"</h3>";		
			
		}catch(NullPointerException e) {
throw e;
		}
	}
	
	
	@DELETE
	@Path("/removeDon")
	@Produces({MediaType.TEXT_PLAIN})
	public void removeDon(@QueryParam("code") int code) {
		try {			
			
			servicedon.remove(code);		
			
		}catch(NullPointerException e) {
throw e;
		}
	}
	
	@GET
	@Path("/getAll")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Don> getAll() {
		List<Don> donsList;
		try {			
			donsList = servicedon.findAll();
			return donsList;
		}catch(NullPointerException e) {
			throw e;
			
		}
	}

	
	@GET
	@Path("/getTypeList")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Type> getTypeList() {
		List<Type> typeList;
		try {			
			typeList = servicedon.findAllTypes();
			return typeList;
		}catch(NullPointerException e) {
			throw e;
			
		}
	}
	
	@GET
	@Path("/getSpecificDon")
	@Produces({MediaType.APPLICATION_JSON})
	public Don getSpecificDon(@QueryParam("id") int id) {
		Don don;
		try {			
			don = servicedon.find(id);
			return don;
		}catch(NullPointerException e) {
			throw e;
			
		}
	}
}
