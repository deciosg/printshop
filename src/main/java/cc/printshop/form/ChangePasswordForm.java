package cc.printshop.form;

public class ChangePasswordForm {
	private String currentPassword;
	private String newPassword;
	private String repeatNewPassword;

	public ChangePasswordForm() {
		super();
	}

	public ChangePasswordForm(String currentPassword, String newPassword, String repeatNewPassword) {
		super();
		this.currentPassword = currentPassword;
		this.newPassword = newPassword;
		this.repeatNewPassword = repeatNewPassword;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRepeatNewPassword() {
		return repeatNewPassword;
	}

	public void setRepeatNewPassword(String repeatNewPassword) {
		this.repeatNewPassword = repeatNewPassword;
	}

}
