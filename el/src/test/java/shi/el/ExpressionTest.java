package shi.el;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;


public class ExpressionTest{
	
	@Test
	public void testLiteral() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'");
		String msg = exp.getValue(String.class);
		assertEquals(msg,"Hello World");
	}
	
	@Test
	public void testMethod() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'.length()");
		Integer length = exp.getValue(Integer.class);
		assertEquals(length,new Integer(11));
	}
	
	@Test
	public void testMathematical() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("100 * 2");
		Integer length = exp.getValue(Integer.class);
		assertEquals(length,new Integer(200));
	}
	
	@Test
	public void testContnxt() {
		Person person = new Person();
		person.setName("test");
		StandardEvaluationContext context = new StandardEvaluationContext(person);
		
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("name");
		String name = exp.getValue(context,String.class);
		assertEquals(name,"test");
		
		exp = parser.parseExpression("name == 'test'");
		Boolean equal = exp.getValue(context,Boolean.class);
		assertEquals(equal,Boolean.TRUE);
	}
	
	
	
}
