package com.cosmotech.connector.commons.pojo

import com.cosmotech.connector.commons.CsmUnitTest
import org.apache.commons.io.FileUtils
import org.junit.jupiter.api.Test
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/**
 * Test class for CsvData methods
 */
class CsvDataTest: CsmUnitTest()  {

    @Test
    fun test_getExportFilePath() {
        val dataTestToPrint = createSampleDataForTest()
        assertEquals(testWorkingFolder
            .resolve(
                dataTestToPrint.fileName
                    .plus(dataTestToPrint.extension)
            ).toString(),
            dataTestToPrint.getExportFilePath()
        )
    }

    @Test
    fun test_writeFile() {
        val dataTestToPrint = createSampleDataForTest()
        val targetFile = Paths.get(dataTestToPrint.getExportFilePath())
        assertFalse(Files.exists(targetFile))
        dataTestToPrint.writeFile()
        assertTrue(Files.exists(targetFile))
    }

    @Test
    fun test_exportData() {
        val dataTestToPrint = createSampleDataForTest()
        val exportCsvFile = testWorkingFolder.resolve(dataTestToPrint.fileName.plus(".csv"))
        assertFalse (Files.exists(exportCsvFile),"Check if $exportCsvFile doesn't already exist")
        dataTestToPrint.writeFile()
        assertTrue (Files.exists(exportCsvFile), "Check that $exportCsvFile has been correctly created")
        val expectedFileExported = getResourceFile("pojo/Test.csv")
        assertTrue(
            FileUtils.contentEqualsIgnoreEOL(
                exportCsvFile.toFile(),
                expectedFileExported.toFile(),
                Charsets.UTF_8.name()
            ),"Check that the content exported is the expected one"
        )
    }

    @Test
    fun test_exportDataWithExistingTargetFile() {
        val dataTestToPrint = createSampleDataForTest()
        val exportCsvFile = testWorkingFolder.resolve(dataTestToPrint.fileName.plus(".csv"))
        Files.createFile(exportCsvFile)
        assertTrue(Files.exists(exportCsvFile),"Check if $exportCsvFile exists")
        assertEquals(Files.size(exportCsvFile),0,"Check if $exportCsvFile is empty")
        dataTestToPrint.writeFile()
        val expectedFileExported = getResourceFile("pojo/Test.csv").toFile()
        assertTrue(
            FileUtils.contentEqualsIgnoreEOL(
                exportCsvFile.toFile(),
                expectedFileExported,
                Charsets.UTF_8.name()
            ),"Check if $exportCsvFile has been overwritten"
        )
    }
}