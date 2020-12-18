package com.cosmotech.connector.commons

import com.cosmotech.connector.commons.pojo.CsvData

/**
 * Cosmo Solution Connector interface
 */
interface Connector<T> {

    /**
     * Build a client to interact with the targeted data storage
     * @return the client
     */
    fun buildClient(): T

    /**
     * Construct data that will be transferred to simulator
     * @return a list of CsvData containing
     */
    fun constructSimulatorData(client:T): List<CsvData>

    /**
     * Retrieve, process and export data
     */
    fun process()

}