package sk2a_2190006.myschedule06

import android.app.ListActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import sk2a_2190006.myschedule06.Defines.Companion.sFmt
import java.text.ParseException
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : ListActivity() , Defines{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,selectDays())

        listView.adapter = adapter
    }

    private fun selectDays(): ArrayList<String> {
        val ret = ArrayList<String>()

        // 今日のカレンダーを取得
        val cal = Calendar.getInstance()

        // 月の最大日数分だけ繰り返す
        val maxday = cal.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in 0 until maxday) {
            cal.set(Calendar.DAY_OF_MONTH, i + 1)
            // 整形した日付の文字列をリストに追加する
            val datestr = sFmt.format(cal.time)
            ret.add(datestr)
        }
        return ret
    }

    override fun onListItemClick(l: ListView, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)

        // 選択したアイテムから日付を取得する
        val date = l.adapter.getItem(position) as String

        try {
            val dtime = Defines.sFmt.parse(date)

            // LayoutMemoを呼び出すIntentを生成
            val intent = Intent(this, Edit::class.java)

            // パラメーターに選択した日付を設定
            intent.putExtra(Defines.KEY_DATE, dtime.time)    //インテントには、データを持たせることができる

            // Intent呼び出しを実行する
            startActivity(intent)

        } catch (e: ParseException) {

        }
    }
}
