package com.cosmotech.connector.commons.pojo

import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter

/**
 * Pojo class to store CSV information
 */
data class CsvData(val fileName:String, val headerNameAndType:MutableMap<String,String>, val rows:MutableList<MutableList<String>>) {

    /** Export Directory path  */
    var exportDirectory = "/mnt/simulation-data/"

    /** File extension */
    val extension = ".csv"

    constructor(
        fileName : String,
        headers : MutableMap<String,String>,
        rows : MutableList<MutableList<String>>,
        exportDirectory : String
    ) : this (fileName,headers,rows) {
        if ( !exportDirectory.endsWith("/") ) {
            this.exportDirectory = exportDirectory.plus("/")
        } else  {
            this.exportDirectory = exportDirectory
        }
    }

    /**
     * Export Data to corresponding CSV file
     * Overwrite existing file if exists
     * @return the string path to the file created/overwritten
     */
    fun writeFile(): String {
        val targetFileName = getExportFilePath()
        csvWriter().open(targetFileName,false) {
            writeRow(headerNameAndType.keys.toList())
            writeAll(rows)
        }
        return targetFileName
    }

    /**
     * Get the export file path
     * Be careful, the exportDirectory can be overwritten
     * The value returned can change
     * @return the CSV file export path
     */
    fun getExportFilePath():String = "$exportDirectory$fileName$extension"



}