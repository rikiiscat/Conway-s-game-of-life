class Model {
    // represent my board
    private val sizeOuter = 80
    private val sizeInner = 75

    private val views = ArrayList<IView>()
    private val board = Array(sizeOuter) { BooleanArray(sizeInner) }

    fun getBoard(): Array<BooleanArray> {
        return board
    }

    fun updateBoard(px:Int, py:Int, type:String) {
        var x = px
        var y = py
        if(type == "Block") {
            if(y < 50) {
                board[x][y] = true
                ++x
                ++y
                if(x < 75) {
                    board[x][y-1] = true
                    if(y < 75) {
                        board[x-1][y] = true
                        board[x][y] = true
                    }
                }
            }
        } else if(type == "Beehive") {
            if(y < 50) {
                board[x][y] = false
                ++x
                ++y
                if(x < 75) {
                    board[x][y-1] = true
                    if(y < 50) {
                        board[x][y] = false
                    }
                }
                if(y < 50) {
                    board[x-1][y] = true
                }
                ++x
                ++y
                if(x < 75) {
                    board[x][y-2] = true
                    if(y <= 50) {
                        board[x][y-1] = false
                    }
                    if(y < 50) {
                        board[x][y] = true
                    }
                }
                if(y < 50) {
                    board[x-2][y] = false
                    if(x <= 75) {
                        board[x-1][y] = true
                    }
                }
                ++x
                if(x < 75 && y < 50) {
                    board[x][y-2] = false
                    board[x][y-1] = true
                    board[x][y] = false
                } else if(x < 75 && y == 50) {
                    board[x][y-2] = false
                    board[x][y-1] = true
                } else if(x < 75 && y == 51) {
                    board[x][y-2] = false
                }
            }
        } else if(type == "Blinker") {
            if(y < 50) {
                board[x][y] = false
                ++x
                ++y
                if(x < 75) {
                    board[x][y-1] = false
                    if(y < 50) {
                        board[x][y] = true
                    }
                }
                if(y < 50) {
                    board[x-1][y] = true
                }
                ++x
                ++y
                if(x < 75) {
                    board[x][y-2] = false
                    if(y <= 50) {
                        board[x][y-1] = true
                    }
                    if(y < 50) {
                        board[x][y] = false
                    }
                }
                if(y < 50) {
                    board[x-2][y] = false
                    if(x <= 75) {
                        board[x-1][y] = false
                    }
                }
            }
        } else if(type == "Toad") {
            if(y < 50) {
                board[x][y] = false
                ++x
                ++y
                if(x < 75) {
                    board[x][y-1] = true
                    if(y < 50) {
                        board[x][y] = true
                    }
                }
                if(y < 50) {
                    board[x-1][y] = true
                }
                ++x
                if(x < 75 && y < 50) {
                    board[x][y-1] = true
                    board[x][y] = true
                } else if(x < 75) {
                    board[x][y-1] = true
                }
                ++x
                if(x < 75 && y < 50) {
                    board[x][y-1] = true
                    board[x][y] = false
                } else if(x < 75) {
                    board[x][y-1] = true
                }
            }
        } else if(type == "Glider") {
            board[x][y] = false
            ++x
            ++y
            if(x < 75) {
                board[x][y-1] = false
                if(y < 50) {
                    board[x][y] = false
                }
            }
            if(y < 50) {
                board[x-1][y] = true
            }
            ++x
            ++y
            if(x < 75) {
                board[x][y-2] = true
                if(y <= 50) {
                    board[x][y-1] = true
                }
                if(y < 50) {
                    board[x][y] = true
                }
            }
            if(y < 50) {
                board[x-2][y] = false
                if(x <= 75) {
                    board[x-1][y] = true
                }
            }
        } else if(type == "Clear") {
            for (i in 0..74) {
                for(j in 0..49) {
                    board[i][j] = false
                }
            }
        }
    }

    fun updateLive(board:Array<BooleanArray>) {
        // 10, 10
        val countArray = Array(sizeOuter) { BooleanArray(sizeInner) }
        for (i in 0..74) {
            for (j in 0..49) {
                var count:Int = 0
                if(i == 0) {
                    if(j == 0) {
                        if(board[0][1]) {
                            ++count
                        }
                        if(board[1][1]) {
                            ++count
                        }
                        if(board[1][0]) {
                            ++count
                        }
                    } else if(j == 49) {
                        if(board[0][48]) {
                            ++count
                        }
                        if(board[1][48]) {
                            ++count
                        }
                        if(board[1][49]) {
                            ++count
                        }
                    } else {
                        if(board[i][j-1]) {
                            ++count
                        }
                        if(board[i+1][j-1]){
                            ++count
                        }
                        if(board[i+1][j]) {
                            ++count
                        }
                        if(board[i][j+1]) {
                            ++count
                        }
                        if(board[i+1][j+1]) {
                            +count
                        }
                    }
                } else if(i == 74) {
                    if(j == 0) {
                        if(board[73][0]) {
                            ++count
                        }
                        if(board[73][1]) {
                            ++count
                        }
                        if(board[74][1]) {
                            ++count
                        }
                    } else if(j == 49) {
                        if(board[74][48]) {
                            ++count
                        }
                        if(board[73][48]) {
                            ++count
                        }
                        if(board[73][49]) {
                            ++count
                        }
                    } else {
                        if(board[i][j-1]) {
                            ++count
                        }
                        if(board[i-1][j-1]) {
                            ++count
                        }
                        if(board[i-1][j]) {
                            ++count
                        }
                        if(board[i-1][j+1]) {
                            ++count
                        }
                        if(board[i][j+1]) {
                            ++count
                        }
                    }
                } else if(j == 0) {
                    if(board[i-1][j]) {
                        ++count
                    }
                    if(board[i+1][j]) {
                        ++count
                    }
                    if(board[i-1][j+1]) {
                        ++count
                    }
                    if(board[i][j+1]) {
                        ++count
                    }
                    if(board[i+1][j+1]) {
                        ++count
                    }
                } else if (j == 49) {
                    if(board[i-1][j-1]) {
                        ++count
                    }
                    if(board[i][j-1]) {
                        ++count
                    }
                    if(board[i+1][j-1]) {
                        ++count
                    }
                    if(board[i-1][j]) {
                        ++count
                    }
                    if(board[i+1][j]) {
                        ++count
                    }
                } else {
                    if(board[i-1][j-1]) {
                        ++count
                    }
                    if(board[i][j-1]) {
                        ++count
                    }
                    if(board[i+1][j-1]) {
                        ++count
                    }
                    if(board[i-1][j]) {
                        ++count
                    }
                    if(board[i+1][j]) {
                        ++count
                    }
                    if(board[i-1][j+1]) {
                        ++count
                    }
                    if(board[i][j+1]) {
                        ++count
                    }
                    if(board[i+1][j+1]) {
                        ++count
                    }
                }
                if(board[i][j] && (count == 2 || count == 3)) {
                    countArray[i][j] = true
                } else if(board[i][j]) {
                    countArray[i][j] = false
                } else if(!board[i][j] && count == 3) {
                    countArray[i][j] = true
                } else {
                    countArray[i][j] = false
                }
            }
        }
        for (i in 0..74) {
            for (j in 0..49) {
                board[i][j] = countArray[i][j]
            }
        }
    }

    // view management
    fun addView(view: IView) {
        views.add(view)
    }

    fun removeView(view: IView) {
        views.remove(view)
    }

    fun notifyView() {
        for (view in views) {
            view.update()
        }
    }
}