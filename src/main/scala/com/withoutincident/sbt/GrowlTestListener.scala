package com.withoutincident.sbt {

import org.scalatools.testing.{Result => TResult}
import _root_.sbt._

class GrowlTestListener extends TestsListener {

  private var numPassed = 0
  private var numFailed = 0
  private var numErrored = 0
  private var numSkipped = 0

  private var logger = new ConsoleLogger

  /** called once, at beginning. */
  def doInit = {
    numPassed = 0
    numFailed = 0
    numErrored = 0
    numSkipped = 0
  }

  /** called for each class or equivalent grouping */
  def startGroup(name:String) {
  }

  /** called for each test method or equivalent */
  def testEvent(event:TestEvent) = {
    event.detail.foreach { evt =>
      evt.result match {
        case TResult.Error => numErrored +=1
        case TResult.Success => numPassed +=1 
        case TResult.Failure => numFailed +=1 
        case TResult.Skipped => numSkipped += 1
      }
    }
  }

  /** called if there was an error during test */
  def endGroup(name: String, t:Throwable) = {
    numErrored += 1
  }

  /** called if test completed */
  def endGroup(name:String, result:Result.Value) = {
  }

  /** called once, at end. */
  def doComplete(finalResult:Result.Value) = {
    finalResult match {
      case Result.Error => reportFailure
      case Result.Failed => reportFailure
      case Result.Passed => reportSuccess
    }
  }

  private def reportFailure = {
    val total = numPassed + numFailed + numErrored
    val testString = total match {
      case 1 => "test"
      case _ => "tests"
    }
    val errorString = numErrored match {
      case 1 => "error"
      case _ => "errors"
    }

    reportMessage(total + " " + testString + ": Suite FAILED.",
                  numPassed + " passed, " +
                  numSkipped + " skipped, " + 
                  numFailed + " failed, " +
                  numErrored + " " + errorString + ".")
  }

  private def reportSuccess = {
    val total = numPassed + numFailed + numErrored
    val testString = total match {
      case 1 => "test"
      case _ => "tests"
    }
    
    reportMessage(total + " " + testString + " PASSED.",
                  "Skipped " + numSkipped + ".")
  }

  private def reportMessage(title:String):Unit = reportMessage(title, "")

  private def reportMessage(title:String, message:String) = {
    val runtime = Runtime.getRuntime
    var cmd = List("growlnotify", "-t", title)

    if (!message.isEmpty)
      cmd = cmd ++ List("-m", message)

    runtime.exec(List("growlnotify", "-t", title, "-m", message).toArray)
  }
}

}
