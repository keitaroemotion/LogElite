package Test

import org.junit._
import com.Kei._

class FormatTest {
	@Test def GetLogNameTest{
		// Test
	  //$logname	ex%s%s%s.log	$yy,$mm,$dd	
	    var dsl = "$logname	ex%s%s%s.log	$yy,$MM,$dd-1"
	    var dslSplit  = dsl.split('\t')
	    var key = dslSplit(0)		
	    var template = dslSplit(1)
	    var args = dslSplit(2).split(',').toList
	    print("DSL | "+Format(template,args))
	}
  
	@Test def FormatTest1{
		var d = new DateX()
		val year = d.Year
		//println(f"$year%year%s  ismeters tall")
		println(String.format( " %s  ismeters tall","mama"))
		println(String.format( " %s  %s  ismeters tall","mama","hoho"))
		println(String.format( " %s  %s %s  ismeters tall","mama","hoho","nana"))
		println(String.format( " %s  %s %s  %s ismeters tall","2014","12","11","10"))
		println(Format( "attension: %s  %s %s  %s ismeters tall", List("2014","12","11","10")))
	}
	
	def Format(text:String, args:List[String]):String={
	  var i = 0
	  var tmp = ""
	  var date = new DateX  
	  for(elem <- text.split("%s")){
	      var d = ""
	      if(args.size > i){d = args(i)}
	       d match{
	      		case "$yy" => d = date.Year.toString
	      		case "$MM" => d = StringifyDateElement(date.Month)
	      		case "$dd" => d = StringifyDateElement(date.Day)
	      		case "$dd-1" => d = StringifyDateElement(date.Day-1)
	      		case "$hh" => d = StringifyDateElement(date.Hour)
	      		case "$mm" => d = StringifyDateElement(date.Minute)
	      		case "$ss" => d = date.Second.toString
	      		case _ => 
	      }
		  tmp += elem+d
		  i = i +1
	  } 
	  tmp
	}

	@Test def DaysInMonthTest{
			import java.util._
			println("-> "+Calendar.JANUARY)
			var mm = 1
			var yy = 2014
			println("|||> "+popDaysInMonth(yy,mm))
	}
	
	def popPreviousDate(d:DateX):DateX={
			
			d
	}
	
	def popDaysInMonth(yy:Int, mm:Int):Int={
			import java.util._
			(new GregorianCalendar(yy, mm-1, 1)).getActualMaximum(Calendar.DAY_OF_MONTH);
	}
	
	def StringifyDateElement(elem:Int):String={
			var str = elem.toString
			if(str.size == 1){ str = "0"+str}
			str
	}
	
}