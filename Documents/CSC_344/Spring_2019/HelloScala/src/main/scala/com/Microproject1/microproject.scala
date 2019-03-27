//import scala.util.parsing.combinator.JavaTokenParsers
import scala.util.parsing.combinator._

abstract class Tree
case class Sum(l: Tree, r: Tree) extends Tree
case class Mult(l: Tree, r: Tree) extends Tree
case class Var(n: String) extends Tree
case class Const(v: Int) extends Tree



class Combinators extends JavaTokenParsers{

  // E -> T + E | T
  // T -> F * T | F
  // F -> Const | Var
  def e: Parser[Tree] = t ~ plusc ~ e ^^ { case l ~ p ~ r => Sum(l, r) } | t
  def t : Parser [Tree] = f ~ mult ~ t ^^ {case l  ~ m ~ r => Mult(l,r) } | f
  def f: Parser[Tree] = const | varname

  def const: Parser[Const] = "[0-9]+".r ^^ { str => Const(str.toInt) }
  def varname: Parser[Var] = "[A-Za-z]+".r ^^ { str => Var(str) }
  def plusc[Tree] = "+"
  def mult [Tree] = "*"
}

object Main extends Combinators {
  type Environment = String => Int

  def eval(t: Tree, env: Environment): Int = t match {
    case Sum(l, r) => eval(l, env) + eval(r, env)
    case Var(n) => env(n)
    case Const(v)  => v
  }

  def main(args: Array[String]){
//    val exp: Tree = Sum(Var("x"),Sum(Var("x"),Sum(Const(7),Var("y"))))
    val exp: Tree = Mult(Var("x"),Mult(Var("x"),Mult(Const(7),Var("y"))))
    val env: Environment = { case "x" => 5 case "y" => 7 }
//    println(eval(exp, env))

//    val rd = new RecursiveDescent()
//    val exp2rd:Tree = rd.parseE("x+x+7+y")
//    println(exp2rd)
//    println(eval(exp2rd, env))
//
    val exp2c:Tree = parseAll(e, "x+x+7+y").get
    println(exp2c)
    println(eval(exp2c, env))
  }
  }