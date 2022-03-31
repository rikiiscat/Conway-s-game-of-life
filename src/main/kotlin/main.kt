import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.input.KeyCode
import javafx.scene.layout.VBox
import javafx.scene.paint.Color
import javafx.scene.shape.Shape
import javafx.stage.Stage
import java.util.*
import javafx.animation.AnimationTimer
import javafx.scene.control.TextArea


class Main : Application() {
    fun printBoard(board:Array<BooleanArray>, gc:javafx.scene.canvas.GraphicsContext) {
        for (i in 0..74) {
            for (j in 0..49) {
                if(board[i][j]) {
                    gc.lineWidth = 1.0
                    gc.stroke = Color.GREY
                    gc.fill = Color.BLACK
                    gc.fillRect(i*14.toDouble(),j*14.toDouble(),14.0,14.0)
                    gc.strokeRect(i*14.toDouble(),j*14.toDouble(), 14.0, 14.0)
                } else {
                    gc.lineWidth = 1.0
                    gc.stroke = Color.GREY
                    gc.fill = Color.WHITE
                    gc.fillRect(i*14.toDouble(),j*14.toDouble(),14.0,14.0)
                    gc.strokeRect(i*14.toDouble(),j*14.toDouble(), 14.0, 14.0)
                }
            }
        }
    }

    override fun start(stage: Stage?) {
        val model = Model()

        var ifManual:Boolean = false
        var frame:Int = 0

        // our layout is the root of the scene graph
        val root = VBox()

        // views are the children of the vbox
        val toolbar = ToolbarView()

        val status = StatusView()
        var statusMessage:TextArea = TextArea("")

        // Create a canvas as a drawing surface
        val canvas = Canvas(1050.0,700.0)
        val gc = canvas.graphicsContext2D
        //gridPane.add()
        //gc.fillRect(0.0,1000.0,20.0,20.0)
        var x: Int = 0
        var y: Int = 0
        //gc.lineWidth = 1.0
        //gc.stroke = Color.BLACK
        //gc.strokeRect(300.0,300.0,30.0,30.0)
        //gc.fill = Color.WHITE

        for (x in 0..74) {
            for (y in 0..49) {
                gc.lineWidth = 1.0
                gc.stroke = Color.GREY
                gc.strokeRect(x*14.toDouble(),(y*14).toDouble(),14.0,14.0)
            }
        }

        // add actions to the buttons
        toolbar.items[0].setOnMouseClicked {
            // block fixed that does not change
            canvas.setOnMouseClicked { e ->
                val x = e.screenX - 188
                val y = e.screenY - 84
                val px: Int = Math.floor(x / 14).toInt()
                val py: Int = Math.floor(y / 14).toInt()
                model.updateBoard(px,py,"Block")
                val board = model.getBoard()
                printBoard(board,gc)
                status.printStatus("Block",px,py,frame)
//                statusMessage = status.statusMessage
            }
        }

        toolbar.items[1].setOnMouseClicked {
            canvas.setOnMouseClicked { e ->
                val x = e.screenX - 188
                val y = e.screenY - 84
                val px: Int = Math.floor(x / 14).toInt()
                val py: Int = Math.floor(y / 14).toInt()
                model.updateBoard(px,py,"Beehive")
                val board = model.getBoard()
                printBoard(board,gc)
                status.printStatus("Beehive",px,py,frame)
//                statusMessage = status.statusMessage
            }
        }

        toolbar.items[2].setOnMouseClicked {
            canvas.setOnMouseClicked { e ->
                val x = e.screenX - 188
                val y = e.screenY - 84
                val px: Int = Math.floor(x / 14).toInt()
                val py: Int = Math.floor(y / 14).toInt()
                model.updateBoard(px,py,"Blinker")
                val board = model.getBoard()
                printBoard(board,gc)
                status.printStatus("Blinker",px,py,frame)
//                statusMessage = status.statusMessage
            }
        }

        toolbar.items[3].setOnMouseClicked {
            canvas.setOnMouseClicked { e ->
                val x = e.screenX - 188
                val y = e.screenY - 84
                val px: Int = Math.floor(x / 14).toInt()
                val py: Int = Math.floor(y / 14).toInt()
                model.updateBoard(px,py,"Toad")
                val board = model.getBoard()
                printBoard(board,gc)
                status.printStatus("Toad",px,py,frame)
//                statusMessage = status.statusMessage
            }
        }

        toolbar.items[4].setOnMouseClicked {
            canvas.setOnMouseClicked { e ->
                val x = e.screenX - 188
                val y = e.screenY - 84
                val px: Int = Math.floor(x / 14).toInt()
                val py: Int = Math.floor(y / 14).toInt()
                model.updateBoard(px,py,"Glider")
                val board = model.getBoard()
                printBoard(board,gc)
                status.printStatus("Glider",px,py,frame)
//                statusMessage = status.statusMessage
            }
        }

        toolbar.items[5].setOnMouseClicked {
            model.updateBoard(0,0,"Clear")
            val board = model.getBoard()
            printBoard(board,gc)
            status.printStatus("Clear",0,0,frame)
        }

        // register views with the model
        model.addView(toolbar)
        model.addView(status)

        // setup and display
        //root.children.addAll(toolbar, group, status)
        val timer = Timer()
        timer.schedule(object : TimerTask() {
            //override run method
            override fun run() {
                ++frame
                if(!ifManual) {
                    root.setOnKeyPressed { event ->
                        when(event.code) {
                            KeyCode.M -> {
                                ifManual = true
                            }
                        }
                    }
                    if(!ifManual) {
                        printBoard(model.getBoard(), gc)
                        model.updateLive(model.getBoard())
                    }
                } else {
                    root.setOnKeyPressed { event ->
                        when(event.code) {
                            KeyCode.A -> {
                                printBoard(model.getBoard(), gc)
                                model.updateLive(model.getBoard())
                                //ifManual = true
                            }
                            KeyCode.M -> {
                                ifManual = false
                            }
                        }
                    }
                }
            }
        }, 0, 1000)

        root.children.addAll(toolbar, canvas, status)
        stage?.scene = Scene(root)
        stage?.isResizable = false
        stage?.width = 1050.0
        stage?.height = 800.0
        stage?.title = "Conway's Game of Life (20820011)"
        stage?.show()
    }
}
