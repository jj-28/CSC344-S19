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
  val ms_index = 0
///*
  def s_match(t: Tree): Boolean = t match {
    case S(c) => s_match(c)

    case E(l, Some(c), Some(r) ) => s_match(l) || s_match(r)

    case E(l, None, None) => s_match(l)

    case T(l, Some(r)) => s_match(l) || s_match(r)

    case T (l, None) => s_match(l)

//    case F (l, Some(r)) => s_match(l)
//    case F (l, None) => s_match(l)
    case F (Some(l), Some(r)) => s_match(l)

    case F (Some(l), None) => s_match(l)

    case A (Some(lp), c, Some(rp)) => s_match(c)

    case A (None, c, None) => s_match(c)


    case C_Cons (c) => true

  }

  def m_match (s: String) : Boolean = s match {

      case E (C_Cons(c), s, i) => c == s.toString().indexOf()
//    case C_Cons (c) if (s.equals("."))  => true

}

  def main(args: Array[String]) {
    print("pattern? ")
    val e_s = scala.io.StdIn.readLine()
    print("string?")
    val kb = scala.io.StdIn.readLine()
    val exp:Tree = parseAll(e, "(ab)|c").get
//    println(exp)
    //val exp:Tree = parseAll(e, e_s).get
    if (m_match(exp)) {
      println("match")
    }
//    println("no match")

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
      "m"| "n"| "o"| "p"| "q"| "r"| "s" | "t"| "u"| "v"| "w" | "x" | "y" | "z" | "."
  }

