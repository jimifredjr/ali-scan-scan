package app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import app.domain.InventoryItem;
import app.service.InventoryService;
import app.service.ScannerService;

@RestController
public class MainController {
	private static Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	protected InventoryService inventoryService;
	
	@Autowired
	protected ScannerService scannerService;
	
	@GetMapping("/inventoryItem")
	public ResponseEntity<?> getInventoryItem() {
		String code = "getCode()";//TODO get code from scanner
		logger.info("Get incentory item, code: {}", code);
		InventoryItem inventoryItem = inventoryService.getInventoryItem(code);
		return new ResponseEntity<>(inventoryItem, HttpStatus.OK);
	}
	
	@PostMapping("/inventoryItem")
	public ResponseEntity<?> addInventoryItem(String name, String price) {
		String code = "getCode()";
		logger.info("Add incentory item, code: {}", code);
		InventoryItem inventoryItem = inventoryService.setInventoryItem(code, name, price);
		return new ResponseEntity<>(inventoryItem, HttpStatus.OK);
	}
}
