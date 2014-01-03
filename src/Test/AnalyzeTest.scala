package Test

import org.junit._
import com.Elite.DslObject
import javax.naming.Name
import com.Elite.Analyzer

class AnalyzeTest extends com.Elite.Analyzer {
  
   // output (1,+2 cols are blank for checklist)
  //-----------------------------------
   //read the output
   //only select the lines which are checked
    //output report
  
  
     @Test def ReadDslTest{
    	 		import com.Kei._
    	 		var dslAssmbly = new Excel().ReadDomainSpecificFile(13,0, logDsl , 0)
//    	 		for(key <- dslAssmbly.keys){
//    	 				println(key)
//    	 		}
     }
  
      val logFile = "/Users/keitaroemotion/dev/garage/logelite/sample.log"
      val logDsl = "/Users/keitaroemotion/dev/garage/logelite/logdsl.xls"
        
           
    @Test def ReadTextFileTest{
    		var file = logFile
//    		 for(line <- scala.io.Source.fromFile(file).getLines){
//    			 	for(col <-  line.split(" ")){
//    			 		    print(col+"	")
//    			 	}
//    			 	println
//    		 } 
     } 
      
      @Test def FormatTest{
    	  	  println(Format("%s %s %s", List("mm","aa","ss")))
      }
      
      
      
      @Test def ReadDslAndTextFileTest{
         var file = logFile

          import java.io._
          var totalLog = List[List[String]]()
		  for(file <- new File(infileDir).listFiles.filter(_.getName.endsWith(".log"))){
			  	totalLog = totalLog.:::(ReadLogFile(file.getAbsolutePath,logDsl))
		  }
         
         new com.Kei.Excel().WriteFile(infileDir, 1, totalLog, "/destFile.xls")
          java.awt.Desktop.getDesktop().open(new File(outFile));
      }
      
      val infileDir = "/Users/keitaroemotion/dev/garage/logelite/20130103"
      val outFile = infileDir+ "/destFile.xls"
    	   
      
}