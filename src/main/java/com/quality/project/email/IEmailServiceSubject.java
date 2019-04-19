package com.quality.project.email;

public interface IEmailServiceSubject {
	
	public void addFollowerEmail(String followerEmail);
	
	public void removeFollowerEmail(String followerEmail);
	
	public void sendEmailToFollowers(String followeeName,String recipeName);

}
