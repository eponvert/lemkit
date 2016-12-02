package com.peoplepattern.classify

import data._

/**
 * Helper object to deal with indexing and writing examples for processing
 * by the external app.
 */
trait ClassifierIndexerWriter {
  import java.io._

  /**
   * Write out the indexed examples to disk in a format that the external app
   * can process. Return number of examples.
   */
  def writeExamples(
    examples: TraversableOnce[Example[Int, Int]],
    file: File): Int

  /**
   * As examples come through, index them, and write them to a file.
   * Return the indexer based on the label and feature maps that can
   * index new examples for classification.
   */
  def indexAndWriteExamples(
    examples: TraversableOnce[Example[String, String]],
    file: File,
    hashingOptions: HashingOptions) = {
    val (_, indexer, numExamples) =
      ClassifierIndexer.index(examples,
        ex => ((), writeExamples(ex, file)), hashingOptions)
    (indexer, numExamples)
  }
}