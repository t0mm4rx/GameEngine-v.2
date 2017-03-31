package fr.tommarx.gameengine.Net;

public interface HTTPListener {

    void onFinish(String data);
    void onProgress(float percentage);
    void onFail(String error);

}
