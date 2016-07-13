// Generated from D:/Programmierprojekte/CodeGenerator Breakout/src/CodeGenerator/grammar\Level.g4 by ANTLR 4.5.3
package CodeGenerator.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LevelParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Separator=1, LineBreak=2, SimpleValue=3, BracketOpen=4, BracketClose=5, 
		WS=6;
	public static final int
		RULE_file = 0, RULE_tupel = 1;
	public static final String[] ruleNames = {
		"file", "tupel"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, "'('", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "Separator", "LineBreak", "SimpleValue", "BracketOpen", "BracketClose", 
		"WS"
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
	public String getGrammarFileName() { return "Level.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LevelParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class FileContext extends ParserRuleContext {
		public List<TupelContext> tupel() {
			return getRuleContexts(TupelContext.class);
		}
		public TupelContext tupel(int i) {
			return getRuleContext(TupelContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LevelListener ) ((LevelListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LevelListener ) ((LevelListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LevelVisitor ) return ((LevelVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(7);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BracketOpen) {
				{
				{
				setState(4);
				tupel();
				}
				}
				setState(9);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class TupelContext extends ParserRuleContext {
		public TerminalNode BracketOpen() { return getToken(LevelParser.BracketOpen, 0); }
		public TerminalNode BracketClose() { return getToken(LevelParser.BracketClose, 0); }
		public TerminalNode LineBreak() { return getToken(LevelParser.LineBreak, 0); }
		public TerminalNode EOF() { return getToken(LevelParser.EOF, 0); }
		public List<TerminalNode> SimpleValue() { return getTokens(LevelParser.SimpleValue); }
		public TerminalNode SimpleValue(int i) {
			return getToken(LevelParser.SimpleValue, i);
		}
		public List<TerminalNode> Separator() { return getTokens(LevelParser.Separator); }
		public TerminalNode Separator(int i) {
			return getToken(LevelParser.Separator, i);
		}
		public TupelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tupel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LevelListener ) ((LevelListener)listener).enterTupel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LevelListener ) ((LevelListener)listener).exitTupel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LevelVisitor ) return ((LevelVisitor<? extends T>)visitor).visitTupel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TupelContext tupel() throws RecognitionException {
		TupelContext _localctx = new TupelContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_tupel);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			match(BracketOpen);
			setState(14);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Separator || _la==SimpleValue) {
				{
				{
				setState(11);
				_la = _input.LA(1);
				if ( !(_la==Separator || _la==SimpleValue) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				}
				setState(16);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(17);
			match(BracketClose);
			setState(18);
			_la = _input.LA(1);
			if ( !(_la==EOF || _la==LineBreak) ) {
			_errHandler.recoverInline(this);
			} else {
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\b\27\4\2\t\2\4\3"+
		"\t\3\3\2\7\2\b\n\2\f\2\16\2\13\13\2\3\3\3\3\7\3\17\n\3\f\3\16\3\22\13"+
		"\3\3\3\3\3\3\3\3\3\2\2\4\2\4\2\4\4\2\3\3\5\5\3\3\4\4\26\2\t\3\2\2\2\4"+
		"\f\3\2\2\2\6\b\5\4\3\2\7\6\3\2\2\2\b\13\3\2\2\2\t\7\3\2\2\2\t\n\3\2\2"+
		"\2\n\3\3\2\2\2\13\t\3\2\2\2\f\20\7\6\2\2\r\17\t\2\2\2\16\r\3\2\2\2\17"+
		"\22\3\2\2\2\20\16\3\2\2\2\20\21\3\2\2\2\21\23\3\2\2\2\22\20\3\2\2\2\23"+
		"\24\7\7\2\2\24\25\t\3\2\2\25\5\3\2\2\2\4\t\20";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}