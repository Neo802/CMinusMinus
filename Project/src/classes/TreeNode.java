package classes;

import java.util.ArrayList;

public class TreeNode {
	public String nodeInfo;
	private ArrayList<TreeNode> children = new ArrayList<TreeNode>();
	
	public TreeNode(String nodeInfo) {
		// TODO Auto-generated constructor stub
		this.nodeInfo = nodeInfo;
	}
	
	public String getNodeInfo() {
		return nodeInfo;
	}
	
	public void addChild(TreeNode child) {
		if (child != null) {
			children.add(child);
		}
	}
	
	public TreeNode[] getChildren() {
		TreeNode[] list = new TreeNode[children.size()];
		return children.toArray(list);
	}
	
}
