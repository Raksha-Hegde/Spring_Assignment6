package com.stackroute.activitystream.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.stereotype.Component;
/*
 * The class "Circle" will be acting as the data model for the circle Table in the database. Please
 * note that this class is annotated with @Entity annotation. Hibernate will scan all package for 
 * any Java objects annotated with the @Entity annotation. If it finds any, then it will begin the 
 * process of looking through that particular Java object to recreate it as a table in your database.
 * 
 * Please note that you will have to use @Component annotation on this class if wish
 * to autowire the class from any other components of the application
 */
@Entity
@Component
public class Circle {

	@Id
	private String circleName;
	private String creatorId;
	private Timestamp createdDate;

	public Circle(String string, String string2, Timestamp timestamp) {
		circleName = string;
		creatorId = string2;
		createdDate = timestamp;
	}

	public Circle() {

	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate() {
		this.createdDate = new Timestamp(System.currentTimeMillis());
	}
}
