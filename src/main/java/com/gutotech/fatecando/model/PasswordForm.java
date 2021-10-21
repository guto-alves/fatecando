package com.gutotech.fatecando.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PasswordForm {

	@NotBlank(message = "Senha atual não pode estar em branco.")
	private String password;
	
	@NotBlank(message = "Nova Senha não pode estar em branco.")
	@Size(min = 4, message = "A senha deve conter pelo menos 4 caracteres.")
	private String newPassword;
	
	@NotBlank(message = "A confirmação da nova senha não pode estar em branco.")
	@Size(min = 4, message = "A senha deve conter pelo menos 4 caracteres.")
	private String confirmNewPassword;

	public PasswordForm() {
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmNewPassword() {
		return confirmNewPassword;
	}

	public void setConfirmNewPassword(String confirmPassword) {
		this.confirmNewPassword = confirmPassword;
	}

}
