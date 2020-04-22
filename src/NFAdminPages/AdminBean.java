package NFAdminPages;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class AdminBean {
	public String adminSearch() {
		return "adminSearch";
	}
	public String adminEdit() {
		return "adminEdit";
	}
	public String viewAll() {
		return "viewAll";
	}
	public String addShow() {
		return "adminAdd";
	}
	public String panel() {
		return "adminpanel";
	}

}

