package com.example.edittext_textchangelistener_forfilter_listitems

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), TextWatcher, SearchView.OnQueryTextListener {

    var i = 0
    lateinit var list: MutableList<CardviewData>
    lateinit var adaptor: AdaptorEX
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        list = mutableListOf()

        whileloop()

        rv.setHasFixedSize(true)
        rv.layoutManager = GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
        adaptor = AdaptorEX(this, list)
        rv.adapter = adaptor

        et_search.addTextChangedListener(this)

    }




    fun whileloop() {

        while (true) {
            list.add(CardviewData("title $i", "description $i"))

            if (i == 1000) {
                return
            }
            i++
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        var item = menu?.findItem(R.id.sv)
        var sv = item?.actionView as SearchView
        sv.imeOptions = EditorInfo.IME_ACTION_DONE
        sv.setOnQueryTextListener(this)

        return true
    }





    
    override fun onQueryTextChange(newText: String?): Boolean {
        var flist = mutableListOf<CardviewData>()

             for (i in list){
                 if (i.title.toLowerCase().contains(newText!!.trim())){
                     flist.add(i)
                     adaptor.list = flist
                     adaptor.notifyDataSetChanged()
                 }
             }

        return false
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false

    }


    override fun afterTextChanged(s: Editable?) {
        var flist = mutableListOf<CardviewData>()

        for (i in list) {
            if (i.title.toLowerCase().contains(s.toString())) {
                flist.add(i)
                adaptor.list = flist
                adaptor.notifyDataSetChanged()
            }
        }
    }
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
    }
}

