//package com.main.project


//import scala.util.parsing.combinator._
//import scala.io.StdIn.readLine


abstract class Tree


case class S(c: Tree) extends Tree
//alternation case class
case class E(l: Tree, c: Option[String], r: Option[Tree]) extends Tree
////concatenation
case class T(l: Tree, r: Option[Tree]) extends Tree
//optional
case class F(l: Option[Tree], r: Option[String]) extends Tree
//return to alternation
case class A(lp: Option[String], c: Tree, rp: Option[String]) extends Tree
//Constants
case class C_Cons(c: String) extends Tree

/*
//S  -: E$
//E  -: T '|' E | T
//T  -: F T | F
//F  -: A '?' | A
//A  -: C | '(' E ')'
*/

object sreduce extends Combinators {
  override def skipWhitespace = false
// global index
  var idx = 0
//match string variable
  var v:String = _
//scan string variable
  var p:String = _
//backup index for alternations
  var b_char= -1
//backup index for optional chars
  var b2_char= -1

//method to backup index
  def backup_alt() : Unit= {
    b_char= idx  
}
  //restore the old index
    def backup_Rev(): Unit= {
    idx = b_char
}

def s_match(t: Tree): Boolean = t match {

    case S(c) => s_match(c)
//back up the index, just in case they reu
    case E(l, Some(c), Some(r) ) => {b_char=idx; s_match(l) || s_match(r) } //; backup_alt()
//    case E(l, Some(c), Some(r) ) => {b_char=idx; if (s_match(l) == false) idx= b_char; s_match(r) || s_match(r); } //; backup_alt()

    case E(l, None, None) => s_match(l)

    case T(l, Some(r)) => s_match(l) && s_match(r)

    case T (l, None) => s_match(l)

//    case F (Some(l), Some(r)) => {b2_char = idx;s_match(l)}
    case F (Some(l), Some(r) ) => {b_char=idx; if (s_match(l) == false) idx= b_char; return true; }

    case F (Some(l), None) => {b2_char = idx;s_match(l)}

    case F (None, None) => {b2_char = idx; true}

    case A (Some(lp), c, Some(rp)) => s_match(c)

    case A (None, c, None) => s_match(c)


//check if the index surpasses the string length, (this is the case if there's an option)
//    case C_Cons (c) if idx  > v.length-1 => {idx= b2_char; idx +=1; false;}

//regular case. If the the pattern tree char and match char are equal, increment and return true
    case C_Cons(c) if  c.equals(String.valueOf(v.charAt(idx))) => {idx += 1; true}

//check if theres a dot in the pattern, AS WELL AS the match string fails the case
    case C_Cons(c) if c.equals(".") && c.equals(String.valueOf(v.charAt(idx))) == false => {idx += 1; true}

//makes sure that the string returns to the
    case C_Cons (c) if c.equals(String.valueOf(v.charAt(idx))) ==false && idx != 0=> {backup_Rev(); false;}

//default case non-match, if fails, return false
    case C_Cons (c) if c.equals(String.valueOf(v.charAt(idx))) ==false =>  false
}

  def main(args: Array[String]) {
    print("pattern? ")
//create input scanner
    val e_s = scala.io.StdIn.readLine()
    p = e_s
//create the pattern tree from the combinators and case classes
    val exp:Tree = parseAll(e, p).get

    print("string? ")
    val kb = scala.io.StdIn.readLine()
//assignment to the global variable v
    v = kb
//supply the string to the pattern matchh. If true, and the index reaches the end of the string,then it matches.
// Else, it doesn't match.
    if (s_match(exp) && idx ==v.length) {
      println("match")
} else {
      println("no match")
}


//      val exp:Tree = parseAll(e, "ab.|c").get
//      println(exp)
//      println(s_match(exp))

}
}
class Combinators extends JavaTokenParsers {
override def skipWhitespace = false
    def s: Parser[Tree] = e ^^{ case c => S(c)}

    def e: Parser[Tree] = t ~ des ~ e ^^ { case l ~ c ~ r => E(l, Some(c), Some(r))} | t ^^{ case l=> E(l , None, None)}

    def t: Parser [Tree] = f ~ t ^^ {case l ~ r => T(l, Some(r))} | f ^^{case l => T(l, None)}

    def f: Parser[Tree] = a ~ opt ^^ {case l ~ r => F(Some(l),Some(r))} | a ^^ {case l  => F(Some(l), None) }

  def a: Parser [Tree] = lp ~ e ~ rp  ^^ {case lp ~ c ~ rp => A(Some(lp), c, Some(rp))} | chose ^^ {case c => A(None ,c, None)}

def chose: Parser[Tree] = cons ^^{case c => C_Cons(c)}

    def rp [Tree] = ")"
    def lp [Tree] ="("
    def des [Tree]=  "|"
    def opt [Tree] = "?"
//    def dot [Tree] = " "
    def cons [Tree] = "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"|
      "a" | "b" | "c"| "d" | "e"| "f"| "g"| "h"| "i"| "j"| "k"| "l"|
      "m"| "n"| "o"| "p"| "q"| "r"| "s" | "t"| "u"| "v"| "w" | "x" | "y" | "z" | "." | " "
  }

