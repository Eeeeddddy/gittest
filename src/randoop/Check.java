package randoop;

import java.io.Serializable;

/**
 * A correctness check performed on some aspect of a unit test's execution.
 * Examples include assertions, calls to invariant or "checkRep" methods,
 * or try-catch clauses that check for expected exceptions.
 * 
 * <p>
 * 
 * <code>Check</code> objects are inserted as a decorations on
 * specific indices of an {@link ExecutableSequence}. Thus, a check is
 * always associated with a specific index in a sequence. A check at
 * index i means that the check is to performed after statement i
 * finishes executing.
 * 
 * <p>
 *
 * A check implements two methods that specify the code to be *
 * emitted before and/or after a statement in a <code>Sequence</code>
 * is executed.
 *
 * <p>
 * 
 * A check may require some code to be emitted before and/or after the
 * statement is printed. For example, a check for checking that
 * <code>x</code> is not null after the statement "<code>Foo x =
 * m()</code>" is executed might emit the assertion code
 * "<code>assertNotNull(x);</code>", and would do so after the
 * statement is printed. As a second example, a check for checking
 * that an expected exception is thrown by a statement would need to
 * emit something like "<code>try {</code>" before the statement, and
 * the catch clause after the statement.
 * 
 * <p>
 * 
 * Checks should be Serializable so that an ExecutableSequence can be
 * serialized along with its associated checks.
 * 
 */
public interface Check extends Serializable {

  /**
   * Returns a string of Java source code to be emitted before a
   * statement containing this check.
   */
  String toCodeStringPreStatement();

  /**
   * Returns a string of Java source code to be emitted after a
   * statement containing this check.
   */
  String toCodeStringPostStatement();

  /**
   * Returns a short string that can be used to uniquely identify
   * this check.
   */
  String get_value();
  
  /**
   * Evaluates this check on the given unfolding execution of a sequence,
   * returning <code>true</code> if the check succeeded, and <code>false</code>
   * otherwise.
   */
  boolean evaluate(Execution execution);
  
}
