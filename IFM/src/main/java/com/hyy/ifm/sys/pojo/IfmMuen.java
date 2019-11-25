package com.hyy.ifm.sys.pojo;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 菜单表
 * 
 * @author Administrator
 *
 */
@Table("IFM_SYS_MUEN")
public class IfmMuen {
	@Id
	private int muenId;
	@Column("MUEN_NAME")
	private String muenName;
	@Column("PARENT_MUEN_ID")
	private String parentMuenId;
	@Column("XH")
	private int xh;
	@Column("URI")
	private String uri;
	@Column("BUTTONS")
	private String buttons;
	@Column("STATUS")
	private String status;
	@Column("ICON")
	private String icon;

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getMuenId() {
		return muenId;
	}

	public void setMuenId(int muenId) {
		this.muenId = muenId;
	}

	public String getMuenName() {
		return muenName;
	}

	public void setMuenName(String muenName) {
		this.muenName = muenName;
	}

	public String getParentMuenId() {
		return parentMuenId;
	}

	public void setParentMuenId(String parentMuenId) {
		this.parentMuenId = parentMuenId;
	}

	public int getXh() {
		return xh;
	}

	public void setXh(int xh) {
		this.xh = xh;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getButtons() {
		return buttons;
	}

	public void setButtons(String buttons) {
		this.buttons = buttons;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
