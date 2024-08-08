package com.simple.basic.memo;

import com.simple.basic.command.MemoVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper // 이게 붙은 인터페이스를 마이바티스가 인식함 (빼면 동작 안됨)
public interface MemoMapper {

    public String hello();

    @Insert("INSERT INTO MEMO(MEMO,PHONE,PW,SECRET) VALUES(#{memo}, #{phone}, #{pw}, #{secret})")
    void insertMemo(MemoVO memo);

    @Select("SELECT * FROM MEMO")
    List<MemoVO> selectMemoList();

    @Delete("DELETE FROM MEMO WHERE MNO = #{mno}")
    void deleteMemo(Long mno);



}
