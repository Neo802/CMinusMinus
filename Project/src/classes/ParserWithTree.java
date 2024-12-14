package classes;

import cup.example.*;

public class ParserWithTree extends Parser{

	public ParserWithTree() 
	{
		super();
	}
	protected TreeNode createDeclarationNode(TreeNode declaration) 
	{ 
		TreeNode newNode = new TreeNode("Declaration");
		newNode.addChild(declaration);
		return newNode;
	}
	
 	protected TreeNode createFunctionDeclarationNode(TreeNode typeSpecifier, TreeNode identifierName, TreeNode paramsList, TreeNode block) 	
 	{ 
 		TreeNode newNode = new TreeNode("FunctionDecl");
 		newNode.addChild(typeSpecifier);
 		newNode.addChild(identifierName);
 		newNode.addChild(paramsList);
 		newNode.addChild(block);
 		return newNode; 
 	}
 	
  	protected TreeNode createTypeSpecifier(String typeName)
  	{ 
  		TreeNode newNode = new TreeNode("Type");
  		newNode.addChild(new TreeNode(typeName));
  		return newNode;
  	}
  	
	protected TreeNode createListNode(String listName, TreeNode firstChild)
	{
		TreeNode newNode = new TreeNode(listName);
		newNode.addChild(firstChild);
		return newNode;
	}
	
	protected TreeNode createVarDeclaration(TreeNode typeSpecifier, TreeNode identifierName, Integer value )
	{
		TreeNode newNode = new TreeNode("VarDecl" + "[" + value + "]");
		newNode.addChild(typeSpecifier);		
		newNode.addChild(identifierName);
		
		return newNode;
	}
	
	protected TreeNode createIfStatement(String identifier, TreeNode ifInstructions, TreeNode elseInstructions)
	{
		TreeNode newNode = new TreeNode("IfStatement", identifier);
		newNode.addChild(ifInstructions);
		if (elseInstructions != null)
			newNode.addChild(elseInstructions);
		return newNode;
	}
	
	protected TreeNode createParameter(TreeNode child) {
		TreeNode newNode;
		if (child == null) {
			newNode = new TreeNode("No Parameter");
		}
		else {
			newNode = new TreeNode("Parameters");
			newNode.addChild(child);
		}
		
		return newNode;
	}
	
	protected TreeNode createFormalsList(TreeNode existingNode, TreeNode formalDecl) {
		TreeNode newNode;
		if (existingNode == null) {
			newNode = new TreeNode("Formal List");
		}
		else {
			newNode = existingNode;
		}
		newNode.addChild(formalDecl);
		
		return newNode;
	}
	
	protected TreeNode createFormalDecl(TreeNode identifierName, TreeNode typeName) {
		TreeNode newNode = new TreeNode("Formal Declaration");
		newNode.addChild(identifierName);
		newNode.addChild(typeName);
		
		return newNode;
	}
	
	protected TreeNode createBlock(TreeNode declList, TreeNode stmtList) {
		TreeNode newNode = new TreeNode("Block");
		newNode.addChild(declList);
		newNode.addChild(stmtList);
		
		return newNode;
	}
}
