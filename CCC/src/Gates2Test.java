import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

class Gates2Test {

	@Test
	void testParkedPlane() {
		Gates2 ccc =  new Gates2();
		int[] planes = {2,2,3,3,4,4};
		int count = 0;
		for (int plane: planes) {
			if(ccc.parkedPlane(plane)) {
				count++;
			}
			else {
				break;
			}
		}
		assertEquals(count, 3);
	}
	
}
