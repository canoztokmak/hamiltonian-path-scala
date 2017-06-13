package com.waka

/**
  * Created by canoztokmak on 12/06/2017.
  */
object Main {
  def main(args: Array[String]): Unit = {
    println("Enter board dimension size between 5-10 ?")
    var boardSize = readInt
    while (10 < boardSize || boardSize < 5) {
      println("Invalid board size.. Please enter a valid board dimension size..")
      boardSize = readInt
    }

    println("Enter entry point ? (x,y) - Space separated two integers..")
    val line = readLine.split(" ")
    var x = line(0).toInt
    var y = line(1).toInt

    while ((boardSize <= x || x < 0) || (boardSize <= y || y < 0)) {
      System.out.println("Invalid (x,y) point.. Please enter a valid entry point..")
      val line = readLine.split(" ")
      x = line(0).toInt
      y = line(1).toInt
    }
    println("Calculating path.. Please wait, it may take a while..")

    val start = System.currentTimeMillis()

    new HamiltonianPath(boardSize).findPath((x, y)) match {
      case Some(path) => HamiltonianPath.printPath(boardSize, path)
      case None => println("No solution..")
    }

    println("It took " + (System.currentTimeMillis() - start) + " ms to compute..")
  }
}
