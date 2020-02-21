package jp.co.sample.emp_management.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;

public class InsertEmployeeForm {
	/** 従業員名 */
	@NotBlank(message="入力必須項目です")
	private String name;
	/** 画像 */
	@NotBlank(message="画像を選択してください")
	private String image;
	/** 性別 */
	@NotBlank(message="入力必須項目です")
	private String gender;
	/** 入社日 */
	@NotBlank(message="入力必須項目です")
	private String hireDate;
	/** メールアドレス */
	@NotBlank(message="入力必須項目です")
	@Email(message="不正なメールアドレス")
	private String mailAddress;
	/** 郵便番号 */
	@NotBlank(message="入力必須項目です")
	@Pattern(message="入力された数値が不正です", regexp="^[0-9]{3}-[0-9]{4}$")
	private String zipCode;
	/** 住所 */
	@NotBlank(message="入力必須項目です")
	private String address;
	/** 電話番号 */
	@NotBlank(message="入力必須項目です")
	@Pattern(message="電話番号が不正です" ,regexp = "0\\d{1,4}-\\d{1,4}-\\d{4}$" )
	private String telephone;
	/** 給料 */
	@NotBlank(message="入力必須項目です")
	@Range(message="入力された数値が不正です",min=0,max=100000000)
	private String salary;
	/** 特性 */
	@NotBlank(message="入力必須項目です")
	private String characteristics;
	/** 扶養人数 */
	@NotBlank(message="入力必須項目です")
	@Range(message="入力された数値が不正です",min=0,max=50)
	private String dependentsCount;
	
	@Override
	public String toString() {
		return "InsertEmployeeForm [name=" + name + ", image=" + image + ", gender=" + gender + ", hireDate=" + hireDate
				+ ", mailAddress=" + mailAddress + ", zipCode=" + zipCode + ", address=" + address + ", telephone="
				+ telephone + ", salary=" + salary + ", characteristics=" + characteristics + ", dependentsCount="
				+ dependentsCount + "]";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(String characteristics) {
		this.characteristics = characteristics;
	}
	public String getDependentsCount() {
		return dependentsCount;
	}
	public void setDependentsCount(String dependentsCount) {
		this.dependentsCount = dependentsCount;
	}
}
