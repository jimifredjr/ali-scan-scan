package app.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import app.domain.InventoryItem;

@Service
public class InventoryService {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public InventoryItem getInventoryItem(String code) {
		InventoryItem inventoryItem = new InventoryItem();
		jdbcTemplate.query("SELECT code, name, price FROM INVENTORY_ITEM", new RowMapper<InventoryItem>() {
			@Override
			public InventoryItem mapRow(ResultSet rs, int rowNum) throws SQLException {
				inventoryItem.setCode(rs.getString(1));
				inventoryItem.setName(rs.getString(2));
				inventoryItem.setPrice(rs.getString(3));
				return inventoryItem;
			}
		});
		return inventoryItem;
	}

	public InventoryItem setInventoryItem(String code, String name, String price) {
		String script = "INSERT (code, name, price) VALUES (?,?,?)";
		jdbcTemplate.update(script, code, name, price);
		return new InventoryItem(code, name, price);
	}
	
	
	
}
