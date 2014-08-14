package test.on2014_08.on2014_08_01_SHU_8_1.TaskC;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/test/on2014_08/on2014_08_01_SHU_8_1/TaskC/TaskC.task"))
			Assert.fail();
	}
}
