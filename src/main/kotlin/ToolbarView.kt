import javafx.scene.control.Button
import javafx.scene.control.ToolBar
import javafx.scene.image.Image
import javafx.scene.image.ImageView

class ToolbarView : IView, ToolBar() {
    init {
        // add buttons to toolbar
        val Button1 = Button("Block")
        val Button2 = Button("Beehive")
        val Button3 = Button("Blinker")
        val Button4 = Button("Toad")
        val Button5 = Button("Glider")
        val Button6 = Button("Clear")
        Button1.graphic = ImageView(Image("Block.png",20.0,20.0,false,false))
        Button2.graphic = ImageView(Image("Beehive.png",20.0,20.0,false,false))
        Button3.graphic = ImageView(Image("Blinker.png",20.0,20.0,false,false))
        Button4.graphic = ImageView(Image("Toad.png",20.0,20.0,false,false))
        Button5.graphic = ImageView(Image("Glider.png",20.0,20.0,false,false))
        Button6.graphic = ImageView(Image("Clear.png",20.0,20.0,false,false))
        this.items.add(Button1)
        this.items.add(Button2)
        this.items.add(Button3)
        this.items.add(Button4)
        this.items.add(Button5)
        this.items.add(Button6)
    }

    override fun update() {
        // update my button state
    }
}