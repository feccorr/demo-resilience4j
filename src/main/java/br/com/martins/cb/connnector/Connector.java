package br.com.martins.cb.connnector;

public interface Connector {
    String failure();

    String success();

    String ignoreException();

    String failureWithFallback();
}
