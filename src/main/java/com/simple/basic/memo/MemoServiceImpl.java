package com.simple.basic.memo;

import com.simple.basic.command.MemoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("memoService")//보통 멤버 변수 명으로 작성한다.
public class MemoServiceImpl implements MemoService {

    @Autowired
    MemoMapper memoMapper; // 매퍼 구현체를 넣어줌


    @Override
    public void memoWrite(MemoVO memo) {

        memoMapper.insertMemo(memo);

    }

    @Override
    public List<MemoVO> memoList() {
        return memoMapper.selectMemoList();
    }

    @Override
    public void memoDelete(Long mno) {
        memoMapper.deleteMemo(mno);
    }


}