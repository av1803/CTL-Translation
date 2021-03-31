package ctl;

import parser.CTLBaseVisitor;
import parser.CTLParser.AndContext;
import parser.CTLParser.AtomicPropositionContext;
import parser.CTLParser.BracketContext;
import parser.CTLParser.ExistsAlwaysContext;
import parser.CTLParser.ExistsEventuallyContext;
import parser.CTLParser.ExistsNextContext;
import parser.CTLParser.ExistsUntilContext;
import parser.CTLParser.FalseContext;
import parser.CTLParser.ForAllAlwaysContext;
import parser.CTLParser.ForAllEventuallyContext;
import parser.CTLParser.ForAllNextContext;
import parser.CTLParser.ForAllUntilContext;
import parser.CTLParser.IffContext;
import parser.CTLParser.ImpliesContext;
import parser.CTLParser.NotContext;
import parser.CTLParser.OrContext;
import parser.CTLParser.RootContext;
import parser.CTLParser.TrueContext;

/**
 * 
 * This class serves as the main interface to the underlying visitor-design pattern
 * generated by Antlr that is used to translate the CTL parse tree to an abstract
 * syntax tree. 
 *
 */

public class Generator extends CTLBaseVisitor<Formula> {

	/**
	 * Visits a root node of the parse tree.
	 * @return	The first formula node of the parse tree
	 */
	@Override
	public Formula visitRoot(RootContext ctx) {
		return visit(ctx.formula(0));
	}

	/**
	 * Visits a bracket node in the parse tree.
	 * @return	The formula node contained within the brackets
	 */
	@Override
	public Formula visitBracket(BracketContext ctx) {
		return visit(ctx.formula());
	}

	/**
	 * Visits the subformula node of a ForAllAlways node in the parse tree. 
	 * @return	A {@code ForAll} instance of an {@code Always} instance of the
	 * 			inner subformula
	 */
	@Override
	public Formula visitForAllAlways(ForAllAlwaysContext ctx) {
		StateFormula inner = (StateFormula) visit(ctx.formula());
		return new ForAll(new Always(inner));
	}

	/**
	 * Visits the left and right subformula nodes of an Or node in the parse tree. 
	 * @return	An {@code Or} instance containing the left and right subformulas
	 */
	@Override
	public Formula visitOr(OrContext ctx) {
		StateFormula left = (StateFormula) visit(ctx.formula(0));
		StateFormula right = (StateFormula) visit(ctx.formula(1));
		return new Or(left, right);
	}

	/**
	 * Visits the left and right subformula nodes of an Iff node in the parse tree. 
	 * @return	An {@code Iff} instance containing the left and right subformulas
	 */
	@Override
	public Formula visitIff(IffContext ctx) {
		StateFormula left = (StateFormula) visit(ctx.formula(0));
		StateFormula right = (StateFormula) visit(ctx.formula(1));	
		return new Iff(left, right);
	}

	/**
	 * Visits a True terminal node in the parse tree.
	 * @return	A {@code True} instance
	 */
	@Override
	public Formula visitTrue(TrueContext ctx) {
		return new True();
	}

	/**
	 * Visits a False terminal node in the parse tree.
	 * @return	A {@code False} instance
	 */
	@Override
	public Formula visitFalse(FalseContext ctx) {
		return new False();
	}

	/**
	 * Visits the subformula node of an ExistsEventually node in the parse tree. 
	 * @return	An {@code Exists} instance of an {@code Eventually} instance of the
	 * 			inner subformula
	 */
	@Override
	public Formula visitExistsEventually(ExistsEventuallyContext ctx) {
		StateFormula inner = (StateFormula) visit(ctx.formula());
		return new Exists(new Eventually(inner));
	}

	/**
	 * Visits an AtomicProposition terminal node in the parse tree.
	 * @return	An {@code AtomicProposition} instance containing a string representation
	 * 			of an atomic proposition as defined by the grammar
	 */
	@Override
	public Formula visitAtomicProposition(AtomicPropositionContext ctx) {
		return new AtomicProposition(ctx.ATOMIC_PROPOSITION().toString());
	}

	/**
	 * Visits the subformula node of a ForAllEventually node in the parse tree. 
	 * @return	A {@code ForAll} instance of an {@code Eventually} instance of the
	 * 			inner subformula
	 */
	@Override
	public Formula visitForAllEventually(ForAllEventuallyContext ctx) {
		StateFormula inner = (StateFormula) visit(ctx.formula());
		return new ForAll(new Eventually(inner));
	}

	/**
	 * Visits the subformula node of a Not node in the parse tree.
	 * @return	A {@code Not} instance of the inner subformula
	 */
	@Override
	public Formula visitNot(NotContext ctx) {
		StateFormula inner = (StateFormula) visit(ctx.formula());
		return new Not(inner);
	}

	/**
	 * Visits the left and right subformula nodes of a ForAllUntil node in the parse tree. 
	 * @return	A {@code ForAll} instance of an {@code Until} instance containing the 
	 * 			left and right subformulas
	 */
	@Override
	public Formula visitForAllUntil(ForAllUntilContext ctx) {
		StateFormula left = (StateFormula) visit(ctx.formula(0));
		StateFormula right = (StateFormula) visit(ctx.formula(1));	
		return new ForAll(new Until(left, right));
	}

	/**
	 * Visits the left and right subformula nodes of an Implies node in the parse tree. 
	 * @return	An {@code Implies} instance containing the left and right subformulas
	 */
	@Override
	public Formula visitImplies(ImpliesContext ctx) {
		StateFormula left = (StateFormula) visit(ctx.formula(0));
		StateFormula right = (StateFormula) visit(ctx.formula(1));
		return new Implies(left, right);
	}

	/**
	 * Visits the subformula node of a ForAllNext node in the parse tree. 
	 * @return	A {@code ForAll} instance of a {@code Next} instance of the
	 * 			inner subformula
	 */
	@Override
	public Formula visitForAllNext(ForAllNextContext ctx) {
		StateFormula inner = (StateFormula) visit(ctx.formula());
		return new ForAll(new Next(inner));
	}

	/**
	 * Visits the left and right subformula nodes of an And node in the parse tree. 
	 * @return	An {@code And} instance containing the left and right subformulas
	 */
	@Override
	public Formula visitAnd(AndContext ctx) {
		StateFormula left = (StateFormula) visit(ctx.formula(0));
		StateFormula right = (StateFormula) visit(ctx.formula(1));
		return new And(left, right);
	}

	/**
	 * Visits the subformula node of an ExistsAlways node in the parse tree. 
	 * @return	An {@code Exists} instance of an {@code Always} instance of the
	 * 			inner subformula
	 */
	@Override
	public Formula visitExistsAlways(ExistsAlwaysContext ctx) {
		StateFormula inner = (StateFormula) visit(ctx.formula());
		return new Exists(new Always(inner));		
	}

	/**
	 * Visits the left and right subformula nodes of an ExistsUntil node in the parse tree. 
	 * @return	An {@code Exists} instance of an {@code Until} instance containing the
	 * 			left and right subformulas
	 */
	@Override
	public Formula visitExistsUntil(ExistsUntilContext ctx) {
		StateFormula left = (StateFormula) visit(ctx.formula(0));
		StateFormula right = (StateFormula) visit(ctx.formula(1));
		return new Exists(new Until(left, right));
	}

	/**
	 * Visits the subformula node of an ExistsNext node in the parse tree. 
	 * @return	An {@code Exists} instance of an {@code Next} instance of the
	 * 			inner subformula
	 */
	@Override
	public Formula visitExistsNext(ExistsNextContext ctx) {
		StateFormula inner = (StateFormula) visit(ctx.formula());
		return new Exists(new Next(inner));
	}

}
