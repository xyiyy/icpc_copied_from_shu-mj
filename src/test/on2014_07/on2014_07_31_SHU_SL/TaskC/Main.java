package test.on2014_07.on2014_07_31_SHU_SL.TaskC;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/test/on2014_07/on2014_07_31_SHU_SL/TaskC/TaskC.task"))
			Assert.fail();
	}
}
