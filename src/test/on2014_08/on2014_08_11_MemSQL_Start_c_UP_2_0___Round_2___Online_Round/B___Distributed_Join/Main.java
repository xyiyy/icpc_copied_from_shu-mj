package test.on2014_08.on2014_08_11_MemSQL_Start_c_UP_2_0___Round_2___Online_Round.B___Distributed_Join;

import net.egork.chelper.tester.NewTester;

import org.junit.Assert;
import org.junit.Test;

public class Main {
	@Test
	public void test() throws Exception {
		if (!NewTester.test("src/test/on2014_08/on2014_08_11_MemSQL_Start_c_UP_2_0___Round_2___Online_Round/B___Distributed_Join/B - Distributed Join.task"))
			Assert.fail();
	}
}
