// Generated from C:/Users/paolosant/IdeaProjects/CompProj/src/parser\FOOL.g4 by ANTLR 4.7
package parser;

    import java.util.ArrayList;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FOOLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SEMIC=1, COLON=2, COMMA=3, CLPAR=4, CRPAR=5, EQ=6, ASM=7, PLUS=8, MINUS=9, 
		TIMES=10, DIV=11, TRUE=12, FALSE=13, LPAR=14, RPAR=15, IF=16, THEN=17, 
		ELSE=18, LET=19, VOID=20, IN=21, VAR=22, FUN=23, INT=24, BOOL=25, CLASS=26, 
		EXTENDS=27, NEW=28, DOT=29, LESSEQ=30, GREATEREQ=31, GREATER=32, LESS=33, 
		AND=34, OR=35, NOT=36, NULL=37, INTEGER=38, ID=39, WS=40, LINECOMENTS=41, 
		BLOCKCOMENTS=42, ERR_UNKNOWN_CHAR=43;
	public static final int
		RULE_prog = 0, RULE_classdec = 1, RULE_decs = 2, RULE_vardec = 3, RULE_varasm = 4, 
		RULE_letVar = 5, RULE_fun = 6, RULE_dec = 7, RULE_met = 8, RULE_type = 9, 
		RULE_exp = 10, RULE_term = 11, RULE_factor = 12, RULE_stm = 13, RULE_stms = 14, 
		RULE_value = 15, RULE_funcall = 16;
	public static final String[] ruleNames = {
		"prog", "classdec", "decs", "vardec", "varasm", "letVar", "fun", "dec", 
		"met", "type", "exp", "term", "factor", "stm", "stms", "value", "funcall"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "':'", "','", "'{'", "'}'", "'=='", "'='", "'+'", "'-'", 
		"'*'", "'/'", "'true'", "'false'", "'('", "')'", "'if'", "'then'", "'else'", 
		"'let'", "'void'", "'in'", "'var'", "'fun'", "'int'", "'bool'", "'class'", 
		"'extends'", "'new'", "'.'", "'<='", "'>='", "'>'", "'<'", "'&&'", "'||'", 
		"'not'", "'null'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "SEMIC", "COLON", "COMMA", "CLPAR", "CRPAR", "EQ", "ASM", "PLUS", 
		"MINUS", "TIMES", "DIV", "TRUE", "FALSE", "LPAR", "RPAR", "IF", "THEN", 
		"ELSE", "LET", "VOID", "IN", "VAR", "FUN", "INT", "BOOL", "CLASS", "EXTENDS", 
		"NEW", "DOT", "LESSEQ", "GREATEREQ", "GREATER", "LESS", "AND", "OR", "NOT", 
		"NULL", "INTEGER", "ID", "WS", "LINECOMENTS", "BLOCKCOMENTS", "ERR_UNKNOWN_CHAR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "FOOL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FOOLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	 
		public ProgContext() { }
		public void copyFrom(ProgContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ClassExpContext extends ProgContext {
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public TerminalNode IN() { return getToken(FOOLParser.IN, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public StmsContext stms() {
			return getRuleContext(StmsContext.class,0);
		}
		public List<ClassdecContext> classdec() {
			return getRuleContexts(ClassdecContext.class);
		}
		public ClassdecContext classdec(int i) {
			return getRuleContext(ClassdecContext.class,i);
		}
		public TerminalNode LET() { return getToken(FOOLParser.LET, 0); }
		public DecsContext decs() {
			return getRuleContext(DecsContext.class,0);
		}
		public ClassExpContext(ProgContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterClassExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitClassExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitClassExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class LetInExpStmsContext extends ProgContext {
		public TerminalNode LET() { return getToken(FOOLParser.LET, 0); }
		public DecsContext decs() {
			return getRuleContext(DecsContext.class,0);
		}
		public TerminalNode IN() { return getToken(FOOLParser.IN, 0); }
		public StmsContext stms() {
			return getRuleContext(StmsContext.class,0);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode SEMIC() { return getToken(FOOLParser.SEMIC, 0); }
		public LetInExpStmsContext(ProgContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterLetInExpStms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitLetInExpStms(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitLetInExpStms(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SingleExpContext extends ProgContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode SEMIC() { return getToken(FOOLParser.SEMIC, 0); }
		public SingleExpContext(ProgContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterSingleExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitSingleExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitSingleExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
			case TRUE:
			case FALSE:
			case LPAR:
			case IF:
			case NEW:
			case NOT:
			case INTEGER:
			case ID:
				_localctx = new SingleExpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(34);
				exp();
				setState(35);
				match(SEMIC);
				}
				break;
			case LET:
				_localctx = new LetInExpStmsContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(37);
				match(LET);
				setState(38);
				decs();
				setState(39);
				match(IN);
				setState(44);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					{
					setState(40);
					exp();
					setState(41);
					match(SEMIC);
					}
					}
					break;
				case 2:
					{
					setState(43);
					stms();
					}
					break;
				}
				}
				break;
			case CLASS:
				_localctx = new ClassExpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(47); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(46);
					classdec();
					}
					}
					setState(49); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CLASS );
				setState(51);
				match(SEMIC);
				setState(54);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LET) {
					{
					setState(52);
					match(LET);
					setState(53);
					decs();
					}
				}

				setState(56);
				match(IN);
				setState(59);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
				case 1:
					{
					setState(57);
					exp();
					}
					break;
				case 2:
					{
					setState(58);
					stms();
					}
					break;
				}
				setState(61);
				match(SEMIC);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassdecContext extends ParserRuleContext {
		public TerminalNode CLASS() { return getToken(FOOLParser.CLASS, 0); }
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public TerminalNode EXTENDS() { return getToken(FOOLParser.EXTENDS, 0); }
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public TerminalNode CLPAR() { return getToken(FOOLParser.CLPAR, 0); }
		public TerminalNode CRPAR() { return getToken(FOOLParser.CRPAR, 0); }
		public List<VardecContext> vardec() {
			return getRuleContexts(VardecContext.class);
		}
		public VardecContext vardec(int i) {
			return getRuleContext(VardecContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public List<MetContext> met() {
			return getRuleContexts(MetContext.class);
		}
		public MetContext met(int i) {
			return getRuleContext(MetContext.class,i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public ClassdecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classdec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterClassdec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitClassdec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitClassdec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClassdecContext classdec() throws RecognitionException {
		ClassdecContext _localctx = new ClassdecContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_classdec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(CLASS);
			setState(66);
			match(ID);
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(67);
				match(EXTENDS);
				setState(68);
				match(ID);
				}
			}

			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LPAR) {
				{
				setState(71);
				match(LPAR);
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
					{
					setState(72);
					vardec();
					setState(77);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(73);
						match(COMMA);
						setState(74);
						vardec();
						}
						}
						setState(79);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(82);
				match(RPAR);
				}
			}

			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CLPAR) {
				{
				setState(85);
				match(CLPAR);
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
					{
					setState(89); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(86);
						met();
						setState(87);
						match(SEMIC);
						}
						}
						setState(91); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << INT) | (1L << BOOL) | (1L << ID))) != 0) );
					}
				}

				setState(95);
				match(CRPAR);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecsContext extends ParserRuleContext {
		public List<DecContext> dec() {
			return getRuleContexts(DecContext.class);
		}
		public DecContext dec(int i) {
			return getRuleContext(DecContext.class,i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public DecsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterDecs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitDecs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitDecs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecsContext decs() throws RecognitionException {
		DecsContext _localctx = new DecsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_decs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(98);
				dec();
				setState(99);
				match(SEMIC);
				}
				}
				setState(103); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VOID) | (1L << INT) | (1L << BOOL) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VardecContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public VardecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vardec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterVardec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitVardec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitVardec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VardecContext vardec() throws RecognitionException {
		VardecContext _localctx = new VardecContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_vardec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			type();
			setState(106);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarasmContext extends ParserRuleContext {
		public VardecContext vardec() {
			return getRuleContext(VardecContext.class,0);
		}
		public TerminalNode ASM() { return getToken(FOOLParser.ASM, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode NULL() { return getToken(FOOLParser.NULL, 0); }
		public VarasmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varasm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterVarasm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitVarasm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitVarasm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarasmContext varasm() throws RecognitionException {
		VarasmContext _localctx = new VarasmContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varasm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			vardec();
			setState(109);
			match(ASM);
			setState(112);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
			case TRUE:
			case FALSE:
			case LPAR:
			case IF:
			case NEW:
			case NOT:
			case INTEGER:
			case ID:
				{
				setState(110);
				exp();
				}
				break;
			case NULL:
				{
				setState(111);
				match(NULL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetVarContext extends ParserRuleContext {
		public TerminalNode LET() { return getToken(FOOLParser.LET, 0); }
		public List<VarasmContext> varasm() {
			return getRuleContexts(VarasmContext.class);
		}
		public VarasmContext varasm(int i) {
			return getRuleContext(VarasmContext.class,i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public LetVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterLetVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitLetVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitLetVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LetVarContext letVar() throws RecognitionException {
		LetVarContext _localctx = new LetVarContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_letVar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(LET);
			setState(118); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(115);
				varasm();
				setState(116);
				match(SEMIC);
				}
				}
				setState(120); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << ID))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode VOID() { return getToken(FOOLParser.VOID, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public StmsContext stms() {
			return getRuleContext(StmsContext.class,0);
		}
		public List<VardecContext> vardec() {
			return getRuleContexts(VardecContext.class);
		}
		public VardecContext vardec(int i) {
			return getRuleContext(VardecContext.class,i);
		}
		public LetVarContext letVar() {
			return getRuleContext(LetVarContext.class,0);
		}
		public TerminalNode IN() { return getToken(FOOLParser.IN, 0); }
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public FunContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fun; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterFun(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitFun(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitFun(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunContext fun() throws RecognitionException {
		FunContext _localctx = new FunContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fun);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case ID:
				{
				setState(122);
				type();
				}
				break;
			case VOID:
				{
				setState(123);
				match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(126);
			match(ID);
			setState(127);
			match(LPAR);
			setState(136);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(128);
				vardec();
				setState(133);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(129);
					match(COMMA);
					setState(130);
					vardec();
					}
					}
					setState(135);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(138);
			match(RPAR);
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LET) {
				{
				setState(139);
				letVar();
				setState(140);
				match(IN);
				}
			}

			setState(146);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				{
				setState(144);
				exp();
				}
				break;
			case 2:
				{
				setState(145);
				stms();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DecContext extends ParserRuleContext {
		public VarasmContext varasm() {
			return getRuleContext(VarasmContext.class,0);
		}
		public FunContext fun() {
			return getRuleContext(FunContext.class,0);
		}
		public DecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecContext dec() throws RecognitionException {
		DecContext _localctx = new DecContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_dec);
		try {
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(148);
				varasm();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				fun();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MetContext extends ParserRuleContext {
		public FunContext fun() {
			return getRuleContext(FunContext.class,0);
		}
		public MetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_met; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterMet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitMet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitMet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MetContext met() throws RecognitionException {
		MetContext _localctx = new MetContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_met);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			fun();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(FOOLParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(FOOLParser.BOOL, 0); }
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public TermContext left;
		public Token operator;
		public ExpContext right;
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(FOOLParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(FOOLParser.MINUS, 0); }
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			((ExpContext)_localctx).left = term();
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(157);
				((ExpContext)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
					((ExpContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(158);
				((ExpContext)_localctx).right = exp();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public FactorContext left;
		public Token operator;
		public TermContext right;
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TerminalNode TIMES() { return getToken(FOOLParser.TIMES, 0); }
		public TerminalNode DIV() { return getToken(FOOLParser.DIV, 0); }
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			((TermContext)_localctx).left = factor();
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TIMES || _la==DIV) {
				{
				setState(162);
				((TermContext)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==TIMES || _la==DIV) ) {
					((TermContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(163);
				((TermContext)_localctx).right = term();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public ValueContext left;
		public Token operator;
		public ValueContext right;
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public TerminalNode EQ() { return getToken(FOOLParser.EQ, 0); }
		public TerminalNode LESSEQ() { return getToken(FOOLParser.LESSEQ, 0); }
		public TerminalNode GREATEREQ() { return getToken(FOOLParser.GREATEREQ, 0); }
		public TerminalNode GREATER() { return getToken(FOOLParser.GREATER, 0); }
		public TerminalNode LESS() { return getToken(FOOLParser.LESS, 0); }
		public TerminalNode AND() { return getToken(FOOLParser.AND, 0); }
		public TerminalNode OR() { return getToken(FOOLParser.OR, 0); }
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			((FactorContext)_localctx).left = value();
			setState(169);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << LESSEQ) | (1L << GREATEREQ) | (1L << GREATER) | (1L << LESS) | (1L << AND) | (1L << OR))) != 0)) {
				{
				setState(167);
				((FactorContext)_localctx).operator = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << LESSEQ) | (1L << GREATEREQ) | (1L << GREATER) | (1L << LESS) | (1L << AND) | (1L << OR))) != 0)) ) {
					((FactorContext)_localctx).operator = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(168);
				((FactorContext)_localctx).right = value();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmContext extends ParserRuleContext {
		public StmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stm; }
	 
		public StmContext() { }
		public void copyFrom(StmContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class IfExpStmsContext extends StmContext {
		public StmsContext thenBranch;
		public StmsContext elseBranch;
		public TerminalNode IF() { return getToken(FOOLParser.IF, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode THEN() { return getToken(FOOLParser.THEN, 0); }
		public List<TerminalNode> CLPAR() { return getTokens(FOOLParser.CLPAR); }
		public TerminalNode CLPAR(int i) {
			return getToken(FOOLParser.CLPAR, i);
		}
		public List<TerminalNode> CRPAR() { return getTokens(FOOLParser.CRPAR); }
		public TerminalNode CRPAR(int i) {
			return getToken(FOOLParser.CRPAR, i);
		}
		public List<StmsContext> stms() {
			return getRuleContexts(StmsContext.class);
		}
		public StmsContext stms(int i) {
			return getRuleContext(StmsContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(FOOLParser.ELSE, 0); }
		public IfExpStmsContext(StmContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterIfExpStms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitIfExpStms(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitIfExpStms(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarAsmStmContext extends StmContext {
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TerminalNode ASM() { return getToken(FOOLParser.ASM, 0); }
		public TerminalNode NULL() { return getToken(FOOLParser.NULL, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode SEMIC() { return getToken(FOOLParser.SEMIC, 0); }
		public VarAsmStmContext(StmContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterVarAsmStm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitVarAsmStm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitVarAsmStm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmContext stm() throws RecognitionException {
		StmContext _localctx = new StmContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_stm);
		int _la;
		try {
			setState(193);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IF:
				_localctx = new IfExpStmsContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(171);
				match(IF);
				setState(172);
				exp();
				setState(173);
				match(THEN);
				setState(174);
				match(CLPAR);
				setState(175);
				((IfExpStmsContext)_localctx).thenBranch = stms();
				setState(176);
				match(CRPAR);
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(177);
					match(ELSE);
					setState(178);
					match(CLPAR);
					setState(179);
					((IfExpStmsContext)_localctx).elseBranch = stms();
					setState(180);
					match(CRPAR);
					}
				}

				}
				break;
			case ID:
				_localctx = new VarAsmStmContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(184);
				match(ID);
				setState(185);
				match(ASM);
				setState(188);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case NULL:
					{
					setState(186);
					match(NULL);
					}
					break;
				case MINUS:
				case TRUE:
				case FALSE:
				case LPAR:
				case IF:
				case NEW:
				case NOT:
				case INTEGER:
				case ID:
					{
					setState(187);
					exp();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(191);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
				case 1:
					{
					setState(190);
					match(SEMIC);
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StmsContext extends ParserRuleContext {
		public List<StmContext> stm() {
			return getRuleContexts(StmContext.class);
		}
		public StmContext stm(int i) {
			return getRuleContext(StmContext.class,i);
		}
		public StmsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterStms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitStms(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitStms(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StmsContext stms() throws RecognitionException {
		StmsContext _localctx = new StmsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_stms);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(195);
				stm();
				}
				}
				setState(198); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IF || _la==ID );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	 
		public ValueContext() { }
		public void copyFrom(ValueContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BaseExpContext extends ValueContext {
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public BaseExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterBaseExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitBaseExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitBaseExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IntValContext extends ValueContext {
		public TerminalNode INTEGER() { return getToken(FOOLParser.INTEGER, 0); }
		public TerminalNode MINUS() { return getToken(FOOLParser.MINUS, 0); }
		public IntValContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterIntVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitIntVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitIntVal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class MetExpContext extends ValueContext {
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TerminalNode DOT() { return getToken(FOOLParser.DOT, 0); }
		public FuncallContext funcall() {
			return getRuleContext(FuncallContext.class,0);
		}
		public MetExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterMetExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitMetExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitMetExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NewExpContext extends ValueContext {
		public TerminalNode NEW() { return getToken(FOOLParser.NEW, 0); }
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public NewExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterNewExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitNewExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitNewExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class IfExpContext extends ValueContext {
		public ExpContext cond;
		public ExpContext thenBranch;
		public ExpContext elseBranch;
		public TerminalNode IF() { return getToken(FOOLParser.IF, 0); }
		public TerminalNode THEN() { return getToken(FOOLParser.THEN, 0); }
		public List<TerminalNode> CLPAR() { return getTokens(FOOLParser.CLPAR); }
		public TerminalNode CLPAR(int i) {
			return getToken(FOOLParser.CLPAR, i);
		}
		public List<TerminalNode> CRPAR() { return getTokens(FOOLParser.CRPAR); }
		public TerminalNode CRPAR(int i) {
			return getToken(FOOLParser.CRPAR, i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(FOOLParser.ELSE, 0); }
		public IfExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterIfExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitIfExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitIfExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BoolValContext extends ValueContext {
		public TerminalNode TRUE() { return getToken(FOOLParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(FOOLParser.FALSE, 0); }
		public TerminalNode NOT() { return getToken(FOOLParser.NOT, 0); }
		public BoolValContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterBoolVal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitBoolVal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitBoolVal(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class FunExpContext extends ValueContext {
		public FuncallContext funcall() {
			return getRuleContext(FuncallContext.class,0);
		}
		public FunExpContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterFunExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitFunExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitFunExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class VarIDContext extends ValueContext {
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TerminalNode NOT() { return getToken(FOOLParser.NOT, 0); }
		public TerminalNode MINUS() { return getToken(FOOLParser.MINUS, 0); }
		public VarIDContext(ValueContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterVarID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitVarID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitVarID(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_value);
		int _la;
		try {
			setState(249);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				_localctx = new IntValContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINUS) {
					{
					setState(200);
					match(MINUS);
					}
				}

				setState(203);
				match(INTEGER);
				}
				break;
			case 2:
				_localctx = new BoolValContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(205);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==NOT) {
					{
					setState(204);
					match(NOT);
					}
				}

				setState(207);
				_la = _input.LA(1);
				if ( !(_la==TRUE || _la==FALSE) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				break;
			case 3:
				_localctx = new BaseExpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(208);
				match(LPAR);
				setState(209);
				exp();
				setState(210);
				match(RPAR);
				}
				break;
			case 4:
				_localctx = new VarIDContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(213);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==MINUS || _la==NOT) {
					{
					setState(212);
					_la = _input.LA(1);
					if ( !(_la==MINUS || _la==NOT) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
				}

				setState(215);
				match(ID);
				}
				break;
			case 5:
				_localctx = new FunExpContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(216);
				funcall();
				}
				break;
			case 6:
				_localctx = new IfExpContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(217);
				match(IF);
				setState(218);
				((IfExpContext)_localctx).cond = exp();
				setState(219);
				match(THEN);
				setState(220);
				match(CLPAR);
				setState(221);
				((IfExpContext)_localctx).thenBranch = exp();
				setState(222);
				match(CRPAR);
				setState(228);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(223);
					match(ELSE);
					setState(224);
					match(CLPAR);
					setState(225);
					((IfExpContext)_localctx).elseBranch = exp();
					setState(226);
					match(CRPAR);
					}
				}

				}
				break;
			case 7:
				_localctx = new NewExpContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(230);
				match(NEW);
				setState(231);
				match(ID);
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==LPAR) {
					{
					setState(232);
					match(LPAR);
					setState(241);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << IF) | (1L << NEW) | (1L << NOT) | (1L << INTEGER) | (1L << ID))) != 0)) {
						{
						setState(233);
						exp();
						setState(238);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(234);
							match(COMMA);
							setState(235);
							exp();
							}
							}
							setState(240);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(243);
					match(RPAR);
					}
				}

				}
				break;
			case 8:
				_localctx = new MetExpContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(246);
				match(ID);
				setState(247);
				match(DOT);
				setState(248);
				funcall();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FuncallContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public FuncallContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcall; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).enterFuncall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FOOLListener ) ((FOOLListener)listener).exitFuncall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FOOLVisitor ) return ((FOOLVisitor<? extends T>)visitor).visitFuncall(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncallContext funcall() throws RecognitionException {
		FuncallContext _localctx = new FuncallContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_funcall);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(ID);
			{
			setState(252);
			match(LPAR);
			setState(261);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MINUS) | (1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << IF) | (1L << NEW) | (1L << NOT) | (1L << INTEGER) | (1L << ID))) != 0)) {
				{
				setState(253);
				exp();
				setState(258);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(254);
					match(COMMA);
					setState(255);
					exp();
					}
					}
					setState(260);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(263);
			match(RPAR);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3-\u010c\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2/\n\2\3\2\6\2\62\n\2\r\2\16"+
		"\2\63\3\2\3\2\3\2\5\29\n\2\3\2\3\2\3\2\5\2>\n\2\3\2\3\2\5\2B\n\2\3\3\3"+
		"\3\3\3\3\3\5\3H\n\3\3\3\3\3\3\3\3\3\7\3N\n\3\f\3\16\3Q\13\3\5\3S\n\3\3"+
		"\3\5\3V\n\3\3\3\3\3\3\3\3\3\6\3\\\n\3\r\3\16\3]\5\3`\n\3\3\3\5\3c\n\3"+
		"\3\4\3\4\3\4\6\4h\n\4\r\4\16\4i\3\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6s\n\6\3"+
		"\7\3\7\3\7\3\7\6\7y\n\7\r\7\16\7z\3\b\3\b\5\b\177\n\b\3\b\3\b\3\b\3\b"+
		"\3\b\7\b\u0086\n\b\f\b\16\b\u0089\13\b\5\b\u008b\n\b\3\b\3\b\3\b\3\b\5"+
		"\b\u0091\n\b\3\b\3\b\5\b\u0095\n\b\3\t\3\t\5\t\u0099\n\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\f\5\f\u00a2\n\f\3\r\3\r\3\r\5\r\u00a7\n\r\3\16\3\16\3"+
		"\16\5\16\u00ac\n\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\5\17\u00b9\n\17\3\17\3\17\3\17\3\17\5\17\u00bf\n\17\3\17\5\17\u00c2"+
		"\n\17\5\17\u00c4\n\17\3\20\6\20\u00c7\n\20\r\20\16\20\u00c8\3\21\5\21"+
		"\u00cc\n\21\3\21\3\21\5\21\u00d0\n\21\3\21\3\21\3\21\3\21\3\21\3\21\5"+
		"\21\u00d8\n\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\5\21\u00e7\n\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u00ef\n"+
		"\21\f\21\16\21\u00f2\13\21\5\21\u00f4\n\21\3\21\5\21\u00f7\n\21\3\21\3"+
		"\21\3\21\5\21\u00fc\n\21\3\22\3\22\3\22\3\22\3\22\7\22\u0103\n\22\f\22"+
		"\16\22\u0106\13\22\5\22\u0108\n\22\3\22\3\22\3\22\2\2\23\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36 \"\2\b\4\2\32\33))\3\2\n\13\3\2\f\r\4\2\b\b "+
		"%\3\2\16\17\4\2\13\13&&\2\u0128\2A\3\2\2\2\4C\3\2\2\2\6g\3\2\2\2\bk\3"+
		"\2\2\2\nn\3\2\2\2\ft\3\2\2\2\16~\3\2\2\2\20\u0098\3\2\2\2\22\u009a\3\2"+
		"\2\2\24\u009c\3\2\2\2\26\u009e\3\2\2\2\30\u00a3\3\2\2\2\32\u00a8\3\2\2"+
		"\2\34\u00c3\3\2\2\2\36\u00c6\3\2\2\2 \u00fb\3\2\2\2\"\u00fd\3\2\2\2$%"+
		"\5\26\f\2%&\7\3\2\2&B\3\2\2\2\'(\7\25\2\2()\5\6\4\2).\7\27\2\2*+\5\26"+
		"\f\2+,\7\3\2\2,/\3\2\2\2-/\5\36\20\2.*\3\2\2\2.-\3\2\2\2/B\3\2\2\2\60"+
		"\62\5\4\3\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64"+
		"\65\3\2\2\2\658\7\3\2\2\66\67\7\25\2\2\679\5\6\4\28\66\3\2\2\289\3\2\2"+
		"\29:\3\2\2\2:=\7\27\2\2;>\5\26\f\2<>\5\36\20\2=;\3\2\2\2=<\3\2\2\2>?\3"+
		"\2\2\2?@\7\3\2\2@B\3\2\2\2A$\3\2\2\2A\'\3\2\2\2A\61\3\2\2\2B\3\3\2\2\2"+
		"CD\7\34\2\2DG\7)\2\2EF\7\35\2\2FH\7)\2\2GE\3\2\2\2GH\3\2\2\2HU\3\2\2\2"+
		"IR\7\20\2\2JO\5\b\5\2KL\7\5\2\2LN\5\b\5\2MK\3\2\2\2NQ\3\2\2\2OM\3\2\2"+
		"\2OP\3\2\2\2PS\3\2\2\2QO\3\2\2\2RJ\3\2\2\2RS\3\2\2\2ST\3\2\2\2TV\7\21"+
		"\2\2UI\3\2\2\2UV\3\2\2\2Vb\3\2\2\2W_\7\6\2\2XY\5\22\n\2YZ\7\3\2\2Z\\\3"+
		"\2\2\2[X\3\2\2\2\\]\3\2\2\2][\3\2\2\2]^\3\2\2\2^`\3\2\2\2_[\3\2\2\2_`"+
		"\3\2\2\2`a\3\2\2\2ac\7\7\2\2bW\3\2\2\2bc\3\2\2\2c\5\3\2\2\2de\5\20\t\2"+
		"ef\7\3\2\2fh\3\2\2\2gd\3\2\2\2hi\3\2\2\2ig\3\2\2\2ij\3\2\2\2j\7\3\2\2"+
		"\2kl\5\24\13\2lm\7)\2\2m\t\3\2\2\2no\5\b\5\2or\7\t\2\2ps\5\26\f\2qs\7"+
		"\'\2\2rp\3\2\2\2rq\3\2\2\2s\13\3\2\2\2tx\7\25\2\2uv\5\n\6\2vw\7\3\2\2"+
		"wy\3\2\2\2xu\3\2\2\2yz\3\2\2\2zx\3\2\2\2z{\3\2\2\2{\r\3\2\2\2|\177\5\24"+
		"\13\2}\177\7\26\2\2~|\3\2\2\2~}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081"+
		"\7)\2\2\u0081\u008a\7\20\2\2\u0082\u0087\5\b\5\2\u0083\u0084\7\5\2\2\u0084"+
		"\u0086\5\b\5\2\u0085\u0083\3\2\2\2\u0086\u0089\3\2\2\2\u0087\u0085\3\2"+
		"\2\2\u0087\u0088\3\2\2\2\u0088\u008b\3\2\2\2\u0089\u0087\3\2\2\2\u008a"+
		"\u0082\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u008c\3\2\2\2\u008c\u0090\7\21"+
		"\2\2\u008d\u008e\5\f\7\2\u008e\u008f\7\27\2\2\u008f\u0091\3\2\2\2\u0090"+
		"\u008d\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0095\5\26"+
		"\f\2\u0093\u0095\5\36\20\2\u0094\u0092\3\2\2\2\u0094\u0093\3\2\2\2\u0095"+
		"\17\3\2\2\2\u0096\u0099\5\n\6\2\u0097\u0099\5\16\b\2\u0098\u0096\3\2\2"+
		"\2\u0098\u0097\3\2\2\2\u0099\21\3\2\2\2\u009a\u009b\5\16\b\2\u009b\23"+
		"\3\2\2\2\u009c\u009d\t\2\2\2\u009d\25\3\2\2\2\u009e\u00a1\5\30\r\2\u009f"+
		"\u00a0\t\3\2\2\u00a0\u00a2\5\26\f\2\u00a1\u009f\3\2\2\2\u00a1\u00a2\3"+
		"\2\2\2\u00a2\27\3\2\2\2\u00a3\u00a6\5\32\16\2\u00a4\u00a5\t\4\2\2\u00a5"+
		"\u00a7\5\30\r\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\31\3\2\2"+
		"\2\u00a8\u00ab\5 \21\2\u00a9\u00aa\t\5\2\2\u00aa\u00ac\5 \21\2\u00ab\u00a9"+
		"\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\33\3\2\2\2\u00ad\u00ae\7\22\2\2\u00ae"+
		"\u00af\5\26\f\2\u00af\u00b0\7\23\2\2\u00b0\u00b1\7\6\2\2\u00b1\u00b2\5"+
		"\36\20\2\u00b2\u00b8\7\7\2\2\u00b3\u00b4\7\24\2\2\u00b4\u00b5\7\6\2\2"+
		"\u00b5\u00b6\5\36\20\2\u00b6\u00b7\7\7\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b3"+
		"\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00c4\3\2\2\2\u00ba\u00bb\7)\2\2\u00bb"+
		"\u00be\7\t\2\2\u00bc\u00bf\7\'\2\2\u00bd\u00bf\5\26\f\2\u00be\u00bc\3"+
		"\2\2\2\u00be\u00bd\3\2\2\2\u00bf\u00c1\3\2\2\2\u00c0\u00c2\7\3\2\2\u00c1"+
		"\u00c0\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c4\3\2\2\2\u00c3\u00ad\3\2"+
		"\2\2\u00c3\u00ba\3\2\2\2\u00c4\35\3\2\2\2\u00c5\u00c7\5\34\17\2\u00c6"+
		"\u00c5\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2"+
		"\2\2\u00c9\37\3\2\2\2\u00ca\u00cc\7\13\2\2\u00cb\u00ca\3\2\2\2\u00cb\u00cc"+
		"\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00fc\7(\2\2\u00ce\u00d0\7&\2\2\u00cf"+
		"\u00ce\3\2\2\2\u00cf\u00d0\3\2\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00fc\t\6"+
		"\2\2\u00d2\u00d3\7\20\2\2\u00d3\u00d4\5\26\f\2\u00d4\u00d5\7\21\2\2\u00d5"+
		"\u00fc\3\2\2\2\u00d6\u00d8\t\7\2\2\u00d7\u00d6\3\2\2\2\u00d7\u00d8\3\2"+
		"\2\2\u00d8\u00d9\3\2\2\2\u00d9\u00fc\7)\2\2\u00da\u00fc\5\"\22\2\u00db"+
		"\u00dc\7\22\2\2\u00dc\u00dd\5\26\f\2\u00dd\u00de\7\23\2\2\u00de\u00df"+
		"\7\6\2\2\u00df\u00e0\5\26\f\2\u00e0\u00e6\7\7\2\2\u00e1\u00e2\7\24\2\2"+
		"\u00e2\u00e3\7\6\2\2\u00e3\u00e4\5\26\f\2\u00e4\u00e5\7\7\2\2\u00e5\u00e7"+
		"\3\2\2\2\u00e6\u00e1\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00fc\3\2\2\2\u00e8"+
		"\u00e9\7\36\2\2\u00e9\u00f6\7)\2\2\u00ea\u00f3\7\20\2\2\u00eb\u00f0\5"+
		"\26\f\2\u00ec\u00ed\7\5\2\2\u00ed\u00ef\5\26\f\2\u00ee\u00ec\3\2\2\2\u00ef"+
		"\u00f2\3\2\2\2\u00f0\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1\u00f4\3\2"+
		"\2\2\u00f2\u00f0\3\2\2\2\u00f3\u00eb\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4"+
		"\u00f5\3\2\2\2\u00f5\u00f7\7\21\2\2\u00f6\u00ea\3\2\2\2\u00f6\u00f7\3"+
		"\2\2\2\u00f7\u00fc\3\2\2\2\u00f8\u00f9\7)\2\2\u00f9\u00fa\7\37\2\2\u00fa"+
		"\u00fc\5\"\22\2\u00fb\u00cb\3\2\2\2\u00fb\u00cf\3\2\2\2\u00fb\u00d2\3"+
		"\2\2\2\u00fb\u00d7\3\2\2\2\u00fb\u00da\3\2\2\2\u00fb\u00db\3\2\2\2\u00fb"+
		"\u00e8\3\2\2\2\u00fb\u00f8\3\2\2\2\u00fc!\3\2\2\2\u00fd\u00fe\7)\2\2\u00fe"+
		"\u0107\7\20\2\2\u00ff\u0104\5\26\f\2\u0100\u0101\7\5\2\2\u0101\u0103\5"+
		"\26\f\2\u0102\u0100\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0102\3\2\2\2\u0104"+
		"\u0105\3\2\2\2\u0105\u0108\3\2\2\2\u0106\u0104\3\2\2\2\u0107\u00ff\3\2"+
		"\2\2\u0107\u0108\3\2\2\2\u0108\u0109\3\2\2\2\u0109\u010a\7\21\2\2\u010a"+
		"#\3\2\2\2).\638=AGORU]_birz~\u0087\u008a\u0090\u0094\u0098\u00a1\u00a6"+
		"\u00ab\u00b8\u00be\u00c1\u00c3\u00c8\u00cb\u00cf\u00d7\u00e6\u00f0\u00f3"+
		"\u00f6\u00fb\u0104\u0107";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}