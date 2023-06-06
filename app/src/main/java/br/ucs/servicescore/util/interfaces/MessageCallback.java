package br.ucs.servicescore.util.interfaces;

/**
 * Interface com métodos listeners para diálogos
 */
public interface MessageCallback {
    void onConfirm(String userInput);

    void onCancel();
}