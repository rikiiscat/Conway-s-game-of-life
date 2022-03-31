import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.scene.control.TextArea

class StatusView : IView, HBox() {
    init {
        val statusMessage = TextArea("Created Something")
        //this.children.add(statusMessage)
        val frameCount = TextArea("                                                                                                                    Frame 0")
        this.children.addAll(statusMessage, frameCount)
    }

    override fun update() {
        //javafx.scene.control.TextArea("Created xx")
    }

    fun printStatus(str:String, x:Int, y:Int, frame:Int) {
        val statusMessage = TextArea("Created " + str + " at " + x + "," + y)
        val frameCount = TextArea("                                                                                                                    Frame " + frame)
        this.children[0] = statusMessage
        this.children[1] = frameCount
    }
}