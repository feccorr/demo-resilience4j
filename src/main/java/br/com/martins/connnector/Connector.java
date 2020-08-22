package br.com.martins.connnector;

public interface Connector {
    String failure();

    String success();

    String ignoreException();

    String failureWithFallback();
}
