package com.screenmatch.screenmatch.service;

public interface IDataConvert {

     <T> T getData(String json,Class<T> Tclass);
}
