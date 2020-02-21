package jp.co.sample.emp_management.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sample.emp_management.domain.Employee;
import jp.co.sample.emp_management.form.InsertEmployeeForm;
import jp.co.sample.emp_management.form.UpdateEmployeeForm;
import jp.co.sample.emp_management.service.EmployeeService;

/**
 * 従業員情報を操作するコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public UpdateEmployeeForm setUpForm() {
		return new UpdateEmployeeForm();
	}

	@ModelAttribute
	public InsertEmployeeForm setUpInsertForm() {
		return new InsertEmployeeForm();
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員一覧を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員一覧画面を出力します.
	 * 
	 * @param model モデル
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	/////////////////////////////////////////////////////
	// ユースケース：検索された従業員一覧を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員一覧画面を出力します.
	 * 
	 * @param model モデル String 検索用ユーザー名
	 * @return 従業員一覧画面
	 */
	@RequestMapping("/search")
	public String search(Model model, String name) {
		List<Employee> employeeList = employeeService.showSearchedList(name);
		System.out.println(employeeList);
		if (employeeList.size() == 0) {
			model.addAttribute("NoList", "一件もありませんでした");
			return showList(model);
		}
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を表示する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細画面を出力します.
	 * 
	 * @param id    リクエストパラメータで送られてくる従業員ID
	 * @param model モデル
	 * @return 従業員詳細画面
	 */
	@RequestMapping("/showDetail")
	public String showDetail(String id, Model model) {
		Employee employee = employeeService.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}

	/**
	 * 従業員登録画面へ遷移するためのメソッド.
	 * 
	 * @return 従業員登録画面へ
	 */
	@RequestMapping("/to_register")
	public String toRegister() {
		return "employee/register_employee";
	}

	/**
	 * 従業員情報を登録するためのメソッド
	 * 
	 * @param form   登録する従業員情報を格納sるフォーム
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping("/register")
	public String register(@Validated InsertEmployeeForm form, BindingResult result, Model model,InsertEmployeeForm insertForm) {
		if (result.hasErrors()) {
			return toRegister();
		}
		try {
			Employee employee = new Employee();
			BeanUtils.copyProperties(form, employee);
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date hireDate = sdFormat.parse(form.getHireDate());
			employee.setHireDate(hireDate);
			Integer salary = Integer.parseInt(form.getSalary());
			employee.setSalary(salary);
			Integer dependentsCount = Integer.parseInt(form.getDependentsCount());
			employee.setDependentsCount(dependentsCount);
			String image = form.getImage().getOriginalFilename();
			employee.setImage(image);
			System.out.println(employee);
			employeeService.insert(employee);
			model.addAttribute("employee", employee);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Path path = Paths.get("/Applications/workspace/ex-emp-management-bugfix/src/main/resources/static/img/" + insertForm.getImage().getOriginalFilename());
		System.out.println(insertForm.getImage().getOriginalFilename());
		try (OutputStream os = Files.newOutputStream(path, StandardOpenOption.CREATE)) {
			byte[] bytes = insertForm.getImage().getBytes();
			os.write(bytes);
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
		return showList(model);
	}

	/////////////////////////////////////////////////////
	// ユースケース：従業員詳細を更新する
	/////////////////////////////////////////////////////
	/**
	 * 従業員詳細(ここでは扶養人数のみ)を更新します.
	 * 
	 * @param form 従業員情報用フォーム
	 * @return 従業員一覧画面へリダクレクト
	 */
	@RequestMapping("/update")
	public String update(@Validated UpdateEmployeeForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return showDetail(form.getId(), model);
		}
		Employee employee = new Employee();
		employee.setId(form.getIntId());
		employee.setDependentsCount(form.getIntDependentsCount());
		employeeService.update(employee);
		return "redirect:/employee/showList";
	}
}
