package com.waka

import org.scalatest.{Matchers, WordSpec}

/**
  * Created by canoztokmak on 12/06/2017.
  */
class HamiltonianPathSpec extends WordSpec with Matchers {

  private val boardSize = 5
  private val vertexCount = boardSize * boardSize
  private val entryPoint_0_0 = (0, 0)
  private val entryPoint_1_1 = (1, 1)

  "FindPath" should {
    "find a valid path" in {
      val pathFinder = new HamiltonianPath(boardSize)
      val maybePath = pathFinder.findPath(entryPoint_0_0)

      maybePath shouldBe defined
      maybePath.get.length shouldBe vertexCount
      maybePath.get.head should equal(TestHelper.positionToVertex(boardSize, entryPoint_0_0))
    }

    "find different paths for different entry points using same instance" in {
      val pathFinder = new HamiltonianPath(boardSize)
      val maybePath1 = pathFinder.findPath(entryPoint_0_0)
      val maybePath2 = pathFinder.findPath(entryPoint_1_1)

      maybePath1 shouldBe defined
      maybePath1.get.length shouldBe vertexCount
      maybePath1.get.head should equal(TestHelper.positionToVertex(boardSize, entryPoint_0_0))

      maybePath2 shouldBe defined
      maybePath2.get.length shouldBe vertexCount
      maybePath2.get.head should equal(TestHelper.positionToVertex(boardSize, entryPoint_1_1))

      maybePath1.get shouldNot equal(maybePath2.get)
    }

    "return None when no path is found" in {
      val pathFinder = new HamiltonianPath(4)
      val maybePath = pathFinder.findPath(entryPoint_0_0)

      maybePath shouldBe None
    }
  }
}
