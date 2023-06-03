package com.ivan.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.ivan.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    enum class Turn{
        NOUGHT,
        X_MARK
    }


    private lateinit var binding:ActivityMainBinding
    private  var list = mutableListOf<Button>()


    private var nought_score = 0
    private var x_score=0

    private var firstTurn = Turn.X_MARK
    private var currentTurn = Turn.X_MARK

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        initializeBoards()
    }

    private fun initializeBoards() {
        list.add(binding.a1)
        list.add(binding.a2)
        list.add(binding.a3)
        list.add(binding.b1)
        list.add(binding.b2)
        list.add(binding.b3)
        list.add(binding.c1)
        list.add(binding.c2)
        list.add(binding.c3)

    }

     fun boardTrapper(view : View){

        if(view !is Button)
            return

        fillBoard(view)


         if(checkVictory(NOUGHT)){
            nought_score++
             result("NOUGHT Wins!").show()
         }

         if(checkVictory(X_MARK)){
             x_score++
             result("CrossMark Wins!").show()
         }

         if(isBoardOverFlow()){
             result("Game - DRAW").show()
         }

    }



    private fun resetBoard() {
        for(b1 in list){
            b1.text=""
        }
        if(firstTurn == Turn.X_MARK){
            firstTurn = Turn.NOUGHT
            currentTurn = Turn.NOUGHT
        }else{
            firstTurn = Turn.X_MARK
            currentTurn = Turn.X_MARK
        }
        changeTextPlayerLabel()
    }


    private fun checkVictory(s1:String) : Boolean{
        Log.d("tag", "checkVictory: checking...")
        //horizontal
        if(compare(binding.a1,s1) && compare(binding.a2,s1) && compare(binding.a3,s1) ){
            return true
        }
        if(compare(binding.b1,s1) && compare(binding.b2,s1) && compare(binding.b3,s1)){
            return true
        }
        if(compare(binding.c1,s1) && compare(binding.c2,s1) && compare(binding.c3,s1)){
            return true
        }

        ///vertical
        if(compare(binding.a1,s1) && compare(binding.b1,s1) && compare(binding.c1,s1)){
            return true
        }
        if(compare(binding.a2,s1) && compare(binding.b2,s1) && compare(binding.c2,s1)){
            return true
        }
        if(compare(binding.a3,s1) && compare(binding.b3,s1) && compare(binding.c3,s1)){
            return true
        }


        //diagonally
        if(compare(binding.a1,s1) && compare(binding.b2,s1) && compare(binding.c3,s1)){
            return true
        }
        if(compare(binding.c1,s1) && compare(binding.b2,s1) && compare(binding.a3,s1)){
            return true
        }

        return false
    }

    private fun compare(b1:Button,symbol:String):Boolean = (symbol == b1.text)

    private fun fillBoard(bt:Button) {

        Log.d("tag", "fillBoard: clicked")

        if (bt.text != "") {
            Log.d("tag", "fillBoard: exit")
            return
        }

        if (currentTurn == Turn.NOUGHT) {
            Log.d("tag", "fillBoard: nought")
            bt.text = NOUGHT
            currentTurn = Turn.X_MARK

        } else if (currentTurn == Turn.X_MARK) {
            Log.d("tag", "fillBoard: xmark")
            bt.text = X_MARK
            currentTurn = Turn.NOUGHT
        }

        changeTextPlayerLabel()
    }

    private fun changeTextPlayerLabel() {
        var temp_text = ""
        Log.d("tag", "changeTextPlayerLabel: changing turns")

        if (currentTurn == Turn.X_MARK){
            binding.heading.text = "Turn $X_MARK"
        }else if(currentTurn == Turn.NOUGHT){
            binding.heading.text = "Turn $NOUGHT"
        }
    }


    private fun result(s: String) : AlertDialog.Builder {
        Log.d("tag", "result: invoking dialog")
        var scoreText="Nought score - $nought_score\nCross score - $x_score\n"

      val builder =   AlertDialog.Builder(this)
            .setTitle(s)
            .setMessage(scoreText)
            .setPositiveButton("RESET"){_,_ ->
                resetBoard()
            }.setCancelable(false)

        return builder

    }

    private fun isBoardOverFlow() : Boolean{

        for(b1 in list){
            if(b1.text==""){
                return false
            }
        }
        return true
    }
    companion object {
       const val NOUGHT = "0"
        const val X_MARK = "X"
    }
}


