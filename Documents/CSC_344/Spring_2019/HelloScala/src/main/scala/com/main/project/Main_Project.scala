package com.main.project


import scala.util.parsing.combinator._
import scala.io.StdIn.readLine


abstract class Tree


case class S(c: Tree) extends Tree

case class E(l: Tree, c: Option[String], r: Option[Tree]) extends Tree

case class T(l: Tree, r: Option[Tree]) extends Tree

//case class F(l: Tree, r: Option[String]) extends Tree
case class F(l: Option[Tree], r: Option[String]) extends Tree
//f  needs to be an optional. When there isn't a question mark, then the left side has to be there.
// when there is, then the a doesnt need to be there

case class A(lp: Option[String], c: Tree, rp: Option[String]) extends Tree

case class C_Cons(c: String) extends Tree

/*
S  -: E$
E  -: T '|' E | T
T  -: F T | F
F  -: A '?' | A
A  -: C | '(' E ')'
*/

object sreduce extends Combinators {
  override def skipWhitespace = false
  var idx = 0
  var v:String = _
  var p:String = _
  var b_char= -1
  var b2_char= -1

  def backup_alt() : Unit= {
    b_char= idx
//return true
  }
  //worry about left index, if left doesnt work reset
    def backup_Rev(): Unit= {
    idx = b_char
//        return false
  }

def s_match(t: Tree): Boolean = t match {
    case S(c) => s_match(c)

    case E(l, Some(c), Some(r) ) => {b_char=idx; s_match(l) || s_match(r) } //; backup_alt()

    case E(l, None, None) => s_match(l)

//    case T(l, Some(r)) if  s_match(l) == false => {backup_Rev(); s_match(r);}

    case T(l, Some(r)) => s_match(l) && s_match(r)

    case T (l, None) => s_match(l)

    case F (Some(l), Some(r)) => {s_match(l)}

    case F (Some(l), None) => {b2_char = idx;s_match(l)}

    case F (None, None) => {b_char = idx; true}

    case A (Some(lp), c, Some(rp)) => s_match(c)

    case A (None, c, None) => s_match(c)
//regular case. If the the pattern tree char and match char are equal, increment and return true
//    case C_Cons (c) if c.equals(String.valueOf(v.charAt(idx))) ==false && idx != 0=> {backup_Rev(); true}
      //check if theres a dot in the pattern, AS WELL AS the match string fails the case
    case C_Cons (c) if idx  > v.length-1 => {idx= b2_char; false;}
    case C_Cons(c) if  c.equals(String.valueOf(v.charAt(idx))) => {idx += 1; true}
//checks if the


    case C_Cons (c) if c.equals(String.valueOf(v.charAt(idx))) ==false && idx != 0=> {backup_Rev(); false;}
//default case, if fails, return false
    case C_Cons (c) if c.equals(String.valueOf(v.charAt(idx))) ==false =>  false

  }

  def main(args: Array[String]) {
    print("pattern? ")
    val e_s = scala.io.StdIn.readLine()
    p = e_s
    val exp:Tree = parseAll(e, p).get

    print("string? ")
    val kb = scala.io.StdIn.readLine()
    //assignment to the global variable v
    v = kb
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

//    def f: Parser[Tree] = a ~ opt ^^ {case l ~ r => F(l,Some(r))} | a ^^ {case l  => F(l, None) }
    def f: Parser[Tree] = a ~ opt ^^ {case l ~ r => F(Some(l),Some(r))} | a ^^ {case l  => F(Some(l), None) }
//  | a ~ opt ^^ {case l ~ r => F(None, None)}

  def a: Parser [Tree] = lp ~ e ~ rp  ^^ {case lp ~ c ~ rp => A(Some(lp), c, Some(rp))} | chose ^^ {case c => A(None ,c, None)}

//    def chose: Parser[Tree] = cons ^^{case c => C_Cons(c)}
def chose: Parser[Tree] = cons ^^{case c => C_Cons(c)}
    //need to handle dot case (. -> any number)
//    def

    def rp [Tree] = ")"
    def lp [Tree] ="("
    def des [Tree]=  "|"
    def opt [Tree] = "?"
//    def dot [Tree] = " "
    def cons [Tree] = "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"|
      "a" | "b" | "c"| "d" | "e"| "f"| "g"| "h"| "i"| "j"| "k"| "l"|
      "m"| "n"| "o"| "p"| "q"| "r"| "s" | "t"| "u"| "v"| "w" | "x" | "y" | "z" | "." | " "
  }

