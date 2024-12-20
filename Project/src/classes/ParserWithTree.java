package classes;

import cup.example.*;

public class ParserWithTree extends Parser{

	public ParserWithTree() 
	{
		super();
	}
	
 	protected TreeNode createFunctionDeclaration(TreeNode typeSpecifier, TreeNode identifierName, TreeNode paramsList, TreeNode block) 	
 	{ 
 		TreeNode newNode = new TreeNode("FunctionDecl", identifierName.getChildren()[0].getData().toString());
 		newNode.addChild(typeSpecifier);
 		newNode.addChild(identifierName);
 		newNode.addChild(paramsList);
 		newNode.addChild(block);
 		
 		return newNode; 
 	}
	
	protected TreeNode createVarDeclaration(TreeNode typeSpecifier, TreeNode identifierName, Integer value )
	{
		TreeNode newNode = new TreeNode("VarDecl", identifierName.getChildren()[0].getData().toString());
		newNode.addChild(typeSpecifier);		
		newNode.addChild(identifierName);
		newNode.addChild(value.toString());
		
		return newNode;
	}
 	
  	protected TreeNode createTypeSpecifier(String typeName)
  	{ 
  		TreeNode newNode = new TreeNode("Type");
  		newNode.addChild(new TreeNode(typeName));
  		return newNode;
  	}
	
	protected TreeNode createIfStatement(TreeNode expression, TreeNode ifInstructions, TreeNode elseInstructions)
	{
		TreeNode newNode = new TreeNode("IfStatement");
		newNode.addChild(expression);
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
	
	private TreeNode createList(TreeNode List, TreeNode Decl, String Text) {
		TreeNode newNode;
		
		if (List == null) {
			newNode = new TreeNode(Text);
		}
		else {
			newNode = List;
			newNode.addChild(Decl);
		}
		
		return newNode;
	}
	
	protected TreeNode createDeclList(TreeNode declList, TreeNode varDecl) {
		TreeNode newNode = createList(declList, varDecl, "Declaration List");
		
		return newNode;
	}
	
	protected TreeNode createStmtList(TreeNode stmtList, TreeNode stmt) {
		TreeNode newNode = createList(stmtList, stmt, "Statement List");
		
		return newNode;
	}
	
	// EXPRESSION OPERATIONS

	private TreeNode insertElements(String text, TreeNode e1, TreeNode e2) {
		TreeNode newNode = new TreeNode(text);
		newNode.addChild(e1);
		newNode.addChild(e2);
		
		return newNode;
	}
	
	protected TreeNode sumOp(TreeNode e1, TreeNode e2) {
		TreeNode newNode = insertElements("Sum", e1, e2);
		
		return newNode;
	}
	
	protected TreeNode diffOp(TreeNode e1, TreeNode e2) {
		TreeNode newNode = insertElements("Difference", e1, e2);
		
		return newNode;
	}
	
	protected TreeNode multiOp(TreeNode e1, TreeNode e2) {
		TreeNode newNode = insertElements("Multiply", e1, e2);
		
		return newNode;
	}
	
	protected TreeNode divisionOp(TreeNode e1, TreeNode e2) {
		TreeNode newNode = insertElements("Divide", e1, e2);
		
		return newNode;
	}
	
	protected TreeNode andOp(TreeNode e1, TreeNode e2) {
		TreeNode newNode = insertElements("And And", e1, e2);
		
		return newNode;
	}
	
	protected TreeNode orOp(TreeNode e1, TreeNode e2) {
		TreeNode newNode = insertElements("Or Or", e1, e2);
		
		return newNode;
	}
	
	protected TreeNode equalityOp(TreeNode e1, TreeNode e2) {
		TreeNode newNode = insertElements("Equality", e1, e2);
		
		return newNode;
	}
	
	protected TreeNode notequalOp(TreeNode e1, TreeNode e2) {
		TreeNode newNode = insertElements("Not Equal", e1, e2);
		
		return newNode;
	}
	
	protected TreeNode lesserOp(TreeNode e1, TreeNode e2) {
		TreeNode newNode = insertElements("Lesser", e1, e2);
		
		return newNode;
	}
	
	protected TreeNode greaterOp(TreeNode e1, TreeNode e2) {
		TreeNode newNode = insertElements("Greater", e1, e2);
		
		return newNode;
	}
	
	protected TreeNode lessEqOp(TreeNode e1, TreeNode e2) {
		TreeNode newNode = insertElements("Less Equal", e1, e2);
		
		return newNode;
	}
	
	protected TreeNode greatEqOp(TreeNode e1, TreeNode e2) {
		TreeNode newNode = insertElements("Greater Equal", e1, e2);
		
		return newNode;
	}
	
}
