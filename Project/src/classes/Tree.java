package classes;

public class Tree {
	
	private TreeNode root = null;
	private int nodesCount = 0;
	
	public TreeNode getRoot()
	{
		return root;
	}
	
	public TreeNode create(String rootData) throws Exception
	{
		if (root != null)
		{
			throw new Exception("The three has already been created");			
		}
		
		root = new TreeNode(rootData);
		nodesCount = 1;
		return root;
	}
	
	public TreeNode create(String rootData, TreeNode child) throws Exception
	{
		TreeNode createdNode = create(rootData);
		root.addChild(child);
		nodesCount = root.getDescendentsCount();
		return createdNode;
	}
	
	
	public TreeNode addChildNode(String childData)
	{		
		TreeNode newNode =  root.addChild(childData);
		nodesCount = getNodesCount() + 1;
		return newNode;
	}
	
	public TreeNode addChildNode(TreeNode child)
	{		
		root.addChild(child);
		nodesCount = getNodesCount() + child.getDescendentsCount();
		return child;
	}
	
	public void printTree()
	{
		if (root == null)
		{
			System.out.println("The tree was not created yet.");
			return;
		}
		
		root.printNode(0);
		System.out.println("Total elements: " + nodesCount);
				
	}

	public int getNodesCount() {
		return nodesCount;
	}
	
}
