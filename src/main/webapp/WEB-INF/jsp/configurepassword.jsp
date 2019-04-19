<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/common/header.jspf"%>
<%@ include file="/common/navigation.jspf"%>


<form method="POST" action="updateconfig" commandName="password">
	<tr>
		<label>Minimum length of password:</label>
		<input type="number" name="passwordLength" min="8" step="1"
			value="${password.getMinLengthOfPassword()}">
	</tr>

	<tr>
		<br>
		<label>Should have upper case alphabet:</label>
		<input type="checkbox" name="uppercase" ${uppercasecheckvalue}>

	</tr>

	<tr>
		<br>
		<label>Should have lower case alphabet:</label>
		<input type="checkbox" name="lowercase" ${lowercasecheckvalue}>
	</tr>

	<tr>
		<br>
		<label>Should have number:</label>
		<input type="checkbox" name="number" ${numbercheckvalue}>
	</tr>

	<tr>
		<br>
		<label>Should have special symbol:</label>
		<input type="checkbox" name="specialChar" ${specialcharcheckvalue}>
	</tr>

	<tr>
		<br>
		<input type="submit" value="Update Configuration" />
	</tr>
</form>
<form method="POST" action="cancelHome">
	<button type="submit" name="cancel" value="cancel">CANCEL</button>
</form>
<%@include file="/common/footer.jspf"%>