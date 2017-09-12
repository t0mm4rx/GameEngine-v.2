package fr.tommarx.gameengine.Util;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class WaitAndDo {

    private ArrayList<WaitAndDoAction> actions;

    public WaitAndDo () {
        actions = new ArrayList<WaitAndDoAction>();
    }

    public void WaitAndDo(float time, Callable callable) {
        actions.add(new WaitAndDoAction(time, callable));
    }

    public void update() {
        ArrayList<WaitAndDoAction> actionsToDelete = new ArrayList<WaitAndDoAction>();
        for (int i = 0; i < actions.size(); i++) {
            WaitAndDoAction action = actions.get(i);
            if (System.currentTimeMillis() - action.timeB >= action.time) {
                try {
                    action.callable.call();
                    actionsToDelete.add(action);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        actions.removeAll(actionsToDelete);
    }

}
