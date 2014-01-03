package com.Elite

import com.Kei._


class Reporter {
			def Write(dir:String,outfile:String){
					new Excel().WriteFile(dir, 0, new Excel().ReadLogResult(outfile,10,0), "report"+new DateX().popDateShort)
			}
}