import com.project.deeplearningengine.DBN;;
public class DLTest {
	public static void main(String[] args) {
		int[][] train_X = {
         		{0,0,0,0,0,1,1,1,1,1,1,0},
                 {1,0,0,1,1,0,1,1,1,1,0,0},
                 {0,0,0,0,0,0,1,1,1,1,1,0},
                 {0,1,1,0,0,0,0,1,0,0,0,1}
                 /*{0,0,0,1,0,1},
                 {0,0,0,1,0,1},
                 {1,1,1,1,0,0},
                 {1,0,1,1,0,1},
                 {0,0,0,1,0,1}*/
         };

         int[][] train_Y = {
         		 {1, 0},
                  {0, 1},
                  {0, 1},
                  {0, 1}
                  /*{1, 0},
                  {1, 0},
                  {0, 1},
                  {0, 1},
                  {1, 0}*/
         };
         int[][] test_X = {
         		{1,1,1,1,0,0},
         		{0,0,0,1,0,1}
         };
         DBN.deepEngine(train_X, train_Y, test_X);
	}

}
