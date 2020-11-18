package com.app.dbloader;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.app.dbloader.dto.StoreOrderCSV;
import com.app.dbloader.exception.InvalidStoreDataException;


public class StoreOrderCSVTest {
	
	@Test
	public void it_should_throw_Exception_when_datestring_is_invalid() {
	    StoreOrderCSV csv = new StoreOrderCSV();
	    csv.setOrderDate("invalid string");
	    csv.setShipDate("invalid string");
		
		Exception exception = assertThrows(InvalidStoreDataException.class, () -> {
	        csv.toStoreOrder();
	    });
		
        assertTrue(exception.getMessage().contains("Invalid data provided"));
	}
}
