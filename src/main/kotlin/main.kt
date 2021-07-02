import java.util.*

fun main(args: Array<String>) {


    // TODO: ۰۱/۰۷/۲۰۲۱  if value == size -1 -----> print *


    print("Enter Number : ")
    var size : Int? = null

    try {
        size = readLine()!!.toInt();
    } catch (e : Exception){

    }
    while (size!! <= 1) {
        print("Invalid \nEnter Number : ")
        try {
            size = readLine()!!.toInt();
        } catch (e : Exception){

        }
    }

    val arrayNumbers = Array(size) { IntArray(size) };
    val winArrayList = arrayListOf<Int>()

    fillWinList(winArrayList, size)

    val listRandom = arrayListOf<Int>()


    // shuffling the values
    for (item in 1..size * size)
        listRandom.add(item)
    listRandom.shuffle()


    // put values inside arr and print it
    for (i in 0 until size) {
        for (j in 0 until size) {
            val value = listRandom.get(0);
            arrayNumbers[i][j] = value;
            listRandom.removeAt(0);

            if (value == size * size)
                print("# ")
            else
                print("$value ")
        }
        println()
    }




    while (true) {


        // moving
        println("Press R,L,U,D to move")
        val input = readLine()
        when (input) {

            "R" -> run {

                arrayNumbers.forEach {

                    if (it.indexOf(size * size) == size - 1) {
                        println("Can't Move");
                        return@run
                    }
                }

                val pair = findIJ(arrayNumbers, size * size);
                val i = pair.first;
                val j = pair.second;
                val temp = arrayNumbers[i][j + 1]
                arrayNumbers[i][j + 1] = size * size
                arrayNumbers[i][j] = temp

                printNumbers(arrayNumbers, size)


            }
            "L" -> run {

                arrayNumbers.forEach {

                    if (it.indexOf(size * size) == 0) {
                        println("Can't Move");
                        return@run
                    }
                }

                val pair = findIJ(arrayNumbers, size * size);
                val i = pair.first;
                val j = pair.second;
                val temp = arrayNumbers[i][j - 1]
                arrayNumbers[i][j - 1] = size * size
                arrayNumbers[i][j] = temp

                printNumbers(arrayNumbers, size)


            }
            "U" -> run {

                arrayNumbers.forEach {

                    if (it.contains(size * size) && arrayNumbers.indexOf(it) == 0) {
                        println("Can't Move");
                        return@run
                    }
                }

                val pair = findIJ(arrayNumbers, size * size);
                val i = pair.first;
                val j = pair.second;
                val temp = arrayNumbers[i - 1][j]
                arrayNumbers[i - 1][j] = size * size
                arrayNumbers[i][j] = temp

                printNumbers(arrayNumbers, size)


            }
            "D" -> run {

                arrayNumbers.forEach {

                    if (it.contains(size * size) && arrayNumbers.indexOf(it) == size - 1) {
                        println("Can't Move");
                        return@run
                    }
                }

                val pair = findIJ(arrayNumbers, size * size);
                val i = pair.first;
                val j = pair.second;
                val temp = arrayNumbers[i + 1][j]
                arrayNumbers[i + 1][j] = size * size
                arrayNumbers[i][j] = temp

                printNumbers(arrayNumbers, size)


            }

        }

        // checking end or not

        var status = true
        entered@ for (i in 0 until size)
            for (j in 0 until size) {
                if (winArrayList.size > 0) {
                    if (arrayNumbers[i][j] == winArrayList.get(0)) {
                        winArrayList.removeAt(0)
                    } else {
                        status = false
                        break@entered
                    }
                }
            }

        if (status) {
            println("Winner Winner Chicken Dinner")
            System.exit(0)
        } else
            fillWinList(winArrayList, size)

    }

}

fun printNumbers(numArr: Array<IntArray>, size: Int) {

    numArr.forEach {

        it.forEach {

            if (it == size * size)
                print("# ")
            else
                print("$it ")

        }

        println()

    }


}

fun findIJ(numArr: Array<IntArray>, number: Int): Pair<Int, Int> {


    var i: Int? = null
    var j: Int? = null

    numArr.forEach {

        if (it.contains(number)) {
            i = numArr.indexOf(it)
            j = it.indexOf(number)
            return@forEach
        }

    }
    return Pair(i!!, j!!)

}

fun fillWinList(winArrayList: ArrayList<Int>, size: Int) {

    winArrayList.clear()
    var num = 2
    while (true) {

        if (num == size * size)
            num = 1;

        winArrayList.add(num)
        num += 2

        if (num == (size * size) + 1)
            break


    }


}