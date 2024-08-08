package com.simple.basic.memo;

import com.simple.basic.command.MemoVO;

import java.util.List;

public interface MemoService {

    void memoWrite(MemoVO memo);
    List<MemoVO> memoList();
    void memoDelete(Long mno);

}
