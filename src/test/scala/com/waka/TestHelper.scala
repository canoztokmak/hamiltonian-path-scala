package com.waka

/**
  * Created by canoztokmak on 12/06/2017.
  */
object TestHelper {
  def vertexToPosition(boardSize: Int, v: Int): (Int, Int) = (v % boardSize, v / boardSize)
  def positionToVertex(boardSize: Int, position: (Int, Int)): Int = position._2 * boardSize + position._1
}
