package com.github.galcyurio

import org.apache.commons.configuration2.builder.fluent.Configurations
import java.io.File

fun main(args: Array<String>) {
    val configuration = Configurations().properties(File("database.properties"))
    val host = configuration.getString("database.host")
    val port = configuration.getInt("database.port")
    val user = configuration.getString("database.user")
    val password = configuration.getString("database.password")
    val timeout = configuration.getLong("database.timeout")

    println("""
        host = $host
        port = $port
        user = $user
        password = $password
        timeout = $timeout
    """.trimIndent())
}