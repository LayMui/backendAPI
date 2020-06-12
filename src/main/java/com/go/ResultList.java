package com.go;

public class ResultList {
    private String httpRequest1;
    private String httpRequest2;
    private String cmpResult;

    public ResultList(String httpRequest1, String httpRequest2, String cmpResult) {
        this.httpRequest1 = httpRequest1;
        this.httpRequest2 = httpRequest2;
        this.cmpResult = cmpResult;
    }

    @Override
    public String toString() {
        return "ResultList{" +
                "httpRequest1='" + httpRequest1 + '\'' +
                ", httpRequest2='" + httpRequest2 + '\'' +
                ", cmpResult='" + cmpResult + '\'' +
                '}';
    }


}
