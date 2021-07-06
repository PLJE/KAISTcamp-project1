package com.example.p1;

public interface ItemTouchHelperListener {
    boolean onItemMove(int from_position , int to_position);
    void onItemSwipe(int position);
}
