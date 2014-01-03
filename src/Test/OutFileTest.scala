package Test


import org.junit._
import org.apache.poi.hssf.usermodel._
import com.Kei._
import com.Elite.Reporter

class OutFileTest extends Reporter{
			@Test def WriteTest{
					Write("/Users/keitaroemotion/dev/",new AnalyzeTest().outFile)
			}
}