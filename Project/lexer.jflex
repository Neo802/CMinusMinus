package cup.example;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java_cup.runtime.Symbol;
import java.lang.*;
import java.io.InputStreamReader;

%%

%class Lexer
%implements sym
%public
%unicode
%line
%column
%cup
%char
%{
	

    public Lexer(ComplexSymbolFactory sf, java.io.InputStream is){
		this(is);
        symbolFactory = sf;
    }
	public Lexer(ComplexSymbolFactory sf, java.io.Reader reader){
		this(reader);
        symbolFactory = sf;
    }
    
    private StringBuffer sb;
    private ComplexSymbolFactory symbolFactory;
    private int csline,cscolumn;

    public Symbol symbol(String name, int code){
		return symbolFactory.newSymbol(name, code,
						new Location(yyline+1,yycolumn+1, yychar), // -yylength()
						new Location(yyline+1,yycolumn+yylength(), yychar+yylength())
				);
    }
    public Symbol symbol(String name, int code, String lexem){
	return symbolFactory.newSymbol(name, code, 
						new Location(yyline+1, yycolumn +1, yychar), 
						new Location(yyline+1,yycolumn+yylength(), yychar+yylength()), lexem);
    }
    
    protected void emit_warning(String message){
    	System.out.println("scanner warning: " + message + " at : 2 "+ 
    			(yyline+1) + " " + (yycolumn+1) + " " + yychar);
    }
    
    protected void emit_error(String message){
    	System.out.println("scanner error: " + message + " at : 2" + 
    			(yyline+1) + " " + (yycolumn+1) + " " + yychar);
    }
%}

/* Declare the lexical states */
%state COMMENT
/* Regular expressions for tokens */
Newline    = \r | \n | \r\n
Whitespace = [ \t\f] | {Newline}
Number     = [0-9]+
Identifier = [a-zA-Z_][a-zA-Z0-9_]*
StringChar = ([ \t\f]|[ \n\t]|\w)
Quotes = \"
StringLiteral = {Quotes}[a-zA-Z0-9_ ]*{Quotes}

/* comments */

Comment = {TraditionalComment} | {EndOfLineComment}
TraditionalComment = "/*" {CommentContent} \*+ "/"
EndOfLineComment = "//" [^\r\n]* {Newline}
CommentContent = ( [^*] | \*+[^*/] )*

ident = ([:jletter:] | "_" ) ([:jletterdigit:] | [:jletter:] | "_" )*


%eofval{
    return symbolFactory.newSymbol("EOF",sym.EOF);
%eofval}

%state CODESEG

%%  

/* Whitespace and comments */
/* Keywords */
<YYINITIAL> {
  {Whitespace} { }   
  "//"         { yybegin(COMMENT); }
  "#"          { yybegin(COMMENT); }
     
  ";"          { return symbolFactory.newSymbol("SEMI", SEMI); }
  "+"          { return symbolFactory.newSymbol("PLUS", PLUS); }
  "-"          { return symbolFactory.newSymbol("MINUS", MINUS); }
  "*"          { return symbolFactory.newSymbol("TIMES", TIMES); }
  "/"          { return symbolFactory.newSymbol("SPLIT", SPLIT); }
  
  "&"          { return symbolFactory.newSymbol("AND", AND); }
  "|"          { return symbolFactory.newSymbol("OR", OR); }
  "&&"          { return symbolFactory.newSymbol("ANDAND", ANDAND); }
  "||"          { return symbolFactory.newSymbol("OROR", OROR); }
  
  "n"          { return symbolFactory.newSymbol("UMINUS", UMINUS); }
  
  "("          { return symbolFactory.newSymbol("LPAREN", LPAREN); }
  ")"          { return symbolFactory.newSymbol("RPAREN", RPAREN); } 
  
  "{"          { return symbolFactory.newSymbol("LBRACE", LBRACE); }
  "}"          { return symbolFactory.newSymbol("RBRACE", RBRACE); } 
  
  "["          { return symbolFactory.newSymbol("LSQBRAKE", LSQBRAKE); }
  "]"          { return symbolFactory.newSymbol("RSQBRAKE", RSQBRAKE); } 
  
  "<"          { return symbolFactory.newSymbol("LARROW", LARROW); }
  ">"          { return symbolFactory.newSymbol("RARROW", RARROW); } 
  
  "="          { return symbolFactory.newSymbol("EQUAL", EQUAL); }  
  "=="          { return symbolFactory.newSymbol("EQUALEQUAL", EQUALEQUAL); } 
  "!="          { return symbolFactory.newSymbol("NOTEQUAL", NOTEQUAL); } 
  "<="          { return symbolFactory.newSymbol("LESSEQUAL", LESSEQUAL); } 
  ">="          { return symbolFactory.newSymbol("GREATEQUAL", GREATEQUAL); } 
  
  ","          { return symbolFactory.newSymbol("COMMA", COMMA); }
  "int"        { return symbolFactory.newSymbol("INT", sym.INT); }
  "bool"       { return symbolFactory.newSymbol("BOOL", sym.BOOL); }
  "void"       { return symbolFactory.newSymbol("VOID", sym.VOID); }
  "true"       { return symbolFactory.newSymbol("TRUE", sym.TRUE); }
  "false"      { return symbolFactory.newSymbol("FALSE", sym.FALSE); }
  "if"         { return symbolFactory.newSymbol("IF", sym.IF); }
  "else"       { return symbolFactory.newSymbol("ELSE", sym.ELSE); }
  "while"      { return symbolFactory.newSymbol("WHILE", sym.WHILE); }
  "return"     { return symbolFactory.newSymbol("RETURN", sym.RETURN); }
  "cin"        { return symbolFactory.newSymbol("CIN", sym.CIN); }
  "cout"       { return symbolFactory.newSymbol("COUT", sym.COUT); }
  {StringLiteral} { return symbol("STRINGLITERAL", sym.STRINGLITERAL, yytext()); }
  {Number}     { return symbolFactory.newSymbol("NUMBER", NUMBER, Integer.parseInt(yytext())); }
  {Identifier} { return symbol("ID", sym.ID, yytext()); }
  
/* Error handling for unrecognized characters */

    .            { emit_warning("Unrecognized character: " + yytext()); }

}