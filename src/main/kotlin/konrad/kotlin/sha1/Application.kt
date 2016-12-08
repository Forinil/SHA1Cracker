package konrad.kotlin.sha1

import konrad.java.sha1.SHA1Util

/**
 * Stworzone przez Konrad Botor dnia 2016-11-23.
 */

fun main(args: Array<String>) {
    val length = 6
    val numOfPasswords = 1000
    val constant = ""
    val passwords = PasswordUtil.generateRandomPasswordList(length, numOfPasswords)
    val times = Array<Long>(numOfPasswords) {0}

    (0..numOfPasswords-1).forEach {
        val password = passwords[it]
        val hash = SHA1Util.calculateSHA1Hash(constant + password)
        times[it] = crackHash(constant, hash, length, password, it+1)
    }

    val averageTime = times.average()
    println("Average time elapsed: %.2f ms".format(averageTime))
}

private fun crackHash(constant: String, hash: String?, length: Int, password: String, count: Int): Long {
    println("Password %d: %s".format(count, password))
    println("Hash %d: %s".format(count, hash))

    val startTime = System.currentTimeMillis()
    var endTime = System.currentTimeMillis()

    val range = 1..generateNumber(length, "9").toInt()

    for (it in range) {
        val psw = "$constant%0${length}d".format(it)
        if (SHA1Util.calculateSHA1Hash(psw) == hash) {
            println("Cracked password %d: %0${length}d".format(count, it))
            endTime = System.currentTimeMillis()
            break
        }
    }

    val elapsed = endTime-startTime

    println("Time elapsed %d: %d ms\n".format(count, elapsed))

    return elapsed
}

private fun generateNumber(length: Int, digit: String): String {
    var number = ""
    (1..length).forEach {
        number += digit
    }
    return number
}