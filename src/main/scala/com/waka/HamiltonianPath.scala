package com.waka

/**
  * Created by canoztokmak on 12/06/2017.
  */

class HamiltonianPath(boardSize: Int) {
  private val vertexCount = boardSize * boardSize
  private val adjacencyMatrix: Array[Array[Boolean]] = generateAdjacencyMatrix(boardSize)
  private var visited: Array[Boolean] = Array.emptyBooleanArray

  private def positionToVertex(point: (Int, Int)) = point._2 * boardSize + point._1

  private def isSafe(v: Int, path: Array[Int], position: Int): Boolean = adjacencyMatrix(path(position-1))(v) && !visited(v)

  private def generateAdjacencyMatrix(boardSize: Int): Array[Array[Boolean]] = {
    val adjacencyMatrix = Array.ofDim[Boolean](vertexCount, vertexCount)

    for (x <- 0 until boardSize; y <- 0 until boardSize) {
      if (0 <= x - 3) adjacencyMatrix(positionToVertex(x, y))(positionToVertex(x - 3, y)) = true

      if (x + 3 < boardSize) adjacencyMatrix(positionToVertex(x, y))(positionToVertex(x + 3, y)) = true

      if (0 <= y - 3) adjacencyMatrix(positionToVertex(x, y))(positionToVertex(x, y - 3)) = true

      if (y + 3 < boardSize) adjacencyMatrix(positionToVertex(x, y))(positionToVertex(x, y + 3)) = true

      if (0 <= x - 2 && 0 <= y - 2) adjacencyMatrix(positionToVertex(x, y))(positionToVertex(x - 2, y - 2)) = true

      if (0 <= x - 2 && y + 2 < boardSize) adjacencyMatrix(positionToVertex(x, y))(positionToVertex(x - 2, y + 2)) = true

      if (x + 2 < boardSize && 0 <= y - 2) adjacencyMatrix(positionToVertex(x, y))(positionToVertex(x + 2, y - 2)) = true

      if (x + 2 < boardSize && y + 2 < boardSize) adjacencyMatrix(positionToVertex(x, y))(positionToVertex(x + 2, y + 2)) = true
    }

    adjacencyMatrix
  }

  // optimized for performance..
  // no pattern matching is used for the result of solve() execution
  // instead of iterating over Range (0 until vertexCount), tail recursive iteration is used
  private def solve(path: Array[Int], position: Int): Option[Array[Int]] = {
    def iterateOnVertices(v: Int = 0): Option[Array[Int]] = {
      if (v < vertexCount) {
        if (isSafe(v, path, position)) {
          path(position) = v
          visited(v) = true

          val r = solve(path, position + 1)

          if (r.isEmpty) {
            path(position) = -1
            visited(v) = false

            iterateOnVertices(v + 1)
          } else {
            Some(path)
          }
        } else {
          iterateOnVertices(v + 1)
        }
      } else {
        None
      }
    }

    if (position == vertexCount) Some(path)
    else iterateOnVertices()
  }

  def findPath(point: (Int, Int)): Option[Array[Int]] = {
    val path = Array.fill(vertexCount)(-1)
    visited = Array.ofDim(vertexCount)

    path(0) = positionToVertex(point)
    visited(path(0)) = true

    solve(path, 1)
  }
}

object HamiltonianPath {
  def printPath(boardSize: Int, path: Array[Int]): Unit = {
    path.foreach { v =>
      val x = v % boardSize
      val y = v / boardSize
      print(s"($x,$y) ")
    }
    println()
  }
}
