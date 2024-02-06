package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {

	private Integer roomNumber;
	private Date checkin;
	private Date checkout;
	
	Date now = new Date();
	
	//é static para que não seja instanciado um novo SimpleDateFormat 
	//para cada objeto Reservation que a aplicação tiver
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
	//Constructor
	public Reservation(Integer roomNumber, Date checkin, Date checkout) {
		
		if (checkin.after(checkout)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		if (checkin.before(now) || checkout.before(now)) {
		    throw new DomainException("Reservation dates must be future dates");
		}
		
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	//Getters and Setters
	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}
	
	public long duration() {
		long diff = checkout.getTime() - checkin.getTime(); 
		//calcula a diferença entre datas em milisegundos
		//usa long porque o valor é muito longo
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); 
		// converte essa diferença para dias
	}
	
	public void updateDate(Date checkin, Date checkout) {
		
		if (checkin.before(now) || checkout.before(now)) {
		    throw new DomainException("Reservation dates for update must be future dates");
		} 
		if (checkin.after(checkout)) {
			throw new DomainException("Check-out date must be after check-in date");
		}
		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	@Override
	public String toString() {
	    
		String durationString;
	    if (duration() > 1) {
	        durationString = duration() + " nights";
	    } else {
	        durationString = duration() + " night";
	    }
	    
		return "Room "
				+ roomNumber
				+ ", chekin: "
				+ sdf.format(checkin)
				+ ", chekout: "
				+ sdf.format(checkout)
				+ ", "
				+durationString;
				
				
	}
}
