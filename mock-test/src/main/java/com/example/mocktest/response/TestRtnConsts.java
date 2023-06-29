package com.example.mocktest.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TestRtnConsts {
    SCC200(TestRtnGrp.Success, 200, "응답성공"),
    ERR400(TestRtnGrp.Validation, 400, "하늘님 memberId가 달라요"),
    ERR401(TestRtnGrp.Validation, 401, "재영님 BookId가 달라요");
    private TestRtnGrp grp;
    private int code;
    private String description;
}
