package konrad.kotlin.sha1

import java.util.*

/**
 * Stworzone przez Konrad Botor dnia 2016-11-22.
 */
object PasswordUtil {

    fun generateRandomPassword(length: Int): String {
        val rand = Random(System.currentTimeMillis())

        return generateRandomPassword(length, rand)
    }

    private fun generateRandomPassword(length: Int, random: Random): String {
        val pass = StringBuffer("")
        val digits = "0123456789"

        (1..length).forEach {
            pass.append(digits[random.nextInt(10)])
        }

        return pass.toString()
    }

    fun generateRandomPasswordList(length: Int, numOfPasswords: Int): MutableList<String> {
        val passwordList = ArrayList<String>(numOfPasswords)
        val random = Random(System.currentTimeMillis())

        (1..numOfPasswords).forEach {
            passwordList.add(generateRandomPassword(length, random))
        }

        return passwordList
    }
}