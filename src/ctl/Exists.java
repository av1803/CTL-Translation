package ctl;

/**
 * 
 * This class represents an existential quantification of a path formula.
 * 
 */

public class Exists extends StateFormula {

	PathFormula inner;	// the inner PathFormula that this Exists Stateformula refers to
	
	/**
	 * Initializes the {@code inner} formula to the <i>PathFormula</i>
	 * provided to this constructor.
	 * @param	formula		A path formula
	 */
	public Exists(PathFormula formula) {
		this.inner = formula;
	}

	/**
	 * Checks if this Formula is equal another object. The checks adhere to the
	 * Equals contract in Java.
	 * @param	obj		The other object that this Formula is being compared to.
	 * @return	Returns <i>true</i> if the two objects are equal, and <i>false</i>
	 * 			if otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exists other = (Exists) obj;
		if (inner == null) {
			if (other.inner != null)
				return false;
		} else if (!inner.equals(other.inner))
			return false;
		return true;
	}

	/**
	 * Returns the {@code hashCode} of this <i>Exists</i> formula.
	 * @return	An integer value representing this object's {@code hashCode}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inner == null) ? 0 : inner.hashCode());
		return result;
	}

	/**
	 * Returns a string representation of this <i>Exists</i> formula.
	 * @return A pretty print version of this formula. The {@code inner} formula
	 * 		   is enclosed in brackets.
	 */
	@Override
	public String toString() {
		return "E(" + inner.toString() + ")";
	}
	
	/**
	 * Returns the {@code inner} PathFormula of this <i>Exists</i> formula. This was
	 * added in consequence of the changes made to the Parser in Project 1.
	 * @return	The {@code inner} field of this formula.
	 */
	public PathFormula getInner() {
		return this.inner;
	}

	@Override
	public StateFormula existentialNormalForm() {
		return new Exists(inner.existentialNormalForm());
	}

	@Override
	public StateFormula positiveNormalForm() {
		return new Exists(inner.positiveNormalForm());
	}
}
