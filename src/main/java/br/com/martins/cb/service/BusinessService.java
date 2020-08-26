package br.com.martins.cb.service;


public interface BusinessService {
    String failure();

    String success();

    String ignore();

    String failureWithFallback();
}
