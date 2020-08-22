package br.com.martins.service;


public interface BusinessService {
    String failure();

    String success();

    String ignore();

    String failureWithFallback();
}
