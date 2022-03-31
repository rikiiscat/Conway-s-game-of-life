Xuelin liu
20820011 x686liu
Kotlin version 1.5.30-release-407 (JRE 16.0.2+0)
MacBookPro14,1 (MacBook Pro (13-inch, 2017, Two Thunderbolt 3 ports))

I choose the second feature to implement. To change to manual mode, press M on keyboard; to change 
from the manual mode to auto timer(one frame per second), press M on keyboard. When the user is
at manual mode, press A to advance one frame at a time.
My window size is 1050*800. Toolbar is at the top. When clicking the clear button, all the frames 
currently on the grid will be cleared. 
The status bar is at the bottom. The status message shows which frame has been added and the position
on the grid user chose to place that frame. The frame count is the time(s) when the frame was
placed. The frame count increases every second(Independent).
Timer has a update rate of 1000 milliseconds.
When the toolbar buttons is clicked, main takes all the mouse information and notice Model. Model
then update the board and update the live cells. Model then pass those information back to main
and main will show the new version of the board to users. Statusview is also called to update the 
status bar.