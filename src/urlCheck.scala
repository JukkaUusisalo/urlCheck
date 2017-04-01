import java.text.SimpleDateFormat
import java.util.Calendar

/**
  * Created by juuus on 4/1/17.
  */
object urlCheck {

  def main(args: Array[String]): Unit = {
    val dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
    if(args.length==3) {
      while (true) {
        val time = get(args(0))
        val ts = dateFormat.format(Calendar.getInstance().getTime())
        if(time > args(1).toLong) println(s"$ts;$time" )
        Thread.sleep(args(2).toLong)
      }
    }

    def get(url:String): Long = {
      val start = System.currentTimeMillis()
      io.Source.fromURL(url)
      val end = System.currentTimeMillis()
      end - start
    }
  }

}
