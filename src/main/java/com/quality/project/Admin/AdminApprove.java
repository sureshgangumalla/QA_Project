package com.quality.project.Admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.mail.MessagingException;

import com.mysql.jdbc.CallableStatement;
import com.quality.project.databaseConnector.DBConnector;
import com.quality.project.email.EmailService;
import com.quality.project.email.EmailServiceSubject;
import com.quality.project.logger.Logger;
import com.quality.project.logger.LoggerInstance;
import com.quality.project.templates.FollowersDatalayer;
import com.quality.project.recipe.RecipeCRUDDataLayer;
import com.quality.project.user.IUser;
import com.quality.project.user.User;

public class AdminApprove {

	private DBConnector dbConnector;
	private LoggerInstance logs;

	public AdminApprove() {
		dbConnector = DBConnector.getDBConnectorInstance();
		logs = LoggerInstance.getInstance();
	}

	public void GiveAdminResponse(int recipeId, String response, String status) {

		if (status.equals("approved")) {
			NotifyAllFollowers(recipeId);
		} else if (status.equals("rejected")) {
			NotifyRejected(response, recipeId);
		}
		
		Connection connection = dbConnector.getDBConnection();
		CallableStatement stmt = null;
		String source = "sp_insertAdminResponse";
		
		try {
			
			if (connection != null) {
				stmt = (CallableStatement) connection
						.prepareCall("{call " + source + "(?, ?, ?)}");
				stmt.setString(1, response);
				stmt.setString(2, status);
				stmt.setInt(3, recipeId);
				stmt.executeUpdate();
				logs.log(Logger.INFO, "Admin Approved recipe with Id " + recipeId);
			}
		} catch (Exception e) {
			System.out.println("Error in executing the Update query");
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
				logs.log(Logger.ERROR, e.getMessage());
			}
		}
			
	}

	public void NotifyAllFollowers(int recipeId) {
		RecipeCRUDDataLayer recipeTemplate = new RecipeCRUDDataLayer();
		EmailServiceSubject emailService = new EmailServiceSubject();
		ArrayList<String> followersEmail = getFollowersEmail(recipeId);
		int userId = recipeTemplate.getRecipe(recipeId).getUserId();
		IUser user = this.getPersonDetails(userId);
		String uploaderName = user.getUsername();
		
		for (String followerEmail : followersEmail) {
			emailService.addFollowerEmail(followerEmail);
		}
		emailService.sendEmailToFollowers(uploaderName, recipeTemplate.getRecipe(recipeId).getRecipeName());

	}

	public ArrayList<String> getFollowersEmail(int recipeId) {
		FollowersDatalayer followersDatalayer = new FollowersDatalayer();
		RecipeCRUDDataLayer recipeTemplate = new RecipeCRUDDataLayer();
		int userId = recipeTemplate.getRecipe(recipeId).getUserId();
		ArrayList<Integer> followersList = followersDatalayer.getFollowersId(userId);
		ArrayList<String> followersEmail = new ArrayList<String>();
		Connection connection = dbConnector.getDBConnection();
		CallableStatement stmt = null;
		ResultSet resultSet = null;
		String source = "sp_fetchFollowersEmail";
		
		for (int followerId : followersList) {
			try {
				if (connection != null) {
					stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
					stmt.setInt(1, userId);
					Boolean hasResuts = stmt.execute();

					if (hasResuts) {
						resultSet = stmt.getResultSet();
					}
				}
				while (resultSet.next()) {
					followersEmail.add(resultSet.getString("email_address"));
				}
			} catch (Exception e) {
				logs.log(Logger.ERROR, e.getMessage());
			} finally {
				try {
					stmt.close();
					resultSet.close();
					connection.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
					logs.log(Logger.ERROR, e.getMessage());
				}
			}
		}
		return followersEmail;
	}

	public void NotifyRejected(String reason, int recipeId) {
		RecipeCRUDDataLayer recipeTemplate = new RecipeCRUDDataLayer();
		int userId = recipeTemplate.getRecipe(recipeId).getUserId();
		EmailService emailService = new EmailService();
		String recipeName = recipeTemplate.getRecipe(recipeId).getRecipeName();
		String message = "Dear User, your recipe with name " + recipeName
				+ " has been rejected by the Admin, Please go through it again and verify !";
		IUser user = this.getPersonDetails(userId);
		String userEmail = user.getUserEmailId();
		try {
			emailService.sendEmail(userEmail, recipeName + "  Has Been Rejected by the Admin", message);
		} catch (MessagingException e) {
			logs.log(Logger.ERROR, e.getMessage());
		}

	}

	public IUser getPersonDetails(int userId) {
		IUser user = new User();
		String source = "sp_fetchFollowersEmail";
		Connection connection = dbConnector.getDBConnection();
		ResultSet resultSet = null;
		CallableStatement stmt = null;
		try {
			if (connection != null) {
				stmt = (CallableStatement) connection.prepareCall("{call " + source + "(?)}");
				stmt.setInt(1, userId);
				Boolean hasResuts = stmt.execute();

				if (hasResuts) {
					resultSet = stmt.getResultSet();
				}
				while (resultSet.next()) {
					user.setUserEmailId(resultSet.getString("email_address"));
					user.setUsername(resultSet.getString("full_name"));
					user.setUserID(resultSet.getInt("user_id"));
				}
			}
		} catch (Exception e) {
			logs.log(Logger.ERROR, e.getMessage());
		} finally {
			try {
				stmt.close();
				resultSet.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
				logs.log(Logger.ERROR, e.getMessage());
			}
		}

		return user;
	}

}
