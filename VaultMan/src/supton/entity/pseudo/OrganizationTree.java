package supton.entity.pseudo;

import java.util.List;

public class OrganizationTree {

	private String nodeName;
	private String nodeId;
	private String nodeTId;
	private Integer nodeOrgType;
	private List<OrganizationTree>  childrenTreeList;
	
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
	public String getNodeTId() {
		return nodeTId;
	}
	public void setNodeTId(String nodeTId) {
		this.nodeTId = nodeTId;
	}
	public Integer getNodeOrgType() {
		return nodeOrgType;
	}
	public void setNodeOrgType(Integer nodeOrgType) {
		this.nodeOrgType = nodeOrgType;
	}
	
	public List<OrganizationTree> getChildrenTreeList() {
		return childrenTreeList;
	}
	public void setChildrenTreeList(List<OrganizationTree> childrenTreeList) {
		this.childrenTreeList = childrenTreeList;
	}



}
