package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

	private Integer roomNumber;
	private Date checkin;
	private Date checkout;
	
	//é static para que não seja instanciado um novo SimpleDateFormat 
	//para cada objeto Reservation que a aplicação tiver
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	
	//Constructor
	public Reservation(Integer roomNumber, Date checkin, Date checkout) {
		
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
		this.checkin = checkin;
		this.checkout = checkout;
	}
	
	@Override
	public String toString() {
		return "Room"
				+ roomNumber
				+ ", chekin: "
				+ sdf.format(checkin)
				+ ", chekout: "
				+ sdf.format(checkout)
				+ ", "
				+ duration()
				+ " nights";
	}
}
