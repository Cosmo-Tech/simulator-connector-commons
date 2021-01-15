package com.cosmotech.connector.commons.pojo


import com.github.doyaaaaaken.kotlincsv.dsl.csvWriter

/**
 * Pojo class to store CSV information
 */
data class CsvData(val fileName:String, val headerNameAndType:MutableMap<String,String>, val rows:MutableList<MutableList<String>>) {

    /** Export Directory path  */
    private var exportDirectory = "/mnt/simulation-data/"

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
     */
    fun exportData() {
        val targetFileName = "$exportDirectory$fileName.csv"
        csvWriter().open(targetFileName,false) {
            writeRow(headerNameAndType.keys.toList())
            writeAll(rows)
        }
    }

}