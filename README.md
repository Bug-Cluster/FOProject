this is second version of project.

WARNING! when we started writing code, we thought posibility of wrong code input should have
   been accounted after Q&A we realized it was not needed so code looks messy as we stopped
   writing safety checks. (SwiftSwap exeption is thrown when code input is incorrect)

Code Tokenisation:
	1) "\n" are removed form string (new line spaces)
	2) code execution starts, pointer points to position in code string
	3) pointer stops untill it detects characters: (';' '{' '}')
	4) it cuts string from previous to current encounter
	5) Tokenisaes line by operation signs.
	6) Starts execution depending on first instruction

Variable support:
	-name should not contain any operation signs (!, -, >, =)
	-can be asigned by (var) or (let)
	-only intigers can be stored 
		(first verison supports int float string by storeing them in object,
		first part of v2 was operating on float, but because of complication 
		we switched it into int)
	-variables can be asigned normaly.
	-variables are saved in "Variables" & "UpperVariables" which are hashmap map<name, value>
		(why they are split is explained in recursion)

Operations:
	-basic operations can be performed (+, -, *, /, %)
	-operations are not executed by arithmetic order but by order of its writen in.
	-operations can be prioritised by "(x)" we call it operational depth, and its
	   recusive so multiple prioritised operation within "(x)" is possibe
	-operations still run if there are no space between operation sings.
	-simple operations like ++ and -- is supported
	-operations dont run in "if(x)" & "while(x)" loops, they accipt static inputs

Print:
	-print can print out any input. if input exists in variabel, it outputs its value,
	   otherwise it outputs input as string
	-spaces or operations on input are not supported.

Recursion(if & while):
	-Supported logics: (==, !=, >=, >, <=, <)
	-"Variables" contains local variables, where new variables are stored in
	-"UpperVariables" contains global variables, new vars cant be added to it, but existing
	   variabless can be read or written.
	-in main loop "UpperVariables" is set null (main executor indicator), when new sub-code
	   is executed, in imports its values in it and creates new map for sub-loop, when 
	   sub-loop finishes, its values are deleted, when sub-loops calles its own loop, its
	   "UpperValues" is no longer null (indicating its not main executor), it imports
	   main executor variables and its own variabels in, new variables created will also
	   exist in its parent. so only global variables are only in main executor.
	-creating new variable that already exists in "UpperVariables" is supported.
	-child loop's child does not check if variable exists in its parent, so it might
	   overwrite variables if not written correctly.

Debug:
	-"Debug!ListVar" is custom command that lists local variables for debuging.
	-in code debug(x) is used to quickly display needed info, we write it as function so
	   it would be easy to use for different type of variables and when we finish, it
	   would be easy to disable them. (thats why they are // out)
	-"DebugCode" tests core functionalitys for testing purpuses. (it might be missing some)
