package com.Elite

class Analyzer {
	var log = new com.Kei.LogX
	def IndexesToValues(indexes:List[Int], values:Array[String]):List[String]={
            log.pln
    		  	var list = List[String]()
    		  	 for(i <- indexes){
    		  		 	list = list.+:(values(i))
    		  	 }
    		  	 list
      }
	
    def Format(text:String, args:List[String]):String={
    		  	 log.pln
			  var i = 0
			  var tmp = ""
			  for(elem <- text.split("%s")){
			      var d = ""
			      if(args.size > i){d = args(i)}
				  tmp += elem+d
				  i = i +1
			  } 
			  tmp
	}  
      
      def popFormattedValue(command:DslObject, lineArray:Array[String]):String={
    		  	log.pln
    	  		Format(command.formatTemplate, IndexesToValues(command.indexInFile, lineArray))
      }
      
      def pop(lineArray:Array[String], command:DslObject):String={
    		  log.pln
    		  try{
    			  lineArray(command.indexInFile(0))
    		  }catch{
    		    case e:Exception => ""
    		  }
      }
      
	def selectLog(path:String):String={
    		  	  var file = new java.io.File(path).getName
    		  	  if(file.startsWith("moon"))  
    		  		  return "@200"
    		  	  if(file.startsWith("mars"))  
    		  		  return "@201"
	  		  if(file.startsWith("jupiter"))  
	  			  return "@203"
	  		  if(file.startsWith("venus"))  
	  			  return "@204"
  			  if(file.startsWith("sample"))  
  				  return "@200"
    		  	  
              throw new Exception ("the file name is not valid")
      }
      
      def ReadLogFile(file:String, logDsl:String):List[List[String]]={
        
        
         var dslObjects =GetDslObject(logDsl,selectLog(file), 0)
         var lists = List[List[String]]()
         
	    	 for(line <- scala.io.Source.fromFile(file).getLines) {
	    			 	var lineConverted = List[String]()
	    			 	var lineArray = line.split(" ")
	    			 	var isLineValid = true
	    			 	
	    			 	for(command <- dslObjects){
	    			 		isLineValid = filter(lineArray, command,isLineValid)
	    			 	    if(isLineValid){
		    			 		if(command.formatTemplate == ""){
		    			 			lineConverted = lineConverted.+:(pop(lineArray,command))
		    			 		 }else{
		    			 			lineConverted = lineConverted.+:(popFormattedValue(command,lineArray))
		    			 		}
	    			 	    }
	    			 	}
	    			 	
	    			 	if(isLineValid){
	    			 		lists = lists.+:(lineConverted)
	    			 	}	
	    		 }
         	lists
      }
 
     def GetDslObject(logDslFile:String, tag:String, sheetNum:Int ):List[DslObject]={
    		  	log.pln
	         var dsls = new com.Kei.Excel().ReadDomainSpecificFile(13,0, logDslFile, sheetNum)(tag)
	   		 var dobj = new DslObject
	         var dslObjects = List[DslObject]()
	         for ( d <- dsls.reverse if(d(1) != "")){
	    			 		dslObjects = dslObjects.+:(dobj.PopObject(d))
	         }
	         dslObjects
      }
      
	def filter(lineArray:Array[String], command:DslObject, isValid:Boolean):Boolean={
    		  //!(Mozilla/*)
    		  	if(!isValid){ return false}
    		  	val value = pop(lineArray,command)
	    	  	if(command.filter(0) != ""){
	    			for(f <- command.filter){
	    				if(f.startsWith("$only:") && !value.startsWith(f.replace("$only:", ""))){
	    						return false
	    				}
	    				if(f.startsWith("!(")){
	    						val condition = f.replace("!(", "").replace(")", "")
	    						if(condition.endsWith("*") && value.startsWith(condition.replace("*", ""))){
	    							return false
	    						}else if (value == condition){
	    							return false
	    						}
	    				}
	    			}
	    		}
	    	  	true
      }
}