package com.quality.project.recipeCategory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.quality.project.databaseConnector.DBConnector;
import com.quality.project.templates.ICRUDTemplate;
import com.mysql.jdbc.CallableStatement;

public class CategoryDataLayerTemplate implements ICRUDTemplate<ICategory> {

	DBConnector dbConnector = DBConnector.getDBConnectorInstance();
	private String source;
 
	@Override
	public int add(ICategory Obj) throws Exception {
		try {
			source = "sp_addCategory";
			Connection connection = dbConnector.getDBConnection();
			int count = 0;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				stmt.setString(1, Obj.getCategoryName());
				count = stmt.executeUpdate();
			}
			connection.close();
			return count;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public int update(ICategory Obj) throws Exception {
		try {
			source = "sp_updateCategory";
			Connection connection = dbConnector.getDBConnection();
			int count = 0;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				stmt.setString(1, Obj.getCategoryName());
				stmt.setInt(2, Obj.getCategoryId());
				count = stmt.executeUpdate();
			}
			connection.close();
			return count;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public int remove(ICategory Obj) throws Exception {
		try {
			source = "sp_removeCategory";
			Connection connection = dbConnector.getDBConnection();
			int count = 0;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				stmt.setString(1, Obj.getCategoryName());
				count = stmt.executeUpdate();
			}
			connection.close();
			return count;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<ICategory> Select() throws Exception {
		try {
			source = "sp_fetchCategory";
			List<ICategory> categoryList = new ArrayList<ICategory>();
			Connection connection = dbConnector.getDBConnection();
			ResultSet resultSet = null;
			if (connection != null) {
				CallableStatement stmt = (CallableStatement) connection.prepareCall("{call " + source + "()}");
				Boolean hasResuts = stmt.execute();
				if (hasResuts) {
					resultSet = stmt.getResultSet();
					while (resultSet.next()) {
						ICategory categoryData = new Category();
						categoryData.setCategoryId(resultSet.getInt("category_id"));
						categoryData.setCategoryName(resultSet.getString("category_name"));
						categoryList.add(categoryData);
					}
				}
			}
			connection.close();
			return categoryList;
		} catch (Exception e) {
			throw e;
		}
	}

}