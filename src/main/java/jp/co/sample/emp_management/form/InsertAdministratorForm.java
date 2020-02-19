package jp.co.sample.emp_management.form;

import javax.validation.constraints.NotBlank;

/**
 * 管理者情報登録時に使用するフォーム.
 * 
 * @author igamasayuki
 * 
 */
public class InsertAdministratorForm {
	/** 名前 */
	@NotBlank(message="入力必須項目です")
	private String name;
	/** メールアドレス */
	@NotBlank(message="入力必須項目です")
	private String mailAddress;
	/** パスワード */
	@NotBlank(message="入力必須項目です")
	private String password;
	/** 確認用パスワード */
	@NotBlank(message="入力必須項目です")
	private String password2;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ ", password2=" + password2 + "]";
	}
	
	
	
}
