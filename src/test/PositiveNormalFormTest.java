package test;

import static ctlform.PositiveTranslation.*;
import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import ctl.Formula;
import ctl.Generator;
import ctlform.PositiveNormalForm;
import ctlform.PositiveTranslation;
import ctlform.RandomFormula;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import parser.CTLLexer;
import parser.CTLParser;

import java.util.ArrayList;

class PositiveNormalFormTest {





	private static Formula getFormula(String in) {
		CharStream input = CharStreams.fromString(in);
		CTLLexer lexer = new CTLLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		CTLParser parser = new CTLParser(tokens);
		ParseTree tree = parser.root();
		Generator generator = new Generator();
		return generator.visit(tree);
	}

	@Test
	void testTranslatedDoubleNegation(){
		String input1 = "! ! True";
		Formula formula1 = getFormula(input1);

		Formula tester = PositiveNormalForm.translate(formula1);

		assertEquals("true", tester.toString());
	}


	@Test
	void testFormulaUnchanged(){

		for(int i = 0; i < 1000; i++) {
			Formula generated = RandomFormula.UntranslatableRandomFormula(10);

			Formula translated = generated.positiveNormalForm();

			assertEquals(translated.toString(), generated.toString());
		}

		for(int i = 0; i < 1000; i++) {
			Formula generated = RandomFormula.translatableENFRandomFormula(10);

			Formula translated = generated.positiveNormalForm();

			assertEquals(translated.toString(), generated.toString());
		}

	}


	@Test
	void testFormulaChanged(){
		for(int i = 0; i < 1000; i++) {
			Formula generated = RandomFormula.translatablePNFRandomFormula(10);
			Formula translated = generated.positiveNormalForm();
			boolean isSame = translated.toString().equals(generated.toString());
			assertEquals(false,isSame);
		}
	}

	@Test
	void testTranslatedNegatedTrue(){
		String input1 = "! True";
		Formula formula1 = getFormula(input1);

		Formula tester = PositiveNormalForm.translate(formula1);

		assertEquals("false", tester.toString());
	}

	@Test
	void testTranslatedNegatedFalse(){
		String input1 = "! false";
		Formula formula1 = getFormula(input1);

		Formula tester = PositiveNormalForm.translate(formula1);

		assertEquals("true", tester.toString());
	}


	@Test
	void testTranslatedNegatedAnd(){
		String input1 = "!!!(!(!(!(Java.lang.Exception))) && !!!Java.lang.Runtime)";
		Formula formula1 = getFormula(input1);

		Formula test = PositiveNormalForm.translate(formula1);

		String expectedOutput = "(Java.lang.Exception)||(Java.lang.Runtime)";
		Formula expected = getFormula(expectedOutput);
		assertEquals(expected, test);
	}

	@Test
	void testTranslatedNegatedForAllNext(){
		String input1 = "!A X True";
		Formula formula1 = getFormula(input1);

		Formula tester = PositiveNormalForm.translate(formula1);

		System.out.println(tester.toString());
	}

	@Test
	void testTranslatedNegatedExistsNext(){
		String input1 = "!E X True";
		Formula formula1 = getFormula(input1);

		Formula tester = PositiveNormalForm.translate(formula1);

		System.out.println(tester.toString());
	}

	//to do
	@Test
	void testTranslatedNegatedExistsUntil(){
		String input1 = "!E(java.lang.Exception U java.lang.Exception)";
		Formula formula1 = getFormula(input1);

		Formula tester = PositiveNormalForm.translate(formula1);

		System.out.println(tester.toString());
	}

	//to do
	@Test
	void testTranslatedNegatedForAllUntil(){
		String input1 = "!A(java.lang.Exception U java.lang.Exception)";
		Formula formula1 = getFormula(input1);
		Formula tester = PositiveNormalForm.translate(formula1);
		System.out.println(tester.toString());
	}


}
