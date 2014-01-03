package com.Elite

class DslObject {
	var key = ""
	var indexInFile = List[Int]()
	var formatTemplate = ""
	  
	def pop(list:List[String], i:Int):String={
			try{
				list(i)
			}catch{
			  case e:Exception => ""
			}
	}
	
	def popIndex(list:List[String], i:Int):List[Int]={
			try{
				var idx = list(i).trim
				if(!idx.contains(",")){
					List[Int](idx.split('.')(0).toInt)
				}else{
					var l = List[Int]()
					for(d <- idx.split(',')){l = l.+:(d.toInt)}
					l
				}
			}catch{
			  case e:Exception => List[Int](0)
			}
	}
	
	var filter = List[String]()
	
	def PopObject(list:List[String]):DslObject={
			var obj = new DslObject
			obj.key = pop(list,1)
			obj.indexInFile = popIndex(list,2)
			obj.formatTemplate = pop(list,3)
			obj.filter = pop(list,4).split(" ").toList
			obj
	}  
}